package net.reederhome.colin.epicmod.weaknesses;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraftforge.event.entity.EntityEvent;
import net.reederhome.colin.epicmod.api.EpicWeaknessType;

public class WeaknessFire extends WeaknessBaseEffect {

	public static final String baseName = "fire";
	
	@Override
	public EpicWeaknessType getType() {
		return EpicWeaknessType.TICK_CHECK;
	}

	@Override
	public boolean checkAndApply(Event event) {
		EntityEvent evt = (EntityEvent) event;
		if(evt.entity.isBurning()) {
			apply(event);
			return true;
		}
		return false;
	}

	@Override
	public String getBaseName() {
		return baseName;
	}

	@Override
	public int getBaseLevel() {
		return 1;
	}

}
