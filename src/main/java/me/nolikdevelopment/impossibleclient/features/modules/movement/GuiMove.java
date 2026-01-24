package me.nolikdevelopment.impossibleclient.features.modules.movement;

import com.mojang.blaze3d.platform.InputConstants;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.PauseScreen;

public class GuiMove extends Module {
    public GuiMove() {
        super("GuiMove", "", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        if (mc.screen instanceof ChatScreen || mc.screen instanceof PauseScreen) return;
        for (KeyMapping keyMapping : new KeyMapping[]{mc.options.keyJump, mc.options.keyUp, mc.options.keyLeft, mc.options.keyRight, mc.options.keyDown}) {
            keyMapping.setDown(InputConstants.isKeyDown(mc.getWindow(), InputConstants.getKey(keyMapping.saveString()).getValue()));
        }
        InputConstants.isKeyDown(mc.getWindow(), InputConstants.getKey(mc.options.keySprint.saveString()).getValue());
        InputConstants.isKeyDown(mc.getWindow(), InputConstants.getKey(mc.options.keyUp.saveString()).getValue());
    }
}
