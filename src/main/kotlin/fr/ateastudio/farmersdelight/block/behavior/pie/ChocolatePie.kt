package fr.ateastudio.farmersdelight.block.behavior.pie

import fr.ateastudio.farmersdelight.block.behavior.PieBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack

object ChocolatePie : PieBlock() {
    override fun getPieSliceItem(): ItemStack {
        return try {
            Items.CHOCOLATE_PIE_SLICE.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}