package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.blitzmc.Main;
import de.blitzmc.chestshop.ChestShopListener;

public class ShopManager {
	
	public static Currency c;

	@SuppressWarnings("static-access")
	public static void buyItem(Player p, int costs, ItemStack i){
		if(Currency.getDollar(p) > costs){
			p.getInventory().addItem(new ItemStack[]{ i });
			c.removeDollar(p, costs);
			p.sendMessage(Main.pr + "§c- §6" + costs + "§a Dollar");
			p.closeInventory();
		}else{
			p.sendMessage(Main.pr + "§cDein Kontostand reicht nicht aus um dieses Item zu kaufen!");
		}
	}
	
	public static File logs = new File("plugins/GS", "Shops.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
	
	public static File logs1 = new File("plugins/GS", "ExpirationDate.yml");
	public static YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(logs1);
	
	public static void setShop(String name, double x1, double x2, double z1,double z2){
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
		save();
	}
	
	public static void setSign(double x, double y, double z, String world, String name) {
		cfg.set(name + ".SignX", x);
		cfg.set(name + ".SignY", y);
		cfg.set(name + ".SignZ", z);
		cfg.set(name + ".SignWorld", world);
		save();
	}
	
   public static void checkForExpiration() {
		for(String name: getConfigSection("").getKeys(false)) {
			Long totalexpirationmillis = cfg.getLong(name + ".expiration");
			
			if(totalexpirationmillis <= System.currentTimeMillis()) {
				PendingMessages.setMessage(cfg.getString(name + ".uuid"), Main.pr + "Dein Shop ist am §6" + new SimpleDateFormat("dd.MM.yyyy").format(totalexpirationmillis) + "§a abgelaufen!");
	
				List<String> Signs = ChestShopListener.ySign.getStringList("Locations");
				List<String> TruhenLoc = ChestShopListener.ySign.getStringList("LocationsT");
				try {
					for (String all : Signs) {
						Location loc2 = ChestShopListener.stringToLoc(all);
						
						
						Sign s = (Sign) loc2.getBlock().getState();
						
						if (loc2.getBlock() != null) {
							if(s.getLine(3).equals(cfg.getString(name + ".playername"))) {
								loc2.getBlock().setType(Material.AIR);
								Signs.remove(ChestShopListener.locToString(loc2.getBlock().getLocation()));
						        
						        ChestShopListener.ySign.set("Locations", Signs);
						        try {
									ChestShopListener.ySign.save(ChestShopListener.Sign);
								} catch (IOException e) {
									
									e.printStackTrace();
								}
							}
						}
					}
					for(String all : TruhenLoc) {
						Location loc = ChestShopListener.stringToLoc(all);
						
						System.out.println(getOwner(loc.getX(), loc.getZ()));
						
						if(getOwner(loc.getX(), loc.getZ()).equals(cfg.getString(name + ".playername"))) {
							loc.getBlock().setType(Material.AIR);
							
							Block chest = loc.getBlock();
						      
						     TruhenLoc.remove(ChestShopListener.locToString(loc));
						     ChestShopListener.ySign.set("LocationsT", TruhenLoc);
						     try {
								ChestShopListener.ySign.save(ChestShopListener.Sign);
							} catch (IOException e) {
								
								e.printStackTrace();
							}
						}
					}
				}catch(ClassCastException e) {
					
				}
				cfg.set(name + ".uuid", null);
				cfg.set(name + ".playername", null);
				cfg.set(name + ".expiration", null);
				save();
			}
		}
		
	}
   
   public static ConfigurationSection getConfigSection(String section){
		
		return cfg.getConfigurationSection(section);
	}
	
	
	
	public static Set<String> getKeys(boolean deep){
		
		return cfg.getKeys(deep);
	}
	
	
	public static void setOwner(String name, Player p) {
		
		Long duration = 2628000000L;
		
		
		
		cfg.set(name + ".uuid", p.getUniqueId().toString());
		cfg.set(name + ".playername", p.getName());
		cfg.set(name + ".expiration", System.currentTimeMillis() + duration);
		
		save();

	}
	
	public static Long getTimeLeft(Player p) {
		for(String name : getConfigSection("").getKeys(false)) {
			if(hasShop(p) != null) {
				if(p.getUniqueId().toString().equals(cfg.getString(name + ".uuid"))) {
					return cfg.getLong(name + ".expiration");
				}
			}
		}
		return null;
	}
	
	public static void add30( Player p) {
		Long duration = 2628000000L;
		
		
		for(String name : getConfigSection("").getKeys(false)) {
			if(isOwned(name) != null) {
				if(p.getUniqueId().toString().equals(cfg.getString(name + ".uuid"))) {
					cfg.set(name + ".expiration", cfg.getLong(name + ".expiration") + duration);
				}
			}
		}
		save1();
	}
	
	public static String getOwner(double x, double z) {
		
		for(String name : cfg.getConfigurationSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      String Stringu = cfg.getString(name + ".playername");
			      return Stringu;
				
			}
		}
		return null;
	}
	
