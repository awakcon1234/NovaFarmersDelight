package fr.ateastudio.farmersdelight.block.behavior.feastblock

import fr.ateastudio.farmersdelight.block.behavior.FeastBlock
import fr.ateastudio.farmersdelight.registry.Items
import xyz.xenondevs.nova.world.item.NovaItem

object RoastChickenBlock : FeastBlock(true) {
    
    override fun servingItem(): NovaItem? {
        return try {
            Items.ROAST_CHICKEN
        } catch (e : Exception) {
            null
        }
    }}