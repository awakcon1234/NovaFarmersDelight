package fr.ateastudio.farmersdelight.item

import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitFun
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.util.registerEvents
import kotlin.random.Random

/**
 * Rustic Delight adds one or two calamari to a squid's drops through a loot modifier. There is
 * no loot-modifier system here, so the drop is appended when the squid dies instead.
 */
@Init(stage = InitStage.POST_WORLD)
object CalamariDropListener : Listener {
    
    @InitFun
    fun init() {
        registerEvents()
    }
    
    @EventHandler
    fun handleDeath(event: EntityDeathEvent) {
        if (event.entityType != EntityType.SQUID && event.entityType != EntityType.GLOW_SQUID) {
            return
        }
        
        event.drops.add(Items.CALAMARI.createItemStack(Random.nextInt(1, 3)))
    }
    
}
