package fr.ateastudio.farmersdelight.block.behavior.feastblock

import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import xyz.xenondevs.nova.world.item.NovaItem

object StuffedPumpkinBlock : FeastBlock(false) {
    
    override fun servingItem(): NovaItem? {
        return try {
            Items.STUFFED_PUMPKIN
        } catch (e : Exception) {
            null
        }
    }}