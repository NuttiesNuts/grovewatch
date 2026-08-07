package uk.sigma_co.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.block.Block;
import uk.sigma_co.Grovewatch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Util {

    public static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, name));
    }

    public static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, name));
    }

    public static ResourceKey<Item> keyOfItem(Item item) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Grovewatch.MOD_ID, BuiltInRegistries.ITEM.getKey(item).getPath()));
    }

    /**
     * Gets text from a translation key, where "type" prefixes the mod ID.
     * Example: "type.grovewatch.key".
     *
     * @param translationType The type of lang being read, added as a prefix
     * @param translationKey  The key itself, added as a suffix after the mod ID
     * @param args            Additional values to be keyed into the text, through markers such as %s
     */
    public static MutableComponent getTextWithType(String translationType, String translationKey, Object... args) {
        return Component.translatable(translationType + "." + Grovewatch.MOD_ID + "." + translationKey, args);
    }

    public static MutableComponent tooltip(String key, Object... args) {
        return getTextWithType("tooltip", key, args);
    }

    public static void addFoodEffectTooltip(ItemStack stack, Consumer<Component> tooltipAdder, float durationFactor, float tickRate) {
        Consumable consumable = stack.get(DataComponents.CONSUMABLE);
        if (consumable == null) {
            return;
        }

        List<ConsumeEffect> consumeEffectList = consumable.onConsumeEffects();
        List<Pair<Holder<Attribute>, AttributeModifier>> attributeList = new ArrayList<>();
        MutableComponent mutableComponent;

        for (ConsumeEffect possibleConsumeEffect : consumeEffectList) {
            if (!(possibleConsumeEffect instanceof ApplyStatusEffectsConsumeEffect statusEffectsEffect))
                continue;

            List<MobEffectInstance> effectList = statusEffectsEffect.effects();
            for (MobEffectInstance instance : effectList) {
                mutableComponent = Component.translatable(instance.getDescriptionId());
                MobEffect effect = instance.getEffect().value();
                effect.createModifiers(instance.getAmplifier(), (attributeHolder, attributeModifier) -> {
                    attributeList.add(new Pair<>(attributeHolder, attributeModifier));
                });

                if (instance.getAmplifier() > 0) {
                    mutableComponent = Component.translatable("potion.withAmplifier", mutableComponent, Component.translatable("potion.potency." + instance.getAmplifier()));
                }

                if (instance.getDuration() > 20) {
                    mutableComponent = Component.translatable("potion.withDuration", mutableComponent, MobEffectUtil.formatDuration(instance, durationFactor, tickRate));
                }

                tooltipAdder.accept(mutableComponent.withStyle(effect.getCategory().getTooltipFormatting()));
            }
        }

        if (!attributeList.isEmpty()) {
            tooltipAdder.accept(CommonComponents.EMPTY);
            tooltipAdder.accept(Component.translatable("potion.whenDrank").withStyle(ChatFormatting.DARK_PURPLE));

            for (Pair<Holder<Attribute>, AttributeModifier> pair : attributeList) {
                AttributeModifier attributemodifier = pair.getSecond();
                double amount = attributemodifier.amount();
                double formattedAmount;
                if (attributemodifier.operation() != AttributeModifier.Operation.ADD_MULTIPLIED_BASE && attributemodifier.operation() != AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL) {
                    formattedAmount = attributemodifier.amount();
                } else {
                    formattedAmount = attributemodifier.amount() * 100.0;
                }

                if (amount > 0.0) {
                    tooltipAdder.accept(Component.translatable("attribute.modifier.plus." + attributemodifier.operation().id(), new Object[]{ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(formattedAmount), Component.translatable(((Attribute) ((Holder) pair.getFirst()).value()).getDescriptionId())}).withStyle(ChatFormatting.BLUE));
                } else if (amount < 0.0) {
                    formattedAmount *= -1.0;
                    tooltipAdder.accept(Component.translatable("attribute.modifier.take." + attributemodifier.operation().id(), new Object[]{ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(formattedAmount), Component.translatable(((Attribute) ((Holder) pair.getFirst()).value()).getDescriptionId())}).withStyle(ChatFormatting.RED));
                }
            }
        }
    }
}
