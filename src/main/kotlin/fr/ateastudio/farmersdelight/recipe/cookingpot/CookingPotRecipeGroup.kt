package fr.ateastudio.farmersdelight.recipe.cookingpot

import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.registry.GuiItems
import fr.ateastudio.farmersdelight.registry.GuiTextures
import fr.ateastudio.farmersdelight.registry.Items
import fr.ateastudio.farmersdelight.util.getCraftingRemainingItem
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.nova.ui.menu.explorer.recipes.createRecipeChoiceItem
import xyz.xenondevs.nova.ui.menu.explorer.recipes.group.RecipeGroup
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.world.item.DefaultGuiItems

object CookingPotRecipeGroup : RecipeGroup<CookingPotRecipe>() {
    override val priority = 4
    override val icon = Items.COOKING_POT.clientsideProvider
    override val texture = GuiTextures.COOKING_POT_RECIPE
    
    private fun getSafeRecipeChoice(inputs: List<RecipeChoice>, index: Int) : Item {
        return if (inputs.size > index) {
            createRecipeChoiceItem(inputs[index])
        } else {
            Item.simple(Material.AIR.toItemStack())
        }
    }
    
    override fun createGui(recipe: CookingPotRecipe): Gui {
        return Gui.builder()
            .setStructure(
                ". i j k . . . p .",
                ". l m n . t . . .",
                ". . f . . b . p ."
            )
            .addIngredient('i', getSafeRecipeChoice(recipe.inputs,0))
            .addIngredient('j', getSafeRecipeChoice(recipe.inputs,1))
            .addIngredient('k', getSafeRecipeChoice(recipe.inputs,2))
            .addIngredient('l', getSafeRecipeChoice(recipe.inputs,3))
            .addIngredient('m', getSafeRecipeChoice(recipe.inputs,4))
            .addIngredient('n', getSafeRecipeChoice(recipe.inputs,5))
            .addIngredient('t', DefaultGuiItems.TP_STOPWATCH
                .createClientsideItemBuilder()
                .setName(Component.translatable("menu.nova.recipe.time", Component.text(recipe.time / Blocks.COOKING_POT.tickrate)))
            )
            .addIngredient('p', recipe.result)
            .addIngredient('f', GuiItems.COOKING_POT_HEATED.createItemStack())
            .addIngredient('b', if (recipe.result.getCraftingRemainingItem().isEmpty) GuiItems.BOWL_UNDERLAY.createItemStack() else recipe.result.getCraftingRemainingItem())
            .build()
    }
}