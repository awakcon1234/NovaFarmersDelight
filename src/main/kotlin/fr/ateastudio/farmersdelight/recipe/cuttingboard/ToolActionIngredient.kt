package fr.ateastudio.farmersdelight.recipe.cuttingboard

import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import xyz.xenondevs.nova.util.item.toItemStack
import xyz.xenondevs.nova.world.item.tool.ToolCategory
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories

sealed class ToolActionIngredient : RecipeChoice {
    abstract fun getChoices() : List<ItemStack>
    
    /**
     * Represents a tool identified by its tag.
     */
    class Tag(private val tag: ToolTag) : ToolActionIngredient() {
        override fun test(itemStack: ItemStack): Boolean {
            return when(tag) {
                ToolTag.SHEARS -> ToolCategory.ofItem(itemStack).contains(VanillaToolCategories.SHEARS)
                ToolTag.KNIVES -> ToolCategory.ofItem(itemStack).any { it.id.value() == "knives" || it.id.value() == "knife" }
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
        override fun getChoices(): List<ItemStack> {
            return when(tag) {
                ToolTag.SHEARS -> listOf(Material.SHEARS.toItemStack())
                ToolTag.KNIVES -> listOf(
                    Items.FLINT_KNIFE.createItemStack(),
                    Items.IRON_KNIFE.createItemStack(),
                    Items.GOLDEN_KNIFE.createItemStack(),
                    Items.DIAMOND_KNIFE.createItemStack(),
                    Items.NETHERITE_KNIFE.createItemStack(),
                )
                else -> listOf(ItemStack.empty())
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
        
        override fun getChoices(): List<ItemStack> {
            return when (action.actionName.split("_").getOrNull(0)) {
                "axe" -> listOf(
                    Material.WOODEN_AXE.toItemStack(),
                    Material.STONE_AXE.toItemStack(),
                    Material.IRON_AXE.toItemStack(),
                    Material.GOLDEN_AXE.toItemStack(),
                    Material.DIAMOND_AXE.toItemStack(),
                    Material.NETHERITE_AXE.toItemStack(),
                )
                "pickaxe" -> listOf(
                    Material.WOODEN_PICKAXE.toItemStack(),
                    Material.STONE_PICKAXE.toItemStack(),
                    Material.IRON_PICKAXE.toItemStack(),
                    Material.GOLDEN_PICKAXE.toItemStack(),
                    Material.DIAMOND_PICKAXE.toItemStack(),
                    Material.NETHERITE_PICKAXE.toItemStack(),
                )
                "shovel" -> listOf(
                    Material.WOODEN_SHOVEL.toItemStack(),
                    Material.STONE_SHOVEL.toItemStack(),
                    Material.IRON_SHOVEL.toItemStack(),
                    Material.GOLDEN_SHOVEL.toItemStack(),
                    Material.DIAMOND_SHOVEL.toItemStack(),
                    Material.NETHERITE_SHOVEL.toItemStack(),
                )
                "hoe", "till" -> listOf(
                    Material.WOODEN_HOE.toItemStack(),
                    Material.STONE_HOE.toItemStack(),
                    Material.IRON_HOE.toItemStack(),
                    Material.GOLDEN_HOE.toItemStack(),
                    Material.DIAMOND_HOE.toItemStack(),
                    Material.NETHERITE_HOE.toItemStack(),
                )
                "sword" -> listOf(
                    Material.WOODEN_SWORD.toItemStack(),
                    Material.STONE_SWORD.toItemStack(),
                    Material.IRON_SWORD.toItemStack(),
                    Material.GOLDEN_SWORD.toItemStack(),
                    Material.DIAMOND_SWORD.toItemStack(),
                    Material.NETHERITE_SWORD.toItemStack(),
                )
                "shears" -> listOf(Material.SHEARS.toItemStack())
                "shield" -> listOf(Material.SHIELD.toItemStack())
                "fishing" -> listOf(Material.FISHING_ROD.toItemStack())
                else -> listOf(ItemStack.empty())
            }
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