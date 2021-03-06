package net.reederhome.colin.epicmod.powers;

import java.util.Random;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PowerJumpBoost extends PowerPotionEffect {

	private int lvl;
	public static final String baseName = "jumpBoost";
	public boolean selfConflict = true;
	
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
		return new PotionEffect(Potion.jump.id, 2, getLevel()*2);
	}

	public PowerJumpBoost() {
		lvl = new Random().nextInt(4)+1;
	}
	
	public PowerJumpBoost(String name) {
		lvl = Integer.parseInt(name.substring(name.indexOf('.')+1));
	}
	
	@Override
	public boolean selfConflict() {return true;}
}