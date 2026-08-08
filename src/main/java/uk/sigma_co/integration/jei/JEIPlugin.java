package uk.sigma_co.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import uk.sigma_co.Grovewatch;
import uk.sigma_co.item.ModItems;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack(ModItems.COFFEE_BERRY), VanillaTypes.ITEM_STACK, Component.translatable("jei." + Grovewatch.MOD_ID + ".info.coffee_berry"));
        registration.addIngredientInfo(new ItemStack(ModItems.COFFEE), VanillaTypes.ITEM_STACK, Component.translatable("jei." + Grovewatch.MOD_ID + ".info.coffee"));
    }
}
