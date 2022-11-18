package de.blitzmc.listeners;

import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.help.HelpTopic;


import de.blitzmc.Main;
import de.blitzmc.Nick.TabRank;
import de.blitzmc.utils.Currency;
import de.blitzmc.utils.ItemBuilder;
import de.blitzmc.utils.PendingMessages;
import de.blitzmc.utils.ShopManager;
import de.blitzmc.utils.TPSpawn;

public class JoinListener implements Listener{
	
	
	static Main plugin;
	public JoinListener(Main main) {
		plugin = main;
	}
	
	/*@EventHandler
	public void onPing(ServerListPingEvent e) {
		String motd = "§a» §6Leben Server | §l1.14.4 §r§6Update";
		e.setMotd(motd);
	}*/

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        
		
		//TabRank.set();
		
		//e.setJoinMessage(null);
		
        String UUID = p.getUniqueId().toString();
        
       
        
        
        /*TPSpawn.tpSpawn(p);
        
        if(PendingMessages.getMessage(p.getUniqueId().toString()) != null) {
        	p.sendMessage(PendingMessages.getMessage(p.getUniqueId().toString()));
        }
        
        if(ShopManager.hasShop(p) != null) {
        	p.sendMessage(Main.pr + "§cDein Shop läuft am §e " + new SimpleDateFormat("dd.MM.yyyy").format(ShopManager.getTimeLeft(p))+ "§c aus! Verlängere ihn mit /shop extend");
        }
		
        if(!Currency.fileExists(p)){
			e.setJoinMessage(null);
			Currency.createPlayer(p);
            p.setGameMode(GameMode.SURVIVAL);
            
            p.sendMessage(Main.pr + "Es sieht so aus, als wärst du neu? Nutze §e/tutorial um alle relevanten Commands zu erfahren.");
			
			p.getInventory().clear();
			p.getInventory().setItem(0, ItemBuilder.getItem(Material.STONE_SWORD, "§7Steinschwert", 0, 1));
			p.getInventory().setItem(1, ItemBuilder.getItem(Material.IRON_PICKAXE, "§7Spitzhacke", 0, 1));
			p.getInventory().setItem(8, ItemBuilder.getItem(Material.BREAD, "§7Billiges Brot", 0, 21));
			
			p.setPlayerListHeader("§6§lBlitzMC Leben Server");
	        p.setPlayerListFooter("§7Discord Server: §6discord.blitzmc.de \n §7Erfahre alle wichtigen Befehle mit §6/tutorial");
			
			
		}else{
			TabRank.set();
			p.setPlayerListHeader("§6§lBlitzMC Leben Server");
	        p.setPlayerListFooter("§7Discord Server: §6discord.blitzmc.de \n §7Erfahre alle wichtigen Befehle mit §6/tutorial");
		}
        p.sendMessage(Main.pr + "§cClientmodfikationen oder AutoClicker, die Vorteile gegenüber anderen erschaffen, sind §4§lverboten §r§cund werden mit einem Bann bestraft.");
        */
	}
	@EventHandler(priority = EventPriority.NORMAL)
    public void onUnknown(PlayerCommandPreprocessEvent event) {
        /*if (!event.isCancelled()) {

            Player p = event.getPlayer();
            String msg = event.getMessage().split(" ")[0];

           if(Main.combatlog.contains(event.getPlayer())) {
        	   event.setCancelled(true);
        	   p.sendMessage(Main.pr + "§cDu befindest dich gerade in einem Kampf!");
           }
        }*/
    }
}
