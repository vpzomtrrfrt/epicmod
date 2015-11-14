package net.reederhome.colin.epicmod.api;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.EntityLivingBase;

public interface IEpicPower {

	public String getName();
	public EpicPowerType getType();
	public void activatePower(Event event);
	public void deactivatePower(EntityLivingBase base);
	public int getLevel();
}