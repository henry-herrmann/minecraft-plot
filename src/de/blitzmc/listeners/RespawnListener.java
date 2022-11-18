package de.blitzmc.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import de.blitzmc.Main;
import de.blitzmc.utils.TPSpawn;

public class RespawnListener implements Listener{

	@EventHandler()
	public void onDie(PlayerRespawnEvent e){
		Player p = e.getPlayer();
		
		
		/*new BukkitRunnable() {
			
			@Override
			public void run() {
				TPSpawn.tpSpawn(p);
				
			}
		}.runTaskLater(Main.getInstance(), 3);*/
		
	}
}
