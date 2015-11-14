package net.reederhome.colin.epicmod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
import net.reederhome.colin.epicmod.api.IEpicData;
import net.reederhome.colin.epicmod.api.IEpicPower;
import net.reederhome.colin.epicmod.api.IEpicWeakness;

public class EpicRegistry {

	private static EpicRegistry instance = null;
	
	private HashMap<String,IEpicData> epicMap;
	private HashMap<String,Class<? extends IEpicPower>> powerMap;
	private HashMap<String,Class<? extends IEpicWeakness>> weaknessMap;
	
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
	}
	
	public IEpicData getDataFromPlayer(String name) {
		if(epicMap.containsKey(name)) {
			return epicMap.get(name);
		}
		else {
			return new EpicData(name);
		}
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
	
	public IEpicPower randomPower() {
		try {
			String[] keys = (String[]) powerMap.keySet().toArray();
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
	
	private EpicRegistry() {
		epicMap = new HashMap<String, IEpicData>();
		powerMap = new HashMap<String, Class<? extends IEpicPower>>();
		weaknessMap = new HashMap<String, Class<? extends IEpicWeakness>>();
	}
	
	public static EpicRegistry get() {
		if(instance == null) {
			instance = new EpicRegistry();
		}
		return instance;
	}
}