package net.reederhome.colin.epicmod.powers;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.reederhome.colin.epicmod.api.EpicPowerType;
import net.reederhome.colin.epicmod.api.IEpicPower;

public class PowerTurnToBlock implements IEpicPower {

	public static final String baseName = "turnTo";
	
	private static final String[] blocks = {"leaves", "cobblestone"};
	
	private String block;
	
	public PowerTurnToBlock() {
		block = blocks[new Random().nextInt(blocks.length)];
	}
	
	public PowerTurnToBlock(String name) {
		block = name.substring(name.indexOf('.')+1);
	}
	
	@Override
	public String getName() {
		return baseName+"."+block;
	}

	@Override
	public EpicPowerType getType() {
		return EpicPowerType.USABLE_BLOCK;
	}

	@Override
	public void activatePower(Event event) {
		PlayerInteractEvent evt = (PlayerInteractEvent)event;
		evt.entity.worldObj.setBlock(evt.x, evt.y, evt.z, Block.getBlockFromName(block));
	}

	@Override
	public void deactivatePower(EntityLivingBase base) {}

	@Override
	public int getLevel() {
		return 2;
	}

}