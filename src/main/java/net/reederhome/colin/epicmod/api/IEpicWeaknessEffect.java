package net.reederhome.colin.epicmod.api;

import net.minecraft.entity.EntityLivingBase;

public interface IEpicWeaknessEffect {

	public String getName();
	public boolean getDisablesPower();
	public void apply(EntityLivingBase ent);
	public int getLevel();
}
