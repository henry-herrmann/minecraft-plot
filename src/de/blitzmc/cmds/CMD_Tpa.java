package de.blitzmc.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;

public class CMD_Tpa implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("tpa")){
			if(args.length == 1){
				try{
					if(!Main.tpa.containsKey(p)){
						Player p2 = Bukkit.getPlayer(args[0]);
						
						Main.tpa.put(p2, p);
						
						p2.sendMessage(Main.pr + "§6" + p.getName() + "§a würde dich gerne besuchen. Nutze §6/tpaaccept");
						p.sendMessage(Main.pr + "Anfrage gesendet!");
					}else{
						p.sendMessage(Main.pr + "§cDu hast schon eine Anfrage gesendet.");
					}
					
				}
				catch(Exception e){
					p.sendMessage(Main.pr + "§cKein Spieler gefunden.");
				}
				
			}
			if(args.length == 0){
				p.sendMessage(Main.pr + "§cSyntax: /tpa <Player>");
			}
		}
		if(cmd.getName().equalsIgnoreCase("tpaaccept")){
			
		if(args.length == 0){
			try {
				Player p2 = Main.tpa.get(p);
				p2.teleport(p.getLocation());
				p2.sendMessage(Main.pr + "Dein Anfrage wurde akzeptiert!");
				p.sendMessage(Main.pr + "Du hast die eingehende Anfrage angenommen!");
				Main.tpa.remove(p2);
				Main.tpa.remove(p);
			}catch(NullPointerException e) {
				
			}
		}else{
			p.sendMessage(Main.pr + "§cSyntax: /tpaaccept!");
		}
		}
		return false;
	}

}
