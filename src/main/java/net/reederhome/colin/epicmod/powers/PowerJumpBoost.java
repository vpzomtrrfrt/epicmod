package net.reederhome.colin.epicmod.powers;

import java.util.Random;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PowerJumpBoost extends PowerPotionEffect {

	private int lvl;
	public static final String baseName = "jumpBoost";
	
	@Override
	public String getName() {
		return baseName+"."+getLevel();
	}

	@Override
	public int getLevel() {
		return lvl;
	}

	@Override
	public PotionEffect getPotionEffect() {
		return new PotionEffect(Potion.jump.id, 1, getLevel()*2);
	}

	public PowerJumpBoost() {
		lvl = new Random().nextInt(4)+1;
	}
}