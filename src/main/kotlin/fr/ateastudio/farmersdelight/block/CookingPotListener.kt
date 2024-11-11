package fr.ateastudio.farmersdelight.block

import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.registry.Tags
import fr.ateastudio.farmersdelight.util.LogDebug
import fr.ateastudio.farmersdelight.util.isTag
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
    
    @EventHandler
    fun onInteraction(wrappedEvent: WrappedPlayerInteractEvent) {
        LogDebug("WrappedPlayerInteractEvent")
        val event = wrappedEvent.event
        val validAction = event.player.isSneaking &&
            event.action.isRightClick &&
            event.hasBlock() &&
            event.clickedBlock?.novaBlock == Blocks.COOKING_POT
        LogDebug("validAction $validAction")
        if (!validAction) return
        val pos = event.clickedBlock!!.pos
        val state = pos.novaBlockState
        LogDebug("state $state")
        if (state != null) {
            val isHandle = state[BlockStateProperties.SUPPORT] == CookingPotSupport.HANDLE
            val default = updateSupport(pos)
            if (isHandle) {
                if (default != CookingPotSupport.HANDLE) {
                    BlockUtils.updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, updateSupport(pos)))
                }
                else {
                    BlockUtils.updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, CookingPotSupport.NONE))
                }
            }
            else {
                BlockUtils.updateBlockState(pos, state.with(BlockStateProperties.SUPPORT, CookingPotSupport.HANDLE))
            }
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