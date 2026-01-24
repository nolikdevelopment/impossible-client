package me.nolikdevelopment.impossibleclient.features.modules.player;

import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;

public class NameProtect extends Module {
    public Setting<String> name = str("Name", "Impossible_Client");
    public NameProtect() {
        super("NameProtect", "", Category.PLAYER);
    }
}
