package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.util.spawnItemEntity
import io.papermc.paper.datacomponent.DataComponentTypes
import io.papermc.paper.datacomponent.item.consumable.ConsumeEffect
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Registry
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffectType
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockInteract
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.util.BlockUtils.breakBlockNaturally
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.tool.ToolCategory
import kotlin.random.Random


@Suppress("UnstableApiUsage")
abstract class PieBlock : BlockBehavior {
    
    abstract fun getPieSliceItem() : ItemStack
    
    open fun getMaxBites() : Int {
        return 4
    }
    
    override fun handlePlace(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockPlace>) {
        updateBlockState(pos, state.with(BlockStateProperties.BITES, 0))
    }
    
    override fun handleInteract(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockInteract>): Boolean {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER]
        if (player != null) {
            val heldStack = ctx[DefaultContextParamTypes.INTERACTION_ITEM_STACK] ?: ItemStack.empty()
            if (ToolCategory.ofItem(heldStack).any { it.id.value() == "knives" || it.id.value() == "knife" }) {
                return cutSlice(pos, state, player)
            }
            
            if (consumeBite(pos, state, player)) {
                return true
            }
        }
        return false
    }
    
    private fun consumeBite(pos: BlockPos, state: NovaBlockState, player: Player): Boolean {
        if (player.foodLevel >= 20 && player.gameMode != GameMode.CREATIVE) {
            return false
        }
        
        val sliceStack = getPieSliceItem()
        val food = sliceStack.getData(DataComponentTypes.FOOD) ?: return false
        val consumable = sliceStack.getData(DataComponentTypes.CONSUMABLE) ?: return false
        val consumeEffects = consumable.consumeEffects()
        
        val consumeEvent = PlayerItemConsumeEvent(player, sliceStack, EquipmentSlot.HAND)
        Bukkit.getPluginManager().callEvent(consumeEvent)
        if (consumeEvent.isCancelled) return false // Cancelled by a plugin
        
        // Simulate eating
        player.foodLevel = (player.foodLevel + food.nutrition()).coerceAtMost(20) // Adjust hunger level
        player.saturation = (player.saturation + food.saturation()) // Adjust saturation
        
        // Apply effects to player
        for (consumeEffect in consumeEffects) {
            if (consumeEffect is ConsumeEffect.ApplyStatusEffects) {
                val probability = consumeEffect.probability()
                val effects = consumeEffect.effects()
                if (effects.any() && probability >= Random.nextFloat()) {
                    for (effect in effects) {
                        player.addPotionEffect(effect)
                    }
                }
            }
            else if (consumeEffect is ConsumeEffect.ClearAllStatusEffects) {
                player.clearActivePotionEffects()
            }
            else if (consumeEffect is ConsumeEffect.PlaySound) {
                val soundRegistry : Registry<Sound> = RegistryAccess.registryAccess().getRegistry(RegistryKey.SOUND_EVENT)
                val sound = soundRegistry.get(consumeEffect.sound())
                if (sound is Sound)
                    player.playSound(player, sound, SoundCategory.PLAYERS, 1.0f, 1.0f, 1)
            }
            else if (consumeEffect is ConsumeEffect.RemoveStatusEffects) {
                val potionRegistry : Registry<PotionEffectType> = RegistryAccess.registryAccess().getRegistry(RegistryKey.MOB_EFFECT)
                for (key in consumeEffect.removeEffects()) {
                    val effect = potionRegistry.get(key.key())
                    if (effect is PotionEffectType)
                        player.removePotionEffect(effect)
                }
            }
            else if (consumeEffect is ConsumeEffect.TeleportRandomly) {
                val diameter = consumeEffect.diameter()
                randomTeleport(player, diameter.toDouble())
            }
            
            
        }
        
        // Handle cake bites
        val bites = state[BlockStateProperties.BITES] ?: return false
        if (bites < getMaxBites() - 1) {
            updateBlockState(pos, state.with(BlockStateProperties.BITES, bites + 1))
        }
        else {
            pos.world.playSound(pos.location, Sound.BLOCK_WOOL_BREAK, SoundCategory.PLAYERS, 0.8f, 0.8f)
            val ctx = Context.intention(DefaultContextIntentions.BlockBreak)
                .param(DefaultContextParamTypes.BLOCK_POS, pos)
                .param(DefaultContextParamTypes.BLOCK_BREAK_EFFECTS, true)
                .param(DefaultContextParamTypes.SOURCE_PLAYER, player)
                .param(DefaultContextParamTypes.BLOCK_DROPS, true)
                .build()
            breakBlockNaturally(ctx)
        }
        
        return true
    }
    
    private fun cutSlice(pos: BlockPos, state: NovaBlockState, player: Player): Boolean {
        val bites = state[BlockStateProperties.BITES] ?: return false
        if (bites < getMaxBites() - 1) {
            updateBlockState(pos, state.with(BlockStateProperties.BITES, bites + 1))
        }
        else {
            pos.world.playSound(pos.location, Sound.BLOCK_WOOL_BREAK, SoundCategory.PLAYERS, 0.8f, 0.8f)
            val ctx = Context.intention(DefaultContextIntentions.BlockBreak)
                .param(DefaultContextParamTypes.BLOCK_POS, pos)
                .param(DefaultContextParamTypes.BLOCK_BREAK_EFFECTS, true)
                .param(DefaultContextParamTypes.SOURCE_PLAYER, player)
                .param(DefaultContextParamTypes.BLOCK_DROPS, true)
                .build()
            breakBlockNaturally(ctx)
            return true
        }
        
        // Calculate item spawn position and motion
        val direction = player.facing.oppositeFace
        val spawnLocation = pos.block.location.add(0.5, 0.3, 0.5)
        val xMotion = direction.direction.x * 0.15
        val yMotion = 0.05
        val zMotion = direction.direction.z * 0.15
        getPieSliceItem().spawnItemEntity(spawnLocation, xMotion, yMotion, zMotion)
        pos.world.playSound(pos.block.location, Sound.BLOCK_WOOL_BREAK, 0.8f, 0.8f)
        return true
    }
    
    private fun randomTeleport(player: Player, diameter: Double) {
        val world = player.world
        val center = player.location
        
        // Calculate random offset within the diameter
        val radius = diameter / 2
        val randomX = center.x + Random.nextDouble(-radius, radius)
        val randomZ = center.z + Random.nextDouble(-radius, radius)
        
        // Get the highest Y position at that X and Z to ensure safe landing
        val newY = world.getHighestBlockYAt(randomX.toInt(), randomZ.toInt()).toDouble()
        
        // Teleport the player
        val newLocation = Location(world, randomX, newY + 1, randomZ) // Add 1 to prevent suffocation
        player.teleport(newLocation)
    }
}