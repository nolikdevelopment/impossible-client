package me.nolikdevelopment.impossibleclient.mixin;

import me.nolikdevelopment.impossibleclient.Impossible;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.nolikdevelopment.impossibleclient.util.traits.Util.mc;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    @Inject(method = "render", at = @At("HEAD"))
    private void aVoid(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        guiGraphics.drawString(mc.font, Impossible.NAME, 1, 1, -1, true);
    }
    // TODO: Если выйти в главное меню, звук вновь воспроизведется
    @Inject(method = "init", at = @At("HEAD"))
    private void aVoid(CallbackInfo ci) {
            mc.getSoundManager().play(SimpleSoundInstance.forMusic(SoundEvents.EXPERIENCE_ORB_PICKUP, 1f));
    }
}
