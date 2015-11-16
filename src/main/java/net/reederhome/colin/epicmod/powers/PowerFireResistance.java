package net.reederhome.colin.epicmod.powers;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PowerFireResistance extends PowerPotionEffect {

	@Override
	public String getName() {
		return "fireResistance";
	}

	@Override
	public int getLevel() {
		return 2;
	}

	@Override
	public PotionEffect getPotionEffect() {
		return new PotionEffect(Potion.fireResistance.id, 2);
	}
	
	@Override
	public boolean selfConflict() {return false;}

}
