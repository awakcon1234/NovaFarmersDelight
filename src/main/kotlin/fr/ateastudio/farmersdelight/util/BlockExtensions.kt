package fr.ateastudio.farmersdelight.util

import org.bukkit.Material
import org.bukkit.block.Block


fun Block.isTag(tag: List<Material>): Boolean {
    return tag.contains(this.type)
}