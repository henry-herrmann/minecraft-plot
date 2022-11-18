package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.Inventar;

public class CMD_Warp implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("Warp")){
			if(args.length == 0) {
				Inventar.WarpGui((Player) sender);
			}
			
			if(args.length == 1) {
				p.sendMessage(Main.pr + "§cNutze: /warp");
			}
		}
		return false;
	}

}
