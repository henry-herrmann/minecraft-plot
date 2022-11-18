package de.blitzmc.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.blitzmc.Main;



public class Inventar {

		//0 1 2s 3 4s 5 6s 7 8
		//9 10 11 12 13 14 15 16 17 
		//18 19 20 21 22 23 24 25 26
		//27 28 29 30 31 32 33 34 35
		//36 37 38 39 40 41 42 43 44
		//45 46 47 48 49 50 51 52 53
		//54 55 56 57 58 59 60 61 62
		
		public static void CreatePlot(Player p, String inventoryname) {
			
			Inventory inv = Bukkit.createInventory(null, 9 * 3, "§8» §6" + inventoryname);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(13, ItemBuilder.getItemWithLore(Material.ITEM_FRAME, "§8» §6Neues Plot", " §7Erstellt ein neues Plot bei deiner Location \n §7Kosten: §6400 Dollar", 0, 1));
			
			p.openInventory(inv);
			
		}
		
		
          public static void MutiplePlots(Player p, String inventoryname) {
        	  
        	File logs1 = new File("plugins/GS", "Player-List.yml");
     		YamlConfiguration pl= YamlConfiguration.loadConfiguration(logs1);
			
			Inventory inv = Bukkit.createInventory(null, 9, "§8» §6" + inventoryname);
			
			for(int i = 0; i < 9; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			if(pl.getString(p.getUniqueId().toString() + ".Name2") != null && pl.getString(p.getUniqueId().toString() + ".Name1") != null && pl.getString(p.getUniqueId().toString() + ".Name") != null) {
				inv.setItem(2, ItemBuilder.getItem(Material.GRASS, "§8» §a1.Plot", 0, 1));
				inv.setItem(4, ItemBuilder.getItem(Material.GRASS, "§8» §a2.Plot", 0, 1));
				inv.setItem(6, ItemBuilder.getItem(Material.GRASS, "§8» §a3.Plot", 0, 1));
			}
			if(pl.getString(p.getUniqueId().toString() + ".Name") == null && pl.getString(p.getUniqueId().toString() + ".Name1") != null && pl.getString(p.getUniqueId().toString() + ".Name2") != null) {
				inv.setItem(3, ItemBuilder.getItem(Material.GRASS, "§8» §a2.Plot", 0, 1));
				inv.setItem(5, ItemBuilder.getItem(Material.GRASS, "§8» §a3.Plot", 0, 1));
			}
			if(pl.getString(p.getUniqueId().toString() + ".Name") != null && pl.getString(p.getUniqueId().toString() + ".Name1") == null && pl.getString(p.getUniqueId().toString() + ".Name2") != null) {
				inv.setItem(3, ItemBuilder.getItem(Material.GRASS, "§8» §a1.Plot", 0, 1));
				inv.setItem(5, ItemBuilder.getItem(Material.GRASS, "§8» §a3.Plot", 0, 1));
			}
			if(pl.getString(p.getUniqueId().toString() + ".Name") != null && pl.getString(p.getUniqueId().toString() + ".Name1") != null && pl.getString(p.getUniqueId().toString() + ".Name2") == null) {
				inv.setItem(3, ItemBuilder.getItem(Material.GRASS, "§8» §a1.Plot", 0, 1));
				inv.setItem(5, ItemBuilder.getItem(Material.GRASS, "§8» §a2.Plot", 0, 1));
			}
			
			p.openInventory(inv);
			
		}
		
		public static void PlotGUI(Player p, String inventoryname) {
			
			
			
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6" + inventoryname);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			//inv.setItem(10, ItemBuilder.getItemWithLore(Material.SPRUCE_DOOR, "§8» §aPlot Teleport", "  §7Betrete dein Plot.", 0, 1));
			//inv.setItem(11, ItemBuilder.getItem(Material.GREEN_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(11, ItemBuilder.getHead(p.getName(), "§8» §aMembers", "§7Zeigt deine Miglieder.", 1));
			inv.setItem(12, ItemBuilder.getItem(Material.GREEN_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(13, ItemBuilder.getItemWithLore(Material.COMPARATOR, "§8» §aEinstellungen", " ", 0, 1));
			inv.setItem(14, ItemBuilder.getItem(Material.GREEN_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(15, ItemBuilder.getItemWithLore(Material.IRON_AXE, "§8» §aVerwaltung", "  §7Verwalte die Mitglieder deines Plots.", 0, 1));
			
			p.openInventory(inv);
			
		}
        public static void Plot2GUI(Player p, String inventoryname) {
			
			
			
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6" + inventoryname);
			
			String member1 = PlotManager.Plot2GetFirstMember(p);
			String member2 = PlotManager.Plot2GetSecondMember(p);
			String member3 = PlotManager.Plot2GetThirdMember(p);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(10, ItemBuilder.getItemWithLore(Material.SPRUCE_DOOR, "§8» §aPlot Teleport", "  §7Betrete dein §ezweites §7Plot.", 0, 1));
			inv.setItem(11, ItemBuilder.getItem(Material.GREEN_STAINED_GLASS_PANE, " ", 0, 1));
			if(member1 == null && member2 == null && member3 == null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§8» §cKeine", 0, 1));
			}
			if(member1 != null && member2 != null && member3 != null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member2 + "\n§6- " + member3, 0, 1));
			}
			if(member1 == null && member2 != null && member3 != null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member2 +"\n§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 == null && member3 != null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 != null && member3 == null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member2, 0, 1));
			}
			if(member1 == null && member2 == null && member3 != null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 == null && member3 == null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member1, 0, 1));
			}
			if(member1 == null && member2 != null && member3 == null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member2, 0, 1));
			}
			if(member1 == null && member2 == null && member3 != null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member3, 0, 1));
			}
			inv.setItem(13, ItemBuilder.getItem(Material.GREEN_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(14, ItemBuilder.getItemWithLore(Material.COMPARATOR, "§8» §aEinstellungen", " ", 0, 1));
			inv.setItem(15, ItemBuilder.getItem(Material.GREEN_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(16, ItemBuilder.getItemWithLore(Material.IRON_AXE, "§8» §aVerwaltung", "  §7Verwalte die Mitglieder deines §ezweiten §7Plots.", 0, 1));
			
			p.openInventory(inv);
			
		}
        
        public static void Plot2GUISettingsDeleteConfirm(Player p){
			Inventory inv = Bukkit.createInventory(null, 9*3, "§cWillst du dein 2. Plot löschen?");
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(12, ItemBuilder.getItem(Material.GREEN_DYE, "§8» §aJa", 0, 1));
			inv.setItem(14, ItemBuilder.getItem(Material.RED_DYE, "§8» §cNein", 0, 1));
			
			p.openInventory(inv);
		}
        
        public static void Plot2GUISettings(Player p, String inventoryname){
			File logs1 = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl= YamlConfiguration.loadConfiguration(logs1);
			
			
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6" + inventoryname);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(10, ItemBuilder.getItem(Material.ARROW, "§8» §cZurück", 0, 1));
			inv.setItem(11, ItemBuilder.getItem(Material.RED_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(12, ItemBuilder.getItemWithLore(Material.ENDER_EYE, "§8» §aPlot-Spawn", "§7Setze deinen Plot-Spawn!", 0, 1));
			inv.setItem(13, ItemBuilder.getItem(Material.RED_STAINED_GLASS_PANE, " ", 0, 1));
			if(pl.getString(p.getUniqueId().toString()+ ".tp-request1").equals("true")){
				inv.setItem(14, ItemBuilder.getItemWithLore(Material.ENDER_CHEST, "§8» §aTeleport Anfragen", "§7Anfragen: §aan", 0, 1));
			}
			if(pl.getString(p.getUniqueId().toString()+ ".tp-request1").equals("false")){
				inv.setItem(14, ItemBuilder.getItemWithLore(Material.ENDER_CHEST, "§8» §aTeleport Anfragen", "§7Anfragen: §caus", 0, 1));
			}
			inv.setItem(15, ItemBuilder.getItem(Material.RED_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(16, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §cLöschen", "§cLösche dein Plot",0, 1));
			
			p.openInventory(inv);
		}
        
        public static void Plot2Administration(Player p){
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6Verwaltung 2");
			
			String member1 = PlotManager.Plot2GetFirstMember(p);
			String member2 = PlotManager.Plot2GetSecondMember(p);
			String member3 = PlotManager.Plot2GetThirdMember(p);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(10, ItemBuilder.getItem(Material.ARROW, "§8» §cZurück", 0, 1));
			inv.setItem(11, ItemBuilder.getItem(Material.YELLOW_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(12, ItemBuilder.getItemWithLore(Material.GREEN_DYE, "§8» §aHinzufügen","§7Füge jemanden zu deinem Plot hinzu!" , 0, 1));
			inv.setItem(13, ItemBuilder.getItem(Material.YELLOW_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(14, ItemBuilder.getItemWithLore(Material.RED_DYE, "§8» §cEntfernen","§7Entferne jemanden von deinem Plot!" , 0, 1));
			inv.setItem(15, ItemBuilder.getItem(Material.YELLOW_STAINED_GLASS_PANE, " ", 0, 1));
			if(member1 == null && member2 == null && member3 == null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§8» §cKeine", 0, 1));
			}
			if(member1 != null && member2 != null && member3 != null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member2 + "\n§6- " + member3, 0, 1));
			}
			if(member1 == null && member2 != null && member3 != null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member2 +"\n§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 == null && member3 != null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 != null && member3 == null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member2, 0, 1));
			}
			if(member1 == null && member2 == null && member3 != null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 == null && member3 == null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member1, 0, 1));
			}
			if(member1 == null && member2 != null && member3 == null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member2, 0, 1));
			}
			if(member1 == null && member2 == null && member3 != null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member3, 0, 1));
			}
			
			
			p.openInventory(inv);
		}
        
        @SuppressWarnings({ "deprecation", "unused" })
		public static void Plot2GuiMember(Player p, String inventoryname){
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
			
			
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6" + inventoryname);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(10, ItemBuilder.getItem(Material.ARROW, "§8» §cZurück", 0, 1));
			inv.setItem(11, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
			
			String member1 = PlotManager.Plot2GetFirstMember(p);
			String member2 = PlotManager.Plot2GetSecondMember(p);
			String member3 = PlotManager.Plot2GetThirdMember(p);
			
			
			if(member1 == null && member2 == null && member3 == null){
				inv.setItem(12, ItemBuilder.getItem(Material.SKELETON_SKULL, "§cKeine Mitglieder", 0, 1));
			}
			if(member1 != null && member2 != null && member3 != null){
				inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
				inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
				inv.setItem(14, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
				inv.setItem(15, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
				inv.setItem(16, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
			}
			if(member1 == null && member2 != null && member3 != null){
				inv.setItem(12, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
				inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
				inv.setItem(14, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
			}
			if(member1 != null && member2 == null && member3 != null){
				inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
				inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
				inv.setItem(14, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
			}
			if(member1 != null && member2 != null && member3 == null){
				inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
				inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
				inv.setItem(14, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
			}
			if(member1 == null && member2 == null && member3 != null){
				inv.setItem(12, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
			}
			if(member1 != null && member2 == null && member3 == null){
				inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
			}
			if(member1 == null && member2 != null && member3 == null){
				inv.setItem(12, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
			}
			if(member1 == null && member2 == null && member3 != null){
				inv.setItem(12, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
			}
			
			p.openInventory(inv);
		}
        
           public static void Plot3GUI(Player p, String inventoryname) {
			
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6" + inventoryname);
			
			String member1 = PlotManager.Plot3GetFirstMember(p);
			String member2 = PlotManager.Plot3GetSecondMember(p);
			String member3 = PlotManager.Plot3GetThirdMember(p);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			inv.setItem(10, ItemBuilder.getItemWithLore(Material.SPRUCE_DOOR, "§8» §aPlot Teleport", "  §7Betrete dein §edrittes §7Plot.", 0, 1));
			inv.setItem(11, ItemBuilder.getItem(Material.GREEN_STAINED_GLASS_PANE, " ", 0, 1));
			if(member1 == null && member2 == null && member3 == null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§8» §cKeine", 0, 1));
			}
			if(member1 != null && member2 != null && member3 != null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member2 + "\n§6- " + member3, 0, 1));
			}
			if(member1 == null && member2 != null && member3 != null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member2 +"\n§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 == null && member3 != null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 != null && member3 == null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member2, 0, 1));
			}
			if(member1 == null && member2 == null && member3 != null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 == null && member3 == null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member1, 0, 1));
			}
			if(member1 == null && member2 != null && member3 == null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member2, 0, 1));
			}
			if(member1 == null && member2 == null && member3 != null){
				inv.setItem(12, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member3, 0, 1));
			}
			inv.setItem(13, ItemBuilder.getItem(Material.GREEN_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(14, ItemBuilder.getItemWithLore(Material.COMPARATOR, "§8» §aEinstellungen", " ", 0, 1));
			inv.setItem(15, ItemBuilder.getItem(Material.GREEN_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(16, ItemBuilder.getItemWithLore(Material.IRON_AXE, "§8» §aVerwaltung", "  §7Verwalte die Mitglieder deines §edritten §7Plots.", 0, 1));
			
			p.openInventory(inv);
			
		}
        
        public static void Plot3GUISettingsDeleteConfirm(Player p){
			Inventory inv = Bukkit.createInventory(null, 9*3, "§cWillst du dein 3. Plot löschen?");
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(12, ItemBuilder.getItem(Material.GREEN_DYE, "§8» §aJa", 10, 1));
			inv.setItem(14, ItemBuilder.getItem(Material.RED_DYE, "§8» §cNein", 8, 1));
			
			p.openInventory(inv);
		}
        
        public static void Plot3GUISettings(Player p, String inventoryname){
			File logs1 = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl= YamlConfiguration.loadConfiguration(logs1);
			
			
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6" + inventoryname);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(10, ItemBuilder.getItem(Material.ARROW, "§8» §cZurück", 0, 1));
			inv.setItem(11, ItemBuilder.getItem(Material.RED_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(12, ItemBuilder.getItemWithLore(Material.ENDER_EYE, "§8» §aPlot-Spawn", "§7Setze deinen Plot-Spawn!", 0, 1));
			inv.setItem(13, ItemBuilder.getItem(Material.RED_STAINED_GLASS_PANE, " ", 0, 1));
			if(pl.getString(p.getUniqueId().toString()+ ".tp-request2").equals("true")){
				inv.setItem(14, ItemBuilder.getItemWithLore(Material.ENDER_CHEST, "§8» §aTeleport Anfragen", "§7Anfragen: §aan", 0, 1));
			}
			if(pl.getString(p.getUniqueId().toString()+ ".tp-request2").equals("false")){
				inv.setItem(14, ItemBuilder.getItemWithLore(Material.ENDER_CHEST, "§8» §aTeleport Anfragen", "§7Anfragen: §caus", 0, 1));
			}
			inv.setItem(15, ItemBuilder.getItem(Material.RED_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(16, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §cLöschen", "§cLösche dein Plot",0, 1));
			
			p.openInventory(inv);
		}
        
        public static void Plot3Administration(Player p){
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6Verwaltung 3");
			
			String member1 = PlotManager.Plot3GetFirstMember(p);
			String member2 = PlotManager.Plot3GetSecondMember(p);
			String member3 = PlotManager.Plot3GetThirdMember(p);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(10, ItemBuilder.getItem(Material.ARROW, "§8» §cZurück", 0, 1));
			inv.setItem(11, ItemBuilder.getItem(Material.YELLOW_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(12, ItemBuilder.getItemWithLore(Material.GREEN_DYE, "§8» §aHinzufügen","§7Füge jemanden zu deinem Plot hinzu!" , 10, 1));
			inv.setItem(13, ItemBuilder.getItem(Material.YELLOW_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(14, ItemBuilder.getItemWithLore(Material.RED_DYE, "§8» §cEntfernen","§7Entferne jemanden von deinem Plot!" , 8, 1));
			inv.setItem(15, ItemBuilder.getItem(Material.YELLOW_STAINED_GLASS_PANE, " ", 0, 1));
			if(member1 == null && member2 == null && member3 == null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§8» §cKeine", 0, 1));
			}
			if(member1 != null && member2 != null && member3 != null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member2 + "\n§6- " + member3, 0, 1));
			}
			if(member1 == null && member2 != null && member3 != null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member2 +"\n§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 == null && member3 != null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 != null && member3 == null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "\n§6- " + member1 +"\n§6- " + member2, 0, 1));
			}
			if(member1 == null && member2 == null && member3 != null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member3, 0, 1));
			}
			if(member1 != null && member2 == null && member3 == null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member1, 0, 1));
			}
			if(member1 == null && member2 != null && member3 == null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member2, 0, 1));
			}
			if(member1 == null && member2 == null && member3 != null){
				inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", "§6- " + member3, 0, 1));
			}
			
			
			p.openInventory(inv);
		}
        
        @SuppressWarnings({ "deprecation", "unused" })
		public static void Plot3GuiMember(Player p, String inventoryname){
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
			
			
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6" + inventoryname);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(10, ItemBuilder.getItem(Material.ARROW, "§8» §cZurück", 0, 1));
			inv.setItem(11, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
			if(PlotManager.existList(p.getUniqueId().toString())){
				
				String member1 = PlotManager.Plot3GetFirstMember(p);
				String member2 = PlotManager.Plot3GetSecondMember(p);
				String member3 = PlotManager.Plot3GetThirdMember(p);
				
				
				if(member1 == null && member2 == null && member3 == null){
					inv.setItem(12, ItemBuilder.getItem(Material.SKELETON_SKULL, "§cKeine Mitglieder", 0, 1));
				}
				if(member1 != null && member2 != null && member3 != null){
					inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
					inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
					inv.setItem(14, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
					inv.setItem(15, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
					inv.setItem(16, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
				}
				if(member1 == null && member2 != null && member3 != null){
					inv.setItem(12, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
					inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
					inv.setItem(14, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
				}
				if(member1 != null && member2 == null && member3 != null){
					inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
					inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
					inv.setItem(14, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
				}
				if(member1 != null && member2 != null && member3 == null){
					inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
					inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
					inv.setItem(14, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
				}
				if(member1 == null && member2 == null && member3 != null){
					inv.setItem(12, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
				}
				if(member1 != null && member2 == null && member3 == null){
					inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
				}
				if(member1 == null && member2 != null && member3 == null){
					inv.setItem(12, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
				}
				if(member1 == null && member2 == null && member3 != null){
					inv.setItem(12, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
				}
			}else{
				if(PlotManager.isMemberofPlot(p) != null){
					String member1 = PlotManager.Plot3GetFirstMember(p);
					String member2 = PlotManager.Plot3GetSecondMember(p);
					String member3 = PlotManager.Plot3GetThirdMember(p);
					
					
					if(member1 == null && member2 == null && member3 == null){
						inv.setItem(12, ItemBuilder.getItem(Material.SKELETON_SKULL, "§cKeine Mitglieder", 0, 1));
					}
					if(member1 != null && member2 != null && member3 != null){
						inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
						inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
						inv.setItem(14, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
						inv.setItem(15, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
						inv.setItem(16, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
					}
					if(member1 == null && member2 != null && member3 != null){
						inv.setItem(12, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
						inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
						inv.setItem(14, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
					}
					if(member1 != null && member2 == null && member3 != null){
						inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
						inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
						inv.setItem(14, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
					}
					if(member1 != null && member2 != null && member3 == null){
						inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
						inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
						inv.setItem(14, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
					}
					if(member1 == null && member2 == null && member3 != null){
						inv.setItem(12, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
					}
					if(member1 != null && member2 == null && member3 == null){
						inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
					}
					if(member1 == null && member2 != null && member3 == null){
						inv.setItem(12, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
					}
					if(member1 == null && member2 == null && member3 != null){
						inv.setItem(12, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
					}
					
				}
			}
			
			p.openInventory(inv);
		}
		
		public static void PlotGUISettingsDeleteConfirm(Player p){
			Inventory inv = Bukkit.createInventory(null, 9*3, "§cWillst du dein Plot löschen?");
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(12, ItemBuilder.getItem(Material.GREEN_DYE, "§8» §aJa", 10, 1));
			inv.setItem(14, ItemBuilder.getItem(Material.RED_DYE, "§8» §cNein", 8, 1));
			
			p.openInventory(inv);
		}
		
		public static void PlotGUISettings(Player p, String inventoryname){
			File logs1 = new File("plugins/GS", "Player-List.yml");
			YamlConfiguration pl= YamlConfiguration.loadConfiguration(logs1);
			
			
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6" + inventoryname);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(11, ItemBuilder.getItem(Material.ARROW, "§8» §cZurück", 0, 1));
			inv.setItem(12, ItemBuilder.getItem(Material.RED_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(13, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §cLöschen", "§cLösche dein Plot",0, 1));
			inv.setItem(14, ItemBuilder.getItem(Material.RED_STAINED_GLASS_PANE, " ", 0, 1));
			//inv.setItem(12, ItemBuilder.getItemWithLore(Material.ENDER_EYE, "§8» §aPlot-Spawn", "§7Setze deinen Plot-Spawn!", 0, 1));
			//inv.setItem(13, ItemBuilder.getItem(Material.RED_STAINED_GLASS_PANE, " ", 0, 1));
			/*if(pl.getString(p.getUniqueId().toString()+ ".tp-request").equals("true")){
				inv.setItem(14, ItemBuilder.getItemWithLore(Material.ENDER_CHEST, "§8» §aTeleport Anfragen", "§7Anfragen: §aan", 0, 1));
			}else{
				inv.setItem(14, ItemBuilder.getItemWithLore(Material.ENDER_CHEST, "§8» §aTeleport Anfragen", "§7Anfragen: §caus", 0, 1));
			}*/
			inv.setItem(15, ItemBuilder.getItem(Material.SHIELD, "§8» §aFlags", 0, 1));
			
			p.openInventory(inv);
		}
		public static void PlotGUIFlags(Player p, String inventoryname){
			HashMap<String, Boolean> flags = PlotManager.getFlags(p);
			
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6" + inventoryname);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			
			}
			
			inv.setItem(0, ItemBuilder.getItemWithLore(Material.DIAMOND_PICKAXE, "§8» §7Blöcke abbauen: " + (flags.get("block-break") == true ? "§aAn" : "§cAus"), "§7Gilt nicht für dich und member deines GS!", 0, 1));
			inv.setItem(1, ItemBuilder.getItemWithLore(Material.GRASS_BLOCK, "§8» §7Blöcke platzieren: " + (flags.get("block-place") == true ? "§aAn" : "§cAus"), "§7Gilt nicht für dich und member deines GS!", 0, 1));
			inv.setItem(2, ItemBuilder.getItemWithLore(Material.DIAMOND_SWORD, "§8» §7PVP: " + (flags.get("pvp") == true ? "§aAn" : "§cAus"), "§7Gilt nicht für dich und member deines GS!", 0, 1));
			inv.setItem(3, ItemBuilder.getItem(Material.TNT, "§8» §7Explosionen: " + (flags.get("tnt") == true ? "§aAn" : "§cAus"), 0, 1));
			inv.setItem(4, ItemBuilder.getItemWithLore(Material.LEVER, "§8» §7Interaktion (Hebel etc.): " + (flags.get("interact") == true ? "§aAn" : "§cAus"), "§7Gilt nicht für dich und member deines GS!", 0, 1));
			inv.setItem(5, ItemBuilder.getItemWithLore(Material.DIAMOND_HORSE_ARMOR, "§8» §7Interaktion mit Tieren (Villager etc.): " + (flags.get("interact-animals") == true ? "§aAn" : "§cAus"), "§7Gilt nicht für dich und member deines GS!", 0, 1));
			inv.setItem(6, ItemBuilder.getItem(Material.FLINT_AND_STEEL, "§8» §7Fire spread: " + (flags.get("fire-spread") == true ? "§aAn" : "§cAus"), 0, 1));
			inv.setItem(7, ItemBuilder.getItem(Material.SKELETON_SKULL, "§8» §7Mob grief: " + (flags.get("mob-grief") == true ? "§aAn" : "§cAus"), 0, 1));
			inv.setItem(8, ItemBuilder.getItem(Material.ZOMBIE_SPAWN_EGG, "§8» §7Mob spawn: " + (flags.get("mob-spawn") == true ? "§aAn" : "§cAus"), 0, 1));
			
			inv.setItem(18, ItemBuilder.getItem(Material.ARROW, "§8» §cZurück", 0, 1));
			
			p.openInventory(inv);
		}
		
		public static void PlotAdministration(Player p){
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6Verwaltung");
			
			
			List<String> members = PlotManager.getMembers(p);
			
			List<String> s = new ArrayList<String>();
			
			for(String uid : members) {
				String name = UUIDFetcher.getName(UUID.fromString(uid));
				
				s.add("\n§6- " + name);
			}
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(10, ItemBuilder.getItem(Material.ARROW, "§8» §cZurück", 0, 1));
			inv.setItem(11, ItemBuilder.getItem(Material.YELLOW_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(12, ItemBuilder.getItemWithLore(Material.GREEN_DYE, "§8» §aHinzufügen","§7Füge jemanden zu deinem Plot hinzu!" , 10, 1));
			inv.setItem(13, ItemBuilder.getItem(Material.YELLOW_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(14, ItemBuilder.getItemWithLore(Material.RED_DYE, "§8» §cEntfernen","§7Entferne jemanden von deinem Plot!" , 8, 1));
			inv.setItem(15, ItemBuilder.getItem(Material.YELLOW_STAINED_GLASS_PANE, " ", 0, 1));
			inv.setItem(16, ItemBuilder.getItem(Material.YELLOW_STAINED_GLASS_PANE, " ", 0, 1));
			//inv.setItem(16, ItemBuilder.getItemWithLore(Material.GOLD_NUGGET, "§8» §aMembers", s.toString(), 0, 1));
			
			
			p.openInventory(inv);
		}
		
		@SuppressWarnings({ "deprecation", "unused" })
		public static void PlotGuiMember(Player p, String inventoryname){
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
			
			
			Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §6" + inventoryname);
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(10, ItemBuilder.getItem(Material.ARROW, "§8» §cZurück", 0, 1));
			inv.setItem(11, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
			if(PlotManager.existList(p.getUniqueId().toString())){
				List<String> members = PlotManager.getMembers(p);
				
				
				if(members.size() == 0) {
					inv.setItem(12, ItemBuilder.getItem(Material.SKELETON_SKULL, "§cKeine Mitglieder", 0, 1));
				}
				
				for(int i=0; i < members.size() && i< 27;i++) {
					String uid = members.get(i);
					String name = UUIDFetcher.getName(UUID.fromString(uid));
					
					inv.setItem(12+i, ItemBuilder.getHead(name, "§8» §6" + name, "", 1));
				}
				
			
				
				/*String member1 = PlotManager.getFirstMember(p);
				String member2 = PlotManager.getSecondMember(p);
				String member3 = PlotManager.getThirdMember(p);
				
				
				if(member1 == null && member2 == null && member3 == null){
					inv.setItem(12, ItemBuilder.getItem(Material.SKELETON_SKULL, "§cKeine Mitglieder", 0, 1));
				}
				if(member1 != null && member2 != null && member3 != null){
					inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
					inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
					inv.setItem(14, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
					inv.setItem(15, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
					inv.setItem(16, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
				}
				if(member1 == null && member2 != null && member3 != null){
					inv.setItem(12, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
					inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
					inv.setItem(14, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
				}
				if(member1 != null && member2 == null && member3 != null){
					inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
					inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
					inv.setItem(14, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
				}
				if(member1 != null && member2 != null && member3 == null){
					inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
					inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
					inv.setItem(14, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
				}
				if(member1 == null && member2 == null && member3 != null){
					inv.setItem(12, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
				}
				if(member1 != null && member2 == null && member3 == null){
					inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
				}
				if(member1 == null && member2 != null && member3 == null){
					inv.setItem(12, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
				}
				if(member1 == null && member2 == null && member3 != null){
					inv.setItem(12, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
				}*/
			}/*else{
				if(PlotManager.isMemberofPlot(p) != null){
					
					String member1 = PlotManager.getFirstMember(p);
					String member2 = PlotManager.getSecondMember(p);
					String member3 = PlotManager.getThirdMember(p);
					
					
					if(member1 == null && member2 == null && member3 == null){
						inv.setItem(12, ItemBuilder.getItem(Material.SKELETON_SKULL, "§cKeine Mitglieder", 0, 1));
					}
					if(member1 != null && member2 != null && member3 != null){
						inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
						inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
						inv.setItem(14, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
						inv.setItem(15, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
						inv.setItem(16, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
					}
					if(member1 == null && member2 != null && member3 != null){
						inv.setItem(12, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
						inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
						inv.setItem(14, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
					}
					if(member1 != null && member2 == null && member3 != null){
						inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
						inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
						inv.setItem(14, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
					}
					if(member1 != null && member2 != null && member3 == null){
						inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
						inv.setItem(13, ItemBuilder.getItem(Material.ORANGE_STAINED_GLASS_PANE, " ", 0, 1));
						inv.setItem(14, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
					}
					if(member1 == null && member2 == null && member3 != null){
						inv.setItem(12, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
					}
					if(member1 != null && member2 == null && member3 == null){
						inv.setItem(12, ItemBuilder.getHead(member1, "§8» §6" + member1, "", 1));
					}
					if(member1 == null && member2 != null && member3 == null){
						inv.setItem(12, ItemBuilder.getHead(member2, "§8» §6" + member2, "", 1));
					}
					if(member1 == null && member2 == null && member3 != null){
						inv.setItem(12, ItemBuilder.getHead(member3, "§8» §6" + member3, "", 1));
					}
					
				}
			}*/
			
			p.openInventory(inv);
		}
		
		public static void WarpGui(Player p) {
			
			Inventory inv = Bukkit.createInventory(null, 9, "§8» §6Warps");
			
			for(int i = 0; i < 9; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(0, ItemBuilder.getItemWithLore(Material.ENDER_EYE, "§8» §aSpawn", "§8§m-----------------\n §7Teleportiere dich zum Spawnpunkt.", 0, 1));
			inv.setItem(2, ItemBuilder.getItemWithLore(Material.GRASS, "§8» §aFarmwelt", "§8§m-----------------\n §7Teleportiere dich zur Farmwelt.", 0, 1));
			inv.setItem(4, ItemBuilder.getItemWithLore(Material.ANVIL, "§8» §aMarkplatz", "§8§m-----------------\n §7Teleportiere dich zum Marktplatz.", 0, 1));
			inv.setItem(6, ItemBuilder.getItemWithLore(Material.NETHER_BRICK, "§8» §cNether", "§8§m-----------------\n §7Teleportiere dich zum §cNether§7..", 0, 1));
			inv.setItem(8, ItemBuilder.getItemWithLore(Material.END_STONE, "§8» §cEnd", "§8§m-----------------\n §7Teleportiere dich zum §cEnd§7..", 0, 1));
			
			p.openInventory(inv);
			
		}
		
		public static void Home1Gui(Player p) {
			Inventory inv = Bukkit.createInventory(null, 9, "§8» §6Home 1.");
			
			for(int i = 0; i < 9; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(3, ItemBuilder.getItem(Material.ENDER_PEARL, "§8» §aSpringe zu deinem 1. Home", 0, 1));
			inv.setItem(5, ItemBuilder.getItem(Material.BARRIER, "§8» §cLösche dein 1. Home", 0, 1));
			p.openInventory(inv);
		}
		
		public static void Home2Gui(Player p) {
			Inventory inv = Bukkit.createInventory(null, 9, "§8» §6Home 2.");
			
			for(int i = 0; i < 9; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(3, ItemBuilder.getItem(Material.ENDER_PEARL, "§8» §aSpringe zu deinem 2. Home", 0, 1));
			inv.setItem(5, ItemBuilder.getItem(Material.BARRIER, "§8» §cLösche dein 2. Home", 0, 1));
			
			p.openInventory(inv);
		}
		
		public static void Home3Gui(Player p) {
			Inventory inv = Bukkit.createInventory(null, 9, "§8» §6Home 3.");
			
			for(int i = 0; i < 9; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(3, ItemBuilder.getItem(Material.ENDER_PEARL, "§8» §aSpringe zu deinem 3. Home", 0, 1));
			inv.setItem(5, ItemBuilder.getItem(Material.BARRIER, "§8» §cLösche dein 3. Home", 0, 1));
			p.openInventory(inv);
		}
		
		public static void Home4Gui(Player p) {
			Inventory inv = Bukkit.createInventory(null, 9, "§8» §6Home 4.");
			
			for(int i = 0; i < 9; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(3, ItemBuilder.getItem(Material.ENDER_PEARL, "§8» §aSpringe zu deinem 4. Home", 0, 1));
			inv.setItem(5, ItemBuilder.getItem(Material.BARRIER, "§8» §cLösche dein 4. Home", 0, 1));
			p.openInventory(inv);
		}
		
		public static void Home5Gui(Player p) {
			Inventory inv = Bukkit.createInventory(null, 9, "§8» §6Home 5.");
			
			for(int i = 0; i < 9; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			inv.setItem(3, ItemBuilder.getItem(Material.ENDER_PEARL, "§8» §aSpringe zu deinem 5. Home", 0, 1));
			inv.setItem(5, ItemBuilder.getItem(Material.BARRIER, "§8» §cLösche dein 5. Home", 0, 1));
			p.openInventory(inv);
		}
		
		public static void HomeGui(Player p){
			Inventory inv = Bukkit.createInventory(null, 9 * 3, "§8» §6Homes");
			
			for(int i = 0; i < 27; i++){
				inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
			}
			
			//9,11,13,15,17
			
			if(!Home.exist(p)){
				if(!p.hasPermission("system.plot.home.5")) {
					inv.setItem(11, ItemBuilder.getItemWithLore(Material.RED_BED, "§8» §aErstelle dein 1. Home", "§7Du kannst max. 3 Homes erstellen!", 0, 1));
					inv.setItem(13, ItemBuilder.getItemWithLore(Material.ORANGE_BED, "§8» §aErstelle dein 2. Home", "§7Du kannst max. 3 Homes erstellen!", 0, 2));
					inv.setItem(15, ItemBuilder.getItemWithLore(Material.YELLOW_BED, "§8» §aErstelle dein 3. Home", "§7Du kannst max. 3 Homes erstellen!", 0, 3));
				}else {
					inv.setItem(9, ItemBuilder.getItemWithLore(Material.RED_BED, "§8» §aErstelle dein 1. Home", "§7Du kannst max. 5 Homes erstellen!", 0, 1));
					inv.setItem(11, ItemBuilder.getItemWithLore(Material.ORANGE_BED, "§8» §aErstelle dein 2. Home", "§7Du kannst max. 5 Homes erstellen!", 0, 2));
					inv.setItem(13, ItemBuilder.getItemWithLore(Material.YELLOW_BED, "§8» §aErstelle dein 3. Home", "§7Du kannst max. 5 Homes erstellen!", 0, 3));
					inv.setItem(15, ItemBuilder.getItemWithLore(Material.GREEN_BED, "§8» §aErstelle dein 4. Home", "§7Du kannst max. 5 Homes erstellen!", 0, 4));
					inv.setItem(17, ItemBuilder.getItemWithLore(Material.BLUE_BED, "§8» §aErstelle dein 5. Home", "§7Du kannst max. 3 Homes erstellen!", 0, 5));
				}
			}
			if(Home.exist(p)){
				if(!p.hasPermission("system.plot.home.5")) {
					if(Home.exist1(p)) {
						inv.setItem(11, ItemBuilder.getItemWithLore(Material.RED_BED, "§8» §a1. Home", "§7Einstellungen", 0, 1));
					}else {
						inv.setItem(11, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §aErstelle dein 1. Home", "§7Du kannst max. 3 Homes erstellen!", 0, 1));
					}
					
					if(Home.exist2(p)) {
						inv.setItem(13, ItemBuilder.getItemWithLore(Material.ORANGE_BED, "§8» §a2. Home", "§7Einstellungen", 0, 2));
					}else {
						inv.setItem(13, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §aErstelle dein 2. Home", "§7Du kannst max. 3 Homes erstellen!", 0, 2));
					}
					
					if(Home.exist3(p)) {
						inv.setItem(15, ItemBuilder.getItemWithLore(Material.YELLOW_BED, "§8» §a3. Home", "§7Einstellungen", 0, 3));
					}else {
						inv.setItem(15, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §aErstelle dein 3. Home", "§7Du kannst max. 3 Homes erstellen!", 0, 3));
					}
				}else {
					if(Home.exist1(p)) {
						inv.setItem(9, ItemBuilder.getItemWithLore(Material.RED_BED, "§8» §a1. Home", "§7Einstellungen", 0, 1));
					}else {
						inv.setItem(9, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §aErstelle dein 1. Home", "§7Du kannst max. 5 Homes erstellen!", 0, 1));
					}
					
					if(Home.exist2(p)) {
						inv.setItem(11, ItemBuilder.getItemWithLore(Material.ORANGE_BED, "§8» §a2. Home", "§7Einstellungen", 0, 2));
					}else {
						inv.setItem(11, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §aErstelle dein 2. Home", "§7Du kannst max. 5 Homes erstellen!", 0,2));
					}
					
					if(Home.exist3(p)) {
						inv.setItem(13, ItemBuilder.getItemWithLore(Material.YELLOW_BED, "§8» §a3. Home", "§7Einstellungen", 0, 3));
					}else {
						inv.setItem(13, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §aErstelle dein 3. Home", "§7Du kannst max. 5 Homes erstellen!", 0, 3));
					}
					
					if(Home.exist4(p)) {
						inv.setItem(15, ItemBuilder.getItemWithLore(Material.GREEN_BED, "§8» §a4. Home", "§7Einstellungen", 0, 4));
					}else {
						inv.setItem(15, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §aErstelle dein 4. Home", "§7Du kannst max. 5 Homes erstellen!", 0, 4));
					}
					
					if(Home.exist5(p)) {
						inv.setItem(17, ItemBuilder.getItemWithLore(Material.BLUE_BED, "§8» §a5. Home", "§7Einstellungen", 0, 5));
					}else {
						inv.setItem(17, ItemBuilder.getItemWithLore(Material.BARRIER, "§8» §aErstelle dein 5. Home", "§7Du kannst max. 5 Homes erstellen!", 0, 5));
					}
					
					
				}
			}
			
			p.openInventory(inv);
		}
		
		public static void JobsGui(Player p, String inventoryname) {
			
			Inventory inv = Bukkit.createInventory(null, 9 * 5, "§8» §6" + inventoryname);
			
            inv.setItem(10, ItemBuilder.getItemWithLore(Material.STONE_PICKAXE, "§8» §aBergarbeiter", "  §7Baue Gestein ab. \n\n§aNur in der Farmwelt gültig\n\n", 0, 1));
			
			if(Main.Bergarbeiter.contains(p)) {
				inv.setItem(19, ItemBuilder.getItem(Material.GREEN_DYE, "§8» §aAktiver Job", 0, 1));
			}
			
			inv.setItem(12, ItemBuilder.getItemWithLore(Material.IRON_AXE, "§8» §aHolzfäller", "  §7Baue Baumstämme ab.\n\n  §aNur in der Farmwelt gültig!", 0, 1));
			
			if(Main.Holzfäller.contains(p)) {
				inv.setItem(21, ItemBuilder.getItem(Material.GREEN_DYE, "§8» §aAktiver Job", 0, 1));
			}
			
			inv.setItem(14, ItemBuilder.getItemWithLore(Material.IRON_SWORD, "§8» §aJäger", " \n §7Töte Tiere in der Farmwelt.", 0, 1));
			
			if(Main.Jäger.contains(p)) {
				inv.setItem(23, ItemBuilder.getItem(Material.GREEN_DYE, "§8» §aAktiver Job", 0, 1));
			}
			
			inv.setItem(16, ItemBuilder.getItemWithLore(Material.CHAINMAIL_CHESTPLATE, "§8» §aAuftragskiller", "  §7Erhalte 45 Dollar für Kills von Spielern.", 0, 1));
			
			if(Main.Auftragskiller.contains(p)) {
				inv.setItem(25, ItemBuilder.getItem(Material.GREEN_DYE, "§8» §aAktiver Job", 0, 1));
			}
			
			inv.setItem(36, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, "", 0, 1));
			inv.setItem(37, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, "", 0, 1));
			inv.setItem(38, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, "", 0, 1));
			inv.setItem(39, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, "", 0, 1));
			inv.setItem(40, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, "", 0, 1));
			inv.setItem(41, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, "", 0, 1));
			inv.setItem(42, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, "", 0, 1));
			
			inv.setItem(44, ItemBuilder.getItem(Material.ARROW, "§8» §6Schließen", 0, 1));

			
			p.openInventory(inv);
			
		}
		
		 @SuppressWarnings("deprecation")
		public static void materialienShop(Player p)
		  {
		    Inventory inv = Bukkit.createInventory(null, 45, "§8 §6Markt");
		    for (int i = 0; i < 45; i++) {
		      inv.setItem(i, ItemBuilder.getItem(Material.GRAY_STAINED_GLASS_PANE, " ", 0, 1));
		    }
		    inv.setItem(4, ItemBuilder.getItem(Material.GOLD_NUGGET, "§6Kontostand: §a" + Currency.getDollar(p), 0, 1));
		    inv.setItem(10, ItemBuilder.getItemWithLore(Material.GRASS, "»§8 §66Gras", "»8§m-----------------\n  §7Preis: §a40 Dollar\n  §7Stück: §c64", 1, 1));
		    inv.setItem(11, ItemBuilder.getItemWithLore(Material.COBBLESTONE, "§8 §6Cobblestone", "§8§m-----------------\n  §7Preis: §a20 Dollar\n  §7Stück: §c64", 1, 1));
		    inv.setItem(12, ItemBuilder.getItemWithLore(Material.DIRT, "§8 §6Potsol", "§8§m-----------------\n  §7Preis: §a5 Dollar\n  §7Stück: §c4", 2, 1));
		    inv.setItem(13, ItemBuilder.getItemWithLore(Material.NETHERRACK, "§8 §6Netherrack", "§8§m-----------------\n  §7Preis: §a25 Dollar\n  §7Stück: §c8", 0, 1));
		    inv.setItem(14, ItemBuilder.getItemWithLore(Material.SEA_LANTERN, "§8 §6Seelaterne", "§8§m-----------------\n  §7Preis: §a30 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(15, ItemBuilder.getItemWithLore(Material.END_STONE, "§8 §6Endstone", "§8§m-----------------\n  §7Preis: §a20 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(16, ItemBuilder.getItemWithLore(Material.OBSIDIAN, "§8 §6Obsidian", "§8§m-----------------\n  §7Preis: §a80 Dollar\n  §7Stück: §c8", 0, 1));
		    inv.setItem(19, ItemBuilder.getItemWithLore(Material.TNT, "§8 §6TNT", "§8§m-----------------\n  §7Preis: §a40 Dollar\n  §7Stück: §c8", 0, 1));
		    inv.setItem(20, ItemBuilder.getItemWithLore(Material.BEACON, "§8 §6Beacon", "§8§m-----------------\n  §7Preis: §a3800 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(21, ItemBuilder.getItemWithLore(Material.QUARTZ, "§8 §6Quartz", "§8§m-----------------\n  §7Preis: §a10 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(22, ItemBuilder.getItemWithLore(Material.PRISMARINE_CRYSTALS, "§8 §6Prismarien", "§8§m-----------------\n  §7Preis: §a15 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(23, ItemBuilder.getItemWithLore(Material.PRISMARINE_SHARD, "§8 §6Prismarienscherbe", "§8§m-----------------\n  §7Preis: §a15 Dollar\n  §7Stück: §c4", 0, 1));
		    inv.setItem(24, ItemBuilder.getItemWithLore(Material.LEGACY_NETHER_BRICK_ITEM, "§8 §6Netherbrick", "§8§m-----------------\n  §7Preis: §a10 Dollar\n  §7Stück: §c4", 0, 1));
		    inv.setItem(25, ItemBuilder.getItemWithLore(Material.BLAZE_ROD, "§8 §6Blazerod", "§8§m-----------------\n  §7Preis: §a100 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(28, ItemBuilder.getItemWithLore(Material.GLOWSTONE_DUST, "§8 §6Glowstone", "§8§m-----------------\n  §7Preis: §a15 Dollar\n  §7Stück: §c4", 0, 1));
		    inv.setItem(29, ItemBuilder.getItemWithLore(Material.GHAST_TEAR, "§8 §6Geistertr§ne", "§8§m-----------------\n  §7Preis: §a25 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(30, ItemBuilder.getItemWithLore(Material.NETHER_WART, "§8 §6Netherwartzen", "§8§m-----------------\n  §7Preis: §a25 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(31, ItemBuilder.getItemWithLore(Material.FLINT_AND_STEEL, "§8 §6Feuerzeug", "§8§m-----------------\n  §7Preis: §a25 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(32, ItemBuilder.getItemWithLore(Material.LAVA_BUCKET, "§8 §6Lavaeimer", "§8§m-----------------\n  §7Preis: §a20 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(33, ItemBuilder.getItemWithLore(Material.WATER_BUCKET, "§8 §6Wassereimer", "§8§m-----------------\n  §7Preis: §a20 Dollar\n  §7Stück: §c1", 0, 1));
		    inv.setItem(34, ItemBuilder.getItemWithLore(Material.BREAD, "§8 §6Brot", "§8§m-----------------\n  §7Preis: §a10 Dollar\n  §7Stück: §c16", 0, 1));
		    
		    p.openInventory(inv);
		  }
		
}
