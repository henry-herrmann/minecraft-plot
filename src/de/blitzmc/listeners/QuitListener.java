package de.blitzmc.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.blitzmc.Main;

public class QuitListener implements Listener{

	@EventHandler
	public void onLeave(PlayerQuitEvent e){
		e.setQuitMessage(null);
		
		if(Main.Trusted.contains(e.getPlayer())){
			Main.Trusted.remove(e.getPlayer());
		}
	}
}
