package uk.sigma_co.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import uk.sigma_co.Grovewatch;

/**
 * References to tags belonging to other mods, which Grovewatch innately supports.
 * These tags are used by other mods for their own mechanics.
 * <p>
 * Credits to Vectorwing (Farmer's Delight) for the implementation reference!
 *  * <a href="https://github.com/MehVahdJukaar/FarmersDelightRefabricated/blob/fabric/1.21.1-3.0.0/src/main/java/vectorwing/farmersdelight/common/tag/CompatibilityTags.java#L15">...</a>
 */
public class CompatibilityTags {
    // Create
    public static final String CREATE = "create";
    public static final TagKey<Item> CREATE_UPRIGHT_ON_BELT = externalItemTag(CREATE, "upright_on_belt");

    // Farmer's Delight
    public static final String FARMERS_DELIGHT = Grovewatch.FARMERS_DELIGHT_ID;
    public static final TagKey<Item> DRINKS = externalItemTag(FARMERS_DELIGHT, "drinks");

    // Create Crafts and Additions
    public static final String CREATE_CA = "createaddition";
    public static final TagKey<Item> CREATE_CA_PLANT_FOODS = externalItemTag(CREATE_CA, "plant_foods");
    public static final TagKey<Item> CREATE_CA_PLANTS = externalItemTag(CREATE_CA, "plants");

    // Serene Seasons
    public static final String SERENE_SEASONS = "sereneseasons";
    public static final TagKey<Block> SERENE_SEASONS_AUTUMN_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "autumn_crops");
    public static final TagKey<Block> SERENE_SEASONS_SPRING_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "spring_crops");
    public static final TagKey<Block> SERENE_SEASONS_SUMMER_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "summer_crops");
    public static final TagKey<Block> SERENE_SEASONS_WINTER_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "winter_crops");
    public static final TagKey<Block> SERENE_SEASONS_UNBREAKABLE_FERTILE_CROPS = externalBlockTag(SERENE_SEASONS, "unbreakable_infertile_crops");
    public static final TagKey<Item> SERENE_SEASONS_AUTUMN_CROPS = externalItemTag(SERENE_SEASONS, "autumn_crops");
    public static final TagKey<Item> SERENE_SEASONS_SPRING_CROPS = externalItemTag(SERENE_SEASONS, "spring_crops");
    public static final TagKey<Item> SERENE_SEASONS_SUMMER_CROPS = externalItemTag(SERENE_SEASONS, "summer_crops");
    public static final TagKey<Item> SERENE_SEASONS_WINTER_CROPS = externalItemTag(SERENE_SEASONS, "winter_crops");

    private static TagKey<Item> externalItemTag(String modId, String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(modId, path));
    }

    private static TagKey<Block> externalBlockTag(String modId, String path) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(modId, path));
    }
}
