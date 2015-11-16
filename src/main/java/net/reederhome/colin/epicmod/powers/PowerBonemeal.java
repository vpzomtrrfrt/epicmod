package net.reederhome.colin.epicmod.powers;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.reederhome.colin.epicmod.api.EpicPowerType;
import net.reederhome.colin.epicmod.api.IEpicPower;

public class PowerBonemeal implements IEpicPower {

	@Override
	public boolean selfConflict() {
		return false;
	}

	@Override
	public String getName() {
		return "bonemeal";
	}

	@Override
	public EpicPowerType getType() {
		return EpicPowerType.USABLE_BLOCK;
	}

	@Override
	public void activatePower(Event event) {
		PlayerInteractEvent evt = (PlayerInteractEvent)event;
		ItemDye.func_150919_a(new ItemStack(Items.dye, 1, 15), evt.world, evt.x, evt.y, evt.z);
	}

	@Override
	public int getLevel() {
		return 2;
	}

	
}