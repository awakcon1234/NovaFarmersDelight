package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.util.getCraftingRemainingItem
import fr.ateastudio.farmersdelight.util.hasCraftingRemainingItem
import net.kyori.adventure.text.Component
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockInteract
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.integration.customitems.CustomItemServiceManager.removeBlock
import xyz.xenondevs.nova.util.BlockUtils.breakBlockNaturally
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.util.addToInventoryOrDrop
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem

abstract class FeastBlock(private val hasLeftovers: Boolean) : BlockBehavior {
    private val maxServings : Int
        get() = 4
    
    abstract fun servingItem() : NovaItem?
    
    private fun getServingItem(state: NovaBlockState): ItemStack {
        return servingItem()?.createItemStack() ?: ItemStack.empty()
    }
    
    override fun handlePlace(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockPlace>) {
        updateBlockState(pos, state.with(BlockStateProperties.SERVINGS, maxServings))
    }
    
    override fun handleInteract(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockInteract>): Boolean {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER] ?: return false
        val hand = ctx[DefaultContextParamTypes.INTERACTION_HAND] ?: return false
        return takeServing(pos, state, player, hand)
    }
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>): List<ItemStack> {
        NovaFarmersDelight.logger.info("getDrops")
        if (ctx[DefaultContextParamTypes.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE) {
            return emptyList()
        }
        
        val servings = state[BlockStateProperties.SERVINGS] ?: 0
        if (servings != maxServings && hasLeftovers) {
            return listOf(Material.BOWL.toItemStack())
        } else {
            return state.block.item
                ?.let { listOf(it.createItemStack()) }
                ?: return emptyList()
        }
    }
    
    private fun takeServing(pos: BlockPos, state: NovaBlockState, player: Player, hand: EquipmentSlot): Boolean {
        val servings = state[BlockStateProperties.SERVINGS] ?: 0
        if (servings == 0) {
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
        
        val serving = getServingItem(state)
        val heldStack = player.inventory.getItem(hand)
        
        if (servings > 0) {
            if (!serving.hasCraftingRemainingItem() || heldStack.isSimilar(serving.getCraftingRemainingItem())) {
                updateBlockState(pos, state.with(BlockStateProperties.SERVINGS, servings - 1))
                    if (player.gameMode != GameMode.CREATIVE && serving.hasCraftingRemainingItem()) {
                        player.inventory.setItem(hand, heldStack.asQuantity(heldStack.amount - 1))
                }
                player.addToInventoryOrDrop(serving)
                val posStateServings = pos.novaBlockState?.get(BlockStateProperties.SERVINGS) ?: 0
                if (posStateServings == 0 && !hasLeftovers) {
                    removeBlock(pos.block, false)
                }
                pos.world.playSound(pos.location, Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.BLOCKS, 1.0f, 1.0f)
                return true
            } else {
                player.sendMessage(Component.translatable("farmersdelight.block.feast.use_container", serving.getCraftingRemainingItem().effectiveName()))
            }
        }
        return false
    }
    
}