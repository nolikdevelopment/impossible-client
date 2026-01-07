package me.nolikdevelopment.impossibleclient.mixin;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.modules.render.FullBright;
import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.class)
public class MixinClientLevel implements Util {
    @Inject(method = "getSkyColor", at = @At("HEAD"), cancellable = true)
    private void aVoid(Vec3 vec3, float f, CallbackInfoReturnable<Integer> cir) {
        FullBright fullBright = Impossible.moduleManager.getModuleByClass(FullBright.class);
        if (fullBright.isEnabled()) {
           cir.setReturnValue(-1);
        }
    }
}
