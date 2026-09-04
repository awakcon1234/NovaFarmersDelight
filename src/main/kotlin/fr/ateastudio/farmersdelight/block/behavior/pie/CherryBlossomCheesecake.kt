package fr.ateastudio.farmersdelight.block.behavior.pie

import fr.ateastudio.farmersdelight.block.behavior.PieBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack

object CherryBlossomCheesecake : PieBlock() {
    override fun getPieSliceItem(): ItemStack {
        return try {
            Items.CHERRY_BLOSSOM_CHEESECAKE_SLICE.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}
