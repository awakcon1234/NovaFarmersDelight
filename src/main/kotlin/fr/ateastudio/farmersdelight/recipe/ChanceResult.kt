package fr.ateastudio.farmersdelight.recipe

import com.google.gson.JsonElement
import com.google.gson.JsonSyntaxException
import net.minecraft.util.GsonHelper
import net.minecraft.util.RandomSource
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.util.item.ItemUtils.getItemStack

const val CUTTING_BOARD_FORTUNE_BONUS = 0.5

class ChanceResult
(private val stack: ItemStack, private val chance: Float) {
    
    fun getStack(): ItemStack {
        return stack
    }
    
    fun rollOutput(rand: RandomSource, fortuneLevel: Int): ItemStack {
        var outputAmount: Int = stack.amount
        val fortuneBonus: Double = CUTTING_BOARD_FORTUNE_BONUS * fortuneLevel
        for (roll in 0 until stack.amount) if (rand.nextFloat() > chance + fortuneBonus) outputAmount--
        if (outputAmount == 0) return ItemStack.empty()
        val out: ItemStack = stack.clone()
        out.amount = outputAmount
        return out
    }
    
    companion object {
        
        fun deserialize(je: JsonElement): ChanceResult {
            if (!je.isJsonObject) throw JsonSyntaxException("Must be a json object")
            
            val json = je.asJsonObject
            val itemId = GsonHelper.getAsString(json, "item")
            val count = GsonHelper.getAsInt(json, "count", 1)
            val chance = GsonHelper.getAsFloat(json, "chance", 1f)
            val itemStack: ItemStack = getItemStack(itemId).asQuantity(count)
            
            return ChanceResult(itemStack, chance)
        }
    }
}