package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import net.kyori.adventure.text.Component
import xyz.xenondevs.nova.addon.registry.ItemRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.world.item.behavior.Consumable

@Suppress("unused")
@Init(stage = InitStage.PRE_PACK)
object Items : ItemRegistry by NovaFarmersDelight.registry {
    
    val COOKING_POT = item(Blocks.COOKING_POT) {
        name(Component.translatable("item.farmersdelight.cooking_pot"))
        maxStackSize(1)
    }
    val CUTTING_BOARD = registerItem(Blocks.CUTTING_BOARD)
    
    val CARROT_CRATE = registerItem(Blocks.CARROT_CRATE)
    val POTATO_CRATE = registerItem(Blocks.POTATO_CRATE)
    val BEETROOT_CRATE = registerItem(Blocks.BEETROOT_CRATE)
    val CABBAGE_CRATE = registerItem(Blocks.CABBAGE_CRATE)
    val TOMATO_CRATE = registerItem(Blocks.TOMATO_CRATE)
    val ONION_CRATE = registerItem(Blocks.ONION_CRATE)
    val RICE_BALE = registerItem(Blocks.RICE_BALE)
    val RICE_BAG = registerItem(Blocks.RICE_BAG)
    
    val MUDDY_FARMLAND = registerItem(Blocks.MUDDY_FARMLAND)
    
    val STRAW = registerItem("straw")
    val CANVAS = registerItem("canvas")
    val TREE_BARK = registerItem("tree_bark")
    
    val SANDY_SHRUB = item(Blocks.SANDY_SHRUB) {
        models {
            selectModel {
                createLayeredModel("block/sandy_shrub")
            }
        }
    }
    val WILD_CABBAGES = item(Blocks.WILD_CABBAGES) {
        models {
            selectModel {
                createLayeredModel("block/wild_cabbages")
            }
        }
    }
    val WILD_ONIONS = item(Blocks.WILD_ONIONS) {
        models {
            selectModel {
                createLayeredModel("block/wild_onions")
            }
        }
    }
    val WILD_TOMATOES = item(Blocks.WILD_TOMATOES) {
        models {
            selectModel {
                createLayeredModel("block/wild_tomatoes")
            }
        }
    }
    val WILD_CARROTS = item(Blocks.WILD_CARROTS) {
        models {
            selectModel {
                createLayeredModel("block/wild_carrots")
            }
        }
    }
    val WILD_POTATOES = item(Blocks.WILD_POTATOES) {
        models {
            selectModel {
                createLayeredModel("block/wild_potatoes")
            }
        }
    }
    val WILD_BEETROOTS = item(Blocks.WILD_BEETROOTS) {
        models {
            selectModel {
                createLayeredModel("block/wild_beetroots")
            }
        }
    }
    val WILD_RICE = item(Blocks.WILD_RICE) {
        models {
            selectModel {
                createLayeredModel("block/wild_rice")
            }
        }
    }
    
    val CABBAGE = registerItem("cabbage", Consumable)
    val TOMATO = registerItem("tomato", Consumable)
    val ONION = item(Blocks.ONION_CROP) {
        name(Component.translatable("item.farmersdelight.onion"))
        behaviors(Consumable)
        models {
            selectModel {
                createLayeredModel("item/onion")
            }
        }
    }
    val RICE_PANICLE = registerItem("rice_panicle")
    val RICE = item(Blocks.RICE_CROP) {
        name(Component.translatable("item.farmersdelight.rice"))
        models {
            selectModel {
                createLayeredModel("item/rice")
            }
        }
    }
    val CABBAGE_SEEDS = item(Blocks.CABBAGES_CROP) {
        name(Component.translatable("item.farmersdelight.cabbage_seeds"))
        models {
            selectModel {
                createLayeredModel("item/cabbage_seeds")
            }
        }
    }
    val TOMATO_SEEDS = item(Blocks.TOMATOES_CROP) {
        name(Component.translatable("item.farmersdelight.tomato_seeds"))
        models {
            selectModel {
                createLayeredModel("item/tomato_seeds")
            }
        }
    }
    val ROTTEN_TOMATO = registerItem("rotten_tomato")
    val FRIED_EGG = item("fried_egg") {
        behaviors(Consumable)
    }
    // TODO Add drinks effects
    val MILK_BOTTLE = item("milk_bottle") {
        behaviors(Consumable)
        maxStackSize(16)
    }
    val HOT_COCOA = item("hot_cocoa") {
        behaviors(Consumable)
        maxStackSize(16)
    }
    val APPLE_CIDER = item("apple_cider") {
        behaviors(Consumable)
        maxStackSize(16)
    }
    val MELON_JUICE = item("melon_juice") {
        behaviors(Consumable)
        maxStackSize(16)
    }
    val TOMATO_SAUCE = item("tomato_sauce") {
        behaviors(Consumable)
    }
    val WHEAT_DOUGH = item("wheat_dough") {
        behaviors(Consumable)
    }
    val RAW_PASTA = item("raw_pasta") {
        behaviors(Consumable)
    }
    val PUMPKIN_SLICE = item("pumpkin_slice") {
        behaviors(Consumable)
    }
    val CABBAGE_LEAF = item("cabbage_leaf") {
        behaviors(Consumable)
    }
    val MINCED_BEEF = item("minced_beef") {
        behaviors(Consumable)
    }
    val BEEF_PATTY = item("beef_patty") {
        behaviors(Consumable)
    }
    val CHICKEN_CUTS = item("chicken_cuts") {
        behaviors(Consumable)
    }
    val COOKED_CHICKEN_CUTS = item("cooked_chicken_cuts") {
        behaviors(Consumable)
    }
    val BACON = item("bacon") {
        behaviors(Consumable)
    }
    val COOKED_BACON = item("cooked_bacon") {
        behaviors(Consumable)
    }
    val COD_SLICE = item("cod_slice") {
        behaviors(Consumable)
    }
    val COOKED_COD_SLICE = item("cooked_cod_slice") {
        behaviors(Consumable)
    }
    val SALMON_SLICE = item("salmon_slice") {
        behaviors(Consumable)
    }
    val COOKED_SALMON_SLICE = item("cooked_salmon_slice") {
        behaviors(Consumable)
    }
    val MUTTON_CHOPS = item("mutton_chops") {
        behaviors(Consumable)
    }
    val COOKED_MUTTON_CHOPS = item("cooked_mutton_chops") {
        behaviors(Consumable)
    }
    val HAM = item("ham") {
        behaviors(Consumable)
    }
    val SMOKED_HAM = item("smoked_ham") {
        behaviors(Consumable)
    }
    val PIE_CRUST = item("pie_crust") {
        behaviors(Consumable)
    }
    val APPLE_PIE_SLICE = item("apple_pie_slice") {
        behaviors(Consumable)
    }
    val SWEET_BERRY_CHEESECAKE_SLICE = item("sweet_berry_cheesecake_slice") {
        behaviors(Consumable)
    }
    val CHOCOLATE_PIE_SLICE = item("chocolate_pie_slice") {
        behaviors(Consumable)
    }
    val SWEET_BERRY_COOKIE = item("sweet_berry_cookie") {
        behaviors(Consumable)
    }
    val HONEY_COOKIE = item("honey_cookie") {
        behaviors(Consumable)
    }
    val MELON_POPSICLE = item("melon_popsicle") {
        behaviors(Consumable)
    }
    val GLOW_BERRY_CUSTARD = item("glow_berry_custard") {
        behaviors(Consumable)
        maxStackSize(16)
    }
    val FRUIT_SALAD = item("fruit_salad") {
        behaviors(Consumable)
        maxStackSize(16)
    }
    
    val MIXED_SALAD = item("mixed_salad") {
        behaviors(Consumable)
        maxStackSize(16)
    }
    val NETHER_SALAD = item("nether_salad") {
        behaviors(Consumable)
        maxStackSize(16)
    }
    val BARBECUE_STICK = item("barbecue_stick") {
        behaviors(Consumable)
    }
    val EGG_SANDWICH = item("egg_sandwich") {
        behaviors(Consumable)
    }
    val CHICKEN_SANDWICH = item("chicken_sandwich") {
        behaviors(Consumable)
    }
    val HAMBURGER = item("hamburger") {
        behaviors(Consumable)
    }
    val BACON_SANDWICH = item("bacon_sandwich") {
        behaviors(Consumable)
    }
    val MUTTON_WRAP = item("mutton_wrap") {
        behaviors(Consumable)
    }
    val DUMPLINGS = item("dumplings") {
        behaviors(Consumable)
    }
    val STUFFED_POTATO = item("stuffed_potato") {
        behaviors(Consumable)
    }
    val CABBAGE_ROLLS = item("cabbage_rolls") {
        behaviors(Consumable)
    }
    val SALMON_ROLL = item("salmon_roll") {
        behaviors(Consumable)
    }
    val COD_ROLL = item("cod_roll") {
        behaviors(Consumable)
    }
    val KELP_ROLL = item("kelp_roll") {
        behaviors(Consumable)
    }
    val KELP_ROLL_SLICE = item("kelp_roll_slice") {
        behaviors(Consumable)
    }
    
    val COOKED_RICE = item("cooked_rice") {
        behaviors(Consumable)
        maxStackSize(16)
    }
    
}