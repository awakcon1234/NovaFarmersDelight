package fr.ateastudio.farmersdelight.block.behavior.feastblock

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import net.kyori.adventure.text.Component
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
import xyz.xenondevs.nova.util.BlockUtils.breakBlockNaturally
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.util.addToInventoryOrDrop
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState

/**
 * Corn Delight's popcorn bucket: four helpings of caramel popcorn taken by hand only, and the
 * paper bucket comes back once it is empty instead of a bowl.
 */
object PopcornBoxBlock : FeastBlock(true) {
    
    override fun getServingItem(state: NovaBlockState): ItemStack {
        return try {
            Items.CARAMEL_POPCORN.createItemStack()
        } catch (e: Exception) {
            ItemStack.empty()
        }
    }
    
    override fun handleInteract(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockInteract>): Boolean {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER] ?: return false
        val hand = ctx[DefaultContextParamTypes.INTERACTION_HAND] ?: return false
        val servings = state[BlockStateProperties.SERVINGS] ?: 0
        
        if (servings == 0) {
            pos.world.playSound(pos.location, Sound.BLOCK_WOOL_BREAK, SoundCategory.PLAYERS, 0.8f, 0.8f)
            val breakCtx = Context.intention(DefaultContextIntentions.BlockBreak)
                .param(DefaultContextParamTypes.BLOCK_POS, pos)
                .param(DefaultContextParamTypes.BLOCK_BREAK_EFFECTS, true)
                .param(DefaultContextParamTypes.SOURCE_PLAYER, player)
                .param(DefaultContextParamTypes.BLOCK_DROPS, true)
                .build()
            breakBlockNaturally(breakCtx)
            return true
        }
        
        val heldStack = player.inventory.getItem(hand)
        if (!heldStack.isEmpty) {
            player.sendMessage(Component.translatable("farmersdelight.block.popcorn.bearhand"))
            return true
        }
        
        updateBlockState(pos, state.with(BlockStateProperties.SERVINGS, servings - 1))
        player.addToInventoryOrDrop(getServingItem(state))
        
        if (servings - 1 == 0) {
            // Writing air over the backing state would leave Nova's own record of the block behind;
            // breaking it through Nova with drops off removes both halves of that record.
            player.addToInventoryOrDrop(Material.PAPER.toItemStack())
            val removeCtx = Context.intention(DefaultContextIntentions.BlockBreak)
                .param(DefaultContextParamTypes.BLOCK_POS, pos)
                .param(DefaultContextParamTypes.BLOCK_BREAK_EFFECTS, false)
                .param(DefaultContextParamTypes.BLOCK_DROPS, false)
                .build()
            breakBlock(removeCtx)
        }
        
        pos.world.playSound(pos.location, Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.BLOCKS, 1.0f, 1.0f)
        return true
    }
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>): List<ItemStack> {
        if (!ctx[DefaultContextParamTypes.BLOCK_DROPS] || ctx[DefaultContextParamTypes.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE) {
            return emptyList()
        }
        
        val servings = state[BlockStateProperties.SERVINGS] ?: 0
        if (servings != maxServings) {
            return listOf(Material.PAPER.toItemStack())
        }
        
        return state.block.item?.let { listOf(it.createItemStack()) } ?: emptyList()
    }
    
}
