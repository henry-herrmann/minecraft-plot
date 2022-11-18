package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_pl implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("pl")) {
			p.sendMessage("Plugins: §aSelf-scripted§r, §anot worth searching on the internet");
		}
		if(cmd.getName().equalsIgnoreCase("?")) {
			p.sendMessage(Main.pr + "§cNutze /tutorial");
		}
		if(cmd.getName().equalsIgnoreCase("plugins")) {
			p.sendMessage("Plugins: §aSelf-scripted§r, §anot worth searching on the internet");
		}
		return false;
	}

}
