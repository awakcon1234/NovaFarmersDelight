package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.util.safeGive
import org.bukkit.Sound
import org.bukkit.block.BlockFace
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.joml.Matrix4f
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockBreak
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockInteract
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.util.item.novaItem
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.item.tool.ToolCategory


object CuttingBoard : BlockBehavior {
    
    override fun handleInteract(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockInteract>): Boolean {
        val player = ctx[DefaultContextParamTypes.SOURCE_PLAYER]
        val facing = state[DefaultBlockStateProperties.FACING]
        
        val displayPos = pos.location.center().add(0.0,0.05,0.0)
        val world = pos.world
        val itemInHand = ctx[DefaultContextParamTypes.INTERACTION_ITEM_STACK] ?: ItemStack.empty()
        
        val existingDisplay = world.getNearbyEntities(displayPos, 0.5, 0.5, 0.5)
            .filterIsInstance<ItemDisplay>()
            .firstOrNull()
        
        // If an ItemDisplay already exists, return without spawning a new one
        if (existingDisplay != null) {
            if (itemInHand.isEmpty) {
                retrieveItem(pos, player)
                return true
            }
            else if (itemInHand.novaItem != null && ToolCategory.ofItem(itemInHand).any {it.id.path.contains("knife")}) {
                // TODO Recipe logic
            }
            return false
        }
        else {
            if (!itemInHand.isEmpty) {
                placeItem(itemInHand.asOne(), pos, facing)
                itemInHand.subtract()
                return true
            }
            return false
        }
        
    }
    
    override fun handleBreak(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockBreak>) {
        retrieveItem(pos)
    }
    
    private fun placeItem(item: ItemStack, pos: BlockPos, facing: BlockFace?) {
        val displayPos = pos.location.center().add(0.0,0.05,0.0)
        val world = pos.world
        val transformation = Matrix4f(0.6f,0f,0f,0.5f,0f,-0f,-0.6f,0.8f,0f,0.6f,-0f,0.5f,0f,0f,0f,1f)
        
        // Additional rotation around Y-axis based on block face direction
        // https://misode.github.io/transformation/
        val facingRotation = when (facing) {
            BlockFace.NORTH -> transformation.rotateLocalY(Math.toRadians(180.0).toFloat())
            BlockFace.SOUTH -> transformation
            BlockFace.WEST -> transformation.rotateLocalY(Math.toRadians(-90.0).toFloat())
            BlockFace.EAST -> transformation.rotateLocalY(Math.toRadians(90.0).toFloat())
            else -> transformation
        }
        
        // Spawn a new ItemDisplay entity
        world.spawn(displayPos, ItemDisplay::class.java) { entity: ItemDisplay ->
            // customize the entity!
            entity.isPersistent = true
            entity.setItemStack(item)
            entity.itemDisplayTransform = ItemDisplay.ItemDisplayTransform.FIXED
            entity.setTransformationMatrix(facingRotation)
        }
        pos.playSound(Sound.BLOCK_WOOD_PLACE, 0.8f, 1f)
    }
    
    private fun retrieveItem(pos: BlockPos, player: Player? = null) {
        val world = pos.world
        val displayPos = pos.location.center().add(0.0,0.05,0.0)
        // Check for an ItemDisplay above the CuttingBoard and remove it
        val itemDisplay = world.getNearbyEntities(displayPos, 0.5, 0.5, 0.5)
            .filterIsInstance<ItemDisplay>()
            .firstOrNull()
        if (itemDisplay != null) {
            val itemStack = itemDisplay.itemStack
            itemDisplay.remove() // Remove the item display entity
            if (player != null) {
                player.safeGive(itemStack)
                pos.playSound(Sound.BLOCK_WOOD_BREAK, 0.8f, 1f)
            }
            else {
                world.dropItem(displayPos, itemStack) // Drop the item at the block's position
            }
        }
    }
}