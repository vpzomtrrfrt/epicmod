package net.reederhome.colin.epicmod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
import net.reederhome.colin.epicmod.api.EpicPowerType;
import net.reederhome.colin.epicmod.api.EpicWeaknessType;
import net.reederhome.colin.epicmod.api.IEpicData;
import net.reederhome.colin.epicmod.api.IEpicPower;
import net.reederhome.colin.epicmod.api.IEpicWeakness;
import net.reederhome.colin.epicmod.api.IEpicWeaknessEffect;

public class EpicRegistry {

	private static EpicRegistry instance = null;
	
	private HashMap<String,IEpicData> epicMap;
	private HashMap<String,Class<? extends IEpicPower>> powerMap;
	private HashMap<String,Class<? extends IEpicWeakness>> weaknessMap;
	private HashMap<String,Class<? extends IEpicWeaknessEffect>> effectMap;
	
	@SubscribeEvent
	public void loadPlayer(LoadFromFile event) {
		File f = event.getPlayerFile("epic");
		String name = event.entityPlayer.getCommandSenderName();
		IEpicData ta = null;
		if(f.exists()) {
			try {
				ta = new EpicData(name, f);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(ta == null) {
			ta = new EpicData(name);
		}
	}
	
	@SubscribeEvent
	public void savePlayer(SaveToFile event) {
		File f = event.getPlayerFile("epic");
		String name = event.entityPlayer.getCommandSenderName();
		IEpicData ta = getDataFromPlayer(name);
		try {
			CompressedStreamTools.write(ta.getData(), f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Saving");
	}
	
	public IEpicData getDataFromPlayer(String name) {
		if(epicMap.containsKey(name)) {
			return epicMap.get(name);
		}
		else {
			IEpicData tr =  new EpicData(name);
			epicMap.put(name, tr);
			return tr;
		}
	}
	
	public IEpicData getDataFromPlayer(ICommandSender p) {
		return getDataFromPlayer(p.getCommandSenderName());
	}
	
	public IEpicPower loadPower(String name) {
		try {
			int ind = name.indexOf('.');
			if(ind>-1) {
				String rn = name.substring(0, ind);
				return powerMap.get(rn).getConstructor(String.class).newInstance(name);
			}
			return powerMap.get(name).newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public IEpicWeakness loadWeakness(String name) {
		try {
			int ind = name.indexOf('.');
			if(ind>-1) {
				String rn = name.substring(0, ind);
				return weaknessMap.get(rn).getConstructor(String.class).newInstance(name);
			}
			return weaknessMap.get(name).newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public IEpicWeaknessEffect loadWeaknessEffect(String name) {
		try {
			return effectMap.get(name).newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String[] getKeys(HashMap<String,?> map) {
		Set<String> keys = map.keySet();
		String[] tr = new String[keys.size()];
		keys.toArray(tr);
		return tr;
	}
	
	public IEpicPower randomPower() {
		try {
			String[] keys = getKeys(powerMap);
			return powerMap.get(keys[new Random().nextInt(keys.length)]).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return randomPower();
	}
	
	public IEpicPower randomPower(int maxLevel) {
		try {
			List<IEpicPower> poss = new ArrayList<IEpicPower>();
			Iterator<String> it = powerMap.keySet().iterator();
			while(it.hasNext()) {
				String name = it.next();
				Class<? extends IEpicPower> cl = powerMap.get(name);
				IEpicPower thing = cl.newInstance();
				if(thing.getLevel()<=maxLevel) {
					poss.add(thing);
				}
			}
			return poss.get(new Random().nextInt(poss.size()));
		} catch(InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return randomPower(maxLevel);
	}
	
	public IEpicWeakness randomWeakness() {
		try {
			String[] keys = getKeys(weaknessMap);
			return weaknessMap.get(keys[new Random().nextInt(keys.length)]).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return randomWeakness();
	}
	
	public IEpicWeakness randomWeakness(int minLevel) {
		try {
			List<IEpicWeakness> poss = new ArrayList<IEpicWeakness>();
			Iterator<String> it = weaknessMap.keySet().iterator();
			while(it.hasNext()) {
				String name = it.next();
				Class<? extends IEpicWeakness> cl = weaknessMap.get(name);
				IEpicWeakness thing = cl.newInstance();
				if(thing.getLevel()>=minLevel) {
					poss.add(thing);
				}
			}
			if(poss.size()==0) {
				return randomWeakness();
			}
			return poss.get(new Random().nextInt(poss.size()));
		} catch(InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return randomWeakness(minLevel);
	}
	
	public void registerPower(Class<? extends IEpicPower> c, String n) {
		powerMap.put(n, c);
	}
	
	public void registerWeakness(Class<? extends IEpicWeakness> c, String n) {
		weaknessMap.put(n, c);
	}
	
	public void registerWeaknessEffect(Class<? extends IEpicWeaknessEffect> c, String n) {
		effectMap.put(n, c);
	}
	
	private EpicRegistry() {
		epicMap = new HashMap<String, IEpicData>();
		powerMap = new HashMap<String, Class<? extends IEpicPower>>();
		weaknessMap = new HashMap<String, Class<? extends IEpicWeakness>>();
		effectMap = new HashMap<String, Class<? extends IEpicWeaknessEffect>>();
	}
	
	public static EpicRegistry get() {
		if(instance == null) {
			instance = new EpicRegistry();
		}
		return instance;
	}

	public IEpicWeaknessEffect randomEffect() {
		try {
			String[] keys = getKeys(effectMap);
			return effectMap.get(keys[new Random().nextInt(keys.length)]).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return randomEffect();
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if(event.entity instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)event.entity;
			IEpicData d = EpicRegistry.get().getDataFromPlayer(p);
			if(d.isEpic()) {
				IEpicWeakness weak = d.getWeakness();
				if(weak.getType()==EpicWeaknessType.TICK_CHECK) {
					weak.checkAndApply(event);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onBlockClicked(PlayerInteractEvent event) {
		if(event.entity.worldObj.isRemote) return;
		EntityPlayer p = event.entityPlayer;
		IEpicData d = EpicRegistry.get().getDataFromPlayer(p);
		if(d.isEpic() && p.isSneaking()) {
			int thing = 0;
			if(event.action==PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
				thing++;
			}
			List<IEpicPower> poss = new ArrayList<IEpicPower>();
			IEpicPower[] powerList = d.getPowers();
			for(int i = 0; i < powerList.length; i++) {
				if(powerList[i].getType()==EpicPowerType.USABLE) {
					poss.add(powerList[i]);
				}
			}
			thing = thing % poss.size();
			IEpicPower chosen = poss.get(thing);
			chosen.activatePower(event);
			chosen.deactivatePower(p);
		}
	}
}