package uk.sigma_co.mob_effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import uk.sigma_co.Grovewatch;

public class ModEffects{
    public static final Holder<MobEffect> CAFFEINE =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, "caffeine"), new CaffeineEffect());

    public static void initialize() {

    }
}
