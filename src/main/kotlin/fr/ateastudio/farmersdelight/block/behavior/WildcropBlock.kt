package fr.ateastudio.farmersdelight.block.behavior

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import kotlin.random.Random


abstract class WildcropBlock : BlockBehavior {
    open val canDropSeed = true
    open val dropProbability = 0.57
    abstract fun seedItem() : ItemStack?
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>): List<ItemStack> {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER]
        val tool = ctx[DefaultContextParamTypes.TOOL_ITEM_STACK]
        val seedItem = seedItem()
        if (!canDropSeed || player?.gameMode == GameMode.CREATIVE) {
            return emptyList()
        }
        
        val result = mutableListOf<ItemStack>()
        if (seedItem is ItemStack) {
            // Determine the amount of drops, with possible modification from the Fortune enchantment
            val fortuneLevel = tool?.getEnchantmentLevel(Enchantment.FORTUNE) ?: 0
            val quantity = simulateBinomialDrops(fortuneLevel)
            result.add(seedItem.asQuantity(quantity))
        }
        
        return result
    }
    
    override fun handleNeighborChanged(pos: BlockPos, state: NovaBlockState) {
        if (!canSurvive(state, pos)) {
            breakBlock(pos)
        }
    }
    
    override suspend fun canPlace(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockPlace>): Boolean {
        return mayPlaceOn(pos.below, state) && pos.block.isEmpty
    }
    
    protected open fun canSurvive(state: NovaBlockState, pos: BlockPos): Boolean {
        val blockBelow = pos.below
        return mayPlaceOn(blockBelow, state)
    }
    
    protected open fun mayPlaceOn(pos: BlockPos, state: NovaBlockState): Boolean {
        return  pos.block.type == Material.ROOTED_DIRT ||
                pos.block.type == Material.COARSE_DIRT ||
                pos.block.type == Material.GRASS_BLOCK ||
                pos.block.type == Material.DIRT
    }
    
    protected fun simulateBinomialDrops(fortuneLevel: Int): Int {
        val trials = 3 + fortuneLevel // n trials: 3 base trials + 1 for each level of Fortune
        var seedDrops = 0
        
        // Perform binomial trials
        for (i in 0 until trials) {
            if (Random.nextDouble(1.0) < dropProbability) {
                seedDrops++
            }
        }
        
        return seedDrops.coerceAtLeast(1) // Ensure at least 1 seed drops
    }
    
    protected fun breakBlock(position: BlockPos){
        val context = Context.intention(DefaultContextIntentions.BlockBreak)
            .param(DefaultContextParamTypes.BLOCK_POS, position)
            .param(DefaultContextParamTypes.BLOCK_BREAK_EFFECTS, true)
            .build()
        
        BlockUtils.breakBlockNaturally(context)
    }
}
