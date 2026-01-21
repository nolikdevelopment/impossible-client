package me.nolikdevelopment.impossibleclient.features.modules.movement;


import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;
import me.nolikdevelopment.impossibleclient.util.models.Timer;
import me.nolikdevelopment.impossibleclient.util.player.InventoryUtil;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;

import net.minecraft.world.item.Items;

public class AutoFirework extends Module {
    public Setting<Integer> delay = num("Delay", 5, 1, 50);

    Timer timer = new Timer();

    public AutoFirework() {
        super("AutoFirework", "", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        if (timer.passedS(delay.getValue())) {
            if (PlayerUtil.isGliding() || PlayerUtil.getArmorSlot(EquipmentSlot.CHEST, Items.ELYTRA) || !PlayerUtil.isUsingItem()) {
                if (InventoryUtil.findItem(Items.FIREWORK_ROCKET)) {
                    if (!PlayerUtil.onGround()) {
                        PlayerUtil.useItem(InteractionHand.MAIN_HAND, 0, mc.player.getYRot(), mc.player.getXRot());
                        timer.reset();
                    }
                }
            }
        }
    }
    @Subscribe public void onDisable() {
        timer.reset();
    }
    @Subscribe public void onEnable() {
        timer.reset();
    }
}
