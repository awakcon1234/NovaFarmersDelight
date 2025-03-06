package fr.ateastudio.farmersdelight.block.behavior.pie

import fr.ateastudio.farmersdelight.block.behavior.PieBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack

object ApplePie : PieBlock() {
    override fun getPieSliceItem(): ItemStack {
        return try {
            Items.APPLE_PIE_SLICE.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}