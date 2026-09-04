package fr.ateastudio.farmersdelight.block.behavior

import org.bukkit.GameMode
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem
import kotlin.random.Random

/**
 * Rustic Delight's giant bell pepper: a melon-like block that breaks into one to nine slices of
 * its colour rather than dropping itself, so it is crafted from slices and eaten back into them.
 */
class GiantBellPepperBlock(private val slice: () -> NovaItem) : BlockBehavior {
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>): List<ItemStack> {
        if (!ctx[DefaultContextParamTypes.BLOCK_DROPS] || ctx[DefaultContextParamTypes.SOURCE_PLAYER]?.gameMode == GameMode.CREATIVE) {
            return emptyList()
        }
        
        return listOf(slice().createItemStack(Random.nextInt(1, 10)))
    }
    
}
