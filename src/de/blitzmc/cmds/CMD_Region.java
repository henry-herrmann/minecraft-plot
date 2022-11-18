package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.listeners.InteractEvent;
import de.blitzmc.utils.RegionManager;

public class CMD_Region implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2, String[] args) {
		if(cmd.getName().equalsIgnoreCase("rg")){
			Player p = (Player) s;
			if(p.hasPermission("system.plot.regions")){
				if(args.length == 2){
					if(args[0].equalsIgnoreCase("create")){
						if(InteractEvent.locs.get(p) != null){
							if(InteractEvent.locs.get(p)[0] != null && InteractEvent.locs.get(p)[1] != null){
								if(!RegionManager.exist(args[1])){
									RegionManager.setRegion(args[1],p.getWorld().getName() ,InteractEvent.locs.get(p)[0].getBlockX(), InteractEvent.locs.get(p)[1].getBlockX(), InteractEvent.locs.get(p)[0].getBlockZ(), InteractEvent.locs.get(p)[1].getBlockZ());
									InteractEvent.locs.remove(p);
									p.sendMessage(Main.pr + "Du hast die Region §6" + args[1] + "§a gesetzt!");
								}else{
									p.sendMessage(Main.pr + "§cDiese Region existiert schon!");
								}
			
							}
						}else{
							p.sendMessage(Main.pr + "§cMakiere zuerst einen Bereich!");
						}
					}else if(args[0].equalsIgnoreCase("delete")){
							if(RegionManager.exist(args[1])){
								RegionManager.delRegion(args[1]);
								p.sendMessage(Main.pr + "Du hast die Region §6" + args[1] + "§a gelöscht!");
							}else{
								p.sendMessage(Main.pr + "§cDiese Region existiert nicht!");
							}
					}
				}
			}
		}
		return false;
	}

}
