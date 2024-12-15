package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import xyz.xenondevs.nova.addon.registry.GuiTextureRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.resources.builder.layout.gui.GuiTextureAlignment

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object GuiTextures : GuiTextureRegistry by NovaFarmersDelight.registry {
    val COOKING_POT = guiTexture("cooking_pot") {
        alignment(alignment = GuiTextureAlignment.ChestDefault)
        path("gui/cooking_pot")
    }
    val COOKING_POT_RECIPE = guiTexture("cooking_pot_recipe") {
        alignment(alignment = GuiTextureAlignment.ChestDefault)
        path("gui/recipe/cooking_pot")
    }
    val CUTTING_BOARD_RECIPE = guiTexture("cutting_board_recipe") {
        alignment(alignment = GuiTextureAlignment.ChestDefault)
        path("gui/recipe/cutting_board")
    }
}