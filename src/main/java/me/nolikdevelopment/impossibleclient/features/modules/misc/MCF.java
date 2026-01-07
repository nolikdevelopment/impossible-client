package me.nolikdevelopment.impossibleclient.features.modules.misc;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.commands.Command;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.glfw.GLFW;

public class MCF extends Module {
    private boolean pressed;

    public MCF() {
        super("MCF", "Middle click friend", Category.MISC);
    }

    @Override
    public void onTick() {
        if (GLFW.glfwGetMouseButton(mc.getWindow().handle(), 2) == 1) {
            if (!pressed) click();
            pressed = true;
        } else {
            pressed = false;
        }
    }

    private void click() {
        Entity targetedEntity = mc.crosshairPickEntity;
        if (!(targetedEntity instanceof Player)) return;
        String name = ((Player) targetedEntity).getGameProfile().name();

        if (Impossible.friendManager.isFriend(name)) {
            Impossible.friendManager.removeFriend(name);

            Command.sendMessage("{red} %s has been unfriended.", name);
        } else {
            Impossible.friendManager.addFriend(name);
            Command.sendMessage("{aqua} %s has been friended.", name);
        }
    }
}
