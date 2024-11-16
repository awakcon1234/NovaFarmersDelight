package fr.ateastudio.farmersdelight.recipe.cookingpot

import com.google.gson.JsonObject
import org.bukkit.Material
import xyz.xenondevs.commons.gson.getFloatOrNull
import xyz.xenondevs.commons.gson.getIntOrNull
import xyz.xenondevs.commons.gson.getStringOrNull
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer
import xyz.xenondevs.nova.util.item.ItemUtils
import xyz.xenondevs.nova.util.item.toItemStack
import java.io.File

object CookingPotRecipeDeserializer : RecipeDeserializer<CookingPotRecipe> {
    override fun deserialize(json: JsonObject, file: File): CookingPotRecipe {
        val inputChoice = json.get("inputs").asJsonArray.map { RecipeDeserializer.parseRecipeChoice(it) }
        val result = ItemUtils.getItemStack(json.getStringOrNull("result")!!)
        result.amount = json.getIntOrNull("amount") ?: 1
        
        val jsonContainer = json.getStringOrNull("container")
        val container = if (jsonContainer != null) ItemUtils.getItemStack(jsonContainer) else Material.AIR.toItemStack()
        
        val time = json.getIntOrNull("time") ?: json.getIntOrNull("cookingtime")!! // legacy support
        val experience = json.getFloatOrNull("experience") ?: json.getFloatOrNull("xp")!! // legacy support
        return CookingPotRecipe(RecipeDeserializer.getRecipeId(file), inputChoice, result, container, time, experience)
    }
}