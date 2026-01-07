package me.nolikdevelopment.impossibleclient.features.modules.client;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.impl.ClientEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.commands.Command;
import me.nolikdevelopment.impossibleclient.features.gui.ImpossibleGui;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class ClickGui
        extends Module {
    private static ClickGui INSTANCE = new ClickGui();
    public Setting<String> prefix = str("Prefix", ".");
    public Setting<Color> color = color("Color", 0, 0, 255, 180);
    public Setting<Color> topColor = color("TopColor", 0, 0, 150, 240);
    public Setting<Boolean> rainbow = bool("Rainbow", false);
    public Setting<Integer> rainbowHue = num("Delay", 240, 0, 600);
    public Setting<Float> rainbowBrightness = num("Brightness", 150.0f, 1.0f, 255.0f);
    public Setting<Float> rainbowSaturation = num("Saturation", 150.0f, 1.0f, 255.0f);

    public ClickGui() {
        super("ClickGui", "Opens the ClickGui", Module.Category.CLIENT);
        setBind(GLFW.GLFW_KEY_RIGHT_SHIFT);
        rainbowHue.setVisibility(v -> rainbow.getValue());
        rainbowBrightness.setVisibility(v -> rainbow.getValue());
        rainbowSaturation.setVisibility(v -> rainbow.getValue());
        this.setInstance();
    }

    public static ClickGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClickGui();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Subscribe
    public void onSettingChange(ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            if (event.getSetting().equals(this.prefix)) {
                Impossible.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage("Prefix set to {global} %s", Impossible.commandManager.getPrefix());
            }
            if (event.getSetting().equals(this.color)) {
                Impossible.colorManager.setColor(this.color.getPlannedValue());
            }
        }
    }

    @Override
    public void onEnable() {
        if (nullCheck()) {
            return;
        }
        mc.setScreen(ImpossibleGui.getClickGui());
    }

    @Override
    public void onLoad() {
        Impossible.colorManager.setColor(this.color.getValue());
        Impossible.commandManager.setPrefix(this.prefix.getValue());
    }

    @Override
    public void onTick() {
        if (!(ClickGui.mc.screen instanceof ImpossibleGui)) {
            this.disable();
        }
    }
}