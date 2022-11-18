package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_r implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("r")) {
			Player p2 = Main.msgs.get(p);
			String msg = "";
			
			if(args.length >= 1) {
				if(Main.msgs.containsKey(p) || Main.msgs.containsKey(p2)) {
					for(int i = 1; i < args.length; i++) {
						msg = msg + " " + args[i];
					}
					
					p.sendMessage(Main.pr + "§e" + p.getName() + " §a>" + " §e" + p2.getName() + "§a: §e" + msg);
					p2.sendMessage(Main.pr + "§e" + p2.getName() + " §a>" + " §e" + p.getName() + "§a: §e" + msg);
				}else {
					p.sendMessage(Main.pr + "§cDir wurde noch keine Nachricht gesendet.");
				}
			}
			if(args.length == 0) {
				p.sendMessage(Main.pr + "§cNutze: /r <Nachricht>");
			}
		}
		return false;
	}

}
