package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import de.blitzmc.Main;


public class PlotManager implements Listener{

	 private static String worldname = "world";
	 
	 
	 public static void setPlot(Player p, String name, int size) {		 
			
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
			
			String uuid = p.getUniqueId().toString();
			
			if(!logs.exists()) {
				try{
					logs.createNewFile();
				} catch (Exception e){
					
				}	}
			
			double x1 = p.getLocation().getBlockX() - size;
			double z1 = p.getLocation().getBlockZ() - size;
			double x2 = p.getLocation().getBlockX() + size;
			double z2 = p.getLocation().getBlockZ() + size;
			
			
			cfg.set(name + ".owner", uuid);
			cfg.set(name + ".name", p.getName());
			cfg.set(name + ".x1", x1);
			cfg.set(name + ".z1", z1);
			cfg.set(name + ".x2", x2);
			cfg.set(name + ".z2", z2);
			cfg.set(name + ".xspawn", p.getLocation().getX());
			cfg.set(name + ".yspawn", p.getLocation().getY());
			cfg.set(name + ".zspawn", p.getLocation().getZ());
			cfg.set(name + ".flags.block-break", false);
			cfg.set(name + ".flags.block-place", false);
			cfg.set(name + ".flags.pvp", false);
			cfg.set(name + ".flags.interact", false);
			cfg.set(name + ".flags.interact-animals", false);
			cfg.set(name + ".flags.mob-grief", false);
			cfg.set(name + ".flags.mob-spawn", true);
			cfg.set(name + ".flags.tnt", false);
			cfg.set(name + ".flags.fire-spread", false);
			
			try {
				cfg.save(logs);
			} catch (IOException e) {
				
			}
	 }
	 
	 public static void updateName(Player p) {
		 File logs = new File("plugins/GS", "Plots.yml");
		 YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		 
		 for(String name : getConfigSection("").getKeys(false)) {
			 if(cfg.getString(name + ".owner").equals(p.getUniqueId().toString())) {
				 if(!cfg.getString(name + ".name").equals(p.getName())) {
					 cfg.set(name + ".name", p.getName());
					 
					 try {
						 cfg.save(logs);
					 } catch (IOException e) {
							
					 }
				 }
			 }
		 }
	 }
	 public static void setPlot2(Player p, String name, int size) {		 
			
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
			
			String uuid = p.getUniqueId().toString();
			
			if(!logs.exists()) {
				try{
					logs.createNewFile();
				} catch (Exception e){
					
				}	}
			
			double x1 = p.getLocation().getBlockX() - size;
			double z1 = p.getLocation().getBlockZ() - size;
			double x2 = p.getLocation().getBlockX() + size;
			double z2 = p.getLocation().getBlockZ() + size;
			
			cfg.set(name + ".owner", uuid);
			cfg.set(name + ".name", p.getName());
			cfg.set(name + ".x1", x1);
			cfg.set(name + ".z1", z1);
			cfg.set(name + ".x2", x2);
			cfg.set(name + ".z2", z2);
			cfg.set(name + ".xspawn", p.getLocation().getX());
			cfg.set(name + ".yspawn", p.getLocation().getY());
			cfg.set(name + ".zspawn", p.getLocation().getZ());
			
			
			
			try {
				cfg.save(logs);
			} catch (IOException e) {
				
			}
	 }
	 
