package fr.ateastudio.farmersdelight.block.behavior.feastblock

import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.block.state.NovaBlockState

object StuffedPumpkinBlock : FeastBlock(false) {
    
    override fun getServingItem(state: NovaBlockState): ItemStack {
        return try {
            Items.STUFFED_PUMPKIN.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}