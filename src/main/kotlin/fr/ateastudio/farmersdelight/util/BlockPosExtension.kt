package fr.ateastudio.farmersdelight.util

import org.bukkit.block.BlockFace
import xyz.xenondevs.nova.world.BlockPos

fun BlockPos.relative(face: BlockFace): BlockPos {
    return this.add(face.modX, face.modY, face.modZ)
}