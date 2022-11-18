package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.ParticleManager;
import de.blitzmc.utils.SetSpawn;

public class CMD_Set implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("set")){
			if(p.hasPermission("system.set")){
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("spawn")){
						SetSpawn.setSpawn(p);
						p.sendMessage(Main.pr + "Du hast den Spawn Punkt gesetzt!");
					}else if(args[0].equalsIgnoreCase("helix")){
						ParticleManager.setHelix(p);
						p.sendMessage(Main.pr + "Ok");
					}else if(args[0].equalsIgnoreCase("plotvillager")){
						p.performCommand("npc create &8» &6Plot");
						p.performCommand("npc type Villager");
						p.sendMessage(Main.pr + "Du hast den Plot Villager gesetzt!");
					}else if(args[0].equalsIgnoreCase("shopvillager")){
						p.performCommand("npc create &8» &6Material-Shop");
						p.performCommand("npc create &8» &6Plot");
						p.sendMessage(Main.pr + "Du hast den Material Villager gesetzt!");
					}else if(args[0].equalsIgnoreCase("farmwelt")){
						SetSpawn.setFarmwelt(p);
						p.sendMessage(Main.pr + "Du hast die Farmwelt gesetzt!");
					}else if(args[0].equalsIgnoreCase("markt")) {
						SetSpawn.setMarkt(p);
						p.sendMessage(Main.pr + "Du hast den Markt Spawn gesetzt!");
					}else if(args[0].equalsIgnoreCase("end")) {
						SetSpawn.setEnd(p);
						p.sendMessage(Main.pr + "Du hast den End Spawn gesetzt!");
					}else if(args[0].equalsIgnoreCase("nether")) {
						SetSpawn.setNether(p);
						p.sendMessage(Main.pr + "Du hast den Nether Spawn gesetzt!");
					}else{
						p.sendMessage(Main.pr + "§cNutze: /set <spawn, helix, plotvillager, shopvillager>");
					}
				}
				if(args.length == 0) {
					p.sendMessage(Main.pr + "§cNutze: /set <spawn, helix, plotvillager, shopvillager, farmwelt, markt>");
				}
			}
		}
		return false;
	}
}
