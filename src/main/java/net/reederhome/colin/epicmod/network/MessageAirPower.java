package net.reederhome.colin.epicmod.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.reederhome.colin.epicmod.EpicRegistry;
import net.reederhome.colin.epicmod.api.EpicPowerType;
import net.reederhome.colin.epicmod.api.IEpicData;
import net.reederhome.colin.epicmod.api.IEpicPower;

public class MessageAirPower implements IMessage {

	public void fromBytes(ByteBuf arg0) {}
	public void toBytes(ByteBuf arg0) {}
	
	public static class Handler implements IMessageHandler<MessageAirPower, IMessage> {

		@Override
		public IMessage onMessage(MessageAirPower arg0, MessageContext arg1) {
			EntityPlayer p = arg1.getServerHandler().playerEntity;
			IEpicData d = EpicRegistry.get().getDataFromPlayer(p);
			if(d.isEpic()) {
				IEpicPower[] powers = d.getPowers();
				for(int i = 0; i < powers.length; i++) {
					if(powers[i].getType()==EpicPowerType.USABLE_AIR) {
						powers[i].activatePower(new PlayerInteractEvent(p, PlayerInteractEvent.Action.RIGHT_CLICK_AIR, (int)p.posX, (int)p.posY, (int)p.posZ, 0, p.worldObj));
						return null;
					}
				}
			}
			return null;
		}
		
	}

}