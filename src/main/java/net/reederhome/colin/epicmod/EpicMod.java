package net.reederhome.colin.epicmod;

import java.io.File;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
import net.reederhome.colin.epicmod.api.EpicApi;
import net.reederhome.colin.epicmod.powers.PowerSetFire;

@Mod(modid = EpicMod.MODID, name = EpicMod.NAME)
public class EpicMod {

	public static final String MODID = "epicmod";
	public static final String NAME = "Epic Mod";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(EpicRegistry.get());
		EpicApi.registerPower(PowerSetFire.class);
	}
}
