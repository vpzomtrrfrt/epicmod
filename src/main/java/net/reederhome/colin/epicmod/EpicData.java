package net.reederhome.colin.epicmod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.reederhome.colin.epicmod.api.EpicPowerType;
import net.reederhome.colin.epicmod.api.IEpicData;
import net.reederhome.colin.epicmod.api.IEpicPower;
import net.reederhome.colin.epicmod.api.IEpicWeakness;

public class EpicData implements IEpicData {

	private String name;
	private IEpicPower[] powers;
	private IEpicWeakness weakness;
	
	@Override
	public boolean isEpic() {
		return powers != null;
	}

	@Override
	public String getPlayerName() {
		return name;
	}

	@Override
	public IEpicPower[] getPowers() {
		return powers;
	}

	@Override
	public IEpicWeakness getWeakness() {
		return weakness;
	}

	@Override
	public NBTTagCompound getData() {
		NBTTagCompound tag = new NBTTagCompound();
		if(powers != null && powers.length>0) {
			NBTTagList powerList = new NBTTagList();
			for(int i = 0; i < powers.length; i++) {
				powerList.appendTag(new NBTTagString(powers[i].getName()));
			}
			tag.setTag("Powers", powerList);
			tag.setString("Weakness", weakness.getName());
		}
		return tag;
	}
	
	public EpicData(String name) {
		this.name = name;
	}
	
	private boolean pwrConflict(Collection<IEpicPower> l, IEpicPower power) {
		String pn = power.getName();
		String pns = pn;
		int ind = pn.indexOf('.');
		if(ind>-1) {
			pns = pns.substring(0, ind);
		}
		Iterator<IEpicPower> it = l.iterator();
		while(it.hasNext()) {
			IEpicPower c = it.next();
			String cn = c.getName();
			String cns = cn;
			int ind2 = cn.indexOf('.');
			if(ind2>-1) {
				cns = cn.substring(0, ind2);
			}
			if(cn.equalsIgnoreCase(pn) || (c.selfConflict() && cns.equalsIgnoreCase(pns))) {
				return true;
			}
		}
		return false;
	}
	
	public void makeEpic() {
		if(!isEpic()) {
			List<IEpicPower> l = new ArrayList<IEpicPower>();
			l.add(EpicRegistry.get().randomPower());
			int curLvl = l.get(0).getLevel();
			weakness = EpicRegistry.get().randomWeakness(curLvl);
			int skipped = 0;
			int blocks = 0;
			int blocks2 = 0;
			if(l.get(0).getType() == EpicPowerType.USABLE_AIR) {
				blocks++;
			}
			if(l.get(0).getType() == EpicPowerType.USABLE_BLOCK) {
				blocks2++;
				blocks++;
			}
			while(curLvl<weakness.getLevel() && skipped < 10) {
				IEpicPower pwr = EpicRegistry.get().randomPower(weakness.getLevel()+1-curLvl);
				if(!pwrConflict(l, pwr) && (blocks<3 || (pwr.getType() != EpicPowerType.USABLE_AIR && pwr.getType() != EpicPowerType.USABLE_BLOCK)) && (pwr.getType() != EpicPowerType.USABLE_BLOCK || blocks2<2)) {
					l.add(pwr);
					if(pwr.getType() == EpicPowerType.USABLE_AIR) {
						blocks++;
					}
					if(pwr.getType() == EpicPowerType.USABLE_BLOCK) {
						blocks2++;
						blocks++;
					}
				}
				else {
					skipped++;
				}
			}
			powers = new IEpicPower[l.size()];
			powers = l.toArray(powers);
			System.out.println(isEpic());
		}
	}
	
	public EpicData(String name, NBTTagCompound tag) {
		this(name);
		if(tag.hasKey("Powers")) {
			NBTTagList powerList = (NBTTagList)tag.getTag("Powers");
			powers = new IEpicPower[powerList.tagCount()];
			for(int i = 0; i < powers.length; i++) {
				powers[i] = EpicRegistry.get().loadPower(powerList.getStringTagAt(i));
			}
			weakness = EpicRegistry.get().loadWeakness(tag.getString("Weakness"));
		}
	}
	
	public EpicData(String name, File f) throws IOException {
		this(name, CompressedStreamTools.read(f));
	}

}
