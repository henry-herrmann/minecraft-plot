package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class RegionManager {

	public static File logs = new File("plugins/GS", "Regions.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
	
	public static void setRegion(String name, String worldname,  double x1, double x2, double z1,double z2){
		if(!logs.exists()) {
			try{
				logs.createNewFile();
			} catch (Exception e){
				
			}
		}
		
		cfg.set(name + ".Name", name);
		cfg.set(name + ".x1", x1);
		cfg.set(name + ".x2", x2);
		cfg.set(name + ".z1", z1);
		cfg.set(name + ".z2", z2);
		cfg.set(name + ".world", worldname);
		save();
	}
	
	public static boolean exist(String name){
	    return cfg.getString(name) != null;
	}
	
	
	public static String isCreated(double x, double z, String worldname){
		for(String name : cfg.getConfigurationSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      if(cfg.getString(name + ".world").equals(worldname)) {
			    	  return cfg.getString(name + ".Name");
			      }
			}
		}
		return null;
	}
	
	public static boolean creatable(Player p){
		int state = 0;
		
		double x1 = p.getLocation().getX() - 15;
		double z1 = p.getLocation().getZ() - 15;
		
		double x2 = p.getLocation().getX() + 15;
		double z2 = p.getLocation().getZ() - 15;
		
		double x3 = p.getLocation().getX() - 15;
		double z3 = p.getLocation().getZ() + 15;
		
		double x4 = p.getLocation().getX() + 15;
		double z4 = p.getLocation().getZ() + 15;
		
		if(isCreated(x1, z1, p.getWorld().getName()) != null){
			state = state + 1;
		}
		if(isCreated(x2, z2, p.getWorld().getName()) != null){
			state = state + 1;
		}
		if(isCreated(x3, z3, p.getWorld().getName()) != null){
			state = state + 1;
		}
		if(isCreated(x4, z4, p.getWorld().getName()) != null){
			state = state + 1;
		}
		
		if(state == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public static void delRegion(String name){	
		cfg.set(name, null);
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
