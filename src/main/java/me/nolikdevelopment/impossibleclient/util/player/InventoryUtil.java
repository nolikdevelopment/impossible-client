package me.nolikdevelopment.impossibleclient.util.player;

import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Item;


// TODO: добавить разных методов
public class InventoryUtil implements Util {
    public static void click(int slot, int button, ClickType type) {
        int id = mc.player.containerMenu.containerId;
        mc.gameMode.handleInventoryMouseClick(id, slot, button, type, mc.player);
    }

    public static void swap(int slot) {
        if (slot < 0 || slot > 8) return;
        mc.player.getInventory().setSelectedSlot(slot);
        mc.gameMode.ensureHasSentCarriedItem();
    }

    public static int getSlot() {
        return mc.player.getInventory().getSelectedSlot();
    }

    public static boolean findItem(Item item) {
        for (int i = 0; i < 9; i++) {
            if (!(PlayerUtil.getWeaponOffhand().getItem() == item)) {
                swap(i);
            }
        }
        return false;
    }
}
