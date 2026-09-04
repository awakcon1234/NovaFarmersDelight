package fr.ateastudio.farmersdelight.block.behavior.crop

import fr.ateastudio.farmersdelight.block.behavior.CropBlock
import fr.ateastudio.farmersdelight.registry.Items
import xyz.xenondevs.nova.world.item.NovaItem

/**
 * Rustic Delight's cotton: four growth stages, planted from seeds and harvested as bolls.
 */
object CottonCrop : CropBlock() {
    
    override fun resultItem(): NovaItem? {
        return try {
            Items.COTTON_BOLL
        } catch (e: Exception) {
            null
        }
    }
    
    override fun seedItem(): NovaItem? {
        return try {
            Items.COTTON_SEEDS
        } catch (e: Exception) {
            null
        }
    }
    
}
