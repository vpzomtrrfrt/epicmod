package net.reederhome.colin.epicmod.weaknesses.effects;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.reederhome.colin.epicmod.api.IEpicWeaknessEffect;

public class EffectHunger implements IEpicWeaknessEffect {

	@Override
	public String getName() {
		return "hunger";
	}

	@Override
	public boolean getDisablesPower() {
		return false;
	}

	@Override
	public void apply(EntityLivingBase ent) {
		if(ent instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)ent;
			if(p.getFoodStats().getFoodLevel()>0) {
				p.getFoodStats().addExhaustion(.4f);
				return;
			}
		}
		ent.attackEntityFrom(DamageSource.starve, 0.2f);
	}

	@Override
	public int getLevel() {
		return 1;
	}

	
}