package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.util.item.toItemStack

class WildBeetroots : WildcropBlock() {
    override fun seedItem(): ItemStack? {
        return try {
            Material.BEETROOT_SEEDS.toItemStack()
        } catch (e : Exception) {
            null
        }
    }
}