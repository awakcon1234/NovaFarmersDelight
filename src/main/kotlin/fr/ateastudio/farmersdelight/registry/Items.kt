package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight.item
import fr.ateastudio.farmersdelight.NovaFarmersDelight.registerItem
import io.papermc.paper.datacomponent.item.consumable.ItemUseAnimation
import io.papermc.paper.registry.keys.SoundEventKeys
import net.kyori.adventure.text.Component
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.world.item.behavior.Consumable
import xyz.xenondevs.nova.world.item.behavior.Damageable
import xyz.xenondevs.nova.world.item.behavior.Enchantable
import xyz.xenondevs.nova.world.item.behavior.Tool

@Suppress("unused", "UnstableApiUsage")
@Init(stage = InitStage.PRE_PACK)
object Items {
    
    val COOKING_POT = item(Blocks.COOKING_POT) {
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
    val CORN_CRATE = registerItem(Blocks.CORN_CRATE)
    val CORN_KERNEL_BAG = registerItem(Blocks.CORN_KERNEL_BAG)
    val BELL_PEPPER_SEEDS_BAG = registerItem(Blocks.BELL_PEPPER_SEEDS_BAG)
    val COFFEE_BEANS_BAG = registerItem(Blocks.COFFEE_BEANS_BAG)
    val ROASTED_COFFEE_BEANS_BAG = registerItem(Blocks.ROASTED_COFFEE_BEANS_BAG)
    val BELL_PEPPER_RED_CRATE = registerItem(Blocks.BELL_PEPPER_RED_CRATE)
    val BELL_PEPPER_ORANGE_CRATE = registerItem(Blocks.BELL_PEPPER_ORANGE_CRATE)
    val BELL_PEPPER_YELLOW_CRATE = registerItem(Blocks.BELL_PEPPER_YELLOW_CRATE)
    val BELL_PEPPER_GREEN_CRATE = registerItem(Blocks.BELL_PEPPER_GREEN_CRATE)
    val BELL_PEPPER_BLUE_CRATE = registerItem(Blocks.BELL_PEPPER_BLUE_CRATE)
    val BELL_PEPPER_PURPLE_CRATE = registerItem(Blocks.BELL_PEPPER_PURPLE_CRATE)
    val BELL_PEPPER_PINK_CRATE = registerItem(Blocks.BELL_PEPPER_PINK_CRATE)
    val BELL_PEPPER_WHITE_CRATE = registerItem(Blocks.BELL_PEPPER_WHITE_CRATE)
    val BELL_PEPPER_BLACK_CRATE = registerItem(Blocks.BELL_PEPPER_BLACK_CRATE)
    val BELL_PEPPER_RED_BLOCK = registerItem(Blocks.BELL_PEPPER_RED_BLOCK)
    val BELL_PEPPER_ORANGE_BLOCK = registerItem(Blocks.BELL_PEPPER_ORANGE_BLOCK)
    val BELL_PEPPER_YELLOW_BLOCK = registerItem(Blocks.BELL_PEPPER_YELLOW_BLOCK)
    val BELL_PEPPER_GREEN_BLOCK = registerItem(Blocks.BELL_PEPPER_GREEN_BLOCK)
    val BELL_PEPPER_BLUE_BLOCK = registerItem(Blocks.BELL_PEPPER_BLUE_BLOCK)
    val BELL_PEPPER_PURPLE_BLOCK = registerItem(Blocks.BELL_PEPPER_PURPLE_BLOCK)
    val BELL_PEPPER_PINK_BLOCK = registerItem(Blocks.BELL_PEPPER_PINK_BLOCK)
    val BELL_PEPPER_WHITE_BLOCK = registerItem(Blocks.BELL_PEPPER_WHITE_BLOCK)
    val BELL_PEPPER_BLACK_BLOCK = registerItem(Blocks.BELL_PEPPER_BLACK_BLOCK)
    val STRAW_BALE = registerItem(Blocks.STRAW_BALE)
    
    val TATAMI = registerItem(Blocks.TATAMI)
    
    val FULL_TATAMI_MAT = item(Blocks.FULL_TATAMI_MAT_HEAD, "full_tatami_mat") {
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/full_tatami_mat")
            }
        }
    }
    val HALF_TATAMI_MAT = item(Blocks.HALF_TATAMI_MAT) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/half_tatami_mat")
            }
        }
    }
    val CANVAS_RUG = registerItem(Blocks.CANVAS_RUG)
    
    val MUDDY_FARMLAND = registerItem(Blocks.MUDDY_FARMLAND)
    
    val FLINT_KNIFE = item("flint_knife") {
        behaviors(Tool(), Damageable(), Enchantable())
        maxStackSize(1)
    }
    val IRON_KNIFE = item("iron_knife") {
        behaviors(Tool(), Damageable(), Enchantable())
        maxStackSize(1)
    }
    val DIAMOND_KNIFE = item("diamond_knife") {
        behaviors(Tool(), Damageable(), Enchantable())
        maxStackSize(1)
    }
    val NETHERITE_KNIFE = item("netherite_knife") {
        behaviors(Tool(), Damageable(), Enchantable())
        maxStackSize(1)
    }
    val GOLDEN_KNIFE = item("golden_knife") {
        behaviors(Tool(), Damageable(), Enchantable())
        maxStackSize(1)
    }
    
    val STRAW = registerItem("straw")
    val CANVAS = registerItem("canvas")
    val TREE_BARK = registerItem("tree_bark")
    
    val SANDY_SHRUB = item(Blocks.SANDY_SHRUB) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/sandy_shrub")
            }
        }
    }
    val WILD_COFFEE = item(Blocks.WILD_COFFEE) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/wild_coffee")
            }
        }
    }
    val WILD_BELL_PEPPERS = item(Blocks.WILD_BELL_PEPPERS) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/wild_bell_peppers")
            }
        }
    }
    val WILD_CORN = item(Blocks.WILD_CORN) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/wild_corn")
            }
        }
    }
    val WILD_CABBAGES = item(Blocks.WILD_CABBAGES) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/wild_cabbages")
            }
        }
    }
    val WILD_ONIONS = item(Blocks.WILD_ONIONS) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/wild_onions")
            }
        }
    }
    val WILD_TOMATOES = item(Blocks.WILD_TOMATOES) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/wild_tomatoes")
            }
        }
    }
    val WILD_CARROTS = item(Blocks.WILD_CARROTS) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/wild_carrots")
            }
        }
    }
    val WILD_POTATOES = item(Blocks.WILD_POTATOES) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/wild_potatoes")
            }
        }
    }
    val WILD_BEETROOTS = item(Blocks.WILD_BEETROOTS) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/wild_beetroots")
            }
        }
    }
    val WILD_RICE = item(Blocks.WILD_RICE) {
        modelDefinition {
            model = buildModel {
                createLayeredModel("block/wild_rice")
            }
        }
    }
    
    val CABBAGE = registerItem("cabbage", Consumable())
    val TOMATO = registerItem("tomato", Consumable())
    val ONION = item(Blocks.ONION_CROP, "onion") {
        name(Component.translatable("item.farmersdelight.onion"))
        behaviors(Consumable())
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/onion")
            }
        }
    }
    val RICE_PANICLE = registerItem("rice_panicle")
    val RICE = item(Blocks.RICE_CROP, "rice") {
        name(Component.translatable("item.farmersdelight.rice"))
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/rice")
            }
        }
    }
    val COFFEE_BEANS = item(Blocks.COFFEE_CROP, "coffee_beans") {
        name(Component.translatable("item.farmersdelight.coffee_beans"))
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/coffee_beans")
            }
        }
    }
    val ROASTED_COFFEE_BEANS = item("roasted_coffee_beans") {
        behaviors(Consumable())
    }
    val GOLDEN_COFFEE_BEANS = item("golden_coffee_beans") {
        behaviors(Consumable())
    }
    val COFFEE = item("coffee") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val DARK_COFFEE = item("dark_coffee") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val MILK_COFFEE = item("milk_coffee") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val CHOCOLATE_COFFEE = item("chocolate_coffee") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val HONEY_COFFEE = item("honey_coffee") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val PUMPKIN_COFFEE = item("pumpkin_coffee") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val BATTER = item("batter") {
        behaviors(Consumable())
    }
    val SYRUP = item("syrup") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val SYRUP_COFFEE = item("syrup_coffee") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val CHERRY_BLOSSOM_COFFEE = item("cherry_blossom_coffee") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val SYRUP_COOKIE = item("syrup_cookie") {
        behaviors(Consumable())
    }
    val CHERRY_BLOSSOM_COOKIE = item("cherry_blossom_cookie") {
        behaviors(Consumable())
    }
    val SYRUP_SANDWICH = item("syrup_sandwich") {
        behaviors(Consumable())
    }
    val CHERRY_BLOSSOM_ROLL = item("cherry_blossom_roll") {
        behaviors(Consumable())
    }
    val SYRUP_CHEESECAKE_SLICE = item("syrup_cheesecake_slice") {
        behaviors(Consumable())
    }
    val CHERRY_BLOSSOM_CHEESECAKE_SLICE = item("cherry_blossom_cheesecake_slice") {
        behaviors(Consumable())
    }
    val PANCAKE = item("pancake") {
        behaviors(Consumable())
    }
    val HONEY_PANCAKE = item("honey_pancake") {
        behaviors(Consumable())
    }
    val CHOCOLATE_PANCAKE = item("chocolate_pancake") {
        behaviors(Consumable())
    }
    val CHERRY_BLOSSOM_PANCAKE = item("cherry_blossom_pancake") {
        behaviors(Consumable())
    }
    val VEGETABLE_PANCAKE = item("vegetable_pancake") {
        behaviors(Consumable())
    }
    val PUMPKIN_PANCAKE = item("pumpkin_pancake") {
        behaviors(Consumable())
    }
    val COFFEE_PANCAKE = item("coffee_pancake") {
        behaviors(Consumable())
    }
    val COFFEE_COOKIE = item("coffee_cookie") {
        behaviors(Consumable())
    }
    val COFFEE_CHEESECAKE_SLICE = item("coffee_cheesecake_slice") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_SEEDS = item(Blocks.BELL_PEPPERS_CROP, "bell_pepper_seeds") {
        name(Component.translatable("item.farmersdelight.bell_pepper_seeds"))
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/bell_pepper_seeds")
            }
        }
    }
    val BELL_PEPPER_RED = item("bell_pepper_red") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_SLICE_RED = item("bell_pepper_slice_red") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_RED = item("roasted_bell_pepper_red") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_SLICE_RED = item("roasted_bell_pepper_slice_red") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_ROLL_RED = item("bell_pepper_roll_red") {
        behaviors(Consumable())
    }
    val STUFFED_BELL_PEPPER_RED = item("stuffed_bell_pepper_red") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_ORANGE = item("bell_pepper_orange") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_SLICE_ORANGE = item("bell_pepper_slice_orange") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_ORANGE = item("roasted_bell_pepper_orange") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_SLICE_ORANGE = item("roasted_bell_pepper_slice_orange") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_ROLL_ORANGE = item("bell_pepper_roll_orange") {
        behaviors(Consumable())
    }
    val STUFFED_BELL_PEPPER_ORANGE = item("stuffed_bell_pepper_orange") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_YELLOW = item("bell_pepper_yellow") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_SLICE_YELLOW = item("bell_pepper_slice_yellow") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_YELLOW = item("roasted_bell_pepper_yellow") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_SLICE_YELLOW = item("roasted_bell_pepper_slice_yellow") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_ROLL_YELLOW = item("bell_pepper_roll_yellow") {
        behaviors(Consumable())
    }
    val STUFFED_BELL_PEPPER_YELLOW = item("stuffed_bell_pepper_yellow") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_GREEN = item("bell_pepper_green") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_SLICE_GREEN = item("bell_pepper_slice_green") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_GREEN = item("roasted_bell_pepper_green") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_SLICE_GREEN = item("roasted_bell_pepper_slice_green") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_ROLL_GREEN = item("bell_pepper_roll_green") {
        behaviors(Consumable())
    }
    val STUFFED_BELL_PEPPER_GREEN = item("stuffed_bell_pepper_green") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_BLUE = item("bell_pepper_blue") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_SLICE_BLUE = item("bell_pepper_slice_blue") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_BLUE = item("roasted_bell_pepper_blue") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_SLICE_BLUE = item("roasted_bell_pepper_slice_blue") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_ROLL_BLUE = item("bell_pepper_roll_blue") {
        behaviors(Consumable())
    }
    val STUFFED_BELL_PEPPER_BLUE = item("stuffed_bell_pepper_blue") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_PURPLE = item("bell_pepper_purple") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_SLICE_PURPLE = item("bell_pepper_slice_purple") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_PURPLE = item("roasted_bell_pepper_purple") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_SLICE_PURPLE = item("roasted_bell_pepper_slice_purple") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_ROLL_PURPLE = item("bell_pepper_roll_purple") {
        behaviors(Consumable())
    }
    val STUFFED_BELL_PEPPER_PURPLE = item("stuffed_bell_pepper_purple") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_PINK = item("bell_pepper_pink") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_SLICE_PINK = item("bell_pepper_slice_pink") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_PINK = item("roasted_bell_pepper_pink") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_SLICE_PINK = item("roasted_bell_pepper_slice_pink") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_ROLL_PINK = item("bell_pepper_roll_pink") {
        behaviors(Consumable())
    }
    val STUFFED_BELL_PEPPER_PINK = item("stuffed_bell_pepper_pink") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_WHITE = item("bell_pepper_white") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_SLICE_WHITE = item("bell_pepper_slice_white") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_WHITE = item("roasted_bell_pepper_white") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_SLICE_WHITE = item("roasted_bell_pepper_slice_white") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_ROLL_WHITE = item("bell_pepper_roll_white") {
        behaviors(Consumable())
    }
    val STUFFED_BELL_PEPPER_WHITE = item("stuffed_bell_pepper_white") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_BLACK = item("bell_pepper_black") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_SLICE_BLACK = item("bell_pepper_slice_black") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_BLACK = item("roasted_bell_pepper_black") {
        behaviors(Consumable())
    }
    val ROASTED_BELL_PEPPER_SLICE_BLACK = item("roasted_bell_pepper_slice_black") {
        behaviors(Consumable())
    }
    val BELL_PEPPER_ROLL_BLACK = item("bell_pepper_roll_black") {
        behaviors(Consumable())
    }
    val STUFFED_BELL_PEPPER_BLACK = item("stuffed_bell_pepper_black") {
        behaviors(Consumable())
    }
    val CORN_SEEDS = item(Blocks.CORN_CROP, "corn_seeds") {
        name(Component.translatable("item.farmersdelight.corn_seeds"))
        behaviors(Consumable())
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/corn_seeds")
            }
        }
    }
    val CORN = item("corn") {
        behaviors(Consumable())
    }
    val CORNCOB = item("corncob") {
    }
    val GRILLED_CORN = item("grilled_corn") {
        behaviors(Consumable())
    }
    val BOILED_CORN = item("boiled_corn") {
        behaviors(Consumable())
    }
    val POPCORN = item("popcorn") {
        behaviors(Consumable())
    }
    val CARAMEL_POPCORN = item("caramel_popcorn") {
        behaviors(Consumable())
    }
    val CORNBREAD_BATTER = item("cornbread_batter") {
        behaviors(Consumable())
    }
    val CORNBREAD = item("cornbread") {
        behaviors(Consumable())
    }
    val CORN_DOG = item("corn_dog") {
        behaviors(Consumable())
    }
    val CLASSIC_CORN_DOG = item("classic_corn_dog") {
        behaviors(Consumable())
    }
    val TORTILLA_RAW = item("tortilla_raw") {
        behaviors(Consumable())
    }
    val TORTILLA = item("tortilla") {
        behaviors(Consumable())
    }
    val TORTILLA_CHIP = item("tortilla_chip") {
        behaviors(Consumable())
    }
    val TACO = item("taco") {
        behaviors(Consumable())
    }
    val CABBAGE_SEEDS = item(Blocks.CABBAGES_CROP, "cabbage_seeds") {
        name(Component.translatable("item.farmersdelight.cabbage_seeds"))
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/cabbage_seeds")
            }
        }
    }
    val TOMATO_SEEDS = item(Blocks.TOMATOES_CROP, "tomato_seeds") {
        name(Component.translatable("item.farmersdelight.tomato_seeds"))
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/tomato_seeds")
            }
        }
    }
    val ROTTEN_TOMATO = registerItem("rotten_tomato")
    val FRIED_EGG = item("fried_egg") {
        behaviors(Consumable())
    }
    // TODO Add drinks effects
    val MILK_BOTTLE = item("milk_bottle") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val HOT_COCOA = item("hot_cocoa") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val APPLE_CIDER = item("apple_cider") {
        behaviors(Consumable(animation = ItemUseAnimation.DRINK, sound = SoundEventKeys.ENTITY_GENERIC_DRINK))
        maxStackSize(16)
    }
    val MELON_JUICE = item("melon_juice") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val TOMATO_SAUCE = item("tomato_sauce") {
        behaviors(Consumable())
    }
    val WHEAT_DOUGH = item("wheat_dough") {
        behaviors(Consumable())
    }
    val RAW_PASTA = item("raw_pasta") {
        behaviors(Consumable())
    }
    val PUMPKIN_SLICE = item("pumpkin_slice") {
        behaviors(Consumable())
    }
    val CABBAGE_LEAF = item("cabbage_leaf") {
        behaviors(Consumable())
    }
    val MINCED_BEEF = item("minced_beef") {
        behaviors(Consumable())
    }
    val BEEF_PATTY = item("beef_patty") {
        behaviors(Consumable())
    }
    val CHICKEN_CUTS = item("chicken_cuts") {
        behaviors(Consumable())
    }
    val COOKED_CHICKEN_CUTS = item("cooked_chicken_cuts") {
        behaviors(Consumable())
    }
    val BACON = item("bacon") {
        behaviors(Consumable())
    }
    val COOKED_BACON = item("cooked_bacon") {
        behaviors(Consumable())
    }
    val COD_SLICE = item("cod_slice") {
        behaviors(Consumable())
    }
    val COOKED_COD_SLICE = item("cooked_cod_slice") {
        behaviors(Consumable())
    }
    val SALMON_SLICE = item("salmon_slice") {
        behaviors(Consumable())
    }
    val COOKED_SALMON_SLICE = item("cooked_salmon_slice") {
        behaviors(Consumable())
    }
    val MUTTON_CHOPS = item("mutton_chops") {
        behaviors(Consumable())
    }
    val COOKED_MUTTON_CHOPS = item("cooked_mutton_chops") {
        behaviors(Consumable())
    }
    val HAM = item("ham") {
        behaviors(Consumable())
    }
    val SMOKED_HAM = item("smoked_ham") {
        behaviors(Consumable())
    }
    val PIE_CRUST = item("pie_crust") {
        behaviors(Consumable())
    }
    val APPLE_PIE = item(Blocks.APPLE_PIE,"apple_pie") {
        maxStackSize(1)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/apple_pie")
            }
        }
    }
    val SWEET_BERRY_CHEESECAKE = item(Blocks.SWEET_BERRY_CHEESECAKE,"sweet_berry_cheesecake") {
        maxStackSize(1)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/sweet_berry_cheesecake")
            }
        }
    }
    val SYRUP_CHEESECAKE = item(Blocks.SYRUP_CHEESECAKE,"syrup_cheesecake") {
        maxStackSize(1)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/syrup_cheesecake")
            }
        }
    }
    val CHERRY_BLOSSOM_CHEESECAKE = item(Blocks.CHERRY_BLOSSOM_CHEESECAKE,"cherry_blossom_cheesecake") {
        maxStackSize(1)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/cherry_blossom_cheesecake")
            }
        }
    }
    val PANCAKES = item(Blocks.PANCAKES,"pancakes") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/pancakes")
            }
        }
    }
    val HONEY_PANCAKES = item(Blocks.HONEY_PANCAKES,"honey_pancakes") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/honey_pancakes")
            }
        }
    }
    val CHOCOLATE_PANCAKES = item(Blocks.CHOCOLATE_PANCAKES,"chocolate_pancakes") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/chocolate_pancakes")
            }
        }
    }
    val CHERRY_BLOSSOM_PANCAKES = item(Blocks.CHERRY_BLOSSOM_PANCAKES,"cherry_blossom_pancakes") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/cherry_blossom_pancakes")
            }
        }
    }
    val VEGETABLE_PANCAKES = item(Blocks.VEGETABLE_PANCAKES,"vegetable_pancakes") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/vegetable_pancakes")
            }
        }
    }
    val PUMPKIN_PANCAKES = item(Blocks.PUMPKIN_PANCAKES,"pumpkin_pancakes") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/pumpkin_pancakes")
            }
        }
    }
    val COFFEE_PANCAKES = item(Blocks.COFFEE_PANCAKES,"coffee_pancakes") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/coffee_pancakes")
            }
        }
    }
    val COFFEE_CHEESECAKE = item(Blocks.COFFEE_CHEESECAKE,"coffee_cheesecake") {
        maxStackSize(1)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/coffee_cheesecake")
            }
        }
    }
    val CHOCOLATE_PIE = item(Blocks.CHOCOLATE_PIE,"chocolate_pie") {
        maxStackSize(1)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/chocolate_pie")
            }
        }
    }
    
    val CAKE_SLICE = item("cake_slice") {
        behaviors(Consumable())
    }
    val APPLE_PIE_SLICE = item("apple_pie_slice") {
        behaviors(Consumable())
    }
    val SWEET_BERRY_CHEESECAKE_SLICE = item("sweet_berry_cheesecake_slice") {
        behaviors(Consumable())
    }
    val CHOCOLATE_PIE_SLICE = item("chocolate_pie_slice") {
        behaviors(Consumable())
    }
    val PUMPKIN_PIE_SLICE = item("pumpkin_pie_slice") {
        behaviors(Consumable())
    }
    val SWEET_BERRY_COOKIE = item("sweet_berry_cookie") {
        behaviors(Consumable())
    }
    val HONEY_COOKIE = item("honey_cookie") {
        behaviors(Consumable())
    }
    val MELON_POPSICLE = item("melon_popsicle") {
        behaviors(Consumable())
    }
    val GLOW_BERRY_CUSTARD = item("glow_berry_custard") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val FRUIT_SALAD = item("fruit_salad") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    
    val MIXED_SALAD = item("mixed_salad") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val NETHER_SALAD = item("nether_salad") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val GLEAMING_SALAD = item("gleaming_salad") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val BARBECUE_STICK = item("barbecue_stick") {
        behaviors(Consumable())
    }
    val EGG_SANDWICH = item("egg_sandwich") {
        behaviors(Consumable())
    }
    val CHICKEN_SANDWICH = item("chicken_sandwich") {
        behaviors(Consumable())
    }
    val HAMBURGER = item("hamburger") {
        behaviors(Consumable())
    }
    val BACON_SANDWICH = item("bacon_sandwich") {
        behaviors(Consumable())
    }
    val MUTTON_WRAP = item("mutton_wrap") {
        behaviors(Consumable())
    }
    val DUMPLINGS = item("dumplings") {
        behaviors(Consumable())
    }
    val STUFFED_POTATO = item("stuffed_potato") {
        behaviors(Consumable())
    }
    val CABBAGE_ROLLS = item("cabbage_rolls") {
        behaviors(Consumable())
    }
    val SALMON_ROLL = item("salmon_roll") {
        behaviors(Consumable())
    }
    val COD_ROLL = item("cod_roll") {
        behaviors(Consumable())
    }
    val KELP_ROLL = item("kelp_roll") {
        behaviors(Consumable())
    }
    val KELP_ROLL_SLICE = item("kelp_roll_slice") {
        behaviors(Consumable())
    }
    
    val COOKED_RICE = item("cooked_rice") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val BONE_BROTH = item("bone_broth") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val BEEF_STEW = item("beef_stew") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val CHICKEN_SOUP = item("chicken_soup") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val VEGETABLE_SOUP = item("vegetable_soup") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val ONION_SOUP = item("onion_soup") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val CREAMED_CORN = item("creamed_corn") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val CORN_SOUP = item("corn_soup") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val CREAMY_CORN_DRINK = item("creamy_corn_drink") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val CORNBREAD_STUFFING = item("cornbread_stuffing") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val NACHOS_BOWL = item("nachos_bowl") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val COFFEE_BRAISED_BEEF = item("coffee_braised_beef") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val BELL_PEPPER_SOUP = item("bell_pepper_soup") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val BELL_PEPPER_PASTA = item("bell_pepper_pasta") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val FISH_STEW = item("fish_stew") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val FRIED_RICE = item("fried_rice") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val PUMPKIN_SOUP = item("pumpkin_soup") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val BAKED_COD_STEW = item("baked_cod_stew") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val NOODLE_SOUP = item("noodle_soup") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    
    val BACON_AND_EGGS = item("bacon_and_eggs") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val PASTA_WITH_MEATBALLS = item("pasta_with_meatballs") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val PASTA_WITH_MUTTON_CHOP = item("pasta_with_mutton_chop") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val MUSHROOM_RICE = item("mushroom_rice") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val ROASTED_MUTTON_CHOPS = item("roasted_mutton_chops") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val VEGETABLE_NOODLES = item("vegetable_noodles") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val STEAK_AND_POTATOES = item("steak_and_potatoes") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val RATATOUILLE = item("ratatouille") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val SQUID_INK_PASTA = item("squid_ink_pasta") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val GRILLED_SALMON = item("grilled_salmon") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    //TODO Meal blocks
    val ROAST_CHICKEN_BLOCK = item(Blocks.ROAST_CHICKEN_BLOCK,"roast_chicken_block") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/roast_chicken_block")
            }
        }
    }
    val ROAST_CHICKEN = item("roast_chicken") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val STUFFED_PUMPKIN_BLOCK = item(Blocks.STUFFED_PUMPKIN_BLOCK,"stuffed_pumpkin_block") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/stuffed_pumpkin_block")
            }
        }
    }
    val STUFFED_PUMPKIN = item("stuffed_pumpkin") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val HONEY_GLAZED_HAM_BLOCK = item(Blocks.HONEY_GLAZED_HAM_BLOCK,"honey_glazed_ham_block") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/honey_glazed_ham_block")
            }
        }
    }
    val HONEY_GLAZED_HAM = item("honey_glazed_ham") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val SHEPHERDS_PIE_BLOCK = item(Blocks.SHEPHERDS_PIE_BLOCK,"shepherds_pie_block") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/shepherds_pie_block")
            }
        }
    }
    val BELL_PEPPER_MEDLEY = item(Blocks.BELL_PEPPER_MEDLEY,"bell_pepper_medley") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/bell_pepper_medley")
            }
        }
    }
    val DARK_BELL_PEPPER_MEDLEY = item(Blocks.DARK_BELL_PEPPER_MEDLEY,"dark_bell_pepper_medley") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/dark_bell_pepper_medley")
            }
        }
    }
    val PALE_BELL_PEPPER_MEDLEY = item(Blocks.PALE_BELL_PEPPER_MEDLEY,"pale_bell_pepper_medley") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/pale_bell_pepper_medley")
            }
        }
    }
    val NACHOS_BLOCK = item(Blocks.NACHOS_BLOCK,"nachos_block") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/nachos_block")
            }
        }
    }
    val POPCORN_BOX = item(Blocks.POPCORN_BOX,"popcorn_box") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                getModel("block/popcorn_box_stage0")
            }
        }
    }
    val GLEAMING_SALAD_BLOCK = item(Blocks.GLEAMING_SALAD_BLOCK,"gleaming_salad_block") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/gleaming_salad_block")
            }
        }
    }
    val SHEPHERDS_PIE = item("shepherds_pie") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    val RICE_ROLL_MEDLEY_BLOCK = item(Blocks.RICE_ROLL_MEDLEY_BLOCK,"rice_roll_medley_block") {
        maxStackSize(16)
        modelDefinition {
            model = buildModel {
                createLayeredModel("item/rice_roll_medley_block")
            }
        }
    }
    val DOG_FOOD = item("dog_food") {
        behaviors(Consumable())
        maxStackSize(16)
    }
    
}