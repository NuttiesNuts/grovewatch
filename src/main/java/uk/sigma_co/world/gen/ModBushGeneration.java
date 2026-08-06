package uk.sigma_co.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import uk.sigma_co.world.ModPlacedFeatures;

public class ModBushGeneration {
    public static void generateBushes() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.SPARSE_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.COFFEE_BUSH_PLACED_KEY);
    }
}
