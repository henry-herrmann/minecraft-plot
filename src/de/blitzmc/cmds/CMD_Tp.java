package de.blitzmc.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_Tp implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("tp")){
			if(p.hasPermission("system.plot.tp")){
				if(args.length == 1){
					try{
						Player p2 = Bukkit.getPlayer(args[0]);
						
						p.teleport(p2);
						p.sendMessage(Main.pr + "You teleported to §6" + p2.getName());
					}
					catch(Exception e){
						p.sendMessage(Main.pr + "§cThe stated player is not online!");
					}
				}
				if(args.length == 4){
					double x = Double.parseDouble(args[0]);
					double y = Double.parseDouble(args[1]);
					double z = Double.parseDouble(args[2]);
					
					
					Location loc = new Location(Bukkit.getWorld(args[3]), x, y, z);
					
					p.teleport(loc);
				}
				if(args.length == 3) {
					p.sendMessage(Main.pr + "§cNutze: /tp <x>. <y>, <z>, <world>");
				}
			}else{
				p.sendMessage(Main.noperm);
			}
		}
		
		return false;
	}	
}
