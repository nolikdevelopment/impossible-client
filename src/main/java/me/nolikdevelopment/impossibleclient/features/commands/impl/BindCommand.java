package me.nolikdevelopment.impossibleclient.features.commands.impl;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.event.impl.KeyInputEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.commands.Command;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Bind;
import me.nolikdevelopment.impossibleclient.util.KeyboardUtil;
import org.lwjgl.glfw.GLFW;

public class BindCommand
        extends Command {
    private boolean listening;
    private Module module;

    public BindCommand() {
        super("bind", new String[]{"<module>"});
        EVENT_BUS.register(this);
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            sendMessage("Please specify a module.");
            return;
        }
        String moduleName = commands[0];
        Module module = Impossible.moduleManager.getModuleByName(moduleName);
        if (module == null) {
            sendMessage("Unknown module '%s'!", moduleName);
            return;
        }

        sendMessage("{gray} Press a key.");
        listening = true;
        this.module = module;
    }

    @Subscribe
    private void onKey(KeyInputEvent event) {
        if (nullCheck() || !listening) return;
        listening = false;
        if (event.getKey() == GLFW.GLFW_KEY_ESCAPE) {
            sendMessage("{gray} Operation cancelled.");
            return;
        }

        String key = KeyboardUtil.getKeyName(event.getKey());
        sendMessage("Bind for {green} %s {} set to {gray} %s", module.getName(), key);
        module.bind.setValue(new Bind(event.getKey()));
    }

}