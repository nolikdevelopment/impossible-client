package me.nolikdevelopment.impossibleclient.mixin;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.impl.Render2DEvent;
import me.nolikdevelopment.impossibleclient.features.modules.render.NoRender;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.nolikdevelopment.impossibleclient.util.traits.Util.EVENT_BUS;

@Mixin(Gui.class)
public class MixinInGameHud {

    @Inject(method = "render", at = @At("RETURN"))
    public void render(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (Minecraft.getInstance().gui.getDebugOverlay().showDebugScreen()) return;

        Render2DEvent event = new Render2DEvent(context, tickCounter.getGameTimeDeltaPartialTick(true));
        EVENT_BUS.post(event);
    }
    @Inject(method = "renderEffects", at = @At("HEAD"), cancellable = true)
    private void aVoid(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        NoRender noRender = Impossible.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled()) {
            ci.cancel();
        }
    }
    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void aVoid(GuiGraphics guiGraphics, float f, CallbackInfo ci) {
        NoRender noRender = Impossible.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled()) {
            ci.cancel();
        }
    }
}
