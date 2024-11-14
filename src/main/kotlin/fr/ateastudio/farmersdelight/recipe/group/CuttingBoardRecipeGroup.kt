package fr.ateastudio.farmersdelight.recipe.group

import fr.ateastudio.farmersdelight.recipe.CuttingBoardRecipe
import fr.ateastudio.farmersdelight.registry.GuiTextures
import fr.ateastudio.farmersdelight.registry.Items
import xyz.xenondevs.commons.collections.mapToArray
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.ui.menu.explorer.recipes.createRecipeChoiceItem
import xyz.xenondevs.nova.ui.menu.explorer.recipes.group.RecipeGroup
import java.util.*

object CuttingBoardRecipeGroup : RecipeGroup<CuttingBoardRecipe>() {
    override val priority = 4
    override val icon = Items.CUTTING_BOARD.model.clientsideProvider
    override val texture = GuiTextures.COOKING_POT_RECIPE
    
    override fun createGui(recipe: CuttingBoardRecipe): Gui {
        val resultInv = VirtualInventory(UUID.nameUUIDFromBytes("inventory.result".toByteArray()), 4, recipe.results.mapToArray { it.getStack() }, IntArray(4) {1})
            
        return Gui.normal()
            .setStructure(
                ". . . . . . . . .",
                ". i . t . r r r r",
                ". . . . . . . . ."
            )
            .addIngredient('i', createRecipeChoiceItem(recipe.input))
            .addIngredient('t', createRecipeChoiceItem(recipe.tool))
            .addIngredient('r', resultInv)
            .build()
    }
 
}