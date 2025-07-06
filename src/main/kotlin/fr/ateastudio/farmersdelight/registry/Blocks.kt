package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight.block
import fr.ateastudio.farmersdelight.NovaFarmersDelight.tileEntity
import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.CookingPotSupport
import fr.ateastudio.farmersdelight.block.ScopedBlockStateProperties
import fr.ateastudio.farmersdelight.block.ScopedBlockStateProperties.PAIRED
import fr.ateastudio.farmersdelight.block.behavior.Ageable
import fr.ateastudio.farmersdelight.block.behavior.CookingPotBehavior
import fr.ateastudio.farmersdelight.block.behavior.CuttingBoard
import fr.ateastudio.farmersdelight.block.behavior.MuddyFarmland
import fr.ateastudio.farmersdelight.block.behavior.PairedBlock
import fr.ateastudio.farmersdelight.block.behavior.TatamiMatFoot
import fr.ateastudio.farmersdelight.block.behavior.TatamiMatHead
import fr.ateastudio.farmersdelight.block.behavior.crop.CabbageCrop
import fr.ateastudio.farmersdelight.block.behavior.crop.OnionCrop
import fr.ateastudio.farmersdelight.block.behavior.crop.RiceCrop
import fr.ateastudio.farmersdelight.block.behavior.crop.TomatoCrop
import fr.ateastudio.farmersdelight.block.behavior.feastblock.HoneyGlazedHamBlock
import fr.ateastudio.farmersdelight.block.behavior.feastblock.RiceRollMedleyBlock
import fr.ateastudio.farmersdelight.block.behavior.feastblock.RoastChickenBlock
import fr.ateastudio.farmersdelight.block.behavior.feastblock.ShepherdsPieBlock
import fr.ateastudio.farmersdelight.block.behavior.feastblock.StuffedPumpkinBlock
import fr.ateastudio.farmersdelight.block.behavior.pie.ApplePie
import fr.ateastudio.farmersdelight.block.behavior.pie.ChocolatePie
import fr.ateastudio.farmersdelight.block.behavior.pie.SweetBerryCheesecake
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.SandyShrub
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildBeetroots
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildCabbages
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildCarrots
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildOnions
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildPotatoes
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildRice
import fr.ateastudio.farmersdelight.block.behavior.wildcrop.WildTomatoes
import fr.ateastudio.farmersdelight.tileentity.CookingPot
import org.bukkit.Material
import org.bukkit.block.BlockFace
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.resources.builder.layout.block.BackingStateCategory
import xyz.xenondevs.nova.world.block.NovaBlock
import xyz.xenondevs.nova.world.block.NovaBlockBuilder
import xyz.xenondevs.nova.world.block.behavior.BlockBehaviorHolder
import xyz.xenondevs.nova.world.block.behavior.BlockDrops
import xyz.xenondevs.nova.world.block.behavior.BlockSounds
import xyz.xenondevs.nova.world.block.behavior.Breakable
import xyz.xenondevs.nova.world.block.behavior.TileEntityDrops
import xyz.xenondevs.nova.world.block.behavior.TileEntityInteractive
import xyz.xenondevs.nova.world.block.behavior.TileEntityLimited
import xyz.xenondevs.nova.world.block.sound.SoundGroup
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.block.state.property.DefaultScopedBlockStateProperties.AXIS
import xyz.xenondevs.nova.world.block.state.property.DefaultScopedBlockStateProperties.FACING_CARTESIAN
import xyz.xenondevs.nova.world.block.state.property.DefaultScopedBlockStateProperties.FACING_HORIZONTAL
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories
import xyz.xenondevs.nova.world.item.tool.VanillaToolTiers

