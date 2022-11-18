package de.blitzmc.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.blitzmc.utils.TPSpawn;

public class WarpListener implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		
		if(e.getView().getTitle().equals("§8» §6Warps")){
			e.setCancelled(true);
			if(e.getCurrentItem() != null){
				if(e.getCurrentItem().getItemMeta() != null){
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aSpawn")){
						TPSpawn.tpSpawn(p);
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aFarmwelt")){
						TPSpawn.tpFarmwelt(p);
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aMarkplatz")) {
						TPSpawn.tpMarkt(p);
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cNether")) {
						TPSpawn.tpNether(p);
					}
					
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cEnd")) {
						TPSpawn.tpEnd(p);
					}
				}
			}
		}
	}
}
