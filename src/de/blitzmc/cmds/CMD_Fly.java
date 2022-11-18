package de.blitzmc.cmds;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;


public class CMD_Fly implements CommandExecutor{
	
	public static ArrayList<Player> fly = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		
		if(cmd.getName().equalsIgnoreCase("fly")) {
			if(!(sender instanceof Player)) {
				System.out.println("You are not a player!");
				return true;
			}
			
			if((!p.hasPermission("system.plot.fly"))) {
				p.sendMessage(Main.noperm);
				return false;
			} else{
			  if(args.length == 0){
				if(fly.contains(p)){
					fly.remove(p);
					p.setAllowFlight(false);
					p.setFlying(false);
					p.sendMessage(Main.pr + "FlyMode §cdeactivated.");
				}else{
					fly.add(p);
					p.setAllowFlight(true);
					p.setFlying(true);
					p.sendMessage(Main.pr + "FlyMode activated.");
				}
			  }else{
				  p.sendMessage(Main.pr + "Nutze: §6/fly");
			  }
			}
			return true;
		}
		return false;
	}

}
