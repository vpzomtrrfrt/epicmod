package net.reederhome.colin.epicmod.powers;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.reederhome.colin.epicmod.api.EpicPowerType;
import net.reederhome.colin.epicmod.api.IEpicPower;

public abstract class PowerPotionEffect implements IEpicPower {

	public abstract PotionEffect getPotionEffect();
	
	@Override
	public EpicPowerType getType() {
		return EpicPowerType.TICK;
	}

	@Override
	public void activatePower(Event event) {
		LivingEvent evt = (LivingEvent)event;
		evt.entityLiving.addPotionEffect(getPotionEffect());
	}

}