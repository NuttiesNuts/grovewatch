package uk.sigma_co.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.WeakHashMap;

public class MossySwordItem extends Item {
    public MossySwordItem(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
    }

    private WeakHashMap map = new WeakHashMap<Entity, BlockPos>();

    private float bonusDamage = 10f;

    @Override
    public Component getName(ItemStack itemStack) {
        return Component.literal("<grad colors=67a538,bfd64f>" + super.getName(itemStack).getString());
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (owner.onGround()) {
            map.put(owner, owner.getOnPos());
        }
    }

    @Override
    public float getAttackDamageBonus(Entity victim, float damage, DamageSource damageSource) {
        Entity attacker = damageSource.getEntity();
        Level level = attacker.level();
        if (map.containsKey(attacker) && level.getBlockState((BlockPos) map.get(attacker)).is(BlockTags.GRASS_BLOCKS)) {
            return bonusDamage;
        } else {
            return 0f;
        }
    }

    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        //mob.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 300), attacker);
        super.postHurtEnemy(itemStack, mob, attacker);
    }
}
