package de.blitzmc.Nick;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.blitzmc.Main;
import de.blitzmc.utils.PlotManager;


public class ChatListener implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();

        String msg = e.getMessage();
        
        /*if(Main.getGroupID(p) == 1) {
            e.setFormat("§4§lAdmin §8× §7" + p.getName() + "§8: §f" + msg);
        } else if(Main.getGroupID(p) == 2) {
            e.setFormat("§b§lDev §8× §7" + p.getName() + "§8: §f" + msg);
        } else if(Main.getGroupID(p) == 3) {
            e.setFormat("§c§lMod §8× §7" + p.getName() + "§8: §f" + msg);
        } else if(Main.getGroupID(p) == 4) {
            e.setFormat("§9§lSup §8× §7" + p.getName() + "§8: §f" + msg);
        } else if(Main.getGroupID(p) == 5) {
            e.setFormat("§9§lSup §8× §7" + p.getName() + "§8: §f" + msg);
        } else if(Main.getGroupID(p) == 6) {
            e.setFormat("§e§lBuilder §8× §7" + p.getName() + "§8: §f" + msg);
        } else if(Main.getGroupID(p) == 7) {
            e.setFormat("§e§lBauteam §8× §7" + p.getName() + "§8: §f" + msg);
        } else if(Main.getGroupID(p) == 8) {
            e.setFormat("§5YT §8× §7" + p.getName() + "§8: §f" + msg);
        } else if(Main.getGroupID(p) == 9) {
            e.setFormat("§dExperte §8× §7" + p.getName() + "§8: §f" + msg);
        } else if(Main.getGroupID(p) == 10) {
            e.setFormat("§7Spieler §8× §7" + p.getName() + "§8: §f" + msg);
        }*/
        
        File logs = new File("plugins/GS", "Plots.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
		
		File logs1 = new File("plugins/GS", "Player-List.yml");
		YamlConfiguration pl= YamlConfiguration.loadConfiguration(logs1);
		
		
		if(Main.delmember.contains(p)) {
			e.setCancelled(true);
			String name = pl.getString(p.getUniqueId().toString() + ".Name");
			PlotManager.removePlotMember(p, e.getMessage(), name);
		}
    	
    	if(Main.member.contains(p)) {
    		e.setCancelled(true);
    		String name = pl.getString(p.getUniqueId().toString() + ".Name");
    		PlotManager.addPlotMember(p, e.getMessage(), name);
    	}
    	if(Main.delmember2.contains(p)) {
    		e.setCancelled(true);
    		String name = pl.getString(p.getUniqueId().toString() + ".Name1");
			PlotManager.removePlotMember(p, e.getMessage(), name);
    	}
    	
    	if(Main.member2.contains(p)) {
    		e.setCancelled(true);
    		String name = pl.getString(p.getUniqueId().toString() + ".Name1");
    		PlotManager.addPlotMember(p, e.getMessage(), name);
    	}
    	if(Main.delmember3.contains(p)) {
    		e.setCancelled(true);
    		String name = pl.getString(p.getUniqueId().toString() + ".Name2");
			PlotManager.removePlotMember(p, e.getMessage(), name);
    	}
    	
    	if(Main.member3.contains(p)) {
    		e.setCancelled(true);
    		String name = pl.getString(p.getUniqueId().toString() + ".Name2");
    		PlotManager.addPlotMember(p, e.getMessage(), name);
    	}

    }

}
