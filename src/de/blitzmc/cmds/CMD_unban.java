package de.blitzmc.cmds;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.BanManager;

public class CMD_unban implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("unban")) {
			if(p.hasPermission("system.tempban")) {
				if(args.length == 1) {
					BanManager bm = new BanManager(BanManager.getUUIDFromName(args[0]));
					
					if(!bm.exist()) {
						p.sendMessage(Main.pr + "§cThe stated player is not banned!");
					}
					if(bm.isTempbanned()) {
						bm.setunTempbanned();
						
						File logs = new File("plugins/GS", "Banlist.yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
						
						
						if(!logs.exists()) {
							try{
								logs.createNewFile();
							} catch (Exception e){
								
							}
						}
						
						cfg.set(p.getName(), null);
						
						try {
							cfg.save(logs);
						} catch (IOException e) {
							
						}
						
						p.sendMessage(Main.pr + "Player successfully unbanned!");
					}
					if(bm.isPermBanned()) {
						p.sendMessage(Main.pr + "Player successfully unbanned!");
						bm.unPermBan();
					}	
				}
			}
		}
		return false;
	}

}
