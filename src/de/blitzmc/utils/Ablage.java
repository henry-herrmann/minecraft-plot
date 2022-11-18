package de.blitzmc.utils;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import de.blitzmc.Main;

public class Ablage {

	public void Ablage(Player p, String name, String worldname) {
		 File logs = new File("plugins/GS", "Plots.yml");
		 YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		if(p.getUniqueId().toString().equals(cfg.getString(name + ".member"))){
		     double x = cfg.getDouble(name + ".xspawn");
			 double y = cfg.getDouble(name + ".yspawn");
			 double z = cfg.getDouble(name + ".zspawn");
			 
			 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
			    
			 p.teleport(loc);
		}
		if(p.getUniqueId().toString().equals(cfg.getString(name + ".member1"))){
		     double x = cfg.getDouble(name + ".xspawn");
			 double y = cfg.getDouble(name + ".yspawn");
			 double z = cfg.getDouble(name + ".zspawn");
			 
			 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
			    
			 p.teleport(loc);
		}
		if(p.getUniqueId().toString().equals(cfg.getString(name + ".member2"))){
		     double x = cfg.getDouble(name + ".xspawn");
			 double y = cfg.getDouble(name + ".yspawn");
			 double z = cfg.getDouble(name + ".zspawn");
			 
			 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
			    
			 p.teleport(loc);
		}
		Location loc = null;
		ItemStack i2 = null;
		Location lo = new Location(loc.getWorld(), loc.getX(), loc.getY()- 0.5, loc.getZ());
		ArmorStand am = (ArmorStand) lo.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		am.setVisible(false);
		am.setGravity(false);
		am.setCustomNameVisible(true);
		am.setCustomName("§1§l" + i2.getType().toString());  
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			
			@Override
			public void run() {
				am.remove();
				
			}
		},30L);
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onentityclick(PlayerInteractEntityEvent e) {
		File logs1 = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs1);
		
		Player p = e.getPlayer();
		if(e.getRightClicked().getType() == EntityType.VILLAGER) {
			Villager w = (Villager) e.getRightClicked();
			
			if(w.getCustomName().equalsIgnoreCase("§8» §6Arbeitsamt")) {
				e.setCancelled(true);
				Inventar.JobsGui(p, "Jobcenter");
			}
			
			if(w.getCustomName().equalsIgnoreCase("§8» §6Plot")){
				Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						if(p.getUniqueId().toString().equals(pl.getString(p.getUniqueId().toString() + ".uuid"))){
							Inventar.PlotGUI(p, "Plot");
						}else{
							p.sendMessage(Main.pr + "§cDu besitzt noch kein Plot!");
						}
					}
				},1);
			}
			if(w.getCustomName().equals("§8» §6Material-Shop")){
               Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						Inventar.materialienShop(p);
					}
				},1);
			}
		}
	}
}
