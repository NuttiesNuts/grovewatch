package uk.sigma_co.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import uk.sigma_co.block.ModBlocks;
import uk.sigma_co.block.custom.CoffeeBushBlock;
import uk.sigma_co.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createCrossBlock(ModBlocks.COFFEE_BUSH, BlockModelGenerators.PlantType.NOT_TINTED,
                CoffeeBushBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.COFFEE_BEAN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.COFFEE_BEAN_ROASTED, ModelTemplates.FLAT_ITEM);
    }
}
