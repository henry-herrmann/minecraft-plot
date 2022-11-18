package de.blitzmc.cmds;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_GM implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("gm")){
			if(p.hasPermission("system.gamemode")){
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("0")){
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(Main.pr + "Your gamemode has been updated to §cSURVIVAL!");
					}
					if(args[0].equalsIgnoreCase("1")){
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(Main.pr + "Your gamemode has been updated to §6CREATIVE§a!");
					}
					if(args[0].equalsIgnoreCase("2")){
						p.setGameMode(GameMode.ADVENTURE);
						p.sendMessage(Main.pr + "Your gamemode has been updated to §4ADVENTURE§a!");
					}
					if(args[0].equalsIgnoreCase("3")){
						p.setGameMode(GameMode.SPECTATOR);
						p.sendMessage(Main.pr + "Your gamemode has been updated to §4SPECTATOR§a!");
					}
				}
			}else{
				p.sendMessage(Main.noperm);
			}
		}
		return false;
	}

	
}
