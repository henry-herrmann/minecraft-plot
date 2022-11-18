package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.TPSpawn;

public class CMD_Spawn implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(cmd.getName().equalsIgnoreCase("spawn")){
			TPSpawn.tpSpawn((Player) sender);
			sender.sendMessage(Main.pr + "Teleported to the Spawn!");
		}
		return false;
	}

	
}
