package de.blitzmc.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import de.blitzmc.cmds.CMD_Build;
import de.blitzmc.utils.ShopManager;

public class SignChange implements Listener{

	@EventHandler
	public void onChange(SignChangeEvent e) {
		Player p = e.getPlayer();
		
		if(e.getLine(0).equals("[ChestShop]")) {
			if(!CMD_Build.buildmode.contains(p)) {
				e.getBlock().setType(Material.AIR);
				e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new org.bukkit.inventory.ItemStack(Material.SPRUCE_SIGN));
			}else {
				ShopManager.setSign(e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ(), e.getBlock().getWorld().getName(), e.getLine(1));
				e.setLine(0, "§b[ChestShop]");
			}
		}
	}
}
