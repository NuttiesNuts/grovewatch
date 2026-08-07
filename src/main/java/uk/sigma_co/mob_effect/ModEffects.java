package uk.sigma_co.mob_effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import uk.sigma_co.Grovewatch;

public class ModEffects{
    public static final Holder<MobEffect> CAFFEINE =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, "caffeine"), new CaffeineEffect().addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.withDefaultNamespace("effect.speed"), (double)0.15F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static void initialize() {

    }
}
