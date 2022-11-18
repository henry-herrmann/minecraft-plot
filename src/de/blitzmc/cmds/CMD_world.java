package de.blitzmc.cmds;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.WorldManager;

public class CMD_world implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cDu bist kein Spieler!");
			return true;
		}
		if(!p.hasPermission("system.worldadmin")) {
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("world")) {
			if(args.length == 2) {
				String worldname = args[1];
				World wld = Bukkit.getWorld(worldname);
				
				if(args[0].equalsIgnoreCase("import")) {
					if(wld != null) {
						p.sendMessage(Main.pr + "§cDiese Welt ist breits vorhanden!");
						return true;
					}
					File getworld = new File(args[1]);
					if(!getworld.exists()) {
						p.sendMessage(Main.pr + "§cDiese Welt befindet sich nicht im Serverordner!");
					}
					
					try {
						WorldCreator wc = new WorldCreator(worldname);
						Bukkit.createWorld(wc);
						Bukkit.getWorld(worldname).setAmbientSpawnLimit(0);
						Bukkit.getWorld(worldname).setAnimalSpawnLimit(0);
						Bukkit.getWorld(worldname).setMonsterSpawnLimit(0);
						Bukkit.getWorld(worldname).setWaterAnimalSpawnLimit(0);
						Bukkit.getWorld(worldname).setStorm(true);
						Bukkit.getWorld(worldname).setPVP(true);
						WorldManager.addWorld(worldname);
						p.sendMessage(Main.pr + "Welt importiert!");
					}catch(Exception e19) {
						
					}
				}
				if(args[0].equalsIgnoreCase("unload")) {
					if (wld == null)
			          {
			            p.sendMessage(Main.pr + "§cDiese Welt ist nicht vorhanden");
			            return true;
			          }
			          if (wld.getPlayers().size() != 0)
			          {
			            p.sendMessage(Main.pr + "§cEs befinden sich Spieler in dieser Welt");
			            return true;
			          }
			          try{
			            p.sendMessage(Main.pr + "§cBitte warten! Die Welt wird entfernt!");
			            Bukkit.unloadWorld(worldname, true);
			            WorldManager.deleteWorld(worldname);
			            p.sendMessage(Main.pr + "Welt wurde entfernt.");
			            
			          }catch (Exception e){
			        	  
			          }
				}
				if(args[0].equalsIgnoreCase("delete")) {
					if (wld != null)
		            {
		              p.sendMessage(Main.pr + "§cDie Welt muss erst ungeladen werden!");
		              return true;
		            }
		            File getworld = new File(args[1]);
		            if (!getworld.exists())
		            {
		              p.sendMessage(Main.pr + "§cDiese Welt befindet sich nicht im Serverordner!");
		              return true;
		            }
		            getworld.delete();
		            WorldManager.deleteWorld(worldname);
		            p.sendMessage(Main.pr + "Welt wurde erfolgreich gelöscht!");
				}
			}
			if(args[0].equalsIgnoreCase("tp")) {
				String worldname = args[1];
				World wld = Bukkit.getWorld(worldname);
				if (wld == null){
	              p.sendMessage(Main.pr + "§cDiese Welt ist nicht vorhanden");
	              return true;
	            }
				
	            try{
	              p.teleport(wld.getSpawnLocation());
	              p.sendMessage(Main.pr + "Du wurdest in die angegebene Welt teleportiert.");
	            }
	            catch (Exception e){
	              
	            }
			}
			if(args.length == 3) {
				String wn = args[1];
				World wld = Bukkit.getWorld(wn);
				if(args[0].equalsIgnoreCase("create")) {
					if (wld != null){
			            p.sendMessage(Main.pr + "§cDiese Welt ist nicht vorhanden");
			            return true;
			        }
					try{
			            WorldCreator wc = new WorldCreator(wn);
			            if (args[2].equalsIgnoreCase("flat")){
			              wc.type(WorldType.FLAT);
			            }else if (args[2].equalsIgnoreCase("normal")) {
			              wc.type(WorldType.NORMAL);
			            }else{
			              return true;
			            }
			            
			            p.sendMessage(Main.pr + "Bitte warten! Die Welt wird erstellt.");
			            wc.createWorld();
			            Bukkit.createWorld(wc);
			            Bukkit.getWorld(wn).setAmbientSpawnLimit(0);
			            Bukkit.getWorld(wn).setAnimalSpawnLimit(0);
			            Bukkit.getWorld(wn).setMonsterSpawnLimit(0);
			            Bukkit.getWorld(wn).setWaterAnimalSpawnLimit(0);
			            Bukkit.getWorld(wn).setStorm(false);
			            Bukkit.getWorld(wn).setPVP(true);
			            WorldManager.addWorld(wn);
			            p.sendMessage(Main.pr + "Welt wurde erstellt,");
			            
			          }catch (Exception e){
			        	  
			          }
				}
			}
		}
		
		return false;
	}

}
