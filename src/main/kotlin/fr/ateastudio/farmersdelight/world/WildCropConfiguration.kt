package fr.ateastudio.farmersdelight.world

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.Holder
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import java.util.*

data class WildCropConfiguration(
    val tries: Int,
    val xzSpread: Int,
    val ySpread: Int,
    val primaryFeature: Holder<PlacedFeature>,
    val secondaryFeature: Holder<PlacedFeature>,
    val floorFeature: Optional<Holder<PlacedFeature>>
) : FeatureConfiguration {
    
    companion object {
        
        @JvmField
        val CODEC: Codec<WildCropConfiguration> = RecordCodecBuilder.create { instance ->
            instance.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(64).forGetter(WildCropConfiguration::tries),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("xz_spread").orElse(4).forGetter(WildCropConfiguration::xzSpread),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("y_spread").orElse(3).forGetter(WildCropConfiguration::ySpread),
                PlacedFeature.CODEC.fieldOf("primary_feature").forGetter(WildCropConfiguration::primaryFeature),
                PlacedFeature.CODEC.fieldOf("secondary_feature").forGetter(WildCropConfiguration::secondaryFeature),
                PlacedFeature.CODEC.optionalFieldOf("floor_feature").forGetter(WildCropConfiguration::floorFeature)
            ).apply(instance, ::WildCropConfiguration)
        }
        
    }
    
}