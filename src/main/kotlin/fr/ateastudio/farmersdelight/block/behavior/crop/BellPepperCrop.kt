package fr.ateastudio.farmersdelight.block.behavior.crop

import fr.ateastudio.farmersdelight.block.behavior.CropBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.GameMode
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem
import kotlin.random.Random

/**
 * Rustic Delight grows bell peppers as three crops (common, pale, dark), each rolling its own
 * three colours. Here they are one crop that rolls all nine, weighted so the common colours stay
 * common and the pale and dark ones stay the find they are upstream. A ripe harvest is seeds plus
 * one pepper, with a second pepper half the time, which is what upstream's extra pools amount to.
 */
object BellPepperCrop : CropBlock() {
    
    private val colourWeights: List<Pair<() -> NovaItem, Int>> = listOf(
        ({ Items.BELL_PEPPER_RED } as () -> NovaItem) to 6,
        ({ Items.BELL_PEPPER_GREEN } as () -> NovaItem) to 3,
        ({ Items.BELL_PEPPER_YELLOW } as () -> NovaItem) to 3,
        ({ Items.BELL_PEPPER_ORANGE } as () -> NovaItem) to 2,
        ({ Items.BELL_PEPPER_WHITE } as () -> NovaItem) to 2,
        ({ Items.BELL_PEPPER_PINK } as () -> NovaItem) to 2,
        ({ Items.BELL_PEPPER_BLUE } as () -> NovaItem) to 2,
        ({ Items.BELL_PEPPER_PURPLE } as () -> NovaItem) to 2,
        ({ Items.BELL_PEPPER_BLACK } as () -> NovaItem) to 2
    )
    
    override fun resultItem(): NovaItem? {
        return try {
            Items.BELL_PEPPER_RED
        } catch (e: Exception) {
            null
        }
    }
    
    override fun seedItem(): NovaItem? {
        return try {
            Items.BELL_PEPPER_SEEDS
        } catch (e: Exception) {
            null
        }
    }
    
    override fun getDrops(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>): List<ItemStack> {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER]
        if (!ctx[DefaultContextParamTypes.BLOCK_DROPS] || player?.gameMode == GameMode.CREATIVE) {
            return emptyList()
        }
        
        val seed = seedItem() ?: return emptyList()
        if (!isMaxAge(state)) {
            return listOf(seed.createItemStack())
        }
        
        val fortune = ctx[DefaultContextParamTypes.TOOL_ITEM_STACK]?.getEnchantmentLevel(Enchantment.FORTUNE) ?: 0
        val drops = mutableListOf(seed.createItemStack(simulateBinomialDrops(fortune)))
        drops += rollColour().createItemStack()
        if (Random.nextBoolean()) {
            drops += rollColour().createItemStack()
        }
        
        return drops
    }
    
    private fun rollColour(): NovaItem {
        var roll = Random.nextInt(colourWeights.sumOf { it.second })
        for ((item, weight) in colourWeights) {
            roll -= weight
            if (roll < 0) {
                return item()
            }
        }
        
        return colourWeights.first().first()
    }
    
}
