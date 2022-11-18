package de.blitzmc.cmds;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.nms.Title;

public class CMD_Build implements CommandExecutor{
	public static ArrayList<Player> buildmode = new ArrayList<>();
	Title title = new Title();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("build")){
			if(p.hasPermission("system.plot.build")){
				if(args.length == 0){
					if(buildmode.contains(p)){
						buildmode.remove(p);
						p.sendMessage(Main.pr + "BuildMode §cdeaktiviert§a!");
					}else{
						buildmode.add(p);
						p.sendMessage(Main.pr + "BuildMode aktiviert!");
					}
				}
				
			}
			if(args.length == 1) {
				if(p.hasPermission("system.plot.build.others")) {
					Player t = Bukkit.getPlayer(args[0]);
					
					if(t != null) {
						if(buildmode.contains(t)){
							buildmode.remove(t);
							t.sendMessage(Main.pr + "Build mode §cdeaktiviert§a!");
							p.sendMessage(Main.pr + "BuildMode für §6" + t.getName() + "§c deaktiviert§a!");
						}else{
							buildmode.add(t);
							t.sendMessage(Main.pr + "Build mode aktiviert!");
							p.sendMessage(Main.pr + "BuildMode für §6" + t.getName() + "§a aktiviert!");
						}
					}else {
						p.sendMessage(Main.pr + "§cDer Spieler ist nicht online!");
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("me")) {
			p.sendMessage("*§cINTERNAL ERROR, FETCHING IP ADDRESS, SECURE TLS CONNECTION TO YOUR ROUTER ESTABLISHED");
		}
		return false;
	}

}
