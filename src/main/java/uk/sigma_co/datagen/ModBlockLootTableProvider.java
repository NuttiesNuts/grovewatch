package uk.sigma_co.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import uk.sigma_co.block.ModBlocks;
import uk.sigma_co.block.custom.CoffeeBushBlock;
import uk.sigma_co.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    protected ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        var enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.COFFEE_BUSH, block -> this.applyExplosionDecay(block,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.COFFEE_BUSH)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CoffeeBushBlock.AGE, 3)))
                        .add(LootItem.lootTableItem(ModItems.COFFEE_BERRY))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.COFFEE_BUSH)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CoffeeBushBlock.AGE, 2)))
                        .add(LootItem.lootTableItem(ModItems.COFFEE_BERRY))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))))
        );
    }
}
