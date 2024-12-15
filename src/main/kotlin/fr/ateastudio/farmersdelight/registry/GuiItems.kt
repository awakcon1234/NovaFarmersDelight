package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import xyz.xenondevs.nova.addon.registry.ItemRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.world.item.NovaItem

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object GuiItems : ItemRegistry by NovaFarmersDelight.registry {
    val COOKING_POT_HEATED = guiItem("cooking_pot_heated")
    val BOWL_UNDERLAY = guiItem("bowl")
    val ARROW_PROGRESS = item("gui/opaque/progress/arrow") {
        localizedName("")
        hidden(true)
        
        modelDefinition {
            model = rangedModels(23) {
                createGuiModel(background = false, stretched = false, "item/gui/progress/arrow/$it")
            }
        }
    }
    
    private fun guiItem(
        name: String,
        localizedName: String? = null,
        stretched: Boolean = false,
        background: Boolean = false
    ): NovaItem = item("gui/opaque/$name") {
        if (localizedName == null) {
            name(null)
        } else localizedName(localizedName)
        hidden(true)
        modelDefinition {
            model = buildModel {
                createGuiModel(background, stretched, "item/gui/$name")
            }
        }
    }
    
}