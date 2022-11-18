package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MessageManager {
	
	public static File logs = new File("plugins/GS", "Messages.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);

	public static void setMessage(Player p, String bool) {
		
		if(!logs.exists()) {
			try{
				logs.createNewFile();
			} catch (Exception e){
				
			}
		}
		
		cfg.set(p.getUniqueId().toString() + ".nachrichten", bool);
		save();
	}
	
	public static boolean getMessageStatus(Player p) {
		
		if(cfg.getString(p.getUniqueId().toString() + ".nachrichten") == "true") {
			return true;
		}else {
			return false;
		}
	}
	
	public static void save(){
		try {
			cfg.save(logs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
