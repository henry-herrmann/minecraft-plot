package de.blitzmc.cmds;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.Inventar;


public class CMD_jobcenter implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("job") || cmd.getName().equalsIgnoreCase("jobs")){
			
			if(args.length == 0) {
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
				Inventar.JobsGui(p, "Jobcenter");
			}
		}
		return false;
	}
}
