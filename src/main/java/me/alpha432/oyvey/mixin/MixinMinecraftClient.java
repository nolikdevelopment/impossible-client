package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.Impossible;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraftClient {
    @Inject(method = "updateTitle", at = @At("HEAD"), cancellable = true)
    private void onUpdateTitle(CallbackInfo ci) {
        Minecraft.getInstance().getWindow().setTitle(Impossible.NAME);
        ci.cancel();
    }
}
