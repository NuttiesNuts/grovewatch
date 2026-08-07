package uk.sigma_co.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import uk.sigma_co.item.ModItems;
import uk.sigma_co.tag.CompatibilityTags;
import uk.sigma_co.util.Util;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(ConventionalItemTags.BERRY_FOODS).add(Util.keyOfItem(ModItems.COFFEE_BERRY));
        builder(CompatibilityTags.CREATE_CA_PLANT_FOODS).add(Util.keyOfItem(ModItems.COFFEE_BERRY));

        builder(ConventionalItemTags.DRINK_CONTAINING_BOTTLE).add(Util.keyOfItem(ModItems.COFFEE));
        builder(ConventionalItemTags.DRINKS).add(Util.keyOfItem(ModItems.COFFEE));
        builder(CompatibilityTags.DRINKS).add(Util.keyOfItem(ModItems.COFFEE));

        builder(ItemTags.SWORDS).add(Util.keyOfItem(ModItems.MOSSY_SWORD));
        builder(ItemTags.DURABILITY_ENCHANTABLE).add(Util.keyOfItem(ModItems.MOSSY_SWORD));
        builder(ItemTags.WEAPON_ENCHANTABLE).add(Util.keyOfItem(ModItems.MOSSY_SWORD));
        builder(ItemTags.SHARP_WEAPON_ENCHANTABLE).add(Util.keyOfItem(ModItems.MOSSY_SWORD));
        builder(ItemTags.FIRE_ASPECT_ENCHANTABLE).add(Util.keyOfItem(ModItems.MOSSY_SWORD));
        builder(ItemTags.MELEE_WEAPON_ENCHANTABLE).add(Util.keyOfItem(ModItems.MOSSY_SWORD));
    }
}
