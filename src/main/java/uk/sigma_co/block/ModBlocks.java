package uk.sigma_co.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import uk.sigma_co.block.custom.CoffeeBushBlock;
import uk.sigma_co.util.Util;

import java.util.function.Function;

public class ModBlocks {

    public static final Block COFFEE_BUSH = register(
            "coffee_bush",
            CoffeeBushBlock::new,
            BlockBehaviour.Properties.of().randomTicks().noCollision().pushReaction(PushReaction.DESTROY).sound(SoundType.SWEET_BERRY_BUSH),
            false
    );

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = Util.keyOfBlock(name);
        Block block = blockFactory.apply(properties.setId(blockKey));

        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = Util.keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }


    public static void initialize() {
    }
}
