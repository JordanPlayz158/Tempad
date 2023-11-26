package me.codexadrian.tempad.client.widgets;

import com.teamresourceful.resourcefullib.client.screens.CursorScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.AbstractContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseWidget extends AbstractContainerEventHandler implements Renderable, NarratableEntry {

    private final List<GuiEventListener> children = new ArrayList<>();
    protected final Font font = Minecraft.getInstance().font;

    public <T extends GuiEventListener> T addChild(T child) {
        this.children.add(child);
        return child;
    }

    public void renderChildren(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        List<GuiEventListener> children = new ArrayList<>(this.children());
        Collections.reverse(children);
        for (GuiEventListener child : children) {
            if (child instanceof Renderable renderable) {
                renderable.render(graphics, mouseX, mouseY, partialTicks);
            }
        }
        if (Minecraft.getInstance().screen instanceof CursorScreen cursorScreen) {
            cursorScreen.setCursor(children);
        }
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return this.children;
    }

    @Override
    public @NotNull NarrationPriority narrationPriority() {
        return NarrationPriority.NONE;
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {

    }
}