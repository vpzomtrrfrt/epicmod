package net.reederhome.colin.epicmod.api;

import cpw.mods.fml.common.eventhandler.Event;

public interface IEpicWeakness {

	public String getName();
	public EpicWeaknessType getType();
	public boolean getDisablesPower();
	public boolean checkAndApply(Event event);
	public int getLevel();
}