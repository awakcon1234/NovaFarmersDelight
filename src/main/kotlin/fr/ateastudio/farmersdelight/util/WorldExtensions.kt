package fr.ateastudio.farmersdelight.util

import org.bukkit.World
import kotlin.random.Random

fun World.random() : Random {
    return Random(this.seed)
}