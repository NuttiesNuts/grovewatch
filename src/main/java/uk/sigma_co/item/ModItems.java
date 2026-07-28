package uk.sigma_co.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import uk.sigma_co.item.custom.MossySwordItem;

import java.util.function.Function;

public class ModItems {

    public static final Item MOSSY_SWORD = register(
            ModItemIds.MOSSY_SWORD,
            MossySwordItem::new,
            new Item.Properties().sword(ToolMaterial.STONE, 6f, -3f)
    );

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        Item item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register((creativeTab) -> creativeTab.insertAfter(Items.NETHERITE_SWORD ,ModItems.MOSSY_SWORD));
    }
}
