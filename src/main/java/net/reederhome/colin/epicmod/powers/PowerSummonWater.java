package net.reederhome.colin.epicmod.powers;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.reederhome.colin.epicmod.api.EpicPowerType;
import net.reederhome.colin.epicmod.api.IEpicPower;

public class PowerSummonWater implements IEpicPower {

	@Override
	public String getName() {
		return "water";
	}

	@Override
	public EpicPowerType getType() {
		return EpicPowerType.USABLE;
	}

	@Override
	public void activatePower(Event event) {
		PlayerInteractEvent evt = (PlayerInteractEvent)event;
		ItemStack stack = new ItemStack(Items.water_bucket);
		stack.getItem().onItemRightClick(stack, evt.world, evt.entityPlayer);
		event.setCanceled(true);
	}

	@Override
	public void deactivatePower(EntityLivingBase base) {}

	@Override
	public int getLevel() {
		return 1;
	}

}