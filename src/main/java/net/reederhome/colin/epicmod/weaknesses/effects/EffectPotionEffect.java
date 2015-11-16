package net.reederhome.colin.epicmod.weaknesses.effects;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.reederhome.colin.epicmod.api.IEpicWeaknessEffect;

public abstract class EffectPotionEffect implements IEpicWeaknessEffect {

	@Override
	public boolean getDisablesPower() {
		return false;
	}

	public abstract PotionEffect getPotionEffect();
	
	@Override
	public void apply(EntityLivingBase ent) {
		ent.addPotionEffect(getPotionEffect());
	}
}
