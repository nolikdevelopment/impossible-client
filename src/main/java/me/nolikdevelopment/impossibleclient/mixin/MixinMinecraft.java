package me.nolikdevelopment.impossibleclient.mixin;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.impl.ScreenEvent;
import me.nolikdevelopment.impossibleclient.features.modules.misc.AutoSign;
import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft implements Util {

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    private void aVoid(Screen screen, CallbackInfo ci) {
        AutoSign autoSign = Impossible.moduleManager.getModuleByClass(AutoSign.class);
        if (autoSign.isEnabled()) {
            ScreenEvent event = new ScreenEvent(screen);
            EVENT_BUS.post(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "updateTitle", at = @At("HEAD"), cancellable = true)
    private void onUpdateTitle(CallbackInfo ci) {
        mc.getWindow().setTitle(Impossible.NAME);
        ci.cancel();
    }
}