package de.blitzmc.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_Msg implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("msg")) {
			if(args.length >= 1) {
				Player t = Bukkit.getPlayer(args[0]);
				
                 String msg = "";
				
				for(int i = 1; i< args.length; i++) {
					msg = msg  + " " + args[i];
				}
				
				Main.msgs.put(t, p);
				p.sendMessage(Main.pr + "§e" + p.getName() + " §a>" + " §e" + t.getName() + "§a: §e" + msg);
				t.sendMessage(Main.pr + "§e" + t.getName() + " §a>" + " §e" + p.getName() + "§a: §e" + msg);
			}
			
			if(args.length < 1) {
				p.sendMessage(Main.pr + "§cSyntax: /msg <Player> <message>");
			}
		}
		
		return false;
	}
}
