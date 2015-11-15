package net.reederhome.colin.epicmod.weaknesses.effects;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.reederhome.colin.epicmod.api.IEpicWeaknessEffect;

public class EffectDamage implements IEpicWeaknessEffect {

	@Override
	public String getName() {
		return "damage";
	}

	@Override
	public boolean getDisablesPower() {
		return false;
	}

	@Override
	public void apply(EntityLivingBase ent) {
		ent.attackEntityFrom(DamageSource.drown, 0.1f);
	}

	@Override
	public int getLevel() {
		return 2;
	}

}
