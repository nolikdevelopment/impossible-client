package me.nolikdevelopment.impossibleclient.features.modules.combat;

import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.player.PlayerUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
// TODO: салосвич
public class AutoPearl extends Module {
    public AutoPearl() {
        super("AutoPearl", "Automatically throws ender pearls", Category.COMBAT);
    }
    @Subscribe public void onTick() {
        if (PlayerUtil.getWeaponOffhand().getItem().equals(Items.ENDER_PEARL)) {
            PlayerUtil.useItem(InteractionHand.MAIN_HAND, 0, mc.player.getYRot(), mc.player.getXRot());
            disable();
        } else {
            disable();
        }
    }
}
