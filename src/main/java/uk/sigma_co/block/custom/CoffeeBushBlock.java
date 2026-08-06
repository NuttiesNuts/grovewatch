package uk.sigma_co.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import uk.sigma_co.item.ModItems;

import java.util.UUID;

public class CoffeeBushBlock extends SweetBerryBushBlock{
    public CoffeeBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(ModItems.COFFEE_BERRY);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        boolean isGrown = age == 3;
        if(age > 1) {
            int count = 1 + level.getRandom().nextInt(2);
            popResource(level, pos, new ItemStack(ModItems.COFFEE_BERRY, count + (isGrown ? 1 : 0)));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS,
                    1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
            BlockState newState = state.setValue(AGE, 1);
            level.setBlock(pos, newState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    protected void entityInside(final BlockState state, final Level level, final BlockPos pos, final Entity entity, final InsideBlockEffectApplier effectApplier, final boolean isPrecise) {
        UUID sigmaUUID = UUID.fromString("bbc525ae-91d0-44cc-b706-656b56e75a04"); // I am a fox too so I should be privileged
        if (entity instanceof LivingEntity && !entity.is(EntityType.FOX) && !entity.getUUID().equals(sigmaUUID) && !entity.is(EntityType.BEE)) {
            entity.makeStuckInBlock(state, new Vec3((double)0.8F, (double)0.75F, (double)0.8F));
        }
    }
}
