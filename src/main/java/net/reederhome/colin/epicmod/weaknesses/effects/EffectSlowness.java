package net.reederhome.colin.epicmod.weaknesses.effects;

import java.util.Random;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EffectSlowness extends EffectPotionEffect {
	
	@Override
	public String getName() {
		return "slowness";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PotionEffect getPotionEffect() {
		return new PotionEffect(Potion.moveSlowdown.id, 1, 1);
	}

}