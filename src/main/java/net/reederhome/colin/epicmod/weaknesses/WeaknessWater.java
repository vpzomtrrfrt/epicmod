package net.reederhome.colin.epicmod.weaknesses;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraftforge.event.entity.EntityEvent;
import net.reederhome.colin.epicmod.api.EpicWeaknessType;

public class WeaknessWater extends WeaknessBaseEffect {

	public static final String baseName = "water";
	
	@Override
	public EpicWeaknessType getType() {
		return EpicWeaknessType.TICK_CHECK;
	}

	@Override
	public String getBaseName() {
		return baseName;
	}

	@Override
	public boolean checkAndApply(Event event) {
		EntityEvent evt = (EntityEvent)event;
		if(evt.entity.isInWater()) {
			apply(event);
			return true;
		}
		return false;
	}

	@Override
	public int getBaseLevel() {
		return 1;
	}
	
	public WeaknessWater(String name) {super(name);}
	public WeaknessWater() {super();}

}
