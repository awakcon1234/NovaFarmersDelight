package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockInteract
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.util.BlockUtils.breakBlock
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.util.addToInventoryOrDrop
import xyz.xenondevs.nova.util.item.novaItem
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem

/**
 * Rustic Delight's plate of pancakes. It is placed holding six; a click takes one, and sneaking
 * with a pancake of the same kind puts one back, up to twelve. The servings value keeps upstream's
 * encoding so the models line up: below six it counts pancakes eaten off the plate, from six up it
 * counts a stack taller than the plate.
 */
open class PancakeBlock(private val pancake: () -> NovaItem) : BlockBehavior {
    
    companion object {
        const val PLATE = 6
        const val MAX = 12
        
        fun present(servings: Int): Int = if (servings < PLATE) PLATE - servings else servings + 1
        fun servingsFor(present: Int): Int = if (present <= PLATE) PLATE - present else present - 1
    }
    
    override fun handleInteract(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockInteract>): Boolean {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER] ?: return false
        val hand = ctx[DefaultContextParamTypes.INTERACTION_HAND] ?: return false
        val held = player.inventory.getItem(hand)
        val servings = state[BlockStateProperties.SERVINGS] ?: 0
        val count = present(servings)
        
        if (player.isSneaking && held.novaItem == pancake()) {
            if (count >= MAX) {
                return true
            }
            
            updateBlockState(pos, state.with(BlockStateProperties.SERVINGS, servingsFor(count + 1)))
            if (player.gameMode != GameMode.CREATIVE) {
                held.amount -= 1
            }
            pos.world.playSound(pos.location, Sound.BLOCK_WOOL_PLACE, SoundCategory.PLAYERS, 0.8f, 0.8f)
            return true
        }
        
        player.addToInventoryOrDrop(pancake().createItemStack())
        pos.world.playSound(pos.location, Sound.BLOCK_WOOL_BREAK, SoundCategory.PLAYERS, 0.8f, 0.8f)
        
        if (count > 1) {
            updateBlockState(pos, state.with(BlockStateProperties.SERVINGS, servingsFor(count - 1)))
        } else {
            // Upstream destroys the emptied plate without loot: the bowl only comes back if the
            // block is broken rather than eaten bare.
            val removeCtx = Context.intention(DefaultContextIntentions.BlockBreak)
                .param(DefaultContextParamTypes.BLOCK_POS, pos)
                .param(DefaultContextParamTypes.BLOCK_BREAK_EFFECTS, false)
                .param(DefaultContextParamTypes.BLOCK_DROPS, false)
                .build()
            breakBlock(removeCtx)
        }
        
        return true
    }
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>): List<ItemStack> {
        if (!ctx[DefaultContextParamTypes.BLOCK_DROPS] || ctx[DefaultContextParamTypes.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE) {
            return emptyList()
        }
        
        val servings = state[BlockStateProperties.SERVINGS] ?: 0
        if (servings == 0) {
            return state.block.item?.let { listOf(it.createItemStack()) } ?: emptyList()
        }
        
        return listOf(pancake().createItemStack(present(servings)), Material.BOWL.toItemStack())
    }
    
}
