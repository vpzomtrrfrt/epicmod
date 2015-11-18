package net.reederhome.colin.epicmod.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.reederhome.colin.epicmod.EpicMod;

public class ItemMotivator extends Item {

	public ItemMotivator() {
		setTextureName(EpicMod.MODID+":motivator");
		setUnlocalizedName("motivator");
		setMaxStackSize(1);
	}
	
	public void addInformation(ItemStack stack, EntityPlayer p, List l, boolean b) {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag != null && tag.hasKey("Power")) {
			l.add("Essence of an Epic's power");
			l.add("Not very useful currently");
			if(b) {
				l.add(tag.getString("Power"));
			}
		}
		else {
			l.add("INVALID ITEM");
			l.add("How did this happen?");
		}
	}
}
