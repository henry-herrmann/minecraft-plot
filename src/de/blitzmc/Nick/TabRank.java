package de.blitzmc.Nick;

import java.awt.Color;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TabRank {
	

	

	public static void set() {
		  for (@SuppressWarnings("unused") Player player : Bukkit.getOnlinePlayers()) {
		   
		   Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

		   Team Owner = board.registerNewTeam("00001Owner");
		   Team Admin = board.registerNewTeam("00002Admin");
		   Team SrModerator = board.registerNewTeam("00003Moderator");
		   Team Moderator = board.registerNewTeam("00004Moderator");
		   Team SrSupporter = board.registerNewTeam("00005SrSupporter");
		   Team Supporter = board.registerNewTeam("00006Supporter");
		   Team JrSupporter = board.registerNewTeam("00007JrSupporter");
		   Team Builder = board.registerNewTeam("00008Builder");
		   Team YouTuber = board.registerNewTeam("00009YouTuber");
		   Team Premium = board.registerNewTeam("00011Premium");
		   Team VIP = board.registerNewTeam("00010VIP");
		   Team Spieler = board.registerNewTeam("00012Spieler");
		   
		   
		   Owner.setPrefix("§4§lOwner §8× §4");
		   Admin.setPrefix("§4§lAdmin §8× §8");
		   Admin.setColor(ChatColor.GRAY);
		   SrModerator.setPrefix("§cSrMod §8× §7");
		   Moderator.setPrefix("§cMod §8× §7");
		   Moderator.setColor(ChatColor.GRAY);
		   SrSupporter.setPrefix("§9SrSup §8× §7");
		   Supporter.setPrefix("§9Sup §8× §7");
		   JrSupporter.setPrefix("§9JrSup §8× §9");
		   Builder.setPrefix("§eBuilder §8× §7");
		   Builder.setColor(ChatColor.GRAY);
		   YouTuber.setPrefix("§5YT §8× §7");
		   VIP.setPrefix("§dExperte §8× §7");
		   VIP.setColor(ChatColor.GRAY);
		   Premium.setPrefix("§6");
		   Spieler.setPrefix("§7");
		   Spieler.setColor(ChatColor.GRAY);

		   /*Bukkit.getOnlinePlayers().forEach(p -> {
			   if (PermissionsEx.getUser(p).inGroup("Owner")) {
				     Owner.addEntry(p.getName());

				    } else if (PermissionsEx.getUser(p).inGroup("Admin")) {
				     Admin.addEntry(p.getName());

				    } else if (PermissionsEx.getUser(p).inGroup("SrModerator")) {
				     SrModerator.addEntry(p.getName());

				    } else if (PermissionsEx.getUser(p).inGroup("Moderator")) {
				     Moderator.addEntry(p.getName());

				    } else if (PermissionsEx.getUser(p).inGroup("SrSupporter")) {
				     SrSupporter.addEntry(p.getName());

				    } else if (PermissionsEx.getUser(p).inGroup("Supporter")) {
				     Supporter.addEntry(p.getName());

				    } else if (PermissionsEx.getUser(p).inGroup("JrSupporter")) {
				     JrSupporter.addEntry(p.getName());

				    } else if (PermissionsEx.getUser(p).inGroup("Builder")) {
				     Builder.addEntry(p.getName());

				    } else if (PermissionsEx.getUser(p).inGroup("YouTuber")) {
				     YouTuber.addEntry(p.getName());

				    } else if (PermissionsEx.getUser(p).inGroup("Premium")) {
				     Premium.addEntry(p.getName());

				    } else if (PermissionsEx.getUser(p).inGroup("Experte")) {
				     VIP.addEntry(p.getName());

				    	
				    }else {
				     Spieler.addEntry(p.getName());
				    }

				    p.setScoreboard(board); 
		   });*/
		  }

		 }
}
