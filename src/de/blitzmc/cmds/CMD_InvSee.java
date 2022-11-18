package de.blitzmc.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;


public class CMD_InvSee implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("invsee")){
			if(p.hasPermission("system.plot.invsee")){
				if(args.length == 1){
					if(sender instanceof Player){
						String name = args[0];
						Player target = Bukkit.getPlayer(name);
						
						if(Bukkit.getPlayer(name) == null){
							p.sendMessage(Main.pr + "The stated player is not online.");
							return false;
						}
						
						if(p == target){
							p.sendMessage(Main.pr + "§cYou can't look in your own inventory.");
							return false;
						}
						
						p.openInventory(target.getInventory());
						return true;
					}else{
						p.sendMessage(Main.pr + "§cYou need to be a player.");
					}
				}else{
					p.sendMessage(Main.pr + "§7Syntax §6/invsee <Name>");
				}
			}else{
				p.sendMessage(Main.noperm);
			}
		}
		return false;
	}

}
