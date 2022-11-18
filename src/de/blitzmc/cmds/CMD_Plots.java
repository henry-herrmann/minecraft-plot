package de.blitzmc.cmds;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.Currency;
import de.blitzmc.utils.Inventar;
import de.blitzmc.utils.PlotManager;
import de.blitzmc.utils.RegionManager;

public class CMD_Plots implements CommandExecutor{
	PlotManager pm;
	Currency c;

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		
		
		 File logs1 = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration pl= YamlConfiguration.loadConfiguration(logs1);
		
		if(cmd.getName().equalsIgnoreCase("plot")){
			if(args.length == 0){
				if(PlotManager.existList(p.getUniqueId().toString())) {
					if(pl.getString(p.getUniqueId().toString() + ".Name") != null && pl.getString(p.getUniqueId().toString() + ".Name1") == null && pl.getString(p.getUniqueId().toString() + ".Name2") == null) {
						Inventar.PlotGUI(p, "Plot");
					}else if(pl.getString(p.getUniqueId().toString() + ".Name") != null && pl.getString(p.getUniqueId().toString() + ".Name1") != null && pl.getString(p.getUniqueId().toString() + ".Name2") == null) {
						Inventar.MutiplePlots(p,  "Multiple Plots");
					}else if(pl.getString(p.getUniqueId().toString() + ".Name") != null && pl.getString(p.getUniqueId().toString() + ".Name1") != null && pl.getString(p.getUniqueId().toString() + ".Name2") != null){
						Inventar.MutiplePlots(p,  "Multiple Plots");
					}else if(pl.getString(p.getUniqueId().toString() + ".Name") == null && pl.getString(p.getUniqueId().toString() + ".Name1") != null && pl.getString(p.getUniqueId().toString() + ".Name2") == null) {
						Inventar.Plot2GUI(p, "Plot 2");
					}else if(pl.getString(p.getUniqueId().toString() + ".Name") == null && pl.getString(p.getUniqueId().toString() + ".Name1") == null && pl.getString(p.getUniqueId().toString() + ".Name2") != null) {
						Inventar.Plot3GUI(p, "Plot 3");
					}else if(pl.getString(p.getUniqueId().toString() + ".Name") != null && pl.getString(p.getUniqueId().toString() + ".Name1") == null && pl.getString(p.getUniqueId().toString() + ".Name2") != null) {
						Inventar.MutiplePlots(p,  "Multiple Plots");
					}
				}else {
					p.sendMessage(Main.pr + "§cBitte nutze: /plot <Name>, um ein Plot auf einer freien Fläche zu erstellen");
				}
				return true;
			}
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("get")){
					if(args[1].equalsIgnoreCase("list")) {
						if(p.hasPermission("system.plot.list")){
							pm.PlotList(p);
							if(Main.inputed.contains(p)){
								for(String input : Main.message.keySet()){
									p.sendMessage(Main.pr + "Input:" + input);
									Main.inputed.remove(p);
								}
							}
						}else{
							p.sendMessage(Main.noperm);
						}
					}
					if(args[1].equalsIgnoreCase("members")) {
						PlotManager.MemberOfPlotList(p);
					}
				}else if(args[0].equalsIgnoreCase("reload")){
					if(p.hasPermission("system.plot.reload")){
						Bukkit.getServer().reload();
						p.sendMessage("Ja");
					}
				}
			}
			if(args.length == 1) {
				
			}
			if(args.length == 1) {
				if(p.getWorld().getName().equals("world")){
					if(pl.getString(p.getUniqueId().toString() + ".Name") == null) {
						if(PlotManager.creatable(p)) {
							if(PlotManager.getPlot() != args[0]) {
								if(RegionManager.creatable(p) && RegionManager.isCreated(p.getLocation().getX(), p.getLocation().getZ(), p.getWorld().getName()) == null) {
									if(PlotManager.exist(args[0])){
										p.sendMessage(Main.pr + "§cName vergeben!");
									}else{
										if(pl.getString(p.getUniqueId().toString() + ".Name") == null) {
											//if(Currency.getDollar(p) >= 1000) {
												PlotManager.setPlayerList(p, args[0]);
												//Currency.removeDollar(p, 1000);
												PlotManager.setPlot(p, args[0], 30);
												//TitleManager.sendTitle(p, 3, 22, 3, "§6Money", "§c-1000");
												p.sendMessage(Main.pr + "§aDu hast das Plot§6 " + args[0] + "§a erstellt!");
											//}else {
												//p.sendMessage(Main.pr + "§cDu brauchst mindestens §e1000 §cDollar.");
											///}
										}/*else if(pl.getString(p.getUniqueId().toString() + ".Name1") == null) {
											if(Currency.getDollar(p) >= 19500) {
												PlotManager.setPlayerList2(p, args[0]);
												Currency.removeDollar(p, 19500);
												PlotManager.setPlot2(p, args[0], 30);
												TitleManager.sendTitle(p, 3, 22, 3, "§6Money", "§c-19500");
												p.sendMessage(Main.pr + "§aDu hast das Plot§6 " + args[0] + "§a erstellt!");
											}else {
												p.sendMessage(Main.pr + "§cFür dein zweites Plot brauchst du mindestens §e19500 §cDollar. Dafür ist es dann extra groß.");
											}
										}*/
									}
								}else{
					    			p.sendMessage(Main.pr + "§cDein Plot darf sich nicht in einer Region befinden!");
					    		}
							}
						}else{
					    	p.sendMessage(Main.pr + "§cDein Plot darf nicht auf einem anderen Plot liegen!");
					    }
					}else {
						p.sendMessage(Main.pr + "§cDu hast schon ein Plot!");
					}
			   }else{
				   p.sendMessage(Main.pr + "§cPlots sind nur in der normalen Welt erlaubt!");
			   }
			}
		}
		return false;
	}
	
	

}
