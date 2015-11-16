package net.reederhome.colin.epicmod.powers;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
		PlayerInteractEvent evt = (PlayerInteractEvent)event;
		int range = 4;
		int dx = evt.x+new Random().nextInt(range*2)-range;
		int dy = evt.y+new Random().nextInt(range*2)-range;
		int dz = evt.z+new Random().nextInt(range*2)-range;
		while(!evt.world.isAirBlock(dx, dy, dz) || !evt.world.isAirBlock(dx, dy+1, dz)) {
			dy++;
		}
		while(evt.world.isAirBlock(dx, dy-1, dz)) {
			dy--;
		}
		evt.entityPlayer.setPositionAndUpdate(dx, dy, dz);
	}


	@Override
	public int getLevel() {
		return 3;
	}

	@Override
	public boolean selfConflict() {return false;}
}
