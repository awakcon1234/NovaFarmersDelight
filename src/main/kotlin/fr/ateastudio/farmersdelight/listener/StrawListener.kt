package fr.ateastudio.farmersdelight.listener

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.registry.Items
import fr.ateastudio.farmersdelight.util.isKnife
import org.bukkit.Material
import org.bukkit.block.data.Ageable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.util.dropItem
import xyz.xenondevs.nova.util.novaBlock
import xyz.xenondevs.nova.util.registerEvents
import kotlin.random.Random

@Init(stage = InitStage.POST_WORLD)
object StrawListener : Listener {
    
    init {
        this.registerEvents()
    }
    
    @EventHandler
    fun onPlayerBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        val block = event.block
        if (event.isCancelled || !player.inventory.itemInMainHand.isKnife()) return
        
        var chance = 0.0
        when (block.type) {
            Material.GRASS_BLOCK, Material.TALL_GRASS -> {
                chance = 0.2
            }
            Material.WHEAT -> {
                val data = block.blockData
                if (data is Ageable) {
                    if (data.age != data.maximumAge) {
                        return // Wheat is not fully grown
                    }
                    chance = 1.0
                }
            }
            else -> {
                chance = when (block.novaBlock) {
                    Blocks.SANDY_SHRUB -> 0.3
                    Blocks.RICE_CROP -> {
                        val cropAge = block.novaBlock?.blockStates
                            ?.firstOrNull { it.properties.contains(BlockStateProperties.AGE) }
                            ?.get(BlockStateProperties.AGE) ?: return
                        val cropMaxAge = block.novaBlock?.blockStates
                            ?.firstOrNull { it.properties.contains(BlockStateProperties.MAX_AGE) }
                            ?.get(BlockStateProperties.MAX_AGE) ?: return
                        if (cropAge < cropMaxAge) return
                        1.0
                    }
                    else -> return // if it's not a recognized novaBlock, stop here
                }
            }
        }
        
        if (Random.nextDouble() <= chance) {
            val straw = Items.STRAW.createItemStack()
            block.location.dropItem(straw)
        }
        
    }
    
}