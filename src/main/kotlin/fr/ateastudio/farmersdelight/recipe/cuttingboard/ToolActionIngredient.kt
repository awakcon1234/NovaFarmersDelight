package fr.ateastudio.farmersdelight.recipe.cuttingboard

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.world.item.tool.ToolCategory
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories

sealed class ToolActionIngredient : RecipeChoice {
    
    /**
     * Represents a tool identified by its tag.
     */
    class Tag(private val tag: ToolTag) : ToolActionIngredient() {
        override fun test(itemStack: ItemStack): Boolean {
            return when(tag) {
                ToolTag.SHEARS -> ToolCategory.ofItem(itemStack).contains(VanillaToolCategories.SHEARS)
                ToolTag.KNIVES -> ToolCategory.ofItem(itemStack).any { it.id.path == "knives" || it.id.path == "knife" }
                else -> true
            }
        }
        
        override fun clone(): RecipeChoice {
            return Tag(tag)
        }
        
        @Deprecated("")
        override fun getItemStack(): ItemStack {
            return when(tag) {
                ToolTag.SHEARS -> Material.SHEARS.toItemStack()
                ToolTag.KNIVES -> Material.WOODEN_SWORD.toItemStack()
                else -> ItemStack.empty()
            }
        }
    }
    
    /**
     * Represents a tool identified by a specific action type.
     */
    class Action(private val type: ToolType, private val action: ToolAction) : ToolActionIngredient() {
        override fun test(itemStack: ItemStack): Boolean {
            return if (action.toolCategory != null && ToolCategory.ofItem(itemStack).contains(action.toolCategory)) {
                true
            } else {
                when (action.actionName.split("_").getOrNull(0)) {
                    "shield" -> itemStack.type == Material.SHIELD
                    "fishing" -> itemStack.type == Material.FISHING_ROD
                    else -> false
                }
            }
        }
        
        override fun clone(): RecipeChoice {
            return Action(type, action)
        }
        
        @Deprecated("")
        override fun getItemStack(): ItemStack {
            return when (action.actionName.split("_").getOrNull(0)) {
                "axe" -> Material.WOODEN_AXE.toItemStack()
                "pickaxe" -> Material.WOODEN_PICKAXE.toItemStack()
                "shovel" -> Material.WOODEN_SHOVEL.toItemStack()
                "hoe", "till" -> Material.WOODEN_HOE.toItemStack()
                "sword" -> Material.WOODEN_SWORD.toItemStack()
                "shears" -> Material.SHEARS.toItemStack()
                "shield" -> Material.SHIELD.toItemStack()
                "fishing" -> Material.FISHING_ROD.toItemStack()
                else -> ItemStack.empty()
            }
        }
    }
}