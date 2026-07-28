package uk.sigma_co.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import uk.sigma_co.Grovewatch;

public class ModItemIds {

    public static final ResourceKey<Item> MOSSY_SWORD = create("mossy_sword");

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, name));
    }
}