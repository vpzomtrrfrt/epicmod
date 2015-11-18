package net.reederhome.colin.epicmod;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.reederhome.colin.epicmod.network.MessageAirPower;

public class CommonProxy {

	public SimpleNetworkWrapper netWrap;
	
	public void things() {
		netWrap = NetworkRegistry.INSTANCE.newSimpleChannel(EpicMod.MODID);
		netWrap.registerMessage(MessageAirPower.Handler.class, MessageAirPower.class, 1, Side.SERVER);
	}
}