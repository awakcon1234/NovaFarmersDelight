package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.inventory.ItemStack

class WildCoffee : WildcropBlock() {
    override fun seedItem(): ItemStack? {
        return try {
            Items.COFFEE_BEANS.createItemStack()
        } catch (e: Exception) {
            null
        }
    }
}
