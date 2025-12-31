package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.Impossible;
import me.alpha432.oyvey.event.impl.HitEvent;
import me.alpha432.oyvey.features.modules.combat.HitSound;
import me.alpha432.oyvey.util.traits.Util;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiPlayerGameMode.class)
public class MixinClientPlayerInteractionManager implements Util {
    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void aVoid(Player player, Entity entity, CallbackInfo ci) {
        HitSound hitSound = Impossible.moduleManager.getModuleByClass(HitSound.class);
        if (hitSound.isEnabled()) {
            HitEvent event = new HitEvent(entity, player);
            EVENT_BUS.post(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }
}