	public static String isOwner(double x, double z) {
		for(String name : cfg.getConfigurationSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      return cfg.getString(name + ".uuid");
			}
		}
		return null;
	}
	
	public static String isOwned(String name) {
		return cfg.getString(name + ".uuid");
	}
	
	public static boolean exist(String name){
	    return cfg.getString(name) != null;
	}
	
	public static String isCreated(double x, double z){
		for(String name : cfg.getConfigurationSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      return cfg.getString(name + ".Name");
			}
		}
		return null;
	}
	
	public static boolean isInShopRegion(double x, double z) {
		for(String name : cfg.getConfigurationSection("").getKeys(false)){
			double x1 = cfg.getDouble(name + ".x1");
			double z1 = cfg.getDouble(name + ".z1");
			double x2 = cfg.getDouble(name + ".x2");
			double z2 = cfg.getDouble(name + ".z2");
			
			if(((x1 <= x && x2 >= x) || (x1 >= x && x2 <= x)) && ((z1 <= z && z2 >= z) || (z1 >= z && z2 <= z))) {
			      return true;
			}
		}
		return false;
	}
	
	public static String hasShop(Player p) {
		for(String name : cfg.getConfigurationSection("").getKeys(false)){
			if(p.getUniqueId().toString().equals(cfg.getString(name + ".uuid"))) {
		    	  return cfg.getString(name + ".uuid");
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
	
	public static void delShop(String name){	
		cfg.set(name, null);
		save();
	}
	
	public static void delPlayer(Player p){	
		for(String name : cfg.getConfigurationSection("").getKeys(false)) {
			if(hasShop(p) != null) {
				if(p.getUniqueId().toString().equals(cfg.getString(name + ".uuid"))) {
					
					
					
					List<String> Signs = ChestShopListener.ySign.getStringList("Locations");
					for (String all : Signs) {
						Location loc2 = ChestShopListener.stringToLoc(all);
						
						
						Sign s = (Sign) loc2.getBlock().getState();
						
						if (loc2.getBlock() != null) {
							if(s.getLine(3).equals(p.getName())) {
								p.sendMessage(Main.pr +"§cBaue zuerst deine Kisten ab!");
								return;
						        
							}
						}
					}
					
					cfg.set(name + ".uuid", null);
					cfg.set(name + ".playername", null);
					cfg.set(name + ".expiration", null);
					Location signloc = new Location(Bukkit.getWorld(cfg.getString(name + ".SignWorld")), cfg.getDouble(name + ".SignX"), cfg.getDouble(name + ".SignY"), cfg.getDouble(name + ".SignZ"));
					Sign s = (Sign) signloc.getBlock().getState();
					
					s.setLine(2, "");
					s.update();
					save();
					p.sendMessage(Main.pr + "Dein Plot wurde gelöscht.");
				}
			}
		}
	}
	
	public static void save(){
		try {
			cfg.save(logs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void save1(){
		try {
			cfg1.save(logs1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
