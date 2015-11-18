package net.reederhome.colin.epicmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.reederhome.colin.epicmod.api.EpicApi;
import net.reederhome.colin.epicmod.items.ItemMotivator;
import net.reederhome.colin.epicmod.powers.PowerBonemeal;
import net.reederhome.colin.epicmod.powers.PowerEnderPort;
import net.reederhome.colin.epicmod.powers.PowerFireResistance;
import net.reederhome.colin.epicmod.powers.PowerJumpBoost;
import net.reederhome.colin.epicmod.powers.PowerSetFire;
import net.reederhome.colin.epicmod.powers.PowerSummonWater;
import net.reederhome.colin.epicmod.powers.PowerTurnToBlock;
import net.reederhome.colin.epicmod.weaknesses.WeaknessFire;
import net.reederhome.colin.epicmod.weaknesses.WeaknessWater;
import net.reederhome.colin.epicmod.weaknesses.effects.EffectDamage;
import net.reederhome.colin.epicmod.weaknesses.effects.EffectHunger;
import net.reederhome.colin.epicmod.weaknesses.effects.EffectSlowness;

@Mod(modid = EpicMod.MODID, name = EpicMod.NAME)
public class EpicMod {

	public static final String MODID = "epicmod";
	public static final String NAME = "Epic Mod";
	
	@Instance
	public static EpicMod instance;
	
	@SidedProxy(serverSide="net.reederhome.colin.epicmod.CommonProxy", clientSide="net.reederhome.colin.epicmod.client.ClientProxy")
	public static CommonProxy proxy;
	
	public static ItemMotivator itemMotivator = new ItemMotivator();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(EpicRegistry.get());
		EpicApi.registerPower(PowerSetFire.class);
		EpicApi.registerPower(PowerTurnToBlock.class, PowerTurnToBlock.baseName);
		EpicApi.registerPower(PowerSummonWater.class);
		EpicApi.registerPower(PowerEnderPort.class);
		EpicApi.registerPower(PowerFireResistance.class);
		EpicApi.registerPower(PowerBonemeal.class);
		EpicApi.registerPower(PowerJumpBoost.class, PowerJumpBoost.baseName);
		EpicApi.registerWeakness(WeaknessWater.class, WeaknessWater.baseName);
		EpicApi.registerWeakness(WeaknessFire.class, WeaknessFire.baseName);
		EpicApi.registerWeaknessEffect(EffectDamage.class);
		EpicApi.registerWeaknessEffect(EffectHunger.class);
		EpicApi.registerWeaknessEffect(EffectSlowness.class);
		
		proxy.things();
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new EpicCommand());
	}
}
