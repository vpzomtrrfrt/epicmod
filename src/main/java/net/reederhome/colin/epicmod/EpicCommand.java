package net.reederhome.colin.epicmod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class EpicCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "epic";
	}

	@Override
	public String getCommandUsage(ICommandSender arg0) {
		return "/epic <grant|check> [player]";
	}

	@Override
	public void processCommand(ICommandSender arg0, String[] arg1) {
		if(arg1.length>0 && arg1.length<3) {
			if(arg1[0].equalsIgnoreCase("grant")) {
				EntityPlayer player;
				if(arg1.length>1) {
					player = getPlayer(arg0, arg1[1]);
				}
				else {
					player = getCommandSenderAsPlayer(arg0);
				}
				((EpicData)EpicRegistry.get().getDataFromPlayer(player)).makeEpic();
				if(EpicRegistry.get().getDataFromPlayer(player).isEpic()) arg0.addChatMessage(new ChatComponentText("Epicification complete!"));
			}
			else if(arg1[0].equalsIgnoreCase("check")) {
				EntityPlayer player;
				if(arg1.length>1) {
					player = getPlayer(arg0, arg1[1]);
				}
				else {
					player = getCommandSenderAsPlayer(arg0);
				}
				String tr = EpicRegistry.get().getDataFromPlayer(player).isEpic()?"That player is an epic.":"That player is not an epic.";
				player.addChatMessage(new ChatComponentText(tr));
			}
			else {
				throw new WrongUsageException("No such action");
			}
		}
		else {
			throw new WrongUsageException(getCommandUsage(null));
		}
	}

}
