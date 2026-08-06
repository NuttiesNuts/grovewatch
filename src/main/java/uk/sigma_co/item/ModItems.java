package uk.sigma_co.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import uk.sigma_co.Grovewatch;
import uk.sigma_co.block.ModBlocks;
import uk.sigma_co.item.custom.MossySwordItem;

import java.util.ArrayList;
import java.util.function.Function;

public class ModItems {

    public static ArrayList<Item> items = new ArrayList<>();

    public static final Item MOSSY_SWORD = register(
            ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, "mossy_sword")),
            MossySwordItem::new,
            new Item.Properties().sword(ToolMaterial.STONE, 6f, -3f)
    );

    public static final Item COFFEE_BERRY = registerItem("coffee_berry",
            properties -> new BlockItem(ModBlocks.COFFEE_BUSH,
                    properties.useItemDescriptionPrefix().food(new FoodProperties(1, 0.5f, true))));

    public static final Item COFFEE_BEAN = register(
            ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, "coffee_bean")),
            Item::new,
            new Item.Properties().food(new FoodProperties(1, 0.5f, true))
    );
    public static final Item COFFEE_BEAN_ROASTED = register(
            ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, "coffee_bean_roasted")),
            Item::new,
            new Item.Properties().food(new FoodProperties(1, 0.5f, true))
    );

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        Item item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        items.add(item);
        return item;
    }

    // broski, idk. Kaupenjoe is different
    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        Item item = function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, name))));

        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, name), item);

        items.add(item);
        return item;
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register((creativeTab) -> creativeTab.insertAfter(Items.NETHERITE_SWORD, MOSSY_SWORD));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((creativeTab) -> creativeTab.insertBefore(Items.GLOW_BERRIES, COFFEE_BERRY));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeTab) -> {
                    creativeTab.insertAfter(Items.GLOW_BERRIES, COFFEE_BERRY);
                    creativeTab.insertAfter(COFFEE_BERRY, COFFEE_BEAN);
                    creativeTab.insertAfter(COFFEE_BEAN, COFFEE_BEAN_ROASTED);
                });
    }
}
