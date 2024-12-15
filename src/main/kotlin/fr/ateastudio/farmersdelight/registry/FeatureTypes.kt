package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight
import xyz.xenondevs.nova.addon.registry.worldgen.FeatureRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.world.generation.ExperimentalWorldGen

@OptIn(ExperimentalWorldGen::class)
@Init(stage = InitStage.POST_PACK_PRE_WORLD)
object FeatureTypes : FeatureRegistry by NovaFarmersDelight.registry {
    
    //TODO
    //val WILD_CROP_FEATURE = registerFeatureType("wild_crop", WildCropFeature)
    
}