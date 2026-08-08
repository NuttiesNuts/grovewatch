package uk.sigma_co.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import uk.sigma_co.Grovewatch;
import uk.sigma_co.item.ModItems;
import uk.sigma_co.tag.CompatibilityTags;

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

                shapeless(RecipeCategory.FOOD, ModItems.GROUND_COFFEE)
                        .requires(ModItems.COFFEE_BEAN_ROASTED)
                        .unlockedBy(getHasName(ModItems.COFFEE_BEAN_ROASTED), has(ModItems.COFFEE_BEAN_ROASTED))
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.COFFEE_FILTER)
                        .requires(Items.PAPER)
                        .requires(Items.PAPER)
                        .requires(Items.PAPER)
                        .requires(Items.PAPER)
                        .requires(ConventionalItemTags.STRINGS)
                        .requires(ConventionalItemTags.STRINGS)
                        .unlockedBy(getHasName(ModItems.GROUND_COFFEE), has(ModItems.GROUND_COFFEE))
                        .save(output);

                simplishCookingRecipe("thin_air", SmeltingRecipe::new, 100, ModItems.COFFEE_BEAN, ModItems.COFFEE_BEAN_ROASTED, 0.5f, CompatibilityTags.CREATE);

                shaped(RecipeCategory.FOOD, ModItems.COFFEE, 1)
                        .pattern("ccc")
                        .pattern("cac")
                        .pattern(" B ")
                        .define('a', ModItems.COFFEE_FILTER)
                        .define('B', Items.WATER_BUCKET)
                        .define('c', ModItems.GROUND_COFFEE)
                        .unlockedBy(getHasName(ModItems.COFFEE_BEAN_ROASTED), has(ModItems.COFFEE_BEAN_ROASTED))
                        .save(withConditions(output, ResourceConditions.not(ResourceConditions.allModsLoaded(Grovewatch.FARMERS_DELIGHT_ID))));
            }
            // whatafah
            public final  <T extends AbstractCookingRecipe> void simplishCookingRecipe(final String source, final AbstractCookingRecipe.Factory<T> factory, final int cookingTime, final ItemLike base, final ItemLike result, final float experience, String whatModMotherFkr) {
                SimpleCookingRecipeBuilder var10000 = SimpleCookingRecipeBuilder.generic(Ingredient.of(base), RecipeCategory.FOOD, CookingBookCategory.FOOD, result, experience, cookingTime, factory).unlockedBy(getHasName(base), this.has(base));
                String var10002 = getItemName(result);
                var10000.save(withConditions(output, ResourceConditions.not(ResourceConditions.allModsLoaded(whatModMotherFkr))), var10002 + "_from_" + source);
            }
        };
    }



    @Override
    public String getName() {
        return "Mod Recipe Provider";
    }
}
