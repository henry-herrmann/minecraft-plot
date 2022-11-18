package de.blitzmc.cmds;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.Currency;

public class CMD_Money implements CommandExecutor{
	Currency c;

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("money") || cmd.getName().equalsIgnoreCase("dollar")){
			if(args.length == 0){
				p.sendMessage(Main.pr + "Dein Kontostand:§6 " + Currency.getDollar(p));
			}
			
			if(args.length == 1) {
				if(p.hasPermission("system.plot.currency.others")) {
					Player t = Bukkit.getPlayer(args[0]);
					
					if(t != null) {
						p.sendMessage(Main.pr + t.getName() + "§a's Balance: §6" + Currency.getDollar(t));
					}else {
						@SuppressWarnings("deprecation")
						OfflinePlayer o = Bukkit.getOfflinePlayer(args[0]);
						
						if(o != null) {
							p.sendMessage(Main.pr + o.getName() + "§a's Balance: §6" + Currency.getOfflineDollar(o));
						}
					}
				}
			}
			
			if(args.length == 3){
				if(args[0].equalsIgnoreCase("pay")){
					Player t = Bukkit.getPlayer(args[1]);
					
					try {
						int amount = Integer.parseInt(args[2]);
						
						if(Currency.getDollar(p) - amount >= 0){
							if(amount < 0){
								p.sendMessage(Main.pr + "§cThe stated amount can not be lower as 0!");
							}else{
								c.addDollar(t, amount);
								c.removeDollar(p, amount);
								p.sendMessage(Main.pr + "Du hast §6" + amount + " §aDollar §6" + t.getName() + "§a überwiesen!");
								t.sendMessage(Main.pr + "§6" + p.getName() + "§ahat dir §6" + amount + "§aüberwiesen!");
							}
						}else{
							p.sendMessage(Main.pr + "§cDein Kontostand ist zu niedrig.");
						}
						
						
					} catch (NumberFormatException e) {
						// TODO: handle exception
					}
				}else if(args[0].equalsIgnoreCase("set")){
					if(p.hasPermission("system.plot.currency.set")){
						Player t = Bukkit.getPlayer(args[1]);
						
						try {
							int amount = Integer.parseInt(args[2]);
							
							Currency.setDollar(t, amount);
							
							p.sendMessage(Main.pr + "§aDer Spieler §6" + t.getName() + "§a hat nun §6" + c.getDollar(t) + " §aDollar!");
							
						} catch (NumberFormatException e) {
							// TODO: handle exception
						}
					}else{
						p.sendMessage(Main.noperm);
					}
				}else if(args[0].equalsIgnoreCase("remove")){
					if(p.hasPermission("system.plot.currency.remove")){
						Player t = Bukkit.getPlayer(args[1]);
						
						try {
							int amount = Integer.parseInt(args[2]);
							
							if(amount < 0){
								p.sendMessage(Main.pr + "§cDu kannst keine Minusbeträge entfernen!");
							}else{
								if(Currency.getDollar(t) >= amount){
									c.removeDollar(t, amount);
									p.sendMessage(Main.pr + "§aDu hast dem Spieler §6" + t.getName() + "§6 " + amount + "§a Dollar entfernt!");
								}else{
									p.sendMessage(Main.pr + "§cDein Kontostand reicht nicht aus!");
								}
							}
							
						} catch (NumberFormatException e) {
							// TODO: handle exception
						}
					}else{
						p.sendMessage(Main.noperm);
					}
				}else if(args[0].equalsIgnoreCase("delete")){
					if(p.hasPermission("system.plot.currency.deleteuser")){
						Player t = Bukkit.getPlayer(args[1]);
						
						if(args[2].equalsIgnoreCase("confirm")) {
							if(t == null){
								p.sendMessage(Main.pr + "§cDer angegebene Spieler ist nicht online!");
							}else{
								c.deleteUser(t);
								p.sendMessage(Main.pr + "§cDie Daten für den Spieler §6" + t.getName() + " §cwurden gelöscht!");
								t.sendMessage(Main.pr + "§aDeine Konto wurde aufgelöst!");
								t.sendMessage(Main.pr + "§aFalls du den Server neu betrittst werden deine daten neu erstellt!");
							}
						}
					}else{
						p.sendMessage(Main.noperm);
					}
				}
			}
		}
		return false;
	}

}
