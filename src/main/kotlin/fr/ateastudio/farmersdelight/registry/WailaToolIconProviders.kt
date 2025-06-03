package fr.ateastudio.farmersdelight.registry

import fr.ateastudio.farmersdelight.NovaFarmersDelight.registerWailaToolIconProvider
import fr.ateastudio.farmersdelight.WailaToolIconProvider
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Suppress("unused")
@Init(stage = InitStage.PRE_WORLD)
object WailaToolIconProviders {
    val ACTIProvider = registerWailaToolIconProvider("farmersdelight", WailaToolIconProvider)
}
