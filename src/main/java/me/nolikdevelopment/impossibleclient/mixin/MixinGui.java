package me.nolikdevelopment.impossibleclient.mixin;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.modules.render.NoRender;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class MixinGui {
    @Inject(method = "renderBossOverlay", at = @At("HEAD"), cancellable = true)
    private void aVoid(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        NoRender noRender = Impossible.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled()) {
            ci.cancel();
        }
    }
}
