package net.reederhome.colin.epicmod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
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
	
	public void makeEpic() {
		if(!isEpic()) {
			List<IEpicPower> l = new ArrayList<IEpicPower>();
			l.add(EpicRegistry.get().randomPower());
			int curLvl = l.get(0).getLevel();
			weakness = EpicRegistry.get().randomWeakness(curLvl);
			while(curLvl<weakness.getLevel()) {
				IEpicPower pwr = EpicRegistry.get().randomPower(weakness.getLevel()+1-curLvl);
				if(!l.contains(pwr)) {
					l.add(pwr);
				}
			}
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
