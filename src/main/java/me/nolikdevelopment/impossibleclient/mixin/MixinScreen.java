package me.nolikdevelopment.impossibleclient.mixin;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.modules.render.NoRender;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class MixinScreen {
    @Inject(method = "renderTransparentBackground", at = @At("HEAD"), cancellable = true)
    private void aVoid(GuiGraphics guiGraphics, CallbackInfo ci) {
        NoRender noRender = Impossible.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled()) {
            ci.cancel();
        }
    }
}
