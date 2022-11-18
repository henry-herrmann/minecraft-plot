package de.blitzmc.listeners;

import java.io.File;
import java.text.SimpleDateFormat;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.blitzmc.utils.BanManager;
import de.blitzmc.utils.PlotManager;

public class LoginListener implements Listener{

	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		
		File logs = new File("plugins/GS", "Wartungen.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
	
		if(cfg.getBoolean("wartungen" + ".status") == true) {
			if(!p.hasPermission("system.plot.wartung")) {
				e.disallow(Result.KICK_OTHER,"§cDer Server befindet sich im Wartungsmodus");
			}
		}
		
		PlotManager.updateName(p);
		
		/*BanManager bm = new BanManager(p.getUniqueId());
		
		 
		
		
		if(bm.getTempbanMilliseconds() <= System.currentTimeMillis()) {
			bm.setunTempbanned();
			
		}else {
			e.disallow(Result.KICK_OTHER, "§cDu wurdest temporär von einem §e" + bm.getTempbanFrom() + " §cgesperrt! \n\n" 
                    + "§cAblauf der Sperre: §e" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(bm.getTempbanMilliseconds()) + "\n\n§cGrund:§e " + bm.getTempbanReason());
		}
		if(!bm.isPermBanned()) {
			
		}else {
			e.disallow(Result.KICK_OTHER, "§cDu wurdest §ePERMANENT §cgesperrt \n\n Grund: §e" + bm.getPermbanReason());
		}*/
		
	}
}
