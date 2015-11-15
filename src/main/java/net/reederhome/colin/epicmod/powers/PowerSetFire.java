package net.reederhome.colin.epicmod.powers;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.reederhome.colin.epicmod.api.EpicPowerType;
import net.reederhome.colin.epicmod.api.IEpicPower;

public class PowerSetFire implements IEpicPower {

	@Override
	public String getName() {
		return "setFire";
	}

	@Override
	public EpicPowerType getType() {
		return EpicPowerType.USABLE_BLOCK;
	}

	@Override
	public void activatePower(Event event) {
		PlayerInteractEvent evt = (PlayerInteractEvent)event;
		ItemStack stack = new ItemStack(Items.flint_and_steel);
		stack.getItem().onItemUse(stack, evt.entityPlayer, evt.entity.worldObj, evt.x, evt.y, evt.z, evt.face, 0, 0, 0);
		evt.setCanceled(true);
	}

	@Override
	public void deactivatePower(EntityLivingBase ent) {}

	@Override
	public int getLevel() {
		return 1;
	}
	
	public boolean equals(Object o) {
		return o.getClass()==getClass();
	}

}