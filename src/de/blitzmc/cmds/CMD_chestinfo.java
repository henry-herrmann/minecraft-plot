package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_chestinfo implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("chestinfo")) {
			if(!de.blitzmc.Main.chestinfo.contains(p)) {
				Main.chestinfo.add(p);
				p.sendMessage(Main.pr + "§cKlicke auf eine Shop-Kiste!");
			}else {
				p.sendMessage(Main.pr + "§cKlicke auf eine Kiste!"); 
			}
		}
		return false;
	}

}
