package uk.sigma_co;

import net.bettercombat.api.fx.ItemConditions;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.sigma_co.block.ModBlocks;
import uk.sigma_co.component.ModComponents;
import uk.sigma_co.item.ModItems;
import uk.sigma_co.mob_effect.ModEffects;
import uk.sigma_co.world.gen.ModWorldGeneration;

public class Grovewatch implements ModInitializer {
	public static final String MOD_ID = "grovewatch";
	public static final String FARMERS_DELIGHT_ID = "farmersdelight";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID.replace("g", "G"));

	@Override
	public void onInitialize() {
		LOGGER.info("skibidi skibidi 67!");

        ModItems.initialize();
        ModBlocks.initialize();
        ModComponents.initialize();
        ModEffects.initialize();

        ModWorldGeneration.generateModWorldGen();

        if  (FabricLoader.getInstance().isModLoaded("bettercombat")){
            // Better Combat trail condition
            ItemConditions.register("isOnGrass", itemStack -> itemStack.get(ModComponents.ON_GRASS_COMPONENT));
        }
    }

    /*
        TODO - Mocha Jungle Coffee variant (noClip foliage)
        TODO - Bag of (roasted & normal) Coffee Beans block
        TODO - Coffee liquid
        TODO - Coffee drink Create mixing recipe
     */
}
