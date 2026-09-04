package fr.ateastudio.farmersdelight.block.behavior.feastblock

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.item.NovaItem

/**
 * A platter of three stuffed peppers. Each serving hands over the pepper that is visibly still
 * on the plate: servings run 3 -> 1, and the third, second and first pepper go in that order.
 */
open class BellPepperMedleyBlock(private val peppers: List<() -> NovaItem>) : FeastBlock(true) {
    
    override val maxServings: Int
        get() = 3
    
    override fun getServingItem(state: NovaBlockState): ItemStack {
        val servings = state[BlockStateProperties.SERVINGS] ?: return ItemStack.empty()
        val index = (servings - 1).coerceIn(0, peppers.lastIndex)
        return try {
            peppers[index]().createItemStack()
        } catch (e: Exception) {
            ItemStack.empty()
        }
    }
    
}

object CommonBellPepperMedley : BellPepperMedleyBlock(listOf({ Items.STUFFED_BELL_PEPPER_GREEN }, { Items.STUFFED_BELL_PEPPER_YELLOW }, { Items.STUFFED_BELL_PEPPER_RED }))
object DarkBellPepperMedley : BellPepperMedleyBlock(listOf({ Items.STUFFED_BELL_PEPPER_BLUE }, { Items.STUFFED_BELL_PEPPER_PURPLE }, { Items.STUFFED_BELL_PEPPER_BLACK }))
object PaleBellPepperMedley : BellPepperMedleyBlock(listOf({ Items.STUFFED_BELL_PEPPER_ORANGE }, { Items.STUFFED_BELL_PEPPER_WHITE }, { Items.STUFFED_BELL_PEPPER_PINK }))
