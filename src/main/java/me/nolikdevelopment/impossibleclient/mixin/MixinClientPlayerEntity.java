package me.nolikdevelopment.impossibleclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.Stage;
import me.nolikdevelopment.impossibleclient.event.impl.SlowEvent;
import me.nolikdevelopment.impossibleclient.event.impl.TickEvent;
import me.nolikdevelopment.impossibleclient.event.impl.UpdateWalkingPlayerEvent;
import me.nolikdevelopment.impossibleclient.features.modules.movement.NoSlow;
import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class MixinClientPlayerEntity implements Util {
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
            SlowEvent event = new SlowEvent();
            EVENT_BUS.post(event);
            if (event.isCancelled()) {
                event.cancel();
            }
        } else {
            return original;
        }
        return false;
    }
    @Inject(method = "handlePortalTransitionEffect", at = @At("HEAD"), cancellable = true)
    private void aVoid(boolean bl, CallbackInfo ci) {
        NoSlow noSlow = Impossible.moduleManager.getModuleByClass(NoSlow.class);
        if (noSlow.isEnabled()) {
            ci.cancel();
        }
    }
}
