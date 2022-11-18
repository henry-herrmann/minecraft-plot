package de.blitzmc.listeners;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import de.blitzmc.Main;



public class InteractEvent implements Listener{
	
	public static Map<Player, Location[]> locs = new HashMap<>();
	public static Map<Player, Location[]> shoplocs = new HashMap<>();

	@EventHandler
	public void Interact(PlayerInteractEvent e){
		if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			Block b = e.getClickedBlock();
			/*if(e.getItem() != null && e.getItem().getType() == Material.BLAZE_ROD){
				e.setCancelled(true);
				Player p = e.getPlayer();
				if(p.hasPermission("system.plot.regions")){
					if(!locs.containsKey(p))
						locs.put(p, new Location[2]);
					
					if(e.getAction() == Action.LEFT_CLICK_BLOCK){
						locs.get(p)[0] = b.getLocation();
						p.sendMessage(Main.pr + "Du hast die §61. §aPosition gesetzt!");
					}else{
						locs.get(p)[1] = b.getLocation();
						p.sendMessage(Main.pr + "Du hast die §62. §aPosition gesetzt!");
					}
				}
				
			}
			if(e.getItem() != null && e.getItem().getType() == Material.STICK) {
				Player p = e.getPlayer();
				
				if(p.hasPermission("system.plot.regions")) {
					e.setCancelled(true);
					if(!shoplocs.containsKey(p)) 
						shoplocs.put(p, new Location[2]);
					if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
						shoplocs.get(p)[0] = b.getLocation();
						p.sendMessage(Main.pr + "Du hast die §61. §aPosition gesetzt!");
					}else {
						shoplocs.get(p)[1] = b.getLocation();
						p.sendMessage(Main.pr + "Du hast die §62. §aPosition gesetzt!");
					}
				}
			}
			if(e.getItem() != null && e.getItem().getType() == Material.COMPASS) {
				Player p = e.getPlayer();
				
				if(p.hasPermission("system.location")) {
					double x = e.getClickedBlock().getLocation().getX();
					double y = e.getClickedBlock().getLocation().getY();
					double z = e.getClickedBlock().getLocation().getZ();
					
					p.sendMessage(Main.pr + "X: §e" + x + " §aY: §e" + y + "§aZ: §e" + z);
				}
			}*/
			
		}
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block b = e.getClickedBlock();
			if ((b.getType() == Material.SPRUCE_SIGN) || (b.getType() == Material.OAK_SIGN) || (b.getType() == Material.SPRUCE_SIGN) || (b.getType() == Material.BIRCH_SIGN) || (b.getType() == Material.BIRCH_WALL_SIGN)) {
				Sign s = (Sign) b.getState();
				Player p = e.getPlayer();
				
				/*if(s.getLine(0).equalsIgnoreCase("§b[ChestShop]")) {
					if(ShopManager.isOwned(s.getLine(1)) == null) {
						if(ShopManager.hasShop(p) == null) {
							if(Currency.getDollar(p) >= 1800) {
								ShopManager.setOwner(s.getLine(1), p);
								s.setLine(2, p.getName());
								s.update();
								Currency.removeDollar(p, 1800);
								p.sendMessage(Main.pr + "Du hast den Shop§6 " + s.getLine(1) + "§a gekauft, dieser läuft am §e" + new SimpleDateFormat("dd.MM.yyyy").format(ShopManager.getTimeLeft(p)) + "§aaus. Verlängere ihn mit /shop extend.");
							}else {
								p.sendMessage(Main.pr + "§cDu brauchst mindestens §e1800 §cDollar pro Monat!");
							}
						}else {
							p.sendMessage(Main.pr + "§cDu besitzt bereits einen Shop!");
						}
						
					}else {
						p.sendMessage(Main.pr + "§cDer Shop ist schon vergeben!");
					}
				}*/
			}
			
		}
	}
	
	@EventHandler
	public void onExecute(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String[] msg = e.getMessage().split(" ");
		if(!p.hasPermission("*")) {
			for (String Loop : Main.plugins) {
		          if (msg[0].equalsIgnoreCase("/" + Loop))
		          {
		        	//p.sendMessage(Main.noperm);
		            //e.setCancelled(true);
		          }
		        }
		}
		
	}
}
