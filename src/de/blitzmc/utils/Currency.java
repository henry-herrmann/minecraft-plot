package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class Currency {
	
	public static void createPlayer(Player p){
		if(!fileExists(p)){
			File logs = new File("plugins/GS", "Currency.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
			
			String uuid = p.getUniqueId().toString();
			
			if(!logs.exists()) {
				try{
					logs.createNewFile();
				} catch (Exception e){
					
				}
			}
			
			cfg.set(uuid + ".dollar", 200);
			cfg.set(uuid + ".name", p.getName());
			
			try {
				cfg.save(logs);
			} catch (IOException e) {
				
			}
		}
	}
	
    public static void deleteUser(Player p) {
		
    	File logs = new File("plugins/GS", "Currency.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
		
		if(fileExists(p)){
			cfg.set(p.getUniqueId().toString(), null);
			
			try {
				cfg.save(logs);
			} catch (IOException e) {
				
			}
		}else{
			p.sendMessage(Main.pr + "Du besitzt noch keine Dollar!");
		}
		
	}
    
    public static void addDollar(Player p, int betrag){
    	File logs = new File("plugins/GS", "Currency.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		String uuid = p.getUniqueId().toString();
		
		if(fileExists(p)){
			cfg.set(uuid + ".dollar", getDollar(p) + betrag);
			try {
				cfg.save(logs);
			} catch (IOException e) {
				
			}
		}else{
			p.sendMessage(Main.pr + "Du besitzt noch keine Dollar!");
		}
    }
    
    public static void addOfflineDollar(OfflinePlayer p, int betrag){
    	File logs = new File("plugins/GS", "Currency.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		String uuid = p.getUniqueId().toString();
		
		if(offlinefileExists(p)){
			cfg.set(uuid + ".dollar", getOfflineDollar(p) + betrag);
			try {
				cfg.save(logs);
			} catch (IOException e) {
				
			}
		}else{
			
		}
    }
    
    public static void setDollar(Player p, int betrag){
    	File logs = new File("plugins/GS", "Currency.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		String uuid = p.getUniqueId().toString();
		
		if(fileExists(p)){
			cfg.set(uuid + ".dollar", betrag);
			try {
				cfg.save(logs);
			} catch (IOException e) {
				
			}
		}else{
			p.sendMessage(Main.pr + "Du besitzt noch keine Dollar!");
		}
    }
    
    public static void removeDollar(Player p, int betrag){
    	File logs = new File("plugins/GS", "Currency.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		String uuid = p.getUniqueId().toString();
		
		if(fileExists(p)){
			cfg.set(uuid + ".dollar", getDollar(p) - betrag);
			try {
				cfg.save(logs);
			} catch (IOException e) {
				
			}
		}else{
			p.sendMessage(Main.pr + "Du besitzt noch keine Dollar!");
		}
    }
    
    public static int getDollar(Player p){
    	File logs = new File("plugins/GS", "Currency.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		String uuid = p.getUniqueId().toString();
		
		if(fileExists(p)){
			int Dollar = cfg.getInt(uuid + ".dollar");
			return Dollar;
		}
		return 0;
    }
    
    
    
    public static int getOfflineDollar(OfflinePlayer o){
    	File logs = new File("plugins/GS", "Currency.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		String uuid = o.getUniqueId().toString();
		
		if(offlinefileExists(o)){
			int Dollar = cfg.getInt(uuid + ".dollar");
			return Dollar;
		}
		return 0;
    }
	
	public static boolean fileExists(Player p){
		File logs = new File("plugins/GS", "Currency.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getString(p.getUniqueId().toString()) != null;
	}
	
	public static boolean offlinefileExists(OfflinePlayer p){
		File logs = new File("plugins/GS", "Currency.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		return cfg.getString(p.getUniqueId().toString()) != null;
	}
}
