package fr.ateastudio.farmersdelight.block.behavior.crop

import fr.ateastudio.farmersdelight.block.behavior.CropBlock
import fr.ateastudio.farmersdelight.registry.Items
import xyz.xenondevs.nova.world.item.NovaItem

/**
 * Rustic Delight's coffee: five growth stages, and the bean is both what is planted and what
 * is harvested.
 */
object CoffeeCrop : CropBlock() {
    
    override fun resultItem(): NovaItem? {
        return try {
            Items.COFFEE_BEANS
        } catch (e: Exception) {
            null
        }
    }
    
    override fun seedItem(): NovaItem? {
        return try {
            Items.COFFEE_BEANS
        } catch (e: Exception) {
            null
        }
    }
    
}
