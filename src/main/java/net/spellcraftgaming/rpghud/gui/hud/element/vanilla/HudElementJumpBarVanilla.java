package net.spellcraftgaming.rpghud.gui.hud.element.vanilla;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.spellcraftgaming.lib.GameData;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElement;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementType;
import net.spellcraftgaming.rpghud.settings.Settings;

public class HudElementJumpBarVanilla extends HudElement {

	public HudElementJumpBarVanilla() {
		super(HudElementType.JUMP_BAR, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return GameData.isRidingLivingMount();
	}

	@Override
	public void drawElement(Gui gui, float zLevel, float partialTicks) {
		ScaledResolution res = new ScaledResolution(this.mc);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();

		this.mc.mcProfiler.startSection("jumpBar");
		float charge = GameData.getHorseJumpPower();
		final int barWidth = 182;
		int x = (res.getScaledWidth() / 2) - (barWidth / 2) + this.settings.getPositionValue(Settings.jump_bar_position)[0];
		int filled = (int) (charge * (barWidth + 1));
		int top = res.getScaledHeight() - 32 + 3 + this.settings.getPositionValue(Settings.jump_bar_position)[1];

		gui.drawTexturedModalRect(x, top, 0, 84, barWidth, 5);

		if (filled > 0) {
			gui.drawTexturedModalRect(x, top, 0, 89, filled, 5);
		}

		GlStateManager.enableBlend();
		this.mc.mcProfiler.endSection();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
