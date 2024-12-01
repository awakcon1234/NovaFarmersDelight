package fr.ateastudio.farmersdelight.block

import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.registry.Tags
import fr.ateastudio.farmersdelight.util.isTag
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.novaBlock
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.player.WrappedPlayerInteractEvent
import xyz.xenondevs.nova.world.pos

@Init(stage = InitStage.POST_WORLD)
object CookingPotListener : Listener {
    
    private val clickMap: MutableMap<Location, Long> = mutableMapOf()
    private const val CLICK_COOLDOWN_TIME: Long = 100
    
    private fun canClick(location: Location) : Boolean {
        val currentTime = System.currentTimeMillis()
        
        // Remove expired entries (older than 1 second)
        removeExpiredEntries(currentTime)
        
        // Check if the location is in the map and if it is still within the cooldown period
        return if (clickMap.containsKey(location)) {
            val lastClickTime = clickMap[location]!!
            if (currentTime - lastClickTime < CLICK_COOLDOWN_TIME) {
                false
            } else {
                clickMap.remove(location)
                true
            }
        } else {
            clickMap[location] = currentTime
            true
        }
    }
    
    private fun removeExpiredEntries(currentTime: Long) {
        val iterator = clickMap.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (currentTime - entry.value > CLICK_COOLDOWN_TIME) {
                iterator.remove() // Remove expired entry
            }
        }
    }
    
    @EventHandler
    fun onInteraction(wrappedEvent: WrappedPlayerInteractEvent) {
        val event = wrappedEvent.event
        val validAction = event.player.isSneaking &&
            event.action.isRightClick &&
            event.clickedBlock?.novaBlock == Blocks.COOKING_POT
        if (!validAction) {
            return
        }
        val pos = event.clickedBlock!!.pos
        if (!canClick((pos.location))) return
        val state = pos.novaBlockState
        if (state != null) {
            val isHandle = state[BlockStateProperties.SUPPORT] == CookingPotSupport.HANDLE
            val default = updateSupport(pos)
            if (isHandle) {
                if (default != CookingPotSupport.HANDLE) {
                    BlockUtils.updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, default))
                }
                else {
                    BlockUtils.updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, updateSupport(pos, BlockFace.UP)))
                }
            }
            else {
                BlockUtils.updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, CookingPotSupport.HANDLE))
            }
            pos.world.playSound(pos.location, Sound.BLOCK_LANTERN_PLACE, SoundCategory.BLOCKS, 0.8f, 1f)
        }
    }
    
    private fun updateSupport(pos: BlockPos, blockFace: BlockFace? = null) : CookingPotSupport{
        val isSuspended = !pos.add(0,1,0).block.isEmpty
        val isTray = pos.below.block.isTag(Tags.TRAY_HEAT_SOURCE)
        
        if (isSuspended && isTray && blockFace != null) {
            return if (blockFace == BlockFace.DOWN) {
                CookingPotSupport.HANDLE
            } else {
                CookingPotSupport.TRAY
            }
        }
        else if (isSuspended) {
            return CookingPotSupport.HANDLE
        }
        else if (isTray) {
            return CookingPotSupport.TRAY
        }
        else {
            return CookingPotSupport.NONE
        }
    }
}