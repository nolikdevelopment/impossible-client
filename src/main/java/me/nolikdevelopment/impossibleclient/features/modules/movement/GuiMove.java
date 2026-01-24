package me.nolikdevelopment.impossibleclient.features.modules.movement;

import com.mojang.blaze3d.platform.InputConstants;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.client.KeyMapping;

public class GuiMove extends Module {
    // TODO: адднуть проверки на скрин
    public GuiMove() {
        super("GuiMove", "", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        for (KeyMapping keyMapping : new KeyMapping[]{mc.options.keyJump, mc.options.keyUp, mc.options.keyLeft, mc.options.keyRight, mc.options.keyDown}) {
            keyMapping.setDown(InputConstants.isKeyDown(mc.getWindow(), InputConstants.getKey(keyMapping.saveString()).getValue()));
        }
        InputConstants.isKeyDown(mc.getWindow(), InputConstants.getKey(mc.options.keySprint.saveString()).getValue());
        InputConstants.isKeyDown(mc.getWindow(), InputConstants.getKey(mc.options.keyUp.saveString()).getValue());
    }
}
