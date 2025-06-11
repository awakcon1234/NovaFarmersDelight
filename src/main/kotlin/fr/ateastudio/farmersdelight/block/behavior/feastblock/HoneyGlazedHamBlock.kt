package fr.ateastudio.farmersdelight.block.behavior.feastblock

import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.block.state.NovaBlockState

object HoneyGlazedHamBlock : FeastBlock(true) {
    
    override fun getServingItem(state: NovaBlockState): ItemStack {
        return try {
            Items.HONEY_GLAZED_HAM.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}