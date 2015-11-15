package net.reederhome.colin.epicmod.weaknesses;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.reederhome.colin.epicmod.EpicRegistry;
import net.reederhome.colin.epicmod.api.IEpicWeakness;
import net.reederhome.colin.epicmod.api.IEpicWeaknessEffect;

public abstract class WeaknessBaseEffect implements IEpicWeakness {

	private IEpicWeaknessEffect effect;
	
	public abstract String getBaseName();
	public abstract int getBaseLevel();
	
	protected void construct(String name) {}
	
	@Override
	public String getName() {
		return getBaseName()+"."+effect.getName();
	}

	@Override
	public boolean getDisablesPower() {
		return effect.getDisablesPower();
	}
	
	public WeaknessBaseEffect() {
		effect = EpicRegistry.get().randomEffect();
	}
	
	public WeaknessBaseEffect(String name) {
		effect = EpicRegistry.get().loadWeaknessEffect(name.substring(name.lastIndexOf('.')+1));
		construct(name.substring(0, name.lastIndexOf('.')));
	}
	
	public int getLevel() {
		return getBaseLevel()+effect.getLevel();
	}
	
	public void apply(Event event) {
		LivingEvent evt = (LivingEvent)event;
		effect.apply(evt.entityLiving);
	}

}
