package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.player.InventoryUtil;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Items;

// TODO: дописать
public class OffHand extends Module {
    public OffHand() {
        super("OffHand", "Automatically places a specific item in your second hand", Category.COMBAT);
    }

    @Subscribe
    public void onTick() {
        if (mc.player.getOffhandItem().getItem() != Items.TOTEM_OF_UNDYING) {
            if (mc.screen instanceof InventoryScreen || mc.screen == null) {
                for (int i = 9; i < 45; i++) {
                    if (mc.player.getInventory().getItem(i >= 36 ? i - 36 : i).getItem() == Items.TOTEM_OF_UNDYING) {
                        InventoryUtil.click(i, 0, ClickType.PICKUP);
                        InventoryUtil.click(45, 0, ClickType.PICKUP);
                        break;
                    }
                }
            }
        }
    }
}