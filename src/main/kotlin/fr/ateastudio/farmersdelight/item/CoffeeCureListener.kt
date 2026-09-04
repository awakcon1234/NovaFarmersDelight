package fr.ateastudio.farmersdelight.item

import fr.ateastudio.farmersdelight.registry.Items
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectTypeCategory
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitFun
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.util.item.novaItem
import xyz.xenondevs.nova.util.registerEvents

/**
 * Rustic Delight's milk coffee clears one effect milk would clear, and chocolate coffee clears one
 * harmful one. Nova's Consumable can only add effects, so the clearing happens here, after the
 * drink has been swallowed. Milk cures nearly every vanilla effect, so any active effect will do.
 */
@Init(stage = InitStage.POST_WORLD)
object CoffeeCureListener : Listener {
    
    @InitFun
    fun init() {
        registerEvents()
    }
    
    @EventHandler
    fun handleConsume(event: PlayerItemConsumeEvent) {
        val item = event.item.novaItem ?: return
        val player = event.player
        
        val candidates: List<PotionEffect> = when (item) {
            Items.MILK_COFFEE -> player.activePotionEffects.toList()
            Items.CHOCOLATE_COFFEE -> player.activePotionEffects.filter { it.type.category == PotionEffectTypeCategory.HARMFUL }
            else -> return
        }
        
        if (candidates.isEmpty()) {
            return
        }
        
        player.removePotionEffect(candidates.random().type)
    }
    
}
