package de.blitzmc.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import de.blitzmc.Main;
import de.blitzmc.cmds.CMD_Build;
import de.blitzmc.utils.TPSpawn;

public class EnderPortalEvent implements Listener{

	@EventHandler
	public void onPort(PlayerPortalEvent e) {
		/*Player p = e.getPlayer();
		
		File logs = new File("plugins/GS", "Wartungen.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		if(cfg.getBoolean("end_access" + ".status") == false) {
			if(!CMD_Build.buildmode.contains(p)) {
				if(e.getCause() == PlayerPortalEvent.TeleportCause.END_PORTAL) {
					e.setCancelled(true);
					TPSpawn.tpSpawn(p);
					p.sendMessage(Main.pr + "§cHabe Geduld! Das End wird in absehbarer Zeit bei einem Event freigegeben.");
				}
			}
		}*/
	}
}
