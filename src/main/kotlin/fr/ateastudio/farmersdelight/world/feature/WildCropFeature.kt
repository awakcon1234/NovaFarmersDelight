package fr.ateastudio.farmersdelight.world.feature

import fr.ateastudio.farmersdelight.util.Logger
import fr.ateastudio.farmersdelight.world.WildCropConfiguration
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import xyz.xenondevs.nova.world.generation.ExperimentalWorldGen
import xyz.xenondevs.nova.world.generation.FeatureType
import kotlin.jvm.optionals.getOrNull

//SOURCE: https://github.com/vectorwing/FarmersDelight/blob/1.21/src/main/java/vectorwing/farmersdelight/common/world/feature/WildCropFeature.java
//TODO Remove logger calls
@OptIn(ExperimentalWorldGen::class)
object WildCropFeature : FeatureType<WildCropConfiguration>(WildCropConfiguration.CODEC) {
    
    override fun place(ctx: FeaturePlaceContext<WildCropConfiguration>): Boolean {
        val config: WildCropConfiguration = ctx.config()
        val origin: BlockPos = ctx.origin()
        val level: WorldGenLevel = ctx.level()
        val random: RandomSource = ctx.random()
        
        Logger.info("Placing wild crop feature at $origin")
        var i = 0
        val tries = config.tries
        val xzSpread = config.xzSpread + 1
        val ySpread = config.ySpread + 1
        
        val mutablePos = MutableBlockPos()
        
        val floorFeature = config.floorFeature.getOrNull()
        if (floorFeature != null) {
            for (j in 0 until tries) {
                mutablePos.setWithOffset(origin, random.nextInt(xzSpread) - random.nextInt(xzSpread), random.nextInt(ySpread) - random.nextInt(ySpread), random.nextInt(xzSpread) - random.nextInt(xzSpread))
                if (floorFeature.value().place(level, ctx.chunkGenerator(), random, mutablePos)) {
                    ++i
                    Logger.info("Placed floor feature at $mutablePos")
                }
            }
        }
        
        for (k in 0 until tries) {
            val shorterXZ = xzSpread - 2
            mutablePos.setWithOffset(origin, random.nextInt(shorterXZ) - random.nextInt(shorterXZ), random.nextInt(ySpread) - random.nextInt(ySpread), random.nextInt(shorterXZ) - random.nextInt(shorterXZ))
            if (config.primaryFeature.value().place(level, ctx.chunkGenerator(), random, mutablePos)) {
                ++i
                Logger.info("Placed primary feature at $mutablePos")
            }
        }
        
        for (l in 0 until tries) {
            mutablePos.setWithOffset(origin, random.nextInt(xzSpread) - random.nextInt(xzSpread), random.nextInt(ySpread) - random.nextInt(ySpread), random.nextInt(xzSpread) - random.nextInt(xzSpread))
            if (config.secondaryFeature.value().place(level, ctx.chunkGenerator(), random, mutablePos)) {
                ++i
                Logger.info("Placed secondary feature at $mutablePos")
            }
        }
        
        return i > 0
    }
}