package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.Impossible;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.alpha432.oyvey.util.traits.Util.mc;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V"))
    private void aVoid(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        guiGraphics.drawString(mc.font, Impossible.NAME, 1, 1, -1, true);
    }
}
