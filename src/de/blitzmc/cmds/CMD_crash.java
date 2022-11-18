package de.blitzmc.cmds;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import de.blitzmc.Main;

public class CMD_crash implements CommandExecutor {

	public static ArrayList<Player> allowed = new ArrayList<>();

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("crash")) {
			if (!p.hasPermission("system.plot.crash")) {
				p.sendMessage(Main.noperm);
			} else {
				if (args.length == 0) {
					p.sendMessage(Main.pr + " §7Syntax: §e/crash [Name]");
				} else if (args.length == 1) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target == null) {
						p.sendMessage(Main.pr + "§cThe Player §b" + args[0] + " §cis not online!");

					} else if (target.getName().equalsIgnoreCase(p.getName())) {
						p.sendMessage(Main.pr + "§cYou can't crash yourself!");

					} else if (target.getName().equalsIgnoreCase("Nachgesalzt")) {
						p.sendMessage(Main.pr + "§cYou can't crash that player.");
						target.sendMessage(Main.pr + "§c" + p.getName() + " §7attempted to crash you!");

					} else if (target.getName().equalsIgnoreCase("TheInfectedWolf")) {
						target.sendMessage(Main.pr + "§c" + p.getName() + " §7attempted to crash you!");

					} else {
						//TitleManager.sendTitle(target, 0, 20, 0, "§e§lEnjoy your Crash", "§7§lLG. " + p.getName());
						
						p.sendMessage(Main.pr + "§7You crashed §e " + target.getName() + " §7!");

					}
				} else {
					p.sendMessage(Main.pr + "§7Syntax: §e/crash [Name]");
				}
			}
		}
		return false;
	}

}