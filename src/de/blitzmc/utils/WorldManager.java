package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class WorldManager {

	public static File logs = new File("plugins/GS", "Worlds.yml");
	public static YamlConfiguration locscfg = YamlConfiguration.loadConfiguration(logs);
	
	public static void addWorld(String worldname) {
		if(!logs.exists()) {
			try{
				logs.createNewFile();
			} catch (Exception e){
				
			}
		}
		
		locscfg.set(worldname + ".name", worldname);
		try {
			locscfg.save(logs);
		} catch (IOException e) {
			
		}
		
	}
	public static void deleteWorld(String worldname) {
		if(!logs.exists()) {
			try{
				logs.createNewFile();
			} catch (Exception e){
				
			}
		}
		locscfg.set(worldname, null);
		try {
			locscfg.save(logs);
		} catch (IOException e) {
			
		}
	}
	
	public static void importWorlds() {
		for (String wns : getConfigSection("").getKeys(false)) {
			WorldCreator wc = new WorldCreator(wns);
			Bukkit.createWorld(wc);
			Bukkit.getWorld(wns).setAmbientSpawnLimit(0);
			Bukkit.getWorld(wns).setAnimalSpawnLimit(0);
			Bukkit.getWorld(wns).setMonsterSpawnLimit(0);
			Bukkit.getWorld(wns).setWaterAnimalSpawnLimit(0);
			Bukkit.getWorld(wns).setStorm(true);
			Bukkit.getWorld(wns).setPVP(true);
			Bukkit.getConsoleSender().sendMessage("§aAlle Welten wurden erfolgreich importiert!");
		}
	}
	
	public static ConfigurationSection getConfigSection(String section){
		
		return locscfg.getConfigurationSection(section);
	}
	
	public static Set<String> getKeys(boolean deep){
		
		return locscfg.getKeys(deep);
	}
}
