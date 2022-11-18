package de.blitzmc.cmds;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.BanManager;

public class CMD_tempban implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		Player p = (Player) sender;
		
		if(p.hasPermission("system.tempban")) {
			if(args.length == 0 | args.length == 1) {
				p.sendMessage(Main.pr + "§cSyntax: /tempban <Player> <duration (1d, 30m)> [Reason]");
				return true;
			}
			
			String format = args[1].substring(args[1].length() - 1, args[1].length());
			
			long duration = Long.valueOf(args[1].substring(0, args[1].length() -1));
			
			
			switch(format) {
			case "s":
				duration = duration * 1000;
				break;
			case "m":
				duration = duration * 1000 * 60;
				break;
			case "h":
				duration = duration * 1000 * 60 * 60;
				break;
			case "d":
				duration = duration * 1000 * 60 * 60 * 24;
				break;
			case "w":
				duration = duration * 1000 * 60 * 60 * 24 * 7;
				break;
				
				default:
					p.sendMessage(Main.pr + "§cUse a valid time format (d,h,m,s)!");
					return true;
			}
			
			if(args.length >= 2) {
				String reason = "";
				
				for(int i = 2; i< args.length; i++) {
					reason = reason + args[i] + " ";
				}
				
				reason.trim();
				
				BanManager bm = new BanManager(BanManager.getUUIDFromName(args[0]));
				
				if(!bm.exist()) {
					p.sendMessage(Main.pr + "§cKeine Akte vorhanden!");
					return true;
				}
				
                String BannedFromRank = "";
				
				if(Main.getGroupID(p) == 1) {
					BannedFromRank = "Administrator";
				}
				if(Main.getGroupID(p) == 3) {
					BannedFromRank = "Moderator";
				}
				long zeit = duration * 1000 * 60 * 60 * 24 * 7;
				
				
				bm.setTempbanned(BannedFromRank, reason.isEmpty() ? "Misbehavior" : reason, duration);
				
				
				
				Player t = Bukkit.getPlayer(args[0]);
				
				File logs = new File("plugins/GS", "Banlist.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
				
				
				if(!logs.exists()) {
					try{
						logs.createNewFile();
					} catch (Exception e){
						
					}
				}
				
				cfg.set(t.getName() + ".status", true);
				cfg.set(t.getName() + ".dauer", bm.getTempbanMilliseconds());
				cfg.set(t.getName() + ".perm", false);
				
				try {
					cfg.save(logs);
				} catch (IOException e) {
					
				}
				
				if(Main.getGroupID(t) == 1) {
					p.sendMessage(Main.pr + "§cYou don't have the permission to ban the Administrator.");
					return true;
				}
				if(Main.getGroupID(t) == 3) {
					p.sendMessage(Main.pr + "§cYou don't have the permission to ban a Moderator.");
					return true;
				}
				
				if(t != null) {
					t.kickPlayer("§cYou have been temporarily banned by a/an §e" + BannedFromRank + "\n\n" 
		                                + "§cExpiration Date: §e" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(bm.getTempbanMilliseconds()) + "\n\n§cReason:§e " + bm.getTempbanReason());
					
					p.sendMessage(Main.pr + "You temporarily banned §e" + args[0] + " §a until §e" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(bm.getTempbanMilliseconds()));
				}else {
					p.sendMessage(Main.pr + "§cThe stated player is not online.");
				}
			}
		}
		return false;
	}

}
