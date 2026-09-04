package fr.ateastudio.farmersdelight.block.behavior.crop

import fr.ateastudio.farmersdelight.block.behavior.TallCropBlock
import fr.ateastudio.farmersdelight.registry.Items
import xyz.xenondevs.nova.world.item.NovaItem

object CornCrop : TallCropBlock() {
    
    // Corn Delight's CornCrop raises the sprouting age from the library default of three.
    override val growUpperAge: Int
        get() = 4
    
    override fun resultItem(): NovaItem? {
        return try {
            Items.CORN
        } catch (e: Exception) {
            null
        }
    }
    
    override fun seedItem(): NovaItem? {
        return try {
            Items.CORN_SEEDS
        } catch (e: Exception) {
            null
        }
    }
    
}
