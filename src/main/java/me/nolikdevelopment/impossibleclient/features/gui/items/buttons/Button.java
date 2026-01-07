package me.nolikdevelopment.impossibleclient.features.gui.items.buttons;

import me.nolikdevelopment.impossibleclient.Impossible;
import me.nolikdevelopment.impossibleclient.features.gui.ImpossibleGui;
import me.nolikdevelopment.impossibleclient.features.gui.Widget;
import me.nolikdevelopment.impossibleclient.features.gui.items.Item;
import me.nolikdevelopment.impossibleclient.features.modules.client.ClickGui;
import me.nolikdevelopment.impossibleclient.util.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;

public class Button
        extends Item {
    private boolean state;

    public Button(String name) {
        super(name);
        this.height = 15;
    }

    @Override
    public void drawScreen(GuiGraphics context, int mouseX, int mouseY, float partialTicks) {
        RenderUtil.rect(context, this.x, this.y, this.x + (float) this.width, this.y + (float) this.height - 0.5f, this.getState() ? (!this.isHovering(mouseX, mouseY) ? Impossible.colorManager.getColorWithAlpha(y, ClickGui.getInstance().color.getValue().getAlpha()) : Impossible.colorManager.getColorWithAlpha(y, ClickGui.getInstance().topColor.getValue().getAlpha())) : (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515));
        drawString(this.getName(), this.x + 2.3f, this.y - 2.0f - (float) ImpossibleGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && this.isHovering(mouseX, mouseY)) {
            this.onMouseClick();
        }
    }

    public void onMouseClick() {
        this.state = !this.state;
        this.toggle();
        mc.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1f));
    }

    public void toggle() {
    }

    public boolean getState() {
        return this.state;
    }

    @Override
    public int getHeight() {
        return 14;
    }

    public boolean isHovering(int mouseX, int mouseY) {
        for (Widget widget : ImpossibleGui.getClickGui().getComponents()) {
            if (!widget.drag) continue;
            return false;
        }
        return (float) mouseX >= this.getX() && (float) mouseX <= this.getX() + (float) this.getWidth() && (float) mouseY >= this.getY() && (float) mouseY < this.getY() + (float) this.height;
    }
}