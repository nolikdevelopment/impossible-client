package me.nolikdevelopment.impossibleclient.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.modules.render.FOV;
import me.nolikdevelopment.impossibleclient.features.modules.render.NoBob;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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
    @Inject(method = "bobView", at = @At("HEAD"), cancellable = true)
    private void aVoid(PoseStack poseStack, float f, CallbackInfo ci) {
        NoBob noBob = Impossible.moduleManager.getModuleByClass(NoBob.class);
        if (noBob.isEnabled()) {
            ci.cancel();
        }
    }
    @Inject(method = "bobHurt", at = @At("HEAD"), cancellable = true)
    private void aVoidHook1(PoseStack poseStack, float f, CallbackInfo ci) {
        NoBob noBob = Impossible.moduleManager.getModuleByClass(NoBob.class);
        if (noBob.isEnabled()) {
            ci.cancel();
        }
    }
}
