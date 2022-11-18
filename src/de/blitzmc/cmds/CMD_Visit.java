package de.blitzmc.cmds;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.blitzmc.Main;
import de.blitzmc.utils.PlotManager;

public class CMD_Visit implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("visit")){
			if(args.length == 1){
				Player t = Bukkit.getPlayer(args[0]);
				
				File logs1 = new File("plugins/GS", "Player-List.yml");
				YamlConfiguration pl= YamlConfiguration.loadConfiguration(logs1);
				
				if(t != null){
					if(t != p){
						if(PlotManager.existList(t.getUniqueId().toString())){
							if(pl.getString(t.getUniqueId().toString() + ".Name") != null && pl.getString(t.getUniqueId().toString() + ".Name1") == null && pl.getString(t.getUniqueId().toString() + ".Name2") == null) {
								if(p.getUniqueId().toString().equals(PlotManager.getFirstMemberUUID(t))) {
									PlotManager.Visit(t, p);
								}else if(p.getUniqueId().toString().equals(PlotManager.getSecondMemberUUID(t))){
									PlotManager.Visit(t, p);
								}else if(p.getUniqueId().toString().equals(PlotManager.getThirdMemberUUID(t))) {
									PlotManager.Visit(t, p);
								}else {
									if(pl.getString(t.getUniqueId().toString() + ".tp-request").equals("true")) {
										PlotManager.Visit(t, p);
									}else {
										p.sendMessage(Main.pr + "§cThe stated player disabled visits!");
									}
								}
							}else if(pl.getString(t.getUniqueId().toString() + ".Name") != null && pl.getString(t.getUniqueId().toString() + ".Name1") != null && pl.getString(t.getUniqueId().toString() + ".Name2") == null) {
								p.sendMessage(Main.pr + "§cThe stated player owns 2 Plots! Please us: §e/visit <Name> <1,2>");
							}else if(pl.getString(t.getUniqueId().toString() + ".Name") != null && pl.getString(t.getUniqueId().toString() + ".Name1") != null && pl.getString(t.getUniqueId().toString() + ".Name2") != null) {
								p.sendMessage(Main.pr + "§cThe stated player owns 3 Plots! Please us: §e/visit <Name> <1,2,3>");
							}
						}else{
							p.sendMessage(Main.pr + "§cThe stated player doesn't own a Plot");
						}
					}else{
						p.sendMessage(Main.pr + "§cDu kannst dich nicht selbst besuchen!");
					}
				}else{
					@SuppressWarnings("deprecation")
					OfflinePlayer o = Bukkit.getOfflinePlayer(args[0]);
					
					if(o != p){
						if(PlotManager.existList(o.getUniqueId().toString())){
							if(pl.getString(o.getUniqueId().toString() + ".Name") != null && pl.getString(o.getUniqueId().toString() + ".Name1") == null && pl.getString(o.getUniqueId().toString() + ".Name2") == null) {
								if(p.getUniqueId().toString().equals(PlotManager.getFirstOffMemberUUID(o))) {
									PlotManager.VisitOffline(o, p);
								}else if(p.getUniqueId().toString().equals(PlotManager.getSecondOffMemberUUID(o))){
									PlotManager.VisitOffline(o, p);
								}else if(p.getUniqueId().toString().equals(PlotManager.getThirdOffMemberUUID(o))){
									PlotManager.VisitOffline(o, p);
								}else {
									if(pl.getString(o.getUniqueId().toString() + ".tp-request").equals("true")) {
										PlotManager.VisitOffline(o, p);
									}else {
										p.sendMessage(Main.pr + "§cThe stated player disabled visits!");
									}
								}
							}else if(pl.getString(o.getUniqueId().toString() + ".Name") != null && pl.getString(o.getUniqueId().toString() + ".Name1") != null && pl.getString(o.getUniqueId().toString() + ".Name2") == null) {
								p.sendMessage(Main.pr + "§cDer Spieler besitzt §e2 §cPlots! Bitte nutze: §e/visit <Name> <1,2>");
							}else if(pl.getString(o.getUniqueId().toString() + ".Name") != null && pl.getString(o.getUniqueId().toString() + ".Name1") != null && pl.getString(o.getUniqueId().toString() + ".Name2") != null) {
								p.sendMessage(Main.pr + "§cDer Spieler besitzt §e3 §cPlots! Bitte nutze: §e/visit <Name> <1,2,3>");
							}
						}
					}
				}
			}
			if(args.length == 2) {
                Player t = Bukkit.getPlayer(args[0]);
				
				File logs1 = new File("plugins/GS", "Player-List.yml");
				YamlConfiguration pl= YamlConfiguration.loadConfiguration(logs1);
				
				if(args[1].equalsIgnoreCase("1")) {
					if(t != null){
						if(t != p){
							if(PlotManager.existList(t.getUniqueId().toString())){
								if(pl.getString(t.getUniqueId().toString() + ".Name") != null) {
									if(p.getUniqueId().toString().equals(PlotManager.getFirstMemberUUID(t))) {
										PlotManager.Visit(t, p);
									}else if(p.getUniqueId().toString().equals(PlotManager.getSecondMemberUUID(t))){
										PlotManager.Visit(t, p);
									}else if(p.getUniqueId().toString().equals(PlotManager.getThirdMemberUUID(t))) {
										PlotManager.Visit(t, p);
									}else {
										if(pl.getString(t.getUniqueId().toString() + ".tp-request").equals("true")) {
											PlotManager.Visit(t, p);
										}else {
											p.sendMessage(Main.pr + "§cThe stated player disabled visits!");
										}
									}
								}
							}else{
								p.sendMessage(Main.pr + "§cThe stated player doesn't own a Plot");
							}
						}else{
							p.sendMessage(Main.pr + "§cYou can not visit yourself!");
						}
					}else{
						@SuppressWarnings("deprecation")
						OfflinePlayer o = Bukkit.getOfflinePlayer(args[0]);
						
						if(o != p){
							if(PlotManager.existList(o.getUniqueId().toString())){
								if(p.getUniqueId().toString().equals(PlotManager.getFirstOffMemberUUID(o))) {
									PlotManager.VisitOffline(o, p);
								}else if(p.getUniqueId().toString().equals(PlotManager.getSecondOffMemberUUID(o))){
									PlotManager.VisitOffline(o, p);
								}else if(p.getUniqueId().toString().equals(PlotManager.getThirdOffMemberUUID(o))){
									PlotManager.VisitOffline(o, p);
								}else {
									if(pl.getString(o.getUniqueId().toString() + ".tp-request").equals("true")) {
										PlotManager.VisitOffline(o, p);
									}else {
										p.sendMessage(Main.pr + "§cThe stated player disabled visits!");
									}
								}
							}else{
								p.sendMessage(Main.pr + "§cThe stated player doesn't own a Plot");
							}
						}
					}
				}
				if(args[1].equalsIgnoreCase("2")) {
					if(t != null){
						if(t != p){
							if(PlotManager.existList(t.getUniqueId().toString())){
								if(pl.getString(t.getUniqueId().toString() + ".Name1") != null) {
									if(pl.getString(t.getUniqueId().toString() + ".Name1") != null) {
										if(p.getUniqueId().toString().equals(PlotManager.getFirstMemberUUID(t))) {
											PlotManager.Visit2(t, p);
										}else if(p.getUniqueId().toString().equals(PlotManager.getSecondMemberUUID(t))){
											PlotManager.Visit2(t, p);
										}else if(p.getUniqueId().toString().equals(PlotManager.getThirdMemberUUID(t))) {
											PlotManager.Visit2(t, p);
										}else {
											if(pl.getString(t.getUniqueId().toString() + ".tp-request").equals("true")) {
												PlotManager.Visit2(t, p);
											}else {
												p.sendMessage(Main.pr + "§cThe stated player disabled visits!");
											}
										}
									}else {
										p.sendMessage(Main.pr + "§cThe stated player doesn't own a second Plot");
									}
								}else{
									p.sendMessage(Main.pr + "§cThe stated player doesn't own a second Plot");
								}
							}else{
								p.sendMessage(Main.pr + "§cThe stated player doesn't own a Plot");
							}
						}else{
							p.sendMessage(Main.pr + "§cYou can not visit yourself!");
						}
					}else{
						@SuppressWarnings("deprecation")
						OfflinePlayer o = Bukkit.getOfflinePlayer(args[0]);
						
						if(o != p){
							if(PlotManager.existList(o.getUniqueId().toString())){
								if(pl.getString(o.getUniqueId().toString() + ".Name1") != null) {
									if(pl.getString(o.getUniqueId().toString() + ".Name1") != null) {
										if(p.getUniqueId().toString().equals(PlotManager.getFirstOffMemberUUID(o))) {
											PlotManager.Visit2Offline(o, p);
										}else if(p.getUniqueId().toString().equals(PlotManager.getSecondOffMemberUUID(o))){
											PlotManager.Visit2Offline(o, p);
										}else if(p.getUniqueId().toString().equals(PlotManager.getThirdOffMemberUUID(o))){
											PlotManager.Visit2Offline(o, p);
										}else {
											if(pl.getString(o.getUniqueId().toString() + ".tp-request").equals("true")) {
												PlotManager.Visit2Offline(o, p);
											}else {
												p.sendMessage(Main.pr + "§cThe stated player disabled visits!");
											}
										}
									}else {
										p.sendMessage(Main.pr + "§cThe stated player doesn't own a second Plot");
									}
								}else{
									p.sendMessage(Main.pr + "§cThe stated player doesn't own a second Plot");
								}
							}else{
								p.sendMessage(Main.pr + "§cThe stated player doesn't own a Plot");
							}
						}
					}
				}
				if(args[1].equalsIgnoreCase("3")) {
					if(t != null){
						if(t != p){
							if(PlotManager.existList(t.getUniqueId().toString())){
								if(pl.getString(t.getUniqueId().toString() + ".Name2") != null) {
									if(pl.getString(t.getUniqueId().toString() + ".Name2") != null) {
										if(p.getUniqueId().toString().equals(PlotManager.getFirstMemberUUID(t))) {
											PlotManager.Visit3(t, p);
										}else if(p.getUniqueId().toString().equals(PlotManager.getSecondMemberUUID(t))){
											PlotManager.Visit3(t, p);
										}else if(p.getUniqueId().toString().equals(PlotManager.getThirdMemberUUID(t))) {
											PlotManager.Visit3(t, p);
										}else {
											if(pl.getString(t.getUniqueId().toString() + ".tp-request").equals("true")) {
												PlotManager.Visit3(t, p);
											}else {
												p.sendMessage(Main.pr + "§cThe stated player disabled visits!");
											}
										}
									}else {
										p.sendMessage(Main.pr + "§cThe stated player doesn't own a third Plot");
									}
								}else{
									p.sendMessage(Main.pr + "§cThe stated player doesn't own a third Plot");
								}
							}else{
								p.sendMessage(Main.pr + "§cThe stated player doesn't own a Plot");
							}
						}else{
							p.sendMessage(Main.pr + "§cYou can not visit yourself!");
						}
					}else{
						@SuppressWarnings("deprecation")
						OfflinePlayer o = Bukkit.getOfflinePlayer(args[0]);
						
						if(o != p){
							if(PlotManager.existList(o.getUniqueId().toString())){
								if(pl.getString(o.getUniqueId().toString() + ".Name2") != null) {
									if(pl.getString(o.getUniqueId().toString() + ".Name2") != null) {
										if(p.getUniqueId().toString().equals(PlotManager.getFirstOffMemberUUID(o))) {
											PlotManager.Visit3Offline(o, p);
										}else if(p.getUniqueId().toString().equals(PlotManager.getSecondOffMemberUUID(o))){
											PlotManager.Visit3Offline(o, p);
										}else if(p.getUniqueId().toString().equals(PlotManager.getThirdOffMemberUUID(o))){
											PlotManager.Visit3Offline(o, p);
										}else {
											if(pl.getString(o.getUniqueId().toString() + ".tp-request").equals("true")) {
												PlotManager.Visit3Offline(o, p);
											}else {
												p.sendMessage(Main.pr + "§cThe stated player disabled visits!");
											}
										}
									}else {
										p.sendMessage(Main.pr + "§cThe stated player doesn't own a third Plot");
									}
								}else{
									p.sendMessage(Main.pr + "§cThe stated player doesn't own a third Plot");
								}
							}else{
								p.sendMessage(Main.pr + "§cYou can not visit yourself!");
							}
						}
					}
				}
			}
		}
		return false;
	}

	
}
