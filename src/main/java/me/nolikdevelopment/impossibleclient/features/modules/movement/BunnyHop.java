package me.nolikdevelopment.impossibleclient.features.modules.movement;

import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;
import me.nolikdevelopment.impossibleclient.util.traits.Util;
import net.minecraft.client.player.LocalPlayer;

import java.util.function.Predicate;

public class BunnyHop extends Module implements Util {
    private enum Jump {
        SPRINTING("Sprinting", p -> p.isSprinting() && (p.zza != 0 || p.xxa != 0)),
        WALKING("Walking", p -> (p.zza != 0 || p.xxa != 0)),
        ALWAYS("Always", p -> true);

        private String name;
        private Predicate<LocalPlayer> condition;

        Jump(String name, Predicate<LocalPlayer> condition) {
            this.name = name;
            this.condition = condition;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public Setting<Jump> type = mode("Jump if", Jump.ALWAYS);

    public BunnyHop() {
        super("BunnyHop", "", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        LocalPlayer p = mc.player;

        if (!p.onGround() || p.isShiftKeyDown()) return;

        if (type.getValue().condition.test(p)) {
            p.jumpFromGround();
        }
    }
}
