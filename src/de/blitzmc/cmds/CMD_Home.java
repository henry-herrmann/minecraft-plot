package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.Home;
import de.blitzmc.utils.Inventar;

public class CMD_Home implements CommandExecutor{
	
	public static Home m;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		
		if(cmd.getName().equalsIgnoreCase("home")){
			Inventar.HomeGui(p);
		}else{
			p.sendMessage(Main.pr + "§cSyntax: /home!");
		}
		return false;
	}

}
