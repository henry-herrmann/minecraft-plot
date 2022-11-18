package de.blitzmc.cmds;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_Banlist implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		
		File logs = new File("plugins/GS", "Banlist.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		
		if(cmd.getName().equalsIgnoreCase("banlist")) {
			if(p.hasPermission("system.tempban")) {
				if(args.length == 0) {
					for(String playername : getConfigSection("").getKeys(false)) {
						if(cfg.getBoolean(playername + ".status") == true) {
							if(cfg.getBoolean(playername+ ".perm") == false){
								p.sendMessage("- §6" + playername + "§a Bis: §6" + cfg.getLong(playername + ".dauer"));
							}else {
								p.sendMessage("- §6" + playername + "§a Bis: §6PERMBAN");
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static ConfigurationSection getConfigSection(String section){
		File logs = new File("plugins/GS", "Banlist.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getConfigurationSection(section);
	}
	
	
	
	public static Set<String> getKeys(boolean deep){
		File logs = new File("plugins/GS", "Banlist.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getKeys(deep);
	}
}
