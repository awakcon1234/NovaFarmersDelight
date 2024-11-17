package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.block.CookingPotSupport
import fr.ateastudio.farmersdelight.block.ScopedBlockStateProperties
import fr.ateastudio.farmersdelight.block.behavior.Ageable
import fr.ateastudio.farmersdelight.block.behavior.CabbageCrop
import fr.ateastudio.farmersdelight.block.behavior.CookingPotBehavior
import fr.ateastudio.farmersdelight.block.behavior.CuttingBoard
import fr.ateastudio.farmersdelight.block.behavior.MuddyFarmland
import fr.ateastudio.farmersdelight.block.behavior.OnionCrop
import fr.ateastudio.farmersdelight.block.behavior.RiceCrop
import fr.ateastudio.farmersdelight.block.behavior.TomatoCrop
import fr.ateastudio.farmersdelight.tileentity.CookingPot
import org.bukkit.Material
import xyz.xenondevs.nova.addon.registry.BlockRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.resources.layout.block.BackingStateCategory
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
import xyz.xenondevs.nova.world.block.state.property.DefaultScopedBlockStateProperties.FACING_HORIZONTAL
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories
import xyz.xenondevs.nova.world.item.tool.VanillaToolTiers

@Init(stage = InitStage.PRE_PACK)
object Blocks : BlockRegistry by NovaFarmersDelight.registry {
    private val CROP = Breakable(0.0, VanillaToolCategories.AXE, VanillaToolTiers.WOOD, false, Material.TALL_GRASS)
    private val MUD = Breakable(0.5, VanillaToolCategories.SHOVEL, VanillaToolTiers.WOOD, false, Material.MUD)
    private val BAG = Breakable(0.8, VanillaToolCategories.SHEARS, VanillaToolTiers.WOOD, false, Material.WHITE_WOOL)
    private val BALE = Breakable(0.8, VanillaToolCategories.HOE, VanillaToolTiers.WOOD, false, Material.HAY_BLOCK)
    private val CRATE = Breakable(2.0, VanillaToolCategories.AXE, VanillaToolTiers.WOOD, false, Material.OAK_PLANKS)
    private val LIGHT_METAL = Breakable(0.5, VanillaToolCategories.PICKAXE, VanillaToolTiers.WOOD, false, Material.CAULDRON)
    private val SPRUCE_PLANK = Breakable(0.5, VanillaToolCategories.AXE, VanillaToolTiers.WOOD, false, Material.SPRUCE_PLANKS)
    
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
        models {
            selectModel {
                val support = getPropertyValueOrThrow(BlockStateProperties.SUPPORT)
                when (support) {
                    CookingPotSupport.HANDLE -> getModel("block/cooking_pot_handle").rotated()
                    CookingPotSupport.TRAY -> getModel("block/cooking_pot_tray").rotated()
                    else -> getModel("block/cooking_pot").rotated()
                }
            }
        }
    }
    val CUTTING_BOARD = block("cutting_board") {
        behaviors(
            SPRUCE_PLANK,
            BlockSounds(SoundGroup.WOOD),
            CuttingBoard
        )
        stateProperties(FACING_HORIZONTAL)
        models {
            stateBacked(BackingStateCategory.TRIPWIRE_ATTACHED)
            selectModel { defaultModel.rotated() }
        }
    }
    
    val CARROT_CRATE = nonInteractiveBlock("carrot_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val POTATO_CRATE = nonInteractiveBlock("potato_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val BEETROOT_CRATE = nonInteractiveBlock("beetroot_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val CABBAGE_CRATE = nonInteractiveBlock("cabbage_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val TOMATO_CRATE = nonInteractiveBlock("tomato_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val ONION_CRATE = nonInteractiveBlock("onion_crate") { behaviors(CRATE, BlockDrops, BlockSounds(SoundGroup.WOOD)) }
    val RICE_BALE = nonInteractiveBlock("rice_bale") { behaviors(BALE, BlockDrops, BlockSounds(SoundGroup.GRASS)) }
    val RICE_BAG = nonInteractiveBlock("rice_bag") { behaviors(BAG, BlockDrops, BlockSounds(SoundGroup.WOOL)) }
    val STRAW_BALE = nonInteractiveBlock("straw_bale") { behaviors(BALE, BlockDrops, BlockSounds(SoundGroup.GRASS)) }
    
    val TATAMI = nonInteractiveBlock("tatami") { behaviors(BAG, BlockDrops, BlockSounds(SoundGroup.WOOL)) }
    
    val MUDDY_FARMLAND = block("muddy_farmland") {
        behaviors(MUD, BlockSounds(SoundGroup.MUD), MuddyFarmland)
        models {
            stateBacked(BackingStateCategory.LEAVES, BackingStateCategory.NOTE_BLOCK)
        }
    }
    
    val SANDY_SHRUB = plantBlock("sandy_shrub") { behaviors(CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_CABBAGES = plantBlock("wild_cabbages") { behaviors(CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_ONIONS = plantBlock("wild_onions") { behaviors(CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_TOMATOES = plantBlock("wild_tomatoes") { behaviors(CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_CARROTS = plantBlock("wild_carrots") { behaviors(CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_POTATOES = plantBlock("wild_potatoes") { behaviors(CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_BEETROOTS = plantBlock("wild_beetroots") { behaviors(CROP, BlockSounds(SoundGroup.GRASS))}
    val WILD_RICE = plantBlock("wild_rice") { behaviors(CROP, BlockSounds(SoundGroup.GRASS))}
    
    val BROWN_MUSHROOM_COLONY = cropBlock("brown_mushroom_colony", TomatoCrop, 3)
    val RED_MUSHROOM_COLONY = cropBlock("red_mushroom_colony", TomatoCrop, 3)
    val CABBAGES_CROP = cropBlock("cabbages", CabbageCrop, 7)
    val TOMATOES_CROP = cropBlock("tomatoes", TomatoCrop, 7,3)
    val ONION_CROP = cropBlock("onions", OnionCrop, 3)
    val RICE_CROP = cropBlock("rice", RiceCrop, 7,3)
    
    
    
    private fun nonInteractiveBlock(
        name: String,
        block: NovaBlockBuilder.() -> Unit
    ): NovaBlock = block(name) {
        block()
        models {
            stateBacked(BackingStateCategory.NOTE_BLOCK, BackingStateCategory.MUSHROOM_BLOCK)
        }
    }
    
    private fun plantBlock(
        name: String,
        block: NovaBlockBuilder.() -> Unit ={}
    ): NovaBlock = block(name) {
        block()
        stateProperties(ScopedBlockStateProperties.AGE, ScopedBlockStateProperties.BUDDING_AGE, ScopedBlockStateProperties.MAX_AGE)
        models {
            stateBacked(BackingStateCategory.TRIPWIRE_UNATTACHED, BackingStateCategory.TRIPWIRE_ATTACHED)
        }
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
        models {
            stateBacked(BackingStateCategory.TRIPWIRE_UNATTACHED, BackingStateCategory.TRIPWIRE_ATTACHED)
            selectModel {
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
    }
    
}