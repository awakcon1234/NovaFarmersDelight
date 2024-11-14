package fr.ateastudio.farmersdelight.recipe

import fr.ateastudio.farmersdelight.registry.RecipeTypes
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.RandomSource
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.commons.collections.removeFirstWhere
import xyz.xenondevs.nova.world.item.recipe.MultiInputChoiceRecipe
import xyz.xenondevs.nova.world.item.recipe.NovaRecipe
import xyz.xenondevs.nova.world.item.recipe.SingleInputChoiceRecipe
import xyz.xenondevs.nova.world.item.recipe.SingleResultRecipe


class CookingPotRecipe(
    override val id: ResourceLocation,
    override val inputs: List<RecipeChoice>,
    override val result: ItemStack,
    val container: ItemStack,
    val time: Int,
    val experience: Float
) : NovaRecipe, SingleResultRecipe, MultiInputChoiceRecipe {
    override val type = RecipeTypes.COOKING_POT
    
    fun matches(container: List<ItemStack>): Boolean {
        val choiceList = java.util.ArrayList(inputs)
        
        return container.filterNot { it.isEmpty }.all { matrixStack ->
            choiceList.removeFirstWhere { (it as RecipeChoice.ExactChoice).test(matrixStack) }
        } && choiceList.isEmpty()
    }
}
class CuttingBoardRecipe(
    override val id: ResourceLocation,
    val group: String,
    override val input: RecipeChoice,
    val tool: RecipeChoice,
    val results: List<ChanceResult>,
    val soundEvent: String
) : NovaRecipe, SingleInputChoiceRecipe {
    override val type = RecipeTypes.CUTTING_BOARD
    fun isSpecial(): Boolean {
        return true
    }
    
    fun getRollableResults(): List<ChanceResult> {
        return this.results
    }
    
    fun rollResults(rand: RandomSource?, fortuneLevel: Int): List<ItemStack> {
        val results: MutableList<ItemStack> = ArrayList()
        val rollableResults = getRollableResults()
        for (output in rollableResults) {
            val stack = output.rollOutput(rand!!, fortuneLevel)
            if (!stack.isEmpty) results.add(stack)
        }
        return results
    }
    
    fun matches(ingredient: ItemStack): Boolean {
        if (ingredient.isEmpty) return false
        return input.test(ingredient)
    }
}