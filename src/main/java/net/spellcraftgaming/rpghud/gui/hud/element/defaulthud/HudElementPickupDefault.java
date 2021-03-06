package net.spellcraftgaming.rpghud.gui.hud.element.defaulthud;

import java.util.List;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.spellcraftgaming.lib.GameData;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElement;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementType;
import net.spellcraftgaming.rpghud.main.ModRPGHud;
import net.spellcraftgaming.rpghud.pickup.ItemPickup;
import net.spellcraftgaming.rpghud.settings.Settings;

public class HudElementPickupDefault extends HudElement {

	public HudElementPickupDefault() {
		super(HudElementType.PICKUP, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return this.mc.playerController.shouldDrawHUD() && this.settings.getBoolValue(Settings.enable_pickup) && !ModRPGHud.instance.pickupHandler.getPickups().isEmpty();
	}

	@Override
	public void drawElement(Gui gui, float zLevel, float partialTicks) {
		ScaledResolution res = new ScaledResolution(this.mc);
		int width = res.getScaledWidth() + this.settings.getPositionValue(Settings.pickup_position)[0];
		int height = res.getScaledHeight() + this.settings.getPositionValue(Settings.pickup_position)[1];
		List<ItemPickup> pickups = ModRPGHud.instance.pickupHandler.getPickups();
		for (int i = 0; i < pickups.size(); i++) {
			ItemPickup pickup = pickups.get(i);
			ItemStack item = pickup.getItem();
			int count = pickup.getCount();
			bind(INTERFACE);
			if (pickup.getTimer() <= 60) {
				GlStateManager.color(1f, 1f, 1f, pickup.getTimer() / 30F);
			}
			gui.drawTexturedModalRect(width - 76, height - 42 - i * 32, 146, 222, 76, 32);
			RenderHelper.enableGUIStandardItemLighting();
			this.mc.getRenderItem().renderItemIntoGUI(item, width - 76 + 8, height - 42 - i * 32 + 8);
			gui.drawString(GameData.getFontRenderer(), "x " + count, width - 76 + 32, height - 42 - i * 32 + 12, -1);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.color(1f, 1f, 1f, 1f);
		}
	}

}
