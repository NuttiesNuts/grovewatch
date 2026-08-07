package uk.sigma_co.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import uk.sigma_co.item.ModItems;
import uk.sigma_co.mob_effect.ModEffects;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModEnglishLangProvider extends FabricLanguageProvider {
    protected ModEnglishLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        ModItems.items.forEach(item -> {
            String key = BuiltInRegistries.ITEM.getKey(item).getPath();
            var splitKey = key.split("_");
            String formatted = Arrays.stream(splitKey).map(s -> s.substring(0, 1).toUpperCase(Locale.ROOT).concat(s.substring(1).toLowerCase(Locale.ROOT))).collect(Collectors.joining(" "));

            translationBuilder.add(item, formatted);
        });

        translationBuilder.add(ModItems.GROVEWATCH_CREATIVE_TAB_KEY, "Grovewatch");
        translationBuilder.add(ModEffects.CAFFEINE.value(), "Caffeinated");
    }
}
