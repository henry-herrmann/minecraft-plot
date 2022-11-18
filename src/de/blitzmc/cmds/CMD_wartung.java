package de.blitzmc.cmds;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_wartung implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("wartung")) {
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("an")) {
					if(p.hasPermission("system.plot.wartung")) {
						File logs = new File("plugins/GS", "Wartungen.yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
						
						cfg.set("wartungen"+ ".status", true);
						try {
							cfg.save(logs);
						} catch (IOException e) {
							
						}
						
						p.sendMessage(Main.pr + "Der Wartungsmodus wurde §eaktiviert!");
					}
				}
				if(args[0].equalsIgnoreCase("aus")) {
					if(p.hasPermission("system.plot.wartung")) {
						File logs = new File("plugins/GS", "Wartungen.yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
						
						cfg.set("wartungen"+ ".status", false);
						try {
							cfg.save(logs);
						} catch (IOException e) {
							
						}
						
						p.sendMessage(Main.pr + "Der Wartungsmodus wurde §edeaktiviert!");
					}
				}
				if(args[0].equalsIgnoreCase("end")) {
					if(p.hasPermission("system.plot.wartung")) {
						File logs = new File("plugins/GS", "Wartungen.yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
						
						cfg.set("end_event"+ ".status", false);
						try {
							cfg.save(logs);
						} catch (IOException e) {
							
						}
						
						p.sendMessage(Main.pr + "Das Ender Dragon Event wurde §edeaktiviert!");
					}
				}
				if(args[0].equalsIgnoreCase("status")) {
					File logs = new File("plugins/GS", "Wartungen.yml");
					YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
					
					p.sendMessage(Main.pr + "Wartungen: §e" + cfg.getString("wartungen" + ".status") );
				}
			}
			if(args.length == 0) {
				p.sendMessage(Main.pr + "§cNutze: /wartung <an,aus,status>");
			}
		}
		return false;
	}
	
	public static void createFile() {
		File logs = new File("plugins/GS", "Wartungen.yml");
		if(!logs.exists()) {
			try{
				logs.createNewFile();
			} catch (Exception e){
				
			}	}
	}
	

}
