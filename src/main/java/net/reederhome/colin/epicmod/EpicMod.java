package net.reederhome.colin.epicmod;

import java.io.File;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
import net.reederhome.colin.epicmod.api.EpicApi;
import net.reederhome.colin.epicmod.powers.PowerEnderPort;
import net.reederhome.colin.epicmod.powers.PowerFireResistance;
import net.reederhome.colin.epicmod.powers.PowerJumpBoost;
import net.reederhome.colin.epicmod.powers.PowerSetFire;
import net.reederhome.colin.epicmod.powers.PowerSummonWater;
import net.reederhome.colin.epicmod.powers.PowerTurnToBlock;
import net.reederhome.colin.epicmod.weaknesses.WeaknessWater;
import net.reederhome.colin.epicmod.weaknesses.effects.EffectDamage;

@Mod(modid = EpicMod.MODID, name = EpicMod.NAME)
public class EpicMod {

	public static final String MODID = "epicmod";
	public static final String NAME = "Epic Mod";
	
	@Instance
	public static EpicMod instance;
	
	@SidedProxy(serverSide="net.reederhome.colin.epicmod.CommonProxy", clientSide="net.reederhome.colin.epicmod.client.ClientProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(EpicRegistry.get());
		EpicApi.registerPower(PowerSetFire.class);
		EpicApi.registerPower(PowerTurnToBlock.class, PowerTurnToBlock.baseName);
		EpicApi.registerPower(PowerSummonWater.class);
		EpicApi.registerPower(PowerEnderPort.class);
		EpicApi.registerPower(PowerFireResistance.class);
		EpicApi.registerPower(PowerJumpBoost.class, PowerJumpBoost.baseName);
		EpicApi.registerWeakness(WeaknessWater.class, WeaknessWater.baseName);
		EpicApi.registerWeaknessEffect(EffectDamage.class);
		
		proxy.things();
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new EpicCommand());
	}
}
