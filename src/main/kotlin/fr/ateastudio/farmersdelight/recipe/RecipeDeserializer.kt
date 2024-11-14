package fr.ateastudio.farmersdelight.recipe

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.minecraft.core.NonNullList
import org.bukkit.Material
import xyz.xenondevs.commons.gson.getFloatOrNull
import xyz.xenondevs.commons.gson.getIntOrNull
import xyz.xenondevs.commons.gson.getObject
import xyz.xenondevs.commons.gson.getStringOrNull
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer.Companion.getRecipeId
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer.Companion.parseRecipeChoice
import xyz.xenondevs.nova.util.item.ItemUtils.getItemStack
import xyz.xenondevs.nova.util.item.toItemStack
import java.io.File


object CookingPotRecipeDeserializer : RecipeDeserializer<CookingPotRecipe> {
    override fun deserialize(json: JsonObject, file: File): CookingPotRecipe {
        val inputChoice = json.get("inputs").asJsonArray.map { parseRecipeChoice(it) }
        val result = getItemStack(json.getStringOrNull("result")!!)
        result.amount = json.getIntOrNull("amount") ?: 1
        
        val jsonContainer = json.getStringOrNull("container")
        val container = if (jsonContainer != null) getItemStack(jsonContainer) else Material.AIR.toItemStack()
        
        val time = json.getIntOrNull("time") ?: json.getIntOrNull("cookingtime")!! // legacy support
        val experience = json.getFloatOrNull("experience") ?: json.getFloatOrNull("xp")!! // legacy support
        return CookingPotRecipe(getRecipeId(file), inputChoice, result, container, time, experience)
    }
}

object CuttingBoardRecipeDeserializer : RecipeDeserializer<CuttingBoardRecipe> {
    override fun deserialize(json: JsonObject, file: File): CuttingBoardRecipe {
        val groupIn = json.getStringOrNull("group") ?: ""
        val inputItemIn = json.get("ingredients").asJsonArray.map { parseRecipeChoice(it) }.first()
        val toolObject = json.getObject("tool")
        val toolIn = parseRecipeChoice(toolObject)
        
        val results = readResults(json.get("result").asJsonArray)
        val soundId = json.getStringOrNull("sound") ?: ""
        return CuttingBoardRecipe(getRecipeId(file), groupIn, inputItemIn, toolIn, results, soundId)
    }
    
    
    private fun readResults(resultArray: JsonArray): List<ChanceResult> {
        val results = NonNullList.create<ChanceResult>()
        for (result in resultArray) {
            results.add(ChanceResult.deserialize(result))
        }
        return results
    }
}