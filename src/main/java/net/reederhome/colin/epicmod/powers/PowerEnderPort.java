package net.reederhome.colin.epicmod.powers;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.reederhome.colin.epicmod.api.EpicPowerType;
import net.reederhome.colin.epicmod.api.IEpicPower;

public class PowerEnderPort implements IEpicPower {

	@Override
	public String getName() {
		return "enderport";
	}

	@Override
	public EpicPowerType getType() {
		return EpicPowerType.USABLE_AIR;
	}

	@Override
	public void activatePower(Event event) {
		LivingEvent evt = (LivingEvent)event;
		int range = 8;
		int dx = (int)evt.entity.posX+new Random().nextInt(range*2)-range;
		int dy = (int)evt.entity.posY+new Random().nextInt(range*2)-range;
		int dz = (int)evt.entity.posZ+new Random().nextInt(range*2)-range;
		while(!evt.entity.worldObj.isAirBlock(dx, dy, dz) || !evt.entity.worldObj.isAirBlock(dx, dy+1, dz)) {
			dy++;
		}
		while(evt.entity.worldObj.isAirBlock(dx, dy-1, dz)) {
			dy--;
		}
		evt.entityLiving.setPositionAndUpdate(dx, dy, dz);
	}


	@Override
	public int getLevel() {
		return 3;
	}

	@Override
	public boolean selfConflict() {return false;}
}
