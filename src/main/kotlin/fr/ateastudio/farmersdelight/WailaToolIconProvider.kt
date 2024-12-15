package fr.ateastudio.farmersdelight

import fr.ateastudio.farmersdelight.registry.ToolCategories.KNIFE
import fr.ateastudio.farmersdelight.registry.ToolTiers.FLINT
import net.kyori.adventure.key.Key
import xyz.xenondevs.nova.ui.waila.info.WailaToolIconProvider
import xyz.xenondevs.nova.world.item.tool.ToolCategory
import xyz.xenondevs.nova.world.item.tool.ToolTier

object WailaToolIconProvider : WailaToolIconProvider {
    
    override fun getIcon(category: ToolCategory, tier: ToolTier?): Key? {
        val name = when (category) {
            KNIFE -> when (tier) {
                FLINT -> "${tier.id.value()}_${category.id.value()}"
                else -> null
            }
            else -> null
        } ?: return null
        return Key.key("farmersdelight","item/$name")
    }
}