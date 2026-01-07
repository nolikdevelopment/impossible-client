package me.nolikdevelopment.impossibleclient.features.modules.render;

import me.nolikdevelopment.impossibleclient.event.impl.Render3DEvent;
import me.nolikdevelopment.impossibleclient.event.system.Subscribe;
import me.nolikdevelopment.impossibleclient.features.Feature;
import me.nolikdevelopment.impossibleclient.features.modules.Module;
import me.nolikdevelopment.impossibleclient.util.render.RenderUtil;
import me.nolikdevelopment.impossibleclient.features.settings.Setting;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.AABB;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ESP extends Module {
    List<Entity> pizdec = new ArrayList<>();

    public Setting<Mode> mode = register(new Setting<>("Mode", Mode.BOX));
    public Setting<Color> color = register(new Setting<>("Color", new Color(255, 255, 255, 180)));
    public Setting<Float> lineWidth = register(new Setting<>("LineWidth", 2.0f, 0.1f, 5.0f));
    
    public ESP() {
        super("ESP", "Translucent entities", Category.RENDER);
    }

     @Subscribe
     public void onTick() {
         pizdec.clear();
         for (Entity entity : mc.level.entitiesForRendering()) {
             if (mc.player.position().distanceTo(entity.position()) > 65) continue;
             if (entity == mc.player) continue;
             if (!isEntity(entity)) continue;
             pizdec.add(entity);
         }
         pizdec.removeIf(entity -> !entity.isAlive());
     }
    
    @Subscribe public void onRender3D(Render3DEvent event) {
       if (Feature.nullCheck()) return;
       if (pizdec.isEmpty()) return;

        float delta = event.getDelta();
        Color colorValue = color.getValue();
        
       for (Entity entity : pizdec) {
           double x = entity.xOld + (entity.getX() - entity.xOld) * delta;
           double y = entity.yOld + (entity.getY() - entity.yOld) * delta;
           double z = entity.zOld + (entity.getZ() - entity.zOld) * delta;

           AABB box = entity.getBoundingBox().move(x - entity.getX(), y - entity.getY(), z - entity.getZ());
           
           switch (mode.getValue()) {
               case BOX -> RenderUtil.drawBox(event.getMatrix(), box, colorValue, lineWidth.getValue());
               case FILLED -> RenderUtil.drawBoxFilled(event.getMatrix(), box, colorValue);
               case BOTH -> {
                   Color filledColor = new Color(colorValue.getRed(), colorValue.getGreen(), colorValue.getBlue(), 170);
                   RenderUtil.drawBoxFilled(event.getMatrix(), box, filledColor);
                   RenderUtil.drawBox(event.getMatrix(), box, colorValue, lineWidth.getValue());
               }
           }
       }
    }
    
    private boolean isEntity(Entity entity) {
        if (entity.getType() == EntityType.ITEM) return true;
        if (entity.getType().getCategory() == MobCategory.MONSTER) return true;
        return false;
    }

        public enum Mode {
        BOX,
        FILLED,
        BOTH
    }
}
