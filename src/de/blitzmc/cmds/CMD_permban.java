package de.blitzmc.cmds;


import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.BanManager;

public class CMD_permban implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("permban")) {
			if(p.hasPermission("system.permban")) {
				if(args.length == 0 | args.length == 1) {
					p.sendMessage(Main.pr + "§cNutze: /permban <Spieler> [Grund]");
					return true;
				}
				
				if(args.length == 2) {
					String reason = "";
					
					for(int i = 1; i< args.length; i++) {
						reason = reason + args[i] + " ";
					}
					
					reason.trim();
					
					BanManager bm = new BanManager(BanManager.getUUIDFromName(args[0]));
					
					if(!bm.exist()) {
						p.sendMessage(Main.pr + "§cKeine Akte vorhanden!");
						return true;
					}
					
					bm.setPermBanned(reason.isEmpty() ? "Fehlverhalten" : reason);
					
					
					Player t = Bukkit.getPlayer(args[0]);
					
					File logs = new File("plugins/GS", "Banlist.yml");
					YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
					
					
					if(!logs.exists()) {
						try{
							logs.createNewFile();
						} catch (Exception e){
							
						}
					}
					
					cfg.set(t.getName() + ".status", true);
					cfg.set(t.getName() + ".perm", true);
					
					try {
						cfg.save(logs);
					} catch (IOException e) {
						
					}
					
					if(t != null) {
						t.kickPlayer("§cDu wurdest §ePERMANENT §cgesperrt \n\n Grund: §e" + bm.getPermbanReason());
						
						p.sendMessage(Main.pr + "Du hast den Spieler §e" + args[0] + " §aerfolgreich §ePERMANENT §agesperrt!");
					}else {
						p.sendMessage(Main.pr + "§cDer Spieler ist nicht online!");
					}
				}
			}
		}
		return false;
	}

}
