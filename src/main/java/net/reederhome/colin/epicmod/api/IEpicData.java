package net.reederhome.colin.epicmod.api;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.nbt.NBTTagCompound;

public interface IEpicData {

	public boolean isEpic();
	public String getPlayerName();
	public IEpicPower[] getPowers();
	public IEpicWeakness getWeakness();
	public NBTTagCompound getData();
}
