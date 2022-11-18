package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetSpawn {

	public static File logs = new File("plugins/GS", "Spawns.yml");
	public static YamlConfiguration locscfg = YamlConfiguration.loadConfiguration(logs);
	
    public static void setSpawn(Player p) {
		if(!logs.exists()) {
			try{
				logs.createNewFile();
			} catch (Exception e){
				
			}
		}
		Location loc = p.getLocation();
		locscfg.set("Spawn.X", loc.getX());
		locscfg.set("Spawn.Y", loc.getY());
		locscfg.set("Spawn.Z", loc.getZ());
		locscfg.set("Spawn.Yaw", loc.getYaw());
		locscfg.set("Spawn.Pitch", loc.getPitch());
		locscfg.set("Spawn.World", loc.getWorld().getName());
		try {
			locscfg.save(logs);
		} catch (IOException e) {
			
		}
	}
    public static void setFarmwelt(Player p){
    	if(!logs.exists()){
    		try {
				logs.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	Location loc = p.getLocation();
    	locscfg.set("Farmwelt.X", loc.getX());
    	locscfg.set("Farmwelt.Y", loc.getY());
    	locscfg.set("Farmwelt.Z", loc.getZ());
    	locscfg.set("Farmwelt.Pitch", loc.getPitch());
    	locscfg.set("Farmwelt.Yaw", loc.getYaw());
    	locscfg.set("Farmwelt.World", loc.getWorld().getName());
    	try {
			locscfg.save(logs);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public static void setNether(Player p){
    	if(!logs.exists()){
    		try {
				logs.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	Location loc = p.getLocation();
    	locscfg.set("Nether.X", loc.getX());
    	locscfg.set("Nether.Y", loc.getY());
    	locscfg.set("Nether.Z", loc.getZ());
    	locscfg.set("Nether.Pitch", loc.getPitch());
    	locscfg.set("Nether.Yaw", loc.getYaw());
    	locscfg.set("Nether.World", loc.getWorld().getName());
    	try {
			locscfg.save(logs);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void setEnd(Player p){
    	if(!logs.exists()){
    		try {
				logs.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	Location loc = p.getLocation();
    	locscfg.set("End.X", loc.getX());
    	locscfg.set("End.Y", loc.getY());
    	locscfg.set("End.Z", loc.getZ());
    	locscfg.set("End.Pitch", loc.getPitch());
    	locscfg.set("End.Yaw", loc.getYaw());
    	locscfg.set("End.World", loc.getWorld().getName());
    	try {
			locscfg.save(logs);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public static void setMarkt(Player p){
    	if(!logs.exists()){
    		try {
				logs.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	Location loc = p.getLocation();
    	locscfg.set("Markt.X", loc.getX());
    	locscfg.set("Markt.Y", loc.getY());
    	locscfg.set("Markt.Z", loc.getZ());
    	locscfg.set("Markt.Pitch", loc.getPitch());
    	locscfg.set("Markt.Yaw", loc.getYaw());
    	locscfg.set("Markt.World", loc.getWorld().getName());
    	try {
			locscfg.save(logs);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
