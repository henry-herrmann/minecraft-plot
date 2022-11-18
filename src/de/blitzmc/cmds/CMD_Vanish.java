package de.blitzmc.cmds;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;



public class CMD_Vanish implements CommandExecutor{

	public static ArrayList<Player> list = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("v") || cmd.getName().equalsIgnoreCase("vanish")) {
			if (p.hasPermission("system.vanish")) {

				if (list.contains(p)) {
					list.remove(p);
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage(Main.pr + "Du bist nun nicht mehr im §6Vanish§7!");

					for (Player all : Bukkit.getOnlinePlayers()) {
						all.showPlayer(p);

					}

				} else if(!list.contains(p)){
           
					if (list.add(p)) {
						p.setGameMode(GameMode.SPECTATOR);
						p.sendMessage(Main.pr + "Du bist nun im §6Vanish§7!");

						for (Player all : Bukkit.getOnlinePlayers()) {
							all.hidePlayer(p);
						}

					}

				}
			}
		}
		return false;
	}
}
