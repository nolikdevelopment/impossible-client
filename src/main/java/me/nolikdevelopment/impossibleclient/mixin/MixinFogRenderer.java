package me.nolikdevelopment.impossibleclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.modules.render.NoRender;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FogRenderer.class)
public abstract class MixinFogRenderer {
    @Shadow
    public abstract GpuBufferSlice getBuffer(FogRenderer.FogMode fogMode);

    @ModifyExpressionValue(method = "getBuffer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/MappableRingBuffer;currentBuffer()Lcom/mojang/blaze3d/buffers/GpuBuffer;"))
    private GpuBuffer aVoid(GpuBuffer original) {
        NoRender noRender = Impossible.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled()) {
            return getBuffer(FogRenderer.FogMode.NONE).buffer();
        }
        return original;
    }
}