@Init(stage = InitStage.PRE_PACK)
object Blocks {
    private val CROP = Breakable(0.0, setOf(VanillaToolCategories.AXE), VanillaToolTiers.WOOD, false, Material.TALL_GRASS)
    private val MUD = Breakable(0.5, setOf(VanillaToolCategories.SHOVEL), VanillaToolTiers.WOOD, false, Material.MUD)
    private val BAG = Breakable(0.8, setOf(VanillaToolCategories.SHEARS), VanillaToolTiers.WOOD, false, Material.WHITE_WOOL)
    private val BALE = Breakable(0.8, setOf(VanillaToolCategories.HOE), VanillaToolTiers.WOOD, false, Material.HAY_BLOCK)
    private val CRATE = Breakable(2.0, setOf(VanillaToolCategories.AXE), VanillaToolTiers.WOOD, false, Material.OAK_PLANKS)
    private val LIGHT_METAL = Breakable(0.5, setOf(VanillaToolCategories.PICKAXE), VanillaToolTiers.WOOD, false, Material.CAULDRON)
    private val SPRUCE_PLANK = Breakable(0.5, setOf(VanillaToolCategories.AXE), VanillaToolTiers.WOOD, false, Material.SPRUCE_PLANKS)
    
    val COOKING_POT = tileEntity("cooking_pot", ::CookingPot) {
        behaviors(
            TileEntityLimited,
            TileEntityDrops,
            TileEntityInteractive,
            LIGHT_METAL,
            BlockSounds(SoundGroup.LANTERN),
            CookingPotBehavior
        )
        tickrate(20)
        stateProperties(FACING_HORIZONTAL, ScopedBlockStateProperties.SUPPORT, ScopedBlockStateProperties.HEATED)
        stateBacked(BackingStateCategory.LEAVES) {
            val support = getPropertyValueOrThrow(BlockStateProperties.SUPPORT)
            when (support) {
                CookingPotSupport.HANDLE -> getModel("block/cooking_pot_handle").rotated()
                CookingPotSupport.TRAY -> getModel("block/cooking_pot_tray").rotated()
                else -> getModel("block/cooking_pot").rotated()
            }
        }
    }
    val CUTTING_BOARD = block("cutting_board") {
        behaviors(
            SPRUCE_PLANK,
            BlockSounds(SoundGroup.WOOD),
            BlockDrops,
            CuttingBoard
        )
        stateProperties(FACING_HORIZONTAL)
        stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED) { defaultModel.rotated() }
    }
    
    val CARROT_CRATE = nonInteractiveBlock("carrot_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val POTATO_CRATE = nonInteractiveBlock("potato_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val BEETROOT_CRATE = nonInteractiveBlock("beetroot_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val CABBAGE_CRATE = nonInteractiveBlock("cabbage_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val TOMATO_CRATE = nonInteractiveBlock("tomato_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val ONION_CRATE = nonInteractiveBlock("onion_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val RICE_BALE = nonInteractiveBlock("rice_bale") {
        behaviors(BALE, BlockDrops, BlockSounds(SoundGroup.GRASS))
        stateProperties(FACING_CARTESIAN)
        stateBacked(BackingStateCategory.MUSHROOM_BLOCK, BackingStateCategory.NOTE_BLOCK) {
            defaultModel.rotated()
        }
    }
    val RICE_BAG = nonInteractiveBlock("rice_bag") { behaviors(BAG, BlockDrops, BlockSounds(SoundGroup.WOOL)) }
    val STRAW_BALE = block("straw_bale") {
        behaviors(BALE, BlockDrops, BlockSounds(SoundGroup.GRASS))
        stateProperties(AXIS)
        stateBacked(BackingStateCategory.MUSHROOM_BLOCK, BackingStateCategory.NOTE_BLOCK) {
            defaultModel.rotated()
        }
    }
    
    val TATAMI = block("tatami") {
        behaviors(PairedBlock, BAG, BlockDrops, BlockSounds(SoundGroup.WOOL))
        stateProperties(FACING_CARTESIAN, PAIRED)
        stateBacked(BackingStateCategory.MUSHROOM_BLOCK, BackingStateCategory.NOTE_BLOCK) {
            val paired = getPropertyValueOrThrow(BlockStateProperties.PAIRED)
            val face = getPropertyValueOrThrow(DefaultBlockStateProperties.FACING)
            if (paired) {
                when (face) {
                    BlockFace.DOWN -> getModel("block/tatami_odd")
                    BlockFace.UP -> getModel("block/tatami_even").rotateX(-180.0)
                    BlockFace.NORTH -> getModel("block/tatami_odd").rotateX(-90.0)
                    BlockFace.SOUTH -> getModel("block/tatami_even").rotateX(90.0)
                    BlockFace.EAST -> getModel("block/tatami_odd").rotateZ(90.0)
                    BlockFace.WEST -> getModel("block/tatami_even").rotateZ(-90.0)
                    else -> getModel("block/tatami_half").rotated()
                }
            }
            else {
                getModel("block/tatami_half").rotated()
            }
        }
    }
    
    val FULL_TATAMI_MAT_HEAD = block("full_tatami_mat") {
        behaviors(TatamiMatHead, BAG, BlockDrops, BlockSounds(SoundGroup.WOOL))
        stateProperties(FACING_HORIZONTAL)
        stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED, BackingStateCategory.TRIPWIRE_UNATTACHED) {
            getModel("block/tatami_mat_head").rotateY(180.0).rotated()
        }
    }
    
    val FULL_TATAMI_MAT_FOOT = block("full_tatami_mat_foot") {
        behaviors(TatamiMatFoot, BAG, BlockSounds(SoundGroup.WOOL))
        stateProperties(FACING_HORIZONTAL)
        stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED, BackingStateCategory.TRIPWIRE_UNATTACHED) {
            getModel("block/tatami_mat_foot").rotated()
        }
    }
    
    val HALF_TATAMI_MAT = block("half_tatami_mat") {
        behaviors(BAG, BlockDrops, BlockSounds(SoundGroup.WOOL))
        stateProperties(FACING_HORIZONTAL)
        stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED, BackingStateCategory.TRIPWIRE_UNATTACHED) {
            val face = getPropertyValueOrThrow(DefaultBlockStateProperties.FACING)
                when (face) {
                    BlockFace.SOUTH, BlockFace.NORTH -> getModel("block/tatami_mat_half")
                    else -> getModel("block/tatami_mat_half").rotateY(90.0)
                }
        }
    }
    
    val CANVAS_RUG = block("canvas_rug") {
        behaviors(BAG, BlockDrops, BlockSounds(SoundGroup.WOOL))
        stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED, BackingStateCategory.TRIPWIRE_UNATTACHED)
    }
    
    val MUDDY_FARMLAND = block("muddy_farmland") {
        behaviors(MUD, BlockSounds(SoundGroup.MUD), MuddyFarmland)
        stateBacked(BackingStateCategory.LEAVES, BackingStateCategory.MUSHROOM_BLOCK, BackingStateCategory.NOTE_BLOCK)
    }
    
    val SANDY_SHRUB = plantBlock("sandy_shrub") { behaviors(SandyShrub(), CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_CABBAGES = plantBlock("wild_cabbages") { behaviors(WildCabbages(), CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_ONIONS = plantBlock("wild_onions") { behaviors(WildOnions(), CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_TOMATOES = plantBlock("wild_tomatoes") { behaviors(WildTomatoes(), CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_CARROTS = plantBlock("wild_carrots") { behaviors(WildCarrots(), CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_POTATOES = plantBlock("wild_potatoes") { behaviors(WildPotatoes(), CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_BEETROOTS = plantBlock("wild_beetroots") { behaviors(WildBeetroots(), CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_RICE = plantBlock("wild_rice") { behaviors(WildRice(), CROP, BlockSounds(SoundGroup.GRASS))}
    
    // val BROWN_MUSHROOM_COLONY = cropBlock("brown_mushroom_colony", TomatoCrop, 3)
    // val RED_MUSHROOM_COLONY = cropBlock("red_mushroom_colony", TomatoCrop, 3)
    val CABBAGES_CROP = cropBlock("cabbages", CabbageCrop, 7)
    val TOMATOES_CROP = cropBlock("tomatoes", TomatoCrop, 7,3)
    val ONION_CROP = cropBlock("onions", OnionCrop, 3)
    val RICE_CROP = cropBlock("rice", RiceCrop, 7,3)
    
    val APPLE_PIE = pieBlock("apple_pie", ApplePie)
    val SWEET_BERRY_CHEESECAKE = pieBlock("sweet_berry_cheesecake", SweetBerryCheesecake)
    val CHOCOLATE_PIE = pieBlock("chocolate_pie", ChocolatePie)
    
    val ROAST_CHICKEN_BLOCK = feastBlock("roast_chicken_block", RoastChickenBlock, true)
    val STUFFED_PUMPKIN_BLOCK = feastBlock("stuffed_pumpkin_block", StuffedPumpkinBlock, false)
    val HONEY_GLAZED_HAM_BLOCK = feastBlock("honey_glazed_ham_block", HoneyGlazedHamBlock, true)
    val SHEPHERDS_PIE_BLOCK = feastBlock("shepherds_pie_block", ShepherdsPieBlock, true)
    val RICE_ROLL_MEDLEY_BLOCK = feastBlock("rice_roll_medley_block", RiceRollMedleyBlock, true, 8)
    
    
    private fun nonInteractiveBlock(
        name: String,
        block: NovaBlockBuilder.() -> Unit
    ): NovaBlock = block(name) {
        block()
        stateBacked(BackingStateCategory.MUSHROOM_BLOCK, BackingStateCategory.NOTE_BLOCK)
    }
    
    private fun plantBlock(
        name: String,
        block: NovaBlockBuilder.() -> Unit ={}
    ): NovaBlock = block(name) {
        block()
        stateBacked(BackingStateCategory.TRIPWIRE_UNATTACHED, BackingStateCategory.TRIPWIRE_ATTACHED)
    }
    
    private fun cropBlock(
        name: String,
        cropBehavior: BlockBehaviorHolder,
        maxAge: Int,
        buddingAge: Int = -1,
        block: NovaBlockBuilder.() -> Unit ={}
    ): NovaBlock = block("${name}_crop") {
        block()
        behaviors(CROP, Ageable(maxAge, buddingAge), cropBehavior, BlockSounds(SoundGroup.CROP))
        stateProperties(ScopedBlockStateProperties.AGE, ScopedBlockStateProperties.BUDDING_AGE, ScopedBlockStateProperties.MAX_AGE)
        stateBacked(BackingStateCategory.TRIPWIRE_UNATTACHED, BackingStateCategory.TRIPWIRE_ATTACHED) {
            val age = getPropertyValueOrThrow(BlockStateProperties.AGE)
            var id = if (age > maxAge) maxAge else age
            if (id > buddingAge) {
                id -= (buddingAge + 1)
                getModel("block/${name}_stage$id")
            }
            else {
                getModel("block/budding_${name}_stage$id")
            }
        }
    }
    
    private fun feastBlock(
        name: String,
        behaviorHolder: BlockBehaviorHolder,
        hasLeftover: Boolean,
        maxServing: Int = 4,
        block: NovaBlockBuilder.() -> Unit ={}
    ): NovaBlock = block(name) {
        block()
        behaviors(behaviorHolder, BlockSounds(SoundGroup.WOOL), Breakable(0.5, emptySet(), null, false, Material.WHEAT))
        stateProperties(ScopedBlockStateProperties.SERVINGS, FACING_HORIZONTAL)
        entityBacked {
            val servings = (maxServing - minOf(getPropertyValueOrThrow(BlockStateProperties.SERVINGS), maxServing))
            val suffix = if (servings <= (maxServing - 1)) "_stage$servings" else if (hasLeftover) "_leftover" else "_stage3"
            getModel("block/${name}$suffix").rotated()
        }
    }
    
   private fun pieBlock(
        name: String,
        behaviorHolder: BlockBehaviorHolder,
        block: NovaBlockBuilder.() -> Unit ={}
    ): NovaBlock = block(name) {
        block()
       behaviors(behaviorHolder, BlockSounds(SoundGroup.WOOL), Breakable(0.5, emptySet(), null, false, Material.CAKE))
        stateProperties(ScopedBlockStateProperties.BITES, FACING_HORIZONTAL)
       entityBacked {
            val bites = getPropertyValueOrThrow(BlockStateProperties.BITES)
            val suffix = if (bites > 0) "_slice$bites" else ""
            getModel("block/${name}$suffix").rotated()
        }
    }
    
}