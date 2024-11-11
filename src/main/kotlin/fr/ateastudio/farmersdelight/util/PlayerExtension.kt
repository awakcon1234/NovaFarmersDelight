package fr.ateastudio.farmersdelight.util

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun Player.safeGive(item: ItemStack) {
    val inventory = this.inventory
    
    // Check if there is room in the player's inventory
    val leftover = inventory.addItem(item)
    
    // If the inventory was full and there is leftover (unadded items), drop them at the player's feet
    if (leftover.isNotEmpty()) {
        for ((_, droppedItem) in leftover) {
            this.world.dropItemNaturally(this.location, droppedItem)
        }
    }
}