package fr.ateastudio.farmersdelight.block

import net.kyori.adventure.key.Key
import xyz.xenondevs.nova.world.block.state.property.impl.BooleanProperty
import xyz.xenondevs.nova.world.block.state.property.impl.EnumProperty
import xyz.xenondevs.nova.world.block.state.property.impl.IntProperty

object BlockStateProperties {
    val AGE = IntProperty(Key.key("farmersdelight","age"))
    val MAX_AGE = IntProperty(Key.key("farmersdelight", "max_age"))
    val BUDDING_AGE = IntProperty(Key.key("farmersdelight", "budding_age"))
    val HEATED = BooleanProperty(Key.key("farmersdelight", "heated"))
    val SUPPORT = EnumProperty<CookingPotSupport>(Key.key("farmersdelight", "support"))
    val PAIRED = BooleanProperty(Key.key("farmersdelight", "paired"))
    val BITES = IntProperty(Key.key("farmersdelight", "bites"))
    val SERVINGS = IntProperty(Key.key("farmersdelight", "servings"))
}

object ScopedBlockStateProperties {
    val AGE = BlockStateProperties.AGE.scope(0..7)
    val MAX_AGE = BlockStateProperties.MAX_AGE.scope(0..7)
    val BUDDING_AGE = BlockStateProperties.BUDDING_AGE.scope(-1..7)
    val HEATED = BlockStateProperties.HEATED.scope(false, true)
    val SUPPORT = BlockStateProperties.SUPPORT.scope(CookingPotSupport.NONE, CookingPotSupport.HANDLE, CookingPotSupport.TRAY)
    val PAIRED = BlockStateProperties.PAIRED.scope(false, true)
    val BITES = BlockStateProperties.BITES.scope(0..3)
    val SERVINGS = BlockStateProperties.SERVINGS.scope(0..8)
}