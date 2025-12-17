package me.alpha432.oyvey.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.event.Stage;
import me.alpha432.oyvey.event.impl.TickEvent;
import me.alpha432.oyvey.event.impl.UpdateWalkingPlayerEvent;
import me.alpha432.oyvey.features.modules.movement.NoSlow;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.alpha432.oyvey.util.traits.Util.EVENT_BUS;

@Mixin(LocalPlayer.class)
public class MixinClientPlayerEntity {
    @Inject(method = "tick", at = @At("TAIL"))
    private void tickHook(CallbackInfo ci) {
        EVENT_BUS.post(new TickEvent());
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;tick()V", shift = At.Shift.AFTER))
    private void tickHook2(CallbackInfo ci) {
        EVENT_BUS.post(new UpdateWalkingPlayerEvent(Stage.PRE));
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;sendPosition()V", shift = At.Shift.AFTER))
    private void tickHook3(CallbackInfo ci) {
        EVENT_BUS.post(new UpdateWalkingPlayerEvent(Stage.POST));
    }
    @ModifyExpressionValue(method = "modifyInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean aVoid(boolean original) {
        NoSlow noSlow = Impossible.moduleManager.getModuleByClass(NoSlow.class);
        if (noSlow.isEnabled()) {
            return false;
        }
        return original;
    }
}
