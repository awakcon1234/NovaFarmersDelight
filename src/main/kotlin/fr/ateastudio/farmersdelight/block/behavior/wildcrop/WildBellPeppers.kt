package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState

/**
 * Upstream's wild bell pepper drops a red pepper alongside its seeds.
 */
class WildBellPeppers : WildcropBlock() {
    
    override fun seedItem(): ItemStack? {
        return try {
            Items.BELL_PEPPER_SEEDS.createItemStack()
        } catch (e: Exception) {
            null
        }
    }
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>): List<ItemStack> {
        val drops = super.getDrops(pos, state, ctx)
        if (drops.isEmpty()) {
            return drops
        }
        
        return drops + Items.BELL_PEPPER_RED.createItemStack()
    }
    
}
