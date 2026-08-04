package uk.sigma_co.component;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import uk.sigma_co.Grovewatch;

public class ModComponents {
    public static final DataComponentType<Boolean> ON_GRASS_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, "on_grass"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
    );

    public static void initialize(){

    }
}
