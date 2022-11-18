package de.blitzmc.cmds;

import java.text.SimpleDateFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.listeners.InteractEvent;
import de.blitzmc.utils.Currency;
import de.blitzmc.utils.ShopManager;

public class CMD_shop implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2, String[] args) {
		Player p = (Player) s;
		
		if(args.length == 0) {
			p.sendMessage(Main.pr + "§cSyntax: /shop <create,delete, extend> <Name>");
			return true;
		}
		if(args.length == 1) {
			if(ShopManager.hasShop(p) != null) {
				if(args[0].equalsIgnoreCase("delete")) {
					ShopManager.delPlayer(p);
					
				}
				if(args[0].equalsIgnoreCase("extend")) {
					if(Currency.getDollar(p) >= 1800) {
						Currency.removeDollar(p, 1800);
						ShopManager.add30(p);
						p.sendMessage(Main.pr + "Dein Shop wurde erfolgreich bis zum §6" + new SimpleDateFormat("dd.MM.yyyy").format(ShopManager.getTimeLeft(p)) + "§a verlängert!");
					}else {
						p.sendMessage(Main.pr + "§cDu brauchst mindestens §e1800 §cDollar pro Monat.");
					}
				}
			}else {
				p.sendMessage(Main.pr + "§cDu besitzt noch kein Shop!");
			}
		}
		if(args.length == 2) {
			if(p.hasPermission("system.plot.regions")) {
				if(args[0].equalsIgnoreCase("create")) {
					if(InteractEvent.shoplocs.get(p) != null) {
						if(InteractEvent.shoplocs.get(p)[0] != null && InteractEvent.shoplocs.get(p)[1] != null) {
							if(!ShopManager.exist(args[1])) {
								ShopManager.setShop(args[1], InteractEvent.shoplocs.get(p)[0].getBlockX(), InteractEvent.shoplocs.get(p)[1].getBlockX(), InteractEvent.shoplocs.get(p)[0].getBlockZ(), InteractEvent.shoplocs.get(p)[1].getBlockZ());
								InteractEvent.shoplocs.remove(p);
								p.sendMessage(Main.pr + "Du hast den Shop §6" + args[1] + "§a gesetzt!");
							}else{
								p.sendMessage(Main.pr + "§cDieser Shop existiert schon!");
							}
						}
					}
				}else if(args[0].equalsIgnoreCase("delete")){
					if(ShopManager.exist(args[1])){
						ShopManager.delShop(args[1]);
						p.sendMessage(Main.pr + "Du hast den Shop §6" + args[1] + "§a gelöscht!");
					}else{
						p.sendMessage(Main.pr + "§cDiese Region existiert nicht!");
					}
			   }
			}
			
		}
		
		
		return false;
	}
}
