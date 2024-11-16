package fr.ateastudio.farmersdelight.recipe.cuttingboard

import org.bukkit.inventory.ItemStack
import kotlin.random.Random

const val CUTTING_BOARD_FORTUNE_BONUS = 0.5

class ChanceResult
(private val stack: ItemStack, private val chance: Float) {
    
    fun getStack(): ItemStack {
        return stack
    }
    
    fun rollOutput(rand: Random, fortuneLevel: Int): ItemStack {
        var outputAmount: Int = stack.amount
        val fortuneBonus: Double = CUTTING_BOARD_FORTUNE_BONUS * fortuneLevel
        for (roll in 0 until stack.amount) if (rand.nextFloat() > chance + fortuneBonus) outputAmount--
        if (outputAmount == 0) return ItemStack.empty()
        val out: ItemStack = stack.clone()
        out.amount = outputAmount
        return out
    }
}