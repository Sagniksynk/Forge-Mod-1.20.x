package net.sagnik.newmod.item.custom.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SoulCookbookScreen extends Screen {

    private int page = 0;
    private static final int TOTAL_PAGES = 3;

    public SoulCookbookScreen() {
        super(Component.literal("Soul Cookbook"));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        int x = this.width / 2 - 100;
        int y = this.height / 2 - 120;

        // Draw book background
        graphics.fill(x, y, x + 200, y + 230, 0xFFD4A96A);
        graphics.fill(x + 4, y + 4, x + 196, y + 226, 0xFFF5E6C8);

        // Draw title bar
        graphics.fill(x, y, x + 200, y + 24, 0xFF8B4513);
        graphics.drawCenteredString(this.font, "§f§lSoul Cookbook", this.width / 2, y + 8, 0xFFFFFF);

        // Draw page number
        graphics.drawCenteredString(this.font, "§7Page " + (page + 1) + " / " + TOTAL_PAGES,
                this.width / 2, y + 218, 0x888888);

        // Draw page content
        switch (page) {
            case 0 -> renderPage0(graphics, x, y);
            case 1 -> renderPage1(graphics, x, y);
            case 2 -> renderPage2(graphics, x, y);
        }

        // Draw navigation arrows
        graphics.drawString(this.font, "§l<", x + 10, y + 215, page > 0 ? 0x5533AA : 0x888888);
        graphics.drawString(this.font, "§l>", x + 183, y + 215, page < TOTAL_PAGES - 1 ? 0x5533AA : 0x888888);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void renderPage0(GuiGraphics graphics, int x, int y) {
        int tx = x + 12;
        int ty = y + 32;

        graphics.drawString(this.font, "§6§lCorrupted Soul Stew", tx, ty, 0xFFFFFF);
        ty += 16;
        graphics.drawString(this.font, "§8A dangerous but powerful dish.", tx, ty, 0xFFFFFF);
        ty += 20;

        graphics.drawString(this.font, "§e§lIngredients:", tx, ty, 0xFFFFFF);
        ty += 14;
        graphics.drawString(this.font, "§7- Mysterious Flesh x1", tx, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§7- Any fuel (coal, wood...)", tx, ty, 0xFFFFFF);
        ty += 20;

        graphics.drawString(this.font, "§e§lHow to craft:", tx, ty, 0xFFFFFF);
        ty += 14;
        graphics.drawString(this.font, "§71. Place Mysterious Flesh", tx, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§7   in top slot of furnace", tx, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§72. Add fuel in bottom slot", tx, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§73. Wait 10 seconds", tx, ty, 0xFFFFFF);
        ty += 20;

        graphics.drawString(this.font, "§e§lProperties:", tx, ty, 0xFFFFFF);
        ty += 14;
        graphics.drawString(this.font, "§7Hunger: §f10pts  §7Saturation: §f1.5x", tx, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§7Stack size: §f1   §7Returns: §fBowl", tx, ty, 0xFFFFFF);
    }

    private void renderPage1(GuiGraphics graphics, int x, int y) {
        int tx = x + 12;
        int ty = y + 32;

        graphics.drawString(this.font, "§a§lGood Effects (random 1):", tx, ty, 0xFFFFFF);
        ty += 14;
        String[] good = {"Regeneration II", "Strength II", "Speed II",
                "Absorption II", "Fire Resistance", "Night Vision"};
        for (String e : good) {
            graphics.drawString(this.font, "§a+ §7" + e + " (20s)", tx, ty, 0xFFFFFF);
            ty += 12;
        }

        ty += 8;
        graphics.drawString(this.font, "§c§lBad Effects (random 1):", tx, ty, 0xFFFFFF);
        ty += 14;
        String[] bad = {"Poison I", "Blindness I", "Weakness I",
                "Nausea I", "Wither I", "Levitation I"};
        for (String e : bad) {
            graphics.drawString(this.font, "§c- §7" + e + " (10s)", tx, ty, 0xFFFFFF);
            ty += 12;
        }
    }

    private void renderPage2(GuiGraphics graphics, int x, int y) {
        int tx = x + 12;
        int ty = y + 32;

        graphics.drawString(this.font, "§d§lRare Outcomes:", tx, ty, 0xFFFFFF);
        ty += 16;
        graphics.drawString(this.font, "§c10% chance:", tx, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§7Wither III for 5s", tx + 8, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§8\"The corruption consumes you...\"", tx + 8, ty, 0xFFFFFF);
        ty += 20;

        graphics.drawString(this.font, "§d5% chance:", tx, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§7Full instant heal", tx + 8, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§8\"The soul energy surges!\"", tx + 8, ty, 0xFFFFFF);
        ty += 24;

        graphics.drawString(this.font, "§e§lWarnings:", tx, ty, 0xFFFFFF);
        ty += 14;
        graphics.drawString(this.font, "§c! §7Never eat near lava", tx, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§c! §7Keep milk ready", tx, ty, 0xFFFFFF);
        ty += 12;
        graphics.drawString(this.font, "§c! §7Don't eat below half health", tx, ty, 0xFFFFFF);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int x = this.width / 2 - 100;
        int y = this.height / 2 - 120;

        // Left arrow
        if (mouseX >= x + 8 && mouseX <= x + 24 && mouseY >= y + 212 && mouseY <= y + 226) {
            if (page > 0) page--;
        }

        // Right arrow
        if (mouseX >= x + 180 && mouseX <= x + 196 && mouseY >= y + 212 && mouseY <= y + 226) {
            if (page < TOTAL_PAGES - 1) page++;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}