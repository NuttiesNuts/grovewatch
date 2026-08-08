package uk.sigma_co.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.sigma_co.mob_effect.ModEffects;

@Mixin(PhantomSpawner.class)
public class PhantomSpawnerMixin {

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerPlayer;isSpectator()Z"
            )
    )
    private boolean grovewatch$skipCaffeinated(boolean isSpectator, @Local ServerPlayer player) {
        return isSpectator || player.hasEffect(ModEffects.CAFFEINE);
    }
}