package fr.ateastudio.farmersdelight.block.behavior.wildcrop

import fr.ateastudio.farmersdelight.block.behavior.WildcropBlock
import org.bukkit.inventory.ItemStack

class SandyShrub : WildcropBlock() {
    override val canDropSeed: Boolean
        get() = false
    override fun seedItem(): ItemStack? {
        return null
    }
}