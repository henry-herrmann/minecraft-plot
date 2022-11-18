package de.blitzmc.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_WB implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("wb")){
			if(p.hasPermission("system.plot.workbench")){
				p.openWorkbench(null, true);
				
			}else{
				p.sendMessage(Main.noperm);
			}
		}
		if(cmd.getName().equalsIgnoreCase("ec")){
			if(args.length == 0){
			if(p.hasPermission("system.plot.enderchest")){
				p.openInventory(p.getEnderChest());
			}else{
				p.sendMessage(Main.noperm);
			}
			}else if(args.length == 1){
				if(p.hasPermission("system.plot.enderchest.admin")){
				try{
					Player p2 = Bukkit.getPlayer(args[0]);
					
					p.openInventory(p2.getEnderChest());
				}
				catch(Exception e){
					p.sendMessage(Main.pr + "§cThe stated player is not online!");
				}
				}else{
					p.sendMessage(Main.noperm);
				}
			}
		}
	return false;	
	}
}
