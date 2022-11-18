package de.blitzmc.cmds;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.Directional;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.material.Stairs;

import de.blitzmc.Main;

public class CMD_event implements CommandExecutor{
	
	int delay;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("event")) {
			if(p.hasPermission("*")) {
				if(args.length == 0) {
					p.sendMessage(Main.pr + "§cNutze: /event <End>");
				}
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("end")) {
						
						Sign b1 = (Sign) new Location(Bukkit.getWorld("world"), -614, 70, 546).getBlock().getState();
						
						b1.setLine(0, "§lDas End");
						b1.setLine(1, "");
						b1.setLine(2, "");
						b1.setLine(3, "");
						b1.update();
						
						
						for(int i = 544; i <= 548; i++) {
							Block b = new Location(Bukkit.getWorld("world"), -615, 70, i).getBlock();
							
							
							b.setType(Material.AIR);
						}
						
						for(int i = 544; i <= 548; i++) {
							Block b = new Location(Bukkit.getWorld("world"), -615, 71, i).getBlock();
							
							
							b.setType(Material.AIR);
						}
						
						for(int i = 544; i <= 548; i++) {
							Block b = new Location(Bukkit.getWorld("world"), -615, 72, i).getBlock();
							
							
							b.setType(Material.AIR);
						}
						
						for(int i = 545; i <= 547; i++) {
							Block b = new Location(Bukkit.getWorld("world"), -615, 73, i).getBlock();
							
							
							b.setType(Material.AIR);
						}
						
						
						for(int i = 544; i <= 548; i++) {
							Block b11 = new Location(Bukkit.getWorld("world"), -615, 69, i).getBlock();
							b11.setType(Material.STONE_BRICK_STAIRS);
							
							Directional d = (Directional) b11.getBlockData();
							
							d.setFacing(BlockFace.EAST);
							
							b11.setBlockData(d);
						}
						
						for(int i = 544; i <= 548; i++) {
							Block b = new Location(Bukkit.getWorld("world"), -616, 69, i).getBlock();
							
							b.setType(Material.AIR);
						}
						
						for(int i = 544; i <= 548; i++) {
							Block b = new Location(Bukkit.getWorld("world"), -617, 69, i).getBlock();
							
							b.setType(Material.AIR);
						}
						for(int i = 544; i <= 548; i++) {
							Block b = new Location(Bukkit.getWorld("world"), -618, 69, i).getBlock();
							
							b.setType(Material.AIR);
						}
						for(int i = 544; i <= 548; i++) {
							Block b = new Location(Bukkit.getWorld("world"), -619, 69, i).getBlock();
							
							b.setType(Material.AIR);
						}
						for(Player all : Bukkit.getOnlinePlayers()) {
							all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1F, 1F);
							all.sendMessage(" ");
	                        all.sendMessage("          §a§lDas Ender Dragon Event hat begonnen!");
	                        all.sendMessage("                         §6Der Sieger wird belohnt!");
	                        all.sendMessage(" ");
						}
						File logs = new File("plugins/GS", "Wartungen.yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
						
						cfg.set("end_access"+ ".status", true);
						cfg.set("end_event"+ ".status", true);
						try {
							cfg.save(logs);
						} catch (IOException e) {
							
						}
					}
				}
			}
		}
		return false;
	}

}
