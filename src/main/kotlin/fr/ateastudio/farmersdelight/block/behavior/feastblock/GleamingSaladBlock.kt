package fr.ateastudio.farmersdelight.block.behavior.feastblock

import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.world.block.state.NovaBlockState

object GleamingSaladBlock : FeastBlock(true) {
    
    override fun getServingItem(state: NovaBlockState): ItemStack {
        return try {
            Items.GLEAMING_SALAD.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}
