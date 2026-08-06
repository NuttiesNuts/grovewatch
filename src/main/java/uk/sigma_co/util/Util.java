package uk.sigma_co.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import uk.sigma_co.Grovewatch;

public class Util {

    public static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, name));
    }

    public static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, name));
    }

    public static ResourceKey<Item> keyOfItem(Item item) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, BuiltInRegistries.ITEM.getKey(item).getPath()));
    }
}
