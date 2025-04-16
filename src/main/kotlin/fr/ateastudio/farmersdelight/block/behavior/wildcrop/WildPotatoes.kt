package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.util.item.toItemStack

class WildPotatoes : WildcropBlock() {
    override fun seedItem(): ItemStack? {
        return try {
            Material.POTATO.toItemStack()
        } catch (e : Exception) {
            null
        }
    }
}