package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.features.gui.OyVeyGui;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.alpha432.oyvey.util.traits.Util.mc;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends EntityRenderer{
    protected MixinLivingEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At("HEAD"), cancellable = true)
    private void aVoid(T livingEntity, S livingEntityRenderState, float f, CallbackInfo ci) {
        if (Impossible.rotationManager.isRotating() && mc.player != mc.player) {
            livingEntityRenderState.bodyRot = Impossible.rotationManager.getYaw();
            livingEntityRenderState.xRot = Impossible.rotationManager.getPitch();
            livingEntity.xRotO = Impossible.rotationManager.getYaw();
          }
        }
    }
