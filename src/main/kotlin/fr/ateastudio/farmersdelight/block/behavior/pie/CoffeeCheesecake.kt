package fr.ateastudio.farmersdelight.block.behavior.pie

import fr.ateastudio.farmersdelight.block.behavior.PieBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack

object CoffeeCheesecake : PieBlock() {
    override fun getPieSliceItem(): ItemStack {
        return try {
            Items.COFFEE_CHEESECAKE_SLICE.createItemStack()
        } catch (e : Exception) {
            ItemStack.empty()
        }
    }
}
