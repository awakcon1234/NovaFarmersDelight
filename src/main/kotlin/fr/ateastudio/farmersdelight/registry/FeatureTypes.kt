@file:Suppress("unused")

package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import fr.ateastudio.farmersdelight.world.feature.WildCropFeature
import xyz.xenondevs.nova.addon.registry.worldgen.FeatureRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.world.generation.ExperimentalWorldGen

@OptIn(ExperimentalWorldGen::class)
@Init(stage = InitStage.POST_PACK_PRE_WORLD)
object FeatureTypes : FeatureRegistry by NovaFarmersDelight.registry {
    
    val WILD_CROP_FEATURE = feature("wild_crop", WildCropFeature)
    
}