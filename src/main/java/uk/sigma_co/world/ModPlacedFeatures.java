package uk.sigma_co.world;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import uk.sigma_co.Grovewatch;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> COFFEE_BUSH_PLACED_KEY = registerKey("coffee_bush_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, COFFEE_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COFFE_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

//        register(context, COFFEE_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COFFE_BUSH_KEY),
//                List.of(
//                        RarityFilter.onAverageOnceEvery(32),
//                        CountPlacement.of(67),
//                        InSquarePlacement.spread(),
//                        PlacementUtils.HEIGHTMAP,
//                        RandomOffsetPlacement.ofTriangle(6, 3),
//                        BiomeFilter.biome()
//                ));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
