package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.PlotManager;

public class CMD_sperren implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sperren")) {
			Player p = (Player) sender;
			
			if(p.hasPermission("system.sperren")) {
				if(args.length == 2) {
					PlotManager.setSperrgebiet(args[0], args[1]);
					p.sendMessage(Main.pr + "Das Plot §6" + args[0] + "§awirde zum Sperrgebiet erklärt!");
				}else {
					p.sendMessage(Main.pr + "§cNutze: /sperren <Plot-Name> <true,false>");
				}
			}else {
				p.sendMessage(Main.noperm);
			}
		}
		return false;
	}

	
}