	 public static String checkSperrgebiet(double x, double z) {
		 File logs = new File("plugins/GS", "Plots.yml");
		 YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs); 
		 
			
		 for(String name : getConfigSection("").getKeys(false)){
				double x1 = cfg.getDouble(name + ".x1");
				double z1 = cfg.getDouble(name + ".z1");
				double x2 = cfg.getDouble(name + ".x2");
				double z2 = cfg.getDouble(name + ".z2");
				
				if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
				      
				      return cfg.getString(name + ".sperrung");
					
				}
			}
			return null;
	 }
	 
	 public static void setSperrgebiet(String name, String value) {
		 File logs = new File("plugins/GS", "Plots.yml");
		 YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs); 
		 
		 cfg.set(name + ".sperrung", value);
		 try {
				cfg.save(logs);
			} catch (IOException e) {
				
			}
	 }
	 
	 public static void savePlayerList() {
		 File logs1 = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs1);
		 try {
				pl.save(logs1);
		     } catch (IOException e1) {
				
		    }
	 }
	 
	 public static void setPlayerList2(Player p, String PlotName){
		 File logs1 = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs1);
		 
		 String uuid = p.getUniqueId().toString();
		 
		 if(!logs1.exists()) {
				try{
					logs1.createNewFile();
				} catch (Exception e){
					
				}
		 }
		 
		 double x1 = p.getLocation().getBlockX() - 30;
		 double z1 = p.getLocation().getBlockZ() - 30;
		 double x2 = p.getLocation().getBlockX() + 30;
		 double z2 = p.getLocation().getBlockZ() + 30;
		 
		 pl.set(uuid + ".Name1", PlotName);
		 pl.set(uuid + ".x2", x1);
		 pl.set(uuid + ".z2", z1);
		 pl.set(uuid + ".x3", x2);
		 pl.set(uuid + ".z3", z2);
		 pl.set(uuid + ".xspawn1", p.getLocation().getX());
		 pl.set(uuid + ".yspawn1", p.getLocation().getY());
		 pl.set(uuid + ".zspawn1", p.getLocation().getZ());
		 pl.set(uuid + ".tp-request1", "false");
		 
		 try {
				pl.save(logs1);
		 } catch (IOException e) {
				
		 }
	 }
	 
	 public static void setPlotHome2(Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
	     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
	     
	     String name = p.getUniqueId().toString();
		 
		 pl.set(name + ".xspawn1", p.getLocation().getX());
		 pl.set(name + ".yspawn1", p.getLocation().getY());
		 pl.set(name + ".zspawn1", p.getLocation().getZ());
		 
		 try {
			 pl.save(logs);
		 } catch (IOException e) {
				
		 }
	 }
	 
	 public static void Visit2(Player target, Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
	     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		  
		 String name = target.getUniqueId().toString();
		  
		 double x = pl.getDouble(name + ".xspawn1");
		 double y = pl.getDouble(name + ".yspawn1");
		 double z = pl.getDouble(name + ".zspawn1");
		 
		 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
		    
		 p.teleport(loc);
		}
	 
	 public static void Visit2Offline(OfflinePlayer target, Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
	     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		  
		 String name = target.getUniqueId().toString();
		  
		 double x = pl.getDouble(name + ".xspawn1");
		 double y = pl.getDouble(name + ".yspawn1");
		 double z = pl.getDouble(name + ".zspawn1");
		 
		 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
		    
		 p.teleport(loc);
		}
	 
	 public static void Teleport2(Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		  
		 String name = p.getUniqueId().toString();
		  
		 double x = pl.getDouble(name + ".xspawn1");
		 double y = pl.getDouble(name + ".yspawn1");
		 double z = pl.getDouble(name + ".zspawn1");
		 
		 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
		    
		 p.teleport(loc);
    }
	 
	 
	 public static void addPlotMember(Player p, String targetName, String name) {
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
		
		UUID uid = UUIDFetcher.getUUID(targetName);
			
		
		if(uid != null) {
			String temp = uid.toString();
			
			if(!cfg.getStringList(name + ".members").contains(temp)) {
				List<String> list = cfg.getStringList(name + ".members");
				list.add(temp);
				
				cfg.set(name + ".members", list);
				
				Main.member.remove(p);
				
				try {
					cfg.save(logs);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(Main.pr + "§aDer Spieler §6" + targetName + "§a wurde zu deinem Plot hinzugefügt!");
			}else {
				p.sendMessage(Main.pr + "§cDer Spieler ist bereits Mitglied deines Plots!");
				Main.member.remove(p);
			}
			
		}else {
			p.sendMessage(Main.pr + "§cDer Spieler existiert nicht!");
		}
		
	 }
	 
	 public static void removePlotMember(Player p, String targetName, String name) {
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
		
         UUID uid = UUIDFetcher.getUUID(targetName);
			
		
		if(uid != null) {
			String temp = uid.toString();
			
			if(cfg.getStringList(name + ".members").contains(temp)) {
				List<String> list = cfg.getStringList(name + ".members");
				list.remove(temp);
				
				cfg.set(name + ".members", list);
				
				Main.delmember.remove(p);
				
				try {
					cfg.save(logs);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(Main.pr + "§aDer Spieler §6" + targetName + "§a wurde von deinem Plot entfernt!");
			}else {
				p.sendMessage(Main.pr + "§cDer Spieler ist nicht Mitglied auf deinem Plot!");
				Main.delmember.remove(p);
			}
			
		}else {
			p.sendMessage(Main.pr + "§cDer Spieler existiert nicht!");
		}
	 }
	 public static String Plot2GetFirstMember(Player p){
	    	File logs = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
			
			File logs1 = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
			
			String uuid = p.getUniqueId().toString();
			
	    	String name = pl.getString(uuid + ".Name1");
	    	
			return cfg.getString(name + ".memberName");
	    }
	    
	    public static String Plot2GetSecondMember(Player p){
	    	File logs = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
			
			File logs1 = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
			
			String uuid = p.getUniqueId().toString();
			
	    	String name = pl.getString(uuid + ".Name1");
	    	
	    	return cfg.getString(name + ".member1Name");
	    }
	    
	    public static String Plot2GetThirdMember(Player p){
	    	File logs = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
			
			File logs1 = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
			
			String uuid = p.getUniqueId().toString();
			
	    	String name = pl.getString(uuid + ".Name1");
	 
	    	return cfg.getString(name + ".member2Name");
	    }
	    
	    public static void delPlot2(Player p){
			File file = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			
			File logs = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
			
			cfg.set(pl.getString(p.getUniqueId().toString() + ".Name1"), null);
			
			String uuid = p.getUniqueId().toString();
			
			 pl.set(uuid + ".Name1", null);
			 pl.set(uuid + ".x2", null);
			 pl.set(uuid + ".z2", null);
			 pl.set(uuid + ".x3", null);
			 pl.set(uuid + ".z3", null);
			 pl.set(uuid + ".xspawn1", null);
			 pl.set(uuid + ".yspawn1", null);
			 pl.set(uuid + ".zspawn1", null);
			 pl.set(uuid + ".tp-request1", null);
			
			try {
				cfg.save(file);
				pl.save(logs);
			} catch (IOException e) {
				
			}
		}
	 public static String isTpRequest2(Player p){
	     File logs1 = new File("plugins/GS", "Player-List.yml");
	     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs1);
			 
	     String uuid = p.getUniqueId().toString();
			 
		return pl.getString(uuid + ".tp-request1");
      }
	 
	 public static void setPlot3(Player p, String name, int size) {		 
			
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
			
			String uuid = p.getUniqueId().toString();
			
			if(!logs.exists()) {
				try{
					logs.createNewFile();
				} catch (Exception e){
					
				}	}
			
			double x1 = p.getLocation().getBlockX() - size;
			double z1 = p.getLocation().getBlockZ() - size;
			double x2 = p.getLocation().getBlockX() + size;
			double z2 = p.getLocation().getBlockZ() + size;
			
			cfg.set(name + ".owner", uuid);
			cfg.set(name + ".name", p.getName());
			cfg.set(name + ".x1", x1);
			cfg.set(name + ".z1", z1);
			cfg.set(name + ".x2", x2);
			cfg.set(name + ".z2", z2);
			cfg.set(name + ".xspawn", p.getLocation().getX());
			cfg.set(name + ".yspawn", p.getLocation().getY());
			cfg.set(name + ".zspawn", p.getLocation().getZ());
			
			
			
			try {
				cfg.save(logs);
			} catch (IOException e) {
				
			}
	 }
	 
	 public static void setPlayerList3(Player p, String PlotName){
		 File logs1 = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs1);
		 
		 String uuid = p.getUniqueId().toString();
		 
		 if(!logs1.exists()) {
				try{
					logs1.createNewFile();
				} catch (Exception e){
					
				}
		 }
		 
		 double x1 = p.getLocation().getBlockX() - 57;
		 double z1 = p.getLocation().getBlockZ() - 57;
		 double x2 = p.getLocation().getBlockX() + 57;
		 double z2 = p.getLocation().getBlockZ() + 57;
		 
		 pl.set(uuid + ".Name2", PlotName);
		 pl.set(uuid + ".x3", x1);
		 pl.set(uuid + ".z3", z1);
		 pl.set(uuid + ".x4", x2);
		 pl.set(uuid + ".z4", z2);
		 pl.set(uuid + ".xspawn2", p.getLocation().getX());
		 pl.set(uuid + ".yspawn2", p.getLocation().getY());
		 pl.set(uuid + ".zspawn2", p.getLocation().getZ());
		 pl.set(uuid + ".tp-request2", "false");
		 
		 try {
				pl.save(logs1);
		 } catch (IOException e) {
				
		 }
	 }
	 
	 public static void setPlotHome3(Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
	     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
	     
	     String name = p.getUniqueId().toString();
		 
		 pl.set(name + ".xspawn2", p.getLocation().getX());
		 pl.set(name + ".yspawn2", p.getLocation().getY());
		 pl.set(name + ".zspawn2", p.getLocation().getZ());
		 
		 try {
			 pl.save(logs);
		 } catch (IOException e) {
				
		 }
	 }
	 
	 public static void Visit3(Player target, Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
	     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		  
		 String name = target.getUniqueId().toString();
		  
		 double x = pl.getDouble(name + ".xspawn2");
		 double y = pl.getDouble(name + ".yspawn2");
		 double z = pl.getDouble(name + ".zspawn2");
		 
		 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
		    
		 p.teleport(loc);
		}
	 
	 public static void Visit3Offline(OfflinePlayer target, Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
	     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		  
		 String name = target.getUniqueId().toString();
		  
		 double x = pl.getDouble(name + ".xspawn2");
		 double y = pl.getDouble(name + ".yspawn2");
		 double z = pl.getDouble(name + ".zspawn2");
		 
		 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
		    
		 p.teleport(loc);
		}
	 
	 public static void Teleport3(Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		  
		 String name = p.getUniqueId().toString();
		  
		 double x = pl.getDouble(name + ".xspawn2");
		 double y = pl.getDouble(name + ".yspawn2");
		 double z = pl.getDouble(name + ".zspawn2");
		 
		 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
		    
		 p.teleport(loc);
 }
	 
	 public static String Plot3GetFirstMember(Player p){
	    	File logs = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
			
			File logs1 = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
			
			String uuid = p.getUniqueId().toString();
			
	    	String name = pl.getString(uuid + ".Name2");
	    	
			return cfg.getString(name + ".memberName");
	    }
	    
	    public static String Plot3GetSecondMember(Player p){
	    	File logs = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
			
			File logs1 = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
			
			String uuid = p.getUniqueId().toString();
			
	    	String name = pl.getString(uuid + ".Name2");
	    	
	    	return cfg.getString(name + ".member1Name");
	    }
	    
	    public static String Plot3GetThirdMember(Player p){
	    	File logs = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
			
			File logs1 = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
			
			String uuid = p.getUniqueId().toString();
			
	    	String name = pl.getString(uuid + ".Name2");
	 
	    	return cfg.getString(name + ".member2Name");
	    }
	    
	    public static void delPlot3(Player p){
			File file = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			
			File logs = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
			
			cfg.set(pl.getString(p.getUniqueId().toString() + ".Name2"), null);
			
			String uuid = p.getUniqueId().toString();
			
			 pl.set(uuid + ".Name2", null);
			 pl.set(uuid + ".x3", null);
			 pl.set(uuid + ".z3", null);
			 pl.set(uuid + ".x4", null);
			 pl.set(uuid + ".z4", null);
			 pl.set(uuid + ".xspawn2", null);
			 pl.set(uuid + ".yspawn2", null);
			 pl.set(uuid + ".zspawn2", null);
			 pl.set(uuid + ".tp-request2", null);
			
			try {
				cfg.save(file);
				pl.save(logs);
			} catch (IOException e) {
				
			}
		}
	 public static String isTpRequest3(Player p){
	     File logs1 = new File("plugins/GS", "Player-List.yml");
	     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs1);
			 
	     String uuid = p.getUniqueId().toString();
			 
		return pl.getString(uuid + ".tp-request2");
   }
	 
	 
	 public static void save(File file, YamlConfiguration cfg){
		 try {
				cfg.save(file);
			} catch (IOException e) {
				
			}
	 }
	 
	 
	 public static String isTpRequest(Player p){
		 File logs1 = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs1);
		 
		 String uuid = p.getUniqueId().toString();
		 
		 return pl.getString(uuid + ".tp-request");
	 }
	 
	 
	 public static void setPlayerList(Player p, String PlotName){
		 File logs1 = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs1);
		 
		 String uuid = p.getUniqueId().toString();
		 
		 if(!logs1.exists()) {
				try{
					logs1.createNewFile();
				} catch (Exception e){
					
				}
		 }
		 
		 double x1 = p.getLocation().getBlockX() - 18;
		 double z1 = p.getLocation().getBlockZ() - 18;
		 double x2 = p.getLocation().getBlockX() + 18;
		 double z2 = p.getLocation().getBlockZ() + 18;
		 
		 pl.set(uuid + ".uuid",uuid);
		 pl.set(uuid + ".Name", PlotName);
		 pl.set(uuid + ".x1", x1);
		 pl.set(uuid + ".z1", z1);
		 pl.set(uuid + ".x2", x2);
		 pl.set(uuid + ".z2", z2);
		 pl.set(uuid + ".xspawn", p.getLocation().getX());
		 pl.set(uuid + ".yspawn", p.getLocation().getY());
		 pl.set(uuid + ".zspawn", p.getLocation().getZ());
		 pl.set(uuid + ".tp-request", "false");
		 
		 try {
				pl.save(logs1);
		 } catch (IOException e) {
				
		 }
	 }
	 
	 public static void setPvPFlag(Player p, boolean deep){
		 File logs1 = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs1);
		 
		 double x1 = p.getLocation().getBlockX() - 15;
		 double z1 = p.getLocation().getBlockZ() - 15;
		 double x2 = p.getLocation().getBlockX() + 15;
		 double z2 = p.getLocation().getBlockZ() + 15;
		 
		 String uuid = p.getUniqueId().toString();
		 
		 pl.set(uuid + ".pvp", deep);
		 pl.set(uuid + ".x1", x1);
		 pl.set(uuid + ".z1", z1);
		 pl.set(uuid + ".x2", x2);
		 pl.set(uuid + ".z2", z2);
		 
		 try {
			 pl.save(logs1);
		 } catch (IOException e) {
				
		 }
	 }
	 
	 public static void setPlotHome(Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
	     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
	     
	     String name = p.getUniqueId().toString();
		 
		 pl.set(name + ".xspawn", p.getLocation().getX());
		 pl.set(name + ".yspawn", p.getLocation().getY());
		 pl.set(name + ".zspawn", p.getLocation().getZ());
		 
		 try {
			 pl.save(logs);
		 } catch (IOException e) {
				
		 }
	 }
	 
	 public static void delPlayerList(Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		 
		 cfg.set(p.getUniqueId().toString(), null);
		 
		 try {
				cfg.save(logs);
		 } catch (IOException e) {
				
		 }
	 }
	 
	public static void Visit(Player target, Player p){
	 File logs = new File("plugins/GS", "Player-List.yml");
     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
	  
	 String name = target.getUniqueId().toString();
	  
	 double x = pl.getDouble(name + ".xspawn");
	 double y = pl.getDouble(name + ".yspawn");
	 double z = pl.getDouble(name + ".zspawn");
	 
	 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
	    
	 p.teleport(loc);
	}
	
	public static void VisitOffline(OfflinePlayer target, Player p){
		 File logs = new File("plugins/GS", "Player-List.yml");
	     YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		  
		 String name = target.getUniqueId().toString();
		  
		 double x = pl.getDouble(name + ".xspawn");
		 double y = pl.getDouble(name + ".yspawn");
		 double z = pl.getDouble(name + ".zspawn");
		 
		 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
		    
		 p.teleport(loc);
		}
    
    public static void Teleport(Player p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		String name = p.getUniqueId().toString();
		  
		 double x = pl.getDouble(name + ".xspawn");
		 double y = pl.getDouble(name + ".yspawn");
		 double z = pl.getDouble(name + ".zspawn");
		 
		 Location loc = new Location(Bukkit.getWorld(worldname), x, y, z);
		    
		 p.teleport(loc);
    }
    
    public static List<String> getMembers(Player p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
        String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
    
    	
    	if(cfg.getStringList(name + ".members") == null) {
    		return new ArrayList<String>();
    	}
    	
    	return cfg.getStringList(name + ".members");
    }
    
    public static HashMap<String, Boolean> getFlags(Player p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
        String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
    
    	
    	HashMap<String, Boolean> flags = new HashMap<>();
    	
    	flags.put("block-break", cfg.getBoolean(name + ".flags.block-break"));
    	flags.put("block-place", cfg.getBoolean(name + ".flags.block-place"));
    	flags.put("pvp", cfg.getBoolean(name + ".flags.pvp"));
    	flags.put("interact", cfg.getBoolean(name + ".flags.interact"));
    	flags.put("interact-animals", cfg.getBoolean(name + ".flags.interact-animals"));
    	flags.put("mob-grief", cfg.getBoolean(name + ".flags.mob-grief"));
    	flags.put("mob-spawn", cfg.getBoolean(name + ".flags.mob-spawn"));
    	flags.put("tnt", cfg.getBoolean(name + ".flags.tnt"));
    	flags.put("fire-spread", cfg.getBoolean(name + ".flags.fire-spread"));
    	
    	return flags;
    }
    
    public static void updateFlag(Player p, String flag, boolean value) {
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
        String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
    	
    	cfg.set(name + ".flags." + flag, value);
    	
    	try {
			cfg.save(logs1);
		} catch (IOException e) {
			
		}
    }
    
    public static boolean getFlag(String uuid, String flag) {
    	File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		for(String name : getConfigSection("").getKeys(false)) {
			if(cfg.getString(name + ".owner").equals(uuid)) {
				HashMap<String, Boolean> flags = new HashMap<>();
		    	
		    	flags.put("block-break", cfg.getBoolean(name + ".flags.block-break"));
		    	flags.put("block-place", cfg.getBoolean(name + ".flags.block-place"));
		    	flags.put("pvp", cfg.getBoolean(name + ".flags.pvp"));
		    	flags.put("interact", cfg.getBoolean(name + ".flags.interact"));
		    	flags.put("interact-animals", cfg.getBoolean(name + ".flags.interact-animals"));
		    	flags.put("mob-grief", cfg.getBoolean(name + ".flags.mob-grief"));
		    	flags.put("mob-spawn", cfg.getBoolean(name + ".flags.mob-spawn"));
		    	flags.put("tnt", cfg.getBoolean(name + ".flags.tnt"));
		    	flags.put("fire-spread", cfg.getBoolean(name + ".flags.fire-spread"));
		    	
		    	return flags.get(flag);
			}
		}
		return false;
    }
    
    public static String getFirstMember(Player p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
		String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
    	
		return cfg.getString(name + ".memberName");
    }
    
    public static String getSecondMember(Player p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
		String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
    	
    	return cfg.getString(name + ".member1Name");
    }
    
    public static String getThirdMember(Player p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
		String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
 
    	return cfg.getString(name + ".member2Name");
    }
    
    public static String getFirstMemberUUID(Player p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
		String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
    	
		return cfg.getString(name + ".member");
    }
    
    public static String getSecondMemberUUID(Player p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
		String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
    	
    	return cfg.getString(name + ".member1");
    }
    
    public static String getThirdMemberUUID(Player p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
		String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
 
    	return cfg.getString(name + ".member2");
    }
    
    public static String getFirstOffMemberUUID(OfflinePlayer p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
		String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
    	
		return cfg.getString(name + ".member");
    }
    
    public static String getSecondOffMemberUUID(OfflinePlayer p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
		String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
    	
    	return cfg.getString(name + ".member1");
    }
    
    public static String getThirdOffMemberUUID(OfflinePlayer p){
    	File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		File logs1 = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs1);
		
		String uuid = p.getUniqueId().toString();
		
    	String name = pl.getString(uuid + ".Name");
 
    	return cfg.getString(name + ".member2");
    }
	
	public static ConfigurationSection getConfigSection(String section){
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getConfigurationSection(section);
	}
	
	public static Set<String> getGS()
	  {
	    return getKeys(false);
	  }
	
	
	public static Set<String> getKeys(boolean deep){
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getKeys(deep);
	}

	public static UUID getOwnerAsMember(Player p){
		File file = new File("plugins/GS", "Plots.yml");
	    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	    for (String name : getConfigSection("").getKeys(false)) {
	    	if ((cfg.getString(name + ".member1").equalsIgnoreCase(p.getUniqueId().toString())) || (cfg.getString(name + ".member2").equalsIgnoreCase(p.getUniqueId().toString())) || (cfg.getString(name + ".member3").equalsIgnoreCase(p.getUniqueId().toString()))) {
	            return UUID.fromString(cfg.getString(name + ".owner"));
	        }
	    }
	    return null;
	}
	
	public static String isMemberofPlot(Player p){
		File file = new File("plugins/GS", "Plots.yml");
	    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	    
	    
	    try{
	    	for(String name : getConfigSection("").getKeys(false)){
	    		if(cfg.getStringList(name + ".members").contains(p.getUniqueId().toString())) {
	    			return "ja";
	    		}
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	
	
	public static String getPlot(){
		
		for(String name : getConfigSection("").getKeys(false)){
			return name;
		}
		return null;
	}
	
	public static String isCreated(double x, double z){
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		for(String name : getConfigSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      
			      return cfg.getString(name + ".owner");
				
			}
		}
		return null;
	}
	
	public static List<String> isMember(double x, double z){
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		for(String name : getConfigSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      
			      return cfg.getStringList(name + ".members");
				
			}
		}
		return null;
	}
	
	public static String isMember1(double x, double z) {
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		for(String name : getConfigSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      
			      return cfg.getString(name + ".member");
				
			}
		}
		return null;
	}
	
	public static String isMember2(double x, double z) {
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		for(String name : getConfigSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      
			      return cfg.getString(name + ".member1");
				
			}
		}
		return null;
	}
	
	public static String isMember3(double x, double z) {
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		for(String name : getConfigSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      
			      return cfg.getString(name + ".member2");
				
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
		
		if(isCreated(x1, z1) != null){
			state = state + 1;
		}
		if(isCreated(x2, z2) != null){
			state = state + 1;
		}
		if(isCreated(x3, z3) != null){
			state = state + 1;
		}
		if(isCreated(x4, z4) != null){
			state = state + 1;
		}
		
		if(state == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getOwnerName(double x, double z){
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		for(String name : getConfigSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      String Stringu = cfg.getString(name + ".name");
			      return Stringu;
				
			}
		}
		return null;
	}
	
	public static List<String> getMembers(Block b){
		double x = b.getX();
		double z = b.getZ();
		
			
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
			for(String name : getConfigSection("").getKeys(false)){
				double x1 = cfg.getDouble(name + ".x1");
				double z1 = cfg.getDouble(name + ".z1");
				double x2 = cfg.getDouble(name + ".x2");
				double z2 = cfg.getDouble(name + ".z2");
				
				if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
				      List<String> Stringu = cfg.getStringList(name + ".members");
				      return Stringu;
					
				}
			}
		return new ArrayList<String>();
	}
	
	public static String getMember2(Block b){
		double x = b.getX();
		double z = b.getZ();
		
			
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
			for(String name : getConfigSection("").getKeys(false)){
				double x1 = cfg.getDouble(name + ".x1");
				double z1 = cfg.getDouble(name + ".z1");
				double x2 = cfg.getDouble(name + ".x2");
				double z2 = cfg.getDouble(name + ".z2");
				
				if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
				      String Stringu = cfg.getString(name + ".member1");
				      return Stringu;
					
				}
			}
		return null;
	}
	public static String getMember3(Block b){
		double x = b.getX();
		double z = b.getZ();
		
			
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
			for(String name : getConfigSection("").getKeys(false)){
				double x1 = cfg.getDouble(name + ".x1");
				double z1 = cfg.getDouble(name + ".z1");
				double x2 = cfg.getDouble(name + ".x2");
				double z2 = cfg.getDouble(name + ".z2");
				
				if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
				      String Stringu = cfg.getString(name + ".member2");
				      return Stringu;
					
				}
			}
		return null;
	}
	
	public static void PlotList(Player p){
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		int count = 0;
		
		for(String name : getConfigSection("").getKeys(false)){
			count = count + 1;
			
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			double x = cfg.getDouble(name + ".xspawn");
			double y = cfg.getDouble(name + ".yspawn");
			double z = cfg.getDouble(name + ".zspawn");
			
			NumberFormat n = NumberFormat.getInstance();
			n.setMaximumFractionDigits(0);
			
			
			
			String owner = cfg.getString(name + ".name");
			
			if(p.hasPermission("system.tp")){
				/*TextComponent tc = new TextComponent();
				
				tc.setText(Main.pr + "§6" + name + "§8| §eX:" + x1 + " Z:" + z1 + " X:" + x2 + " Z:" + z2);
				tc.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z + " world"));
				tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Teleportiere dich zu dem Plot von §6" + owner+ "§7!").create()));
				
				p.spigot().sendMessage(tc);*/
			}else{
				p.sendMessage(Main.pr + "§6" + Bukkit.getPlayer(owner) + "§8| §eX:" + x1 + " Z:" + z1 + " X:" + x2 + " Z:" + z2);
			}
		}
		if(count != 0){
			p.sendMessage(" ");
			p.sendMessage(Main.pr + "Insgesamt §6" + count + " Plots");
		}else{
			p.sendMessage(Main.pr + "§cEs wurden keine Plots erstellt!");
		}
	}
	
	public static void MemberOfPlotList(Player p){
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		int count = 0;
		
		for(String name : getConfigSection("").getKeys(false)){
			count = count + 1;
			
			String owner = cfg.getString(name + ".name");
				
			try {
				if((cfg.getString(name + ".member").equals(p.getUniqueId().toString())) || (cfg.getString(name + ".member1").equals(p.getUniqueId().toString())) || (cfg.getString(name + ".member2").equals(p.getUniqueId().toString()))) {
					p.sendMessage("§6- §aName: §6" + name + " §aOwner:" + owner);
				}
			}catch(NullPointerException e) {
				
			}
		}
		if(count != 0){
			p.sendMessage(" ");
			p.sendMessage(Main.pr + "Insgesamt §6" + count + " Plots");
		}else{
			p.sendMessage(Main.pr + "§cDu bist auf keinem Plot Mitglied!");
		}
	}
	
	
	public static String getBlockOwner(Block b){
		double x = b.getX();
		double z = b.getZ();
		
			
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
			for(String name : getConfigSection("").getKeys(false)){
				double x1 = cfg.getDouble(name + ".x1");
				double z1 = cfg.getDouble(name + ".z1");
				double x2 = cfg.getDouble(name + ".x2");
				double z2 = cfg.getDouble(name + ".z2");
				
				if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
				      String Stringu = cfg.getString(name + ".owner");
				      return Stringu;
					
				}
			}
		return null;
	}
	
	public static boolean existList(String name){
		File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
	    return pl.getString(name) != null;
	}
	
	public static boolean exist(String name){
		File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
	    return cfg.getString(name) != null;
	}
	
	public static void delPlot(Player p){
		File file = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		File logs = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl = YamlConfiguration.loadConfiguration(logs);
		
		cfg.set(pl.getString(p.getUniqueId().toString() + ".Name"), null);
		
		String uuid = p.getUniqueId().toString();
		
		 pl.set(uuid + ".Name", null);
		 pl.set(uuid + ".x1", null);
		 pl.set(uuid + ".z1", null);
		 pl.set(uuid + ".x2", null);
		 pl.set(uuid + ".z2", null);
		 pl.set(uuid + ".xspawn", null);
		 pl.set(uuid + ".yspawn", null);
		 pl.set(uuid + ".zspawn", null);
		 pl.set(uuid + ".tp-request", null);
		
		try {
			cfg.save(file);
			pl.save(logs);
		} catch (IOException e) {
			
		}
		
	}
}
