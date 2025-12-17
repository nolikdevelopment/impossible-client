package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.features.modules.render.FOV;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Inject(method = "getFov", at = @At("HEAD"), cancellable = true)
    private void aVoid(Camera camera, float f, boolean bl, CallbackInfoReturnable<Float> cir) {
        FOV fov = Impossible.moduleManager.getModuleByClass(FOV.class);
        if (fov.isEnabled()) {
            cir.setReturnValue(110f);
        }
    }
}
