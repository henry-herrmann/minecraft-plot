package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_ptime implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("ptime")) {
			if(p.hasPermission("system.plot.ptime")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("day")) {
						p.setPlayerTime(1000, false);
						p.sendMessage(Main.pr + "Your time has been updated to §6DAY");
					}
					if(args[0].equalsIgnoreCase("night")) {
						p.setPlayerTime(13000, false);
						p.sendMessage(Main.pr + "Your time has been updated to §6NIGHT");
					}
				}
			}
		}
		
		return false;
	}
}
