package de.blitzmc.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_Execute implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("execute")){
			if(p.hasPermission("system.plot.execute")){
				if(args.length > 1){
					Player t = Bukkit.getPlayer(args[0]);
					
					String msg = "";
					for(int i = 1; i < args.length; i++){
						msg = msg + args[i] + " ";
					}
					
					if(t != null){
						if(p!=t){
							if(!t.hasPermission("system.team")){
								t.performCommand(msg);
								p.sendMessage(Main.pr + "Der Command §6/" + msg + "§a wurde von dem Spieler §6" + t.getName() + "§a ausgeführt!");
							}else{
								p.sendMessage(Main.pr + "§cDu hast keine Berichtigung ein Teammitglied zu steuern!");
							}
						}
					}else{
						p.sendMessage(Main.pr + "§cDer angegebene Spieler ist nicht online!");
					}
				}
			}else{
				p.sendMessage(Main.noperm);
			}
		}
		return false;
	}

}
