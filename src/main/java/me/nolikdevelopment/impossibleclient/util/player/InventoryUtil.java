package me.nolikdevelopment.impossibleclient.util.player;

import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.world.inventory.ClickType;


// TODO: добавить разных методов
public class InventoryUtil implements Util {
    public static void click(int slot, int button, ClickType type) {
        int id = mc.player.containerMenu.containerId;
        mc.gameMode.handleInventoryMouseClick(id, slot, button, type, mc.player);
    }
}
