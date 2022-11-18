package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.blitzmc.Main;

public class ParticleManager {

	public static File logs = new File("plugins/GS", "Particle.yml");
	public static YamlConfiguration locscfg = YamlConfiguration.loadConfiguration(logs);
	
    public static void setHelix(Player p) {
		if(!logs.exists()) {
			try{
				logs.createNewFile();
			} catch (Exception e){
				
			}
		}
		Location loc = p.getLocation();
		locscfg.set("Helix.X", loc.getX());
		locscfg.set("Helix.Y", loc.getY());
		locscfg.set("Helix.Z", loc.getZ());
		locscfg.set("Helix.World", loc.getWorld().getName());
		try {
			locscfg.save(logs);
		} catch (IOException e) {
			
		}
	}
    
    public static void createHelix(Particle e){
    	if(getHelixLocation() != null){
    		new BukkitRunnable() {
   			 
   			 Location loc = getHelixLocation();
   			 double t = 0;
   			 double r = 1;
   			 double up = 0.11;
   			
   			@Override
   			public void run() {
   				t = t + Math.PI/8;
   				double x = r*Math.cos(t);
   				double y = up*t;
   				double z = r*Math.sin(t);
   				loc.add(x,y,z);
   				loc.subtract(x,y,z);
   				if(t > Math.PI*8){
   					this.cancel();
   				}
   			}
   		}.runTaskTimer(Main.getInstance(), 0, 2);
    	}
	 }
    
    public static Location getHelixLocation(){
    	double x = locscfg.getDouble("Helix.X");
    	double y = locscfg.getDouble("Helix.Y");
    	double z = locscfg.getDouble("Helix.Z");
    	World w = Bukkit.getWorld(locscfg.getString("Helix.World"));
    	
    	Location loc = new Location(w, x, y, z);
    	return loc;
    }
}
