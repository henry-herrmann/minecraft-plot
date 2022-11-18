package de.blitzmc.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedEvent implements Listener{

	@EventHandler
	public void onEnterBed(PlayerBedEnterEvent e) {
		Player p = e.getPlayer();
		
		if(p.getWorld().getName().equals("world")) {
			//p.setPlayerTime(100, false);
			//p.teleport(e.getBed().getLocation());
		}
	}
}
