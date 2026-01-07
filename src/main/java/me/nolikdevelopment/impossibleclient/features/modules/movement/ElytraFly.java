package me.nolikdevelopment.impossibleclient.features.modules.movement;


import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;
import me.nolikdevelopment.impossibleclient.util.MathUtil;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;

public class ElytraFly extends Module {
    public Setting<Mode> mode = mode("Mode:", Mode.Control);
    public enum Mode {
        Control
    }
    public ElytraFly() {
        super("ElytraFly", "Elytra fly...", Category.MOVEMENT);
    }
    @Subscribe public void onTick() {
        switch (mode.getValue()) {
            case Control -> {
                if (PlayerUtil.getArmorSlot(EquipmentSlot.CHEST, Items.ELYTRA)) {
                    if (PlayerUtil.isGliding()) {
                        if (mc.options.keyJump.isDown()) {
                            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, 1, mc.player.getDeltaMovement().z);
                        } else if (mc.options.keyShift.isDown()) {
                            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, -1, mc.player.getDeltaMovement().z);
                        } else {
                            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x, -0.00001, mc.player.getDeltaMovement().z);
                        }
                        double speed[] = MathUtil.directionSpeed(2);
                        mc.player.setDeltaMovement(speed[0], mc.player.getDeltaMovement().y, speed[1]);
                    }
                }
            }
        }
    }
}