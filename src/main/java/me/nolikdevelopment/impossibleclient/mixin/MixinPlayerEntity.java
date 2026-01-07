package me.nolikdevelopment.impossibleclient.mixin;


import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.modules.misc.Reach;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class MixinPlayerEntity {
    @Inject(method = "entityInteractionRange", at = @At("HEAD"), cancellable = true)
    private void aVoidHook1(CallbackInfoReturnable<Double> cir) {
         Reach reach = Impossible.moduleManager.getModuleByClass(Reach.class);
         if (reach.isEnabled()) {
             cir.setReturnValue(8d);
         }
    }
    @Inject(method = "blockInteractionRange", at = @At("HEAD"), cancellable = true)
    private void aVoidHook2(CallbackInfoReturnable<Double> cir) {
        Reach reach = Impossible.moduleManager.getModuleByClass(Reach.class);
        if (reach.isEnabled()) {
            cir.setReturnValue(8d);
        }
    }
}
