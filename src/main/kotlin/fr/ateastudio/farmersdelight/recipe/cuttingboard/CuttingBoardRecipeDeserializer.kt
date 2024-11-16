package fr.ateastudio.farmersdelight.recipe.cuttingboard

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.commons.gson.getFloatOrNull
import xyz.xenondevs.commons.gson.getIntOrNull
import xyz.xenondevs.commons.gson.getObject
import xyz.xenondevs.commons.gson.getString
import xyz.xenondevs.commons.gson.getStringOrNull
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer.Companion.getRecipeId
import xyz.xenondevs.nova.serialization.json.serializer.RecipeDeserializer.Companion.parseRecipeChoice
import xyz.xenondevs.nova.util.item.ItemUtils
import xyz.xenondevs.nova.util.item.ItemUtils.getItemStack
import java.io.File


object CuttingBoardRecipeDeserializer : RecipeDeserializer<CuttingBoardRecipe> {
    override fun deserialize(json: JsonObject, file: File): CuttingBoardRecipe {
        val ingredientsMap = HashMap<RecipeChoice, Int>()
        val ingredients = json.get("ingredients")
        if (ingredients is JsonObject) {
            ingredients.entrySet().forEach { (key, value) ->
                val choice = ItemUtils.getRecipeChoice(listOf(key))
                ingredientsMap[choice] = value.asInt
            }
        } else if (ingredients is JsonArray) {
            ingredients.forEach {
                it as JsonObject
                
                val items = it.get("item") ?: it.get("items")
                val choice = parseRecipeChoice(items)
                ingredientsMap[choice] = it.getIntOrNull("amount") ?: 0
            }
        }
        val inputItemIn = ingredientsMap.firstNotNullOf { (material, _) -> material }
        
        val resultsMap = HashSet<ChanceResult>()
        val results = json.get("result")
        if (results is JsonObject) {
            results.entrySet().forEach { (key, value) ->
                val item = getItemStack(key)
                val amount = value.asInt
                val chance = 1f
                resultsMap.add(ChanceResult(item.asQuantity(amount),chance))
            }
        } else if (results is JsonArray) {
            results.forEach {
                it as JsonObject
                val item = getItemStack(it.getStringOrNull("item") ?: it.getStringOrNull("items") ?: "")
                val amount = it.getIntOrNull("count") ?: it.getIntOrNull("amount") ?: 1
                val chance = it.getFloatOrNull("chance") ?: it.getFloatOrNull("chances") ?: 1f
                resultsMap.add(ChanceResult(item.asQuantity(amount),chance))
            }
        }
        
        val toolObject = json.getObject("tool")
        val toolIn = parseTool(toolObject)
        
        //val results = readResults(json.get("result").asJsonArray)
        val soundId = json.getStringOrNull("sound") ?: ""
        return CuttingBoardRecipe(getRecipeId(file), inputItemIn, toolIn, resultsMap.toList(), soundId)
    }
    
    
    private fun parseTool(toolObject: JsonObject?): ToolActionIngredient {
        return when {
            toolObject == null -> throw IllegalArgumentException("Tool object is missing")
            toolObject.has("tag") -> ToolActionIngredient.Tag(ToolTag.fromString(toolObject.getString("tag")))
            toolObject.has("type") && toolObject.has("action") -> ToolActionIngredient.Action(
                type = ToolType.fromString(toolObject.getString("type")),
                action = ToolAction.fromString(toolObject.getString("action"))
            )
            else -> throw IllegalArgumentException("Unrecognized tool format: $toolObject")
        }
    }

}