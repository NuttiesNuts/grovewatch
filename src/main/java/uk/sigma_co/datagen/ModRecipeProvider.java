package uk.sigma_co.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import uk.sigma_co.item.ModItems;


public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);


            shapeless(RecipeCategory.FOOD, ModItems.COFFEE_BEAN)
                        .requires(ModItems.COFFEE_BERRY)
                        .unlockedBy(getHasName(ModItems.COFFEE_BERRY), has(ModItems.COFFEE_BERRY))
                        .save(output);

            simpleCookingRecipe("thin_air", SmeltingRecipe::new, 100, ModItems.COFFEE_BEAN, ModItems.COFFEE_BEAN_ROASTED, 0.5f);
            }
        };
    }

    @Override
    public String getName() {
        return "Mod Recipe Provider";
    }
}
