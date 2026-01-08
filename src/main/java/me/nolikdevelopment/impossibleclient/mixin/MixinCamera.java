package me.nolikdevelopment.impossibleclient.mixin;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.modules.render.ViewClip;
import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public class MixinCamera implements Util {
    @Inject(method = "getMaxZoom", at = @At("HEAD"), cancellable = true)
    private void aVoid(float f, CallbackInfoReturnable<Float> cir) {
        ViewClip viewClip = Impossible.moduleManager.getModuleByClass(ViewClip.class);
        if (viewClip.isEnabled()) {
            cir.setReturnValue(5f);
        }
    }
}
