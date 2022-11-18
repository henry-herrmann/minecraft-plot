package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PendingMessages {

	public static File logs = new File("plugins/GS", "Messages.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
	
	public static void setMessage(String uuid, String msg) {
		if(!logs.exists()) {
			try{
				logs.createNewFile();
			} catch (Exception e){
				
			}
		}
		
		cfg.set(uuid + ".msg", msg);
		save();
	}
	
	public static String getMessage(String uuid) {
		return cfg.getString(uuid + "msg");
	}
	public static void delMessage(String uuid, String msg) {
		if(!logs.exists()) {
			try{
				logs.createNewFile();
			} catch (Exception e){
				
			}
		}
		cfg.set(uuid+ ".msg", null);
		save();
	}
	
	public static void save(){
		try {
			cfg.save(logs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
