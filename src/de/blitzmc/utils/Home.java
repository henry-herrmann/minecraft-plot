package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Home {

	public static void createFile(){
			File logs = new File("plugins/GS", "Homes.yml");
			
			
			if(!logs.exists()) {
				try{
					logs.createNewFile();
				} catch (Exception e){
					
				}
			}
	}
	
	public static boolean fileExists(Player p){
		File logs = new File("plugins/GS", "Homes.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getString(p.getUniqueId().toString()) != null;
	}
	
	public static void addHome(Player p, int number){
		File logs = new File("plugins/GS", "Homes.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		String uuid = p.getUniqueId().toString();
		
		cfg.set(uuid + ".uuid", p.getUniqueId().toString());
		
		if(number == 1) {
			cfg.set(uuid + ".world1", p.getWorld().getName());
			cfg.set(uuid + ".x1", Double.valueOf(p.getLocation().getX()));
			cfg.set(uuid + ".y1", Double.valueOf(p.getLocation().getY()));
			cfg.set(uuid + ".z1", Double.valueOf(p.getLocation().getZ()));
			cfg.set(uuid + ".yaw1", Float.valueOf(p.getLocation().getYaw()));
			cfg.set(uuid + ".pitch1", Float.valueOf(p.getLocation().getPitch()));
		}
		if(number == 2) {
			cfg.set(uuid + ".world2", p.getWorld().getName());
			cfg.set(uuid + ".x2", Double.valueOf(p.getLocation().getX()));
			cfg.set(uuid + ".y2", Double.valueOf(p.getLocation().getY()));
			cfg.set(uuid + ".z2", Double.valueOf(p.getLocation().getZ()));
			cfg.set(uuid + ".yaw2", Float.valueOf(p.getLocation().getYaw()));
			cfg.set(uuid + ".pitch2", Float.valueOf(p.getLocation().getPitch()));
		}
		if(number == 3) {
			cfg.set(uuid + ".world3", p.getWorld().getName());
			cfg.set(uuid + ".x3", Double.valueOf(p.getLocation().getX()));
			cfg.set(uuid + ".y3", Double.valueOf(p.getLocation().getY()));
			cfg.set(uuid + ".z3", Double.valueOf(p.getLocation().getZ()));
			cfg.set(uuid + ".yaw3", Float.valueOf(p.getLocation().getYaw()));
			cfg.set(uuid + ".pitch3", Float.valueOf(p.getLocation().getPitch()));
		}
		
		if(number == 4) {
			cfg.set(uuid + ".world4", p.getWorld().getName());
			cfg.set(uuid + ".x4", Double.valueOf(p.getLocation().getX()));
			cfg.set(uuid + ".y4", Double.valueOf(p.getLocation().getY()));
			cfg.set(uuid + ".z4", Double.valueOf(p.getLocation().getZ()));
			cfg.set(uuid + ".yaw4", Float.valueOf(p.getLocation().getYaw()));
			cfg.set(uuid + ".pitch4", Float.valueOf(p.getLocation().getPitch()));
		}
		if(number == 5) {
			cfg.set(uuid + ".world5", p.getWorld().getName());
			cfg.set(uuid + ".x5", Double.valueOf(p.getLocation().getX()));
			cfg.set(uuid + ".y5", Double.valueOf(p.getLocation().getY()));
			cfg.set(uuid + ".z5", Double.valueOf(p.getLocation().getZ()));
			cfg.set(uuid + ".yaw5", Float.valueOf(p.getLocation().getYaw()));
			cfg.set(uuid + ".pitch5", Float.valueOf(p.getLocation().getPitch()));
		}
		
		
		try {
			cfg.save(logs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean exist(Player p){
		File logs = new File("plugins/GS", "Homes.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getString(p.getUniqueId().toString() + ".uuid") != null;
	}
	
	public static boolean exist1(Player p) {
		File logs = new File("plugins/GS", "Homes.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getString(p.getUniqueId().toString() + ".x1") != null;
	}
	
	public static boolean exist2(Player p) {
		File logs = new File("plugins/GS", "Homes.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getString(p.getUniqueId().toString() + ".x2") != null;
	}
	public static boolean exist3(Player p) {
		File logs = new File("plugins/GS", "Homes.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getString(p.getUniqueId().toString() + ".x3") != null;
	}
	public static boolean exist4(Player p) {
		File logs = new File("plugins/GS", "Homes.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getString(p.getUniqueId().toString() + ".x4") != null;
	}
	public static boolean exist5(Player p) {
		File logs = new File("plugins/GS", "Homes.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getString(p.getUniqueId().toString() + ".x5") != null;
	}
	
	public static Location getLocation(Player p, int number){
		File logs = new File("plugins/GS", "Homes.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		String uuid = p.getUniqueId().toString();
		
		if(number == 1) {
			double x1 = cfg.getDouble(uuid + ".x1");
			double y1 = cfg.getDouble(uuid + ".y1");
			double z1 = cfg.getDouble(uuid + ".z1");
			
			float yaw1 = (float) cfg.getDouble(uuid + ".yaw1");
			float pitch1 = (float) cfg.getDouble(uuid + ".pitch1");
			
			return new Location(Bukkit.getWorld(cfg.getString(uuid + ".world1")), x1, y1, z1, yaw1, pitch1);
		}
		if(number == 2) {
			double x2 = cfg.getDouble(uuid + ".x2");
			double y2 = cfg.getDouble(uuid + ".y2");
			double z2 = cfg.getDouble(uuid + ".z2");
			
			float yaw2 = (float) cfg.getDouble(uuid + ".yaw2");
			float pitch2 = (float) cfg.getDouble(uuid + ".pitch2");
			
			return new Location(Bukkit.getWorld(cfg.getString(uuid + ".world2")), x2, y2, z2, yaw2, pitch2);
		}
		if(number == 3) {
			double x3 = cfg.getDouble(uuid + ".x3");
			double y3 = cfg.getDouble(uuid + ".y3");
			double z3 = cfg.getDouble(uuid + ".z3");
			
			float yaw3 = (float) cfg.getDouble(uuid + ".yaw3");
			float pitch3 = (float) cfg.getDouble(uuid + ".pitch3");
			
			return new Location(Bukkit.getWorld(cfg.getString(uuid + ".world3")), x3, y3, z3, yaw3, pitch3);
		}
		if(number == 4) {
			double x2 = cfg.getDouble(uuid + ".x4");
			double y2 = cfg.getDouble(uuid + ".y4");
			double z2 = cfg.getDouble(uuid + ".z4");
			
			float yaw4 = (float) cfg.getDouble(uuid + ".yaw4");
			float pitch4 = (float) cfg.getDouble(uuid + ".pitch4");
			
			return new Location(Bukkit.getWorld(cfg.getString(uuid + ".world4")), x2, y2, z2, yaw4, pitch4);
		}
		if(number == 5) {
			double x5 = cfg.getDouble(uuid + ".x5");
			double y5 = cfg.getDouble(uuid + ".y5");
			double z5 = cfg.getDouble(uuid + ".z5");
			
			float yaw5 = (float) cfg.getDouble(uuid + ".yaw5");
			float pitch5 = (float) cfg.getDouble(uuid + ".pitch5");
			
			return new Location(Bukkit.getWorld(cfg.getString(uuid + ".world5")), x5, y5, z5, yaw5, pitch5);
		}
		return null;
	}
	
	public static void delHome(Player p, int number){
		File logs = new File("plugins/GS", "Homes.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		if(number == 1) {
			cfg.set(p.getUniqueId().toString() + ".x1", null);
			cfg.set(p.getUniqueId().toString() + ".y1", null);
			cfg.set(p.getUniqueId().toString() + ".z1", null);
			cfg.set(p.getUniqueId().toString() + ".yaw1", null);
			cfg.set(p.getUniqueId().toString() + ".pitch1", null);
			cfg.set(p.getUniqueId().toString() + ".world1", null);
		}
		
		if(number == 2) {
			cfg.set(p.getUniqueId().toString() + ".x2", null);
			cfg.set(p.getUniqueId().toString() + ".y2", null);
			cfg.set(p.getUniqueId().toString() + ".z2", null);
			cfg.set(p.getUniqueId().toString() + ".yaw2", null);
			cfg.set(p.getUniqueId().toString() + ".pitch2", null);
			cfg.set(p.getUniqueId().toString() + ".world2", null);
		}
		if(number == 3) {
			cfg.set(p.getUniqueId().toString() + ".x3", null);
			cfg.set(p.getUniqueId().toString() + ".y3", null);
			cfg.set(p.getUniqueId().toString() + ".z3", null);
			cfg.set(p.getUniqueId().toString() + ".yaw3", null);
			cfg.set(p.getUniqueId().toString() + ".pitch3", null);
			cfg.set(p.getUniqueId().toString() + ".world3", null);
		}
		
		if(number == 4) {
			cfg.set(p.getUniqueId().toString() + ".x4", null);
			cfg.set(p.getUniqueId().toString() + ".y4", null);
			cfg.set(p.getUniqueId().toString() + ".z4", null);
			cfg.set(p.getUniqueId().toString() + ".yaw4", null);
			cfg.set(p.getUniqueId().toString() + ".pitch4", null);
			cfg.set(p.getUniqueId().toString() + ".world4", null);
		}
		if(number == 5) {
			cfg.set(p.getUniqueId().toString() + ".x5", null);
			cfg.set(p.getUniqueId().toString() + ".y5", null);
			cfg.set(p.getUniqueId().toString() + ".z5", null);
			cfg.set(p.getUniqueId().toString() + ".yaw5", null);
			cfg.set(p.getUniqueId().toString() + ".pitch5", null);
			cfg.set(p.getUniqueId().toString() + ".world5", null);
		}
		
		if(cfg.getString(p.getUniqueId().toString() + ".x1") == null && cfg.getString(p.getUniqueId().toString() + ".x2")  == null && cfg.getString(p.getUniqueId().toString() + ".x3")  == null && cfg.getString(p.getUniqueId().toString() + ".x4")  == null && cfg.getString(p.getUniqueId().toString() + ".x5")  == null) {
			cfg.set(p.getUniqueId().toString(), null);
		}
		
		
		try {
			cfg.save(logs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
