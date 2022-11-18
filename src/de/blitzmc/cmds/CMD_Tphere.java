package de.blitzmc.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_Tphere implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("tphere")){
			if(p.hasPermission("system.plot.tphere")){
			if(args.length == 1){
				try{
					Player p2 = Bukkit.getPlayer(args[0]);
					
					p2.teleport(p);
					p.sendMessage(Main.pr + "You teleported §6" + p2.getName() + "§a to your current location.");
				}
				catch(Exception e){
					p.sendMessage(Main.pr + "§cThe stated player is not online.");
				}
			}
			}else{
				p.sendMessage(Main.noperm);
			}
		}
		return false;
	}

}
