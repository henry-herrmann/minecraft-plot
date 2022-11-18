package de.blitzmc.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.blitzmc.Main;
import de.blitzmc.utils.AnvilGUI;
import de.blitzmc.utils.Home;
import de.blitzmc.utils.Inventar;
import de.blitzmc.utils.ItemBuilder;
import de.blitzmc.utils.MessageManager;
import de.blitzmc.utils.PlotManager;
import de.blitzmc.utils.RegionManager;
import de.blitzmc.utils.ShopManager;
import de.blitzmc.utils.AnvilGUI.AnvilClickEvent;
import de.blitzmc.utils.AnvilGUI.AnvilClickEventHandler;
import de.blitzmc.utils.AnvilGUI.AnvilSlot;
import de.blitzmc.utils.Currency;


public class InventoryClick implements Listener{
	
	public static PlotManager pm;

	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		
		 File logs1 = new File("plugins/GS", "Player-List.yml");
		 YamlConfiguration pl= YamlConfiguration.loadConfiguration(logs1);
		
		if(e.getView().getTitle().equalsIgnoreCase("§8» §6Erstelle dein Plot")){
			e.setCancelled(true);
			try {
				if(e.getCurrentItem()!= null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §6Neues Plot")){
                  if(Currency.getDollar(p) >= 400) {
                	    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
   					
   					    AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
   					    	
   					    	
   						
   						@Override
   						public void onAnvilClick(AnvilClickEvent event) {
   							if(event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT){
   								event.setWillClose(true);
   								event.setWillDestroy(true);
   								
   								if(p.getWorld().getName().equals("world")){
   									if(PlotManager.creatable(p)){
								    		if(PlotManager.getPlot() != event.getName()) {
								    			if(RegionManager.creatable(p) && RegionManager.isCreated(p.getLocation().getX(), p.getLocation().getZ(), p.getWorld().getName()) == null){
									    			if(p.getUniqueId().toString().equals(pl.getString(p.getUniqueId().toString() + ".uuid"))){
											    		p.sendMessage(Main.pr + "§cDu hast bereits ein Plot!");
													}else{
														if(PlotManager.exist(event.getName())){
															p.sendMessage(Main.pr + "§cDieser Name wurde bereits vergeben!");
														}else{
															PlotManager.setPlayerList(p, event.getName());
															Currency.removeDollar(p, 400);
															PlotManager.setPlot(p, event.getName(), 18);
															p.sendMessage(Main.pr + "§aDu hast das Plot §6" + event.getName() + "§a erstellt!");
														}
													}
									    		}else{
									    			p.sendMessage(Main.pr + "§cDas Plot darf sich nicht in einer Region befinden!");
									    		}
								    		}
									    }else{
									    	p.sendMessage(Main.pr + "§cDas Plot darf sich nicht auf einem anderen Plot befinden!");
									    }
   								}else{
   									p.sendMessage(Main.pr + "§cDu darfst keine Plots in dieser Welt erstellen!");
   								}
   							}else{
   								event.setWillClose(false);
   								event.setWillDestroy(false);
   							}
   						}
   					});
                   }else {
                	   
                	   p.sendMessage(Main.pr + "§cDu brauchst mindestens §e400 §cDollar!");
                   }
				}
			}catch(NullPointerException e3) {
				
			}
		}
		
		
		if(e.getView().getTitle().equalsIgnoreCase("§8» §6Plot")){
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null){
					if(e.getCurrentItem() != null) {
						if(e.getCurrentItem().getItemMeta() != null) {
							try {
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aVerwaltung")) {
									Inventar.PlotAdministration(p);
							    }
								
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aPlot Teleport")){
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.0F, 1.0F);
									
									p.closeInventory();
									PlotManager.Teleport(p);
								}
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aMembers")){
									Inventar.PlotGuiMember(p, "Members");
								}
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aEinstellungen")){
									Inventar.PlotGUISettings(p, "Einstellungen");
								}
							}catch (NullPointerException e1) {
								
							}
						}
					}
					
				}
			}catch(NullPointerException e8) {
				
			}
		}
		if(e.getView().getTitle().equalsIgnoreCase("§8» §6Plot 2")){
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null){
					if(e.getCurrentItem() != null) {
						if(e.getCurrentItem().getItemMeta() != null) {
							try {
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aVerwaltung")) {
									Inventar.Plot2Administration(p);
							    }
								
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aPlot Teleport")){
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.0F, 1.0F);
									
									p.closeInventory();
									PlotManager.Teleport2(p);
								}
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aMembers")){
									
								}
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aEinstellungen")){
									Inventar.Plot2GUISettings(p, "Einstellungen 2");
								}
							}catch (NullPointerException e1) {
								
							}
						}
					}
					
				}
			}catch(NullPointerException e8) {
				
			}
		}
		
		if (e.getView().getTitle().equalsIgnoreCase("§8» §6Members 2")){
		      e.setCancelled(true);
		      
	    }
		if(e.getView().getTitle().equalsIgnoreCase("§8» §6Einstellungen 2")){
            e.setCancelled(true);
            
            try {

                File logs = new File("plugins/GS", "Plots.yml");
    			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
    			
    			if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)){
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aPlot-Spawn")){
    					double x = p.getLocation().getBlockX();
    					double z = p.getLocation().getBlockZ();
    					
    					
    					
                       String name = p.getUniqueId().toString();
    					
                       if(PlotManager.isCreated(x, z) != null) {
                       	if(p.getUniqueId().toString().equals(pl.getString(name + ".uuid"))) {
                       		p.closeInventory();
								PlotManager.setPlotHome2(p);
								p.sendMessage(Main.pr + "Du hast den Spawn Punkt deines Plots geändert!");
                       	}
                       }else {
                       	p.sendMessage(Main.pr + "§cDu musst dich auf deinem Plot befinden!");
							p.closeInventory();
                       }
    					
    				}
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aTeleport Anfragen")){
    					
    					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.0F, 1.0F);
    					
    					if(p.getUniqueId().toString().equals(pl.getString(p.getUniqueId().toString() + ".uuid"))){
    						if(PlotManager.isTpRequest2(p).equals("true")){
    				        	pl.set(p.getUniqueId().toString() + ".tp-request1", "false");
    				        	PlotManager.savePlayerList();
    				        	p.sendMessage(Main.pr + "Du hast Teleport Anfragen §cdeaktiviert§a!");
    				        	Inventar.Plot2GUISettings(p, "Einstellungen 2");
    				        }else{
    				        	pl.set(p.getUniqueId().toString() + ".tp-request1", "true");
    				        	PlotManager.savePlayerList();
    				        	p.sendMessage(Main.pr + "Du hast Teleport Anfragen §aaktiviert!");
    				        	Inventar.Plot2GUISettings(p, "Einstellungen 2");
    				        }
    						PlotManager.save(logs1, pl);
    					}else{
    						p.closeInventory();
    						p.sendMessage(Main.pr + "§cDu kannst die Einstellungen eines Plots nicht als Mitglied verändern!");
    					}
    				}
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cZurück")){
    					Inventar.Plot2GUI(p, "Plot 2");
    				}
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cLöschen")){
    					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
    					
    					Inventar.Plot2GUISettingsDeleteConfirm(p);
    				}
    			}
            }catch(NullPointerException e10) {
            	
            }
		}
		
		if(e.getView().getTitle().equals("§cWillst du dein 2. Plot löschen?")){
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null){
					if(e.getCurrentItem().getItemMeta() != null){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aJa")){
							
							p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK,1, 1);
							p.closeInventory();
							if(pl.getString(p.getUniqueId().toString() + ".Name") == null && pl.getString(p.getUniqueId().toString() + ".Name1") != null && pl.getString(p.getUniqueId().toString() + ".Name2") == null) {
								PlotManager.delPlot2(p);
								PlotManager.delPlayerList(p);
							}else {
								PlotManager.delPlot2(p);
							}
							p.sendMessage(Main.pr + "Dein Grundstück wurde erfolgreich gelöscht!");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cNein")){
							Inventar.PlotGUISettings(p, "Einstellungen 2");
						}
					}
				}
			}catch(NullPointerException e11) {
				
			}
		}
		
		if(e.getView().getTitle().equals("§8» §6Verwaltung 2")){
			e.setCancelled(true);
			
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
			
			
			try {

				if(e.getCurrentItem() != null){
					if(e.getCurrentItem().getItemMeta() != null){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cZurück")){
							Inventar.Plot2GUI(p, "Plot 2");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aHinzufügen")){
							Main.member2.add(p);
							p.closeInventory();
							p.sendMessage(Main.pr + "Bitte tippe den Namen des Spielers ein!");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cEntfernen")){
							Main.delmember2.add(p);
							p.closeInventory();
							p.sendMessage(Main.pr + "Bitte tippe den Namen des Spielers ein!");
						}
					}
				}
			}catch(NullPointerException e12) {
				
			}
		}
		
		if(e.getView().getTitle().equals("§8» §6Plot 3")){
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null){
					if(e.getCurrentItem() != null) {
						if(e.getCurrentItem().getItemMeta() != null) {
							try {
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aVerwaltung")) {
									Inventar.Plot3Administration(p);
							    }
								
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aPlot Teleport")){
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.0F, 1.0F);
									
									p.closeInventory();
									PlotManager.Teleport3(p);
								}
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aMembers")){
								}
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aEinstellungen")){
									Inventar.Plot3GUISettings(p, "Einstellungen 3");
								}
							}catch (NullPointerException e1) {
								
							}
						}
					}
					
				}
			}catch(NullPointerException e8) {
				
			}
		}
		
		if (e.getView().getTitle().equals("§8» §6Members 3")){
		      e.setCancelled(true);
		      
	    }
		if(e.getView().getTitle().equals("§8» §6Einstellungen 3")){
            e.setCancelled(true);
            
            try {

                File logs = new File("plugins/GS", "Plots.yml");
    			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
    			
    			if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)){
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aPlot-Spawn")){
    					double x = p.getLocation().getBlockX();
    					double z = p.getLocation().getBlockZ();
    					String name = p.getUniqueId().toString();
    					
    					if(PlotManager.isCreated(x, z) != null) {
                        	if(p.getUniqueId().toString().equals(pl.getString(name + ".uuid"))) {
                        		p.closeInventory();
								PlotManager.setPlotHome3(p);
								p.sendMessage(Main.pr + "Du hast den Spawn Punkt deines Plots geändert!");
                        	}
                        }else {
                        	p.sendMessage(Main.pr + "§cDu musst dich auf deinem Plot befinden!");
							p.closeInventory();
                        }
    				}
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aTeleport Anfragen")){
    					
    					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.0F, 1.0F);
    					
    					if(p.getUniqueId().toString().equals(pl.getString(p.getUniqueId().toString() + ".uuid"))){
    						if(PlotManager.isTpRequest3(p).equals("true")){
    				        	pl.set(p.getUniqueId().toString() + ".tp-request2", "false");
    				        	PlotManager.savePlayerList();
    				        	p.sendMessage(Main.pr + "Du hast Teleport Anfragen §cdeaktiviert§a!");
    				        	Inventar.Plot3GUISettings(p, "Einstellungen 3");
    				        }else{
    				        	pl.set(p.getUniqueId().toString() + ".tp-request2", "true");
    				        	PlotManager.savePlayerList();
    				        	p.sendMessage(Main.pr + "Du hast Teleport Anfragen §aaktiviert!");
    				        	Inventar.Plot3GUISettings(p, "Einstellungen 3");
    				        }
    						PlotManager.save(logs1, pl);
    					}else{
    						p.closeInventory();
    						p.sendMessage(Main.pr + "§cDu kannst die Einstellungen eines Plots nicht als Mitglied verändern!");
    					}
    				}
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cZurück")){
    					Inventar.Plot2GUI(p, "Plot 3");
    				}
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cLöschen")){
    					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
    					
    					Inventar.Plot3GUISettingsDeleteConfirm(p);
    				}
    			}
            }catch(NullPointerException e10) {
            	
            }
		}
		
		if(e.getView().getTitle().equals("§cWillst du dein 3. Plot löschen?")){
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null){
					if(e.getCurrentItem().getItemMeta() != null){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aJa")){
							
							p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK,1, 1);
							p.closeInventory();
							if(pl.getString(p.getUniqueId().toString() + ".Name") == null && pl.getString(p.getUniqueId().toString() + ".Name1") == null && pl.getString(p.getUniqueId().toString() + ".Name2") != null) {
								PlotManager.delPlot3(p);
								PlotManager.delPlayerList(p);
							}else {
								PlotManager.delPlot3(p);
							}
							p.sendMessage(Main.pr + "Dein Grundstück wurde erfolgreich gelöscht!");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cNein")){
							Inventar.PlotGUISettings(p, "Einstellungen 3");
						}
					}
				}
			}catch(NullPointerException e11) {
				
			}
		}
		
		if(e.getView().getTitle().equals("§8» §6Verwaltung 3")){
			e.setCancelled(true);
			
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
			
			
			try {

				if(e.getCurrentItem() != null){
					if(e.getCurrentItem().getItemMeta() != null){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cZurück")){
							Inventar.Plot2GUI(p, "Plot 3");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aHinzufügen")){
							Main.member3.add(p);
							p.closeInventory();
							p.sendMessage(Main.pr + "Bitte tippe den Namen des Spielers ein!");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cEntfernen")){
							Main.delmember3.add(p);
							p.closeInventory();
							p.sendMessage(Main.pr + "Bitte tippe den Namen des Spielers ein!");
						}
					}
				}
			}catch(NullPointerException e12) {
				
			}
		}
		
		if(e.getView().getTitle().equals("§8» §6Multiple Plots")) {
			e.setCancelled(true);
			try {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §a1.Plot")) {
					Inventar.PlotGUI(p, "Plot");
				}
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §a2.Plot")) {
					Inventar.Plot2GUI(p, "Plot 2");
				}
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §a3.Plot")) {
					Inventar.Plot3GUI(p, "Plot 3");
				}
			}catch(NullPointerException e1) {
				
			}
		}
		
		if (e.getView().getTitle().equals("§8» §6Members")){
	      e.setCancelled(true);
	      try {
	    	  if ((e.getCurrentItem() != null) && 
	    		        (e.getCurrentItem().getItemMeta() != null) && 
	    		        (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cZurück"))) {
	    		        Inventar.PlotGUI(p, "Plot");
	    		      } 
	      }catch(NullPointerException e9) {
	    	  
	      }
	    }
		
		
		
		if(e.getView().getTitle().equals("§8» §6Jobcenter")) {
			e.setCancelled(true);
			try {
				if(e.getCurrentItem()  != null) {
					if(e.getCurrentItem().getItemMeta() != null) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aBergarbeiter")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);

							if(!Main.Bergarbeiter.contains(p)) {
								Main.Bergarbeiter.add(p);
								
								p.sendMessage(Main.pr + "§aDu bist nun Bergarbeiter, gehe in die Farmwelt und farme Stein und Erze um Geld zu erlangen.");
								Inventar.JobsGui(p, "Jobcenter");
							}
							if(Main.Jäger.contains(p)) {
								Main.Jäger.remove(p);
							}
							if(Main.Holzfäller.contains(p)) {
								Main.Holzfäller.remove(p);
							}
							if(Main.Auftragskiller.contains(p)) {
								Main.Auftragskiller.remove(p);
							}
							
							Inventar.JobsGui(p, "Jobcenter");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aHolzfäller")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);

							if(!Main.Holzfäller.contains(p)) {
								Main.Holzfäller.add(p);
								
								p.sendMessage(Main.pr + "§aDu bist nun Holzfäller, gehe in die Farmwelt und farme Holzstämme um Geld zu erlangen.");
								Inventar.JobsGui(p, "Jobcenter");
							}
							if(Main.Bergarbeiter.contains(p)) {
								Main.Bergarbeiter.remove(p);
							}
							if(Main.Jäger.contains(p)) {
								Main.Jäger.remove(p);
							}
							if(Main.Auftragskiller.contains(p)) {
								Main.Auftragskiller.remove(p);
							}
							
							Inventar.JobsGui(p, "Jobcenter");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aJäger")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);

							if(!Main.Jäger.contains(p)) {
								Main.Jäger.add(p);
								
								p.sendMessage(Main.pr + "§aDu bist nun Jäger, gehe in die Farmwelt und töte Tiere um Geld zu erlangen.");
								Inventar.JobsGui(p, "Jobcenter");
							}
							if(Main.Bergarbeiter.contains(p)) {
								Main.Bergarbeiter.remove(p);
							}
							if(Main.Holzfäller.contains(p)) {
								Main.Holzfäller.remove(p);
							}
							if(Main.Auftragskiller.contains(p)) {
								Main.Auftragskiller.remove(p);
							}
							
							Inventar.JobsGui(p, "Jobcenter");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aAuftragskiller")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);

							if(!Main.Auftragskiller.contains(p)) {
								Main.Auftragskiller.add(p);
								
								p.sendMessage(Main.pr + "§aDu bist nun Auftragskiller, Töte Spieler um Geld um dein Geld ausgezahllt zu bekommen.");
								Inventar.JobsGui(p, "Jobcenter");
							}
							if(Main.Bergarbeiter.contains(p)) {
								Main.Bergarbeiter.remove(p);
							}
							if(Main.Jäger.contains(p)) {
								Main.Jäger.remove(p);
							}
							if(Main.Holzfäller.contains(p)) {
								Main.Holzfäller.remove(p);
							}
							
							Inventar.JobsGui(p, "Jobcenter");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §6Schließen")) {
							
							p.closeInventory();
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Nachrichten §aan")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);

							MessageManager.setMessage(p, "true");
							Inventar.JobsGui(p, "Jobcenter");
							
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Nachrichten §caus")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);

							MessageManager.setMessage(p, "false");
							Inventar.JobsGui(p, "Jobcenter");
							
						}
					}
				}
			}catch(NullPointerException e9) {
				
			}
		}
		if(e.getView().getTitle().equals("§8» §6Einstellungen")){
            e.setCancelled(true);
            
            try {

                File logs = new File("plugins/GS", "Plots.yml");
    			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
    			
    			if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)){
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aPlot-Spawn")){
    					double x = p.getLocation().getBlockX();
    					double z = p.getLocation().getBlockZ();
    					
                        String name = p.getUniqueId().toString();
                        
                        if(PlotManager.isCreated(x, z) != null) {
                        	if(p.getUniqueId().toString().equals(pl.getString(name + ".uuid"))) {
                        		p.closeInventory();
								PlotManager.setPlotHome(p);
								p.sendMessage(Main.pr + "Du hast den Spawn Punkt deines Plots geändert!");
                        	}
                        }else {
                        	p.sendMessage(Main.pr + "§cDu musst dich auf deinem Plot befinden!");
							p.closeInventory();
                        }
    					
    					
    				}
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aTeleport Anfragen")){
    					
    					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.0F, 1.0F);
    					
    					if(p.getUniqueId().toString().equals(pl.getString(p.getUniqueId().toString() + ".uuid"))){
    						if(PlotManager.isTpRequest(p).equals("true")){
    				        	pl.set(p.getUniqueId().toString() + ".tp-request", "false");
    				        	PlotManager.savePlayerList();
    				        	p.sendMessage(Main.pr + "Du hast Teleport Anfragen §cdeaktiviert§a!");
    				        	Inventar.PlotGUISettings(p, "Einstellungen");
    				        }else{
    				        	pl.set(p.getUniqueId().toString() + ".tp-request", "true");
    				        	PlotManager.savePlayerList();
    				        	p.sendMessage(Main.pr + "Du hast Teleport Anfragen §aaktiviert!");
    				        	Inventar.PlotGUISettings(p, "Einstellungen");
    				        }
    						PlotManager.save(logs1, pl);
    					}else{
    						p.closeInventory();
    						p.sendMessage(Main.pr + "§cDu kannst die Einstellungen eines Plots nicht als Mitglied verändern!");
    					}
    				}
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cZurück")){
    					Inventar.PlotGUI(p, "Plot");
    				}
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cLöschen")){
    					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
    					
    					Inventar.PlotGUISettingsDeleteConfirm(p);
    				}
    				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aFlags")) {
    					Inventar.PlotGUIFlags(p, "Flags");
    				}
    			}
            }catch(NullPointerException e10) {
            	
            }
		}
		
		if(e.getView().getTitle().equals("§8» §6Flags")) {
			e.setCancelled(true);
			
			try {
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getItemMeta() != null) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cZurück")){
	    					Inventar.PlotGUI(p, "Plot");
	    				}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Blöcke abbauen: §cAus")) {
							PlotManager.updateFlag(p, "block-break", true);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Blöcke abbauen: §aAn")) {
							PlotManager.updateFlag(p, "block-break", false);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Blöcke platzieren: §cAus")) {
							PlotManager.updateFlag(p, "block-place", true);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Blöcke platzieren: §aAn")) {
							PlotManager.updateFlag(p, "block-place", false);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7PVP: §cAus")) {
							PlotManager.updateFlag(p, "pvp", true);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7PVP: §aAn")) {
							PlotManager.updateFlag(p, "pvp", false);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Interaktion (Hebel etc.): §cAus")) {
							PlotManager.updateFlag(p, "interact", true);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Interaktion (Hebel etc.): §aAn")) {
							PlotManager.updateFlag(p, "interact", false);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Interaktion mit Tieren (Villager etc.): §cAus")) {
							PlotManager.updateFlag(p, "interact-animals", true);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Interaktion mit Tieren (Villager etc.): §aAn")) {
							PlotManager.updateFlag(p, "interact-animals", false);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Mob grief: §cAus")) {
							PlotManager.updateFlag(p, "mob-grief", true);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Mob grief: §aAn")) {
							PlotManager.updateFlag(p, "mob-grief", false);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Mob spawn: §cAus")) {
							PlotManager.updateFlag(p, "mob-spawn", true);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Mob spawn: §aAn")) {
							PlotManager.updateFlag(p, "mob-spawn", false);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Explosionen: §cAus")) {
							PlotManager.updateFlag(p, "tnt", true);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Explosionen: §aAn")) {
							PlotManager.updateFlag(p, "tnt", false);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Fire spread: §cAus")) {
							PlotManager.updateFlag(p, "fire-spread", true);
							Inventar.PlotGUIFlags(p, "Flags");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §7Fire spread: §aAn")) {
							PlotManager.updateFlag(p, "fire-spread", false);
							Inventar.PlotGUIFlags(p, "Flags");
						}
					}
				}
			}catch(NullPointerException e21) {
				
			}
		}
		
		if(e.getView().getTitle().equals("§8» §6Home 1.")) {
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getItemMeta() != null) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aSpringe zu deinem 1. Home")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
							
							p.teleport(Home.getLocation(p, 1));
							p.closeInventory();
							p.sendMessage(Main.pr + "Du hast dich zu deinem 1. Home teleportiert!");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cLösche dein 1. Home")) {
							p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
							
							Home.delHome(p, 1);
							p.closeInventory();
							p.sendMessage(Main.pr + "Du hast dein 1. Home gelöscht!");
						}
					}
				}
			}catch(NullPointerException e5) {
				
			}
		}
		
		if(e.getView().getTitle().equalsIgnoreCase("§8» §6Home 2.")) {
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getItemMeta() != null) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aSpringe zu deinem 2. Home")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
							
							p.teleport(Home.getLocation(p, 2));
							p.closeInventory();
							p.sendMessage(Main.pr + "Du hast dich zu deinem 2. Home teleportiert!");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cLösche dein 2. Home")) {
							p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
							
							Home.delHome(p, 2);
							p.closeInventory();
							p.sendMessage(Main.pr + "Du hast dein 2. Home gelöscht!");
						}
					}
				}
			}catch(NullPointerException e6) {
				
			}
		}
		if(e.getView().getTitle().equals("§8» §6Home 3.")) {
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getItemMeta() != null) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aSpringe zu deinem 3. Home")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
							
							p.teleport(Home.getLocation(p, 3));
							p.closeInventory();
							p.sendMessage(Main.pr + "Du hast dich zu deinem 3. Home teleportiert!");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cLösche dein 3. Home")) {
							p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
							
							Home.delHome(p, 3);
							p.closeInventory();
							p.sendMessage(Main.pr + "Du hast dein 3. Home gelöscht!");
						}
					}
				}
			}catch(NullPointerException e7) {
				
			}
		}
		
		
		if(e.getView().getTitle().equals("§8» §6Home 4.")) {
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getItemMeta() != null) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aSpringe zu deinem 4. Home")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
							
							p.teleport(Home.getLocation(p, 4));
							p.closeInventory();
							p.sendMessage(Main.pr + "Du hast dich zu deinem 4. Home teleportiert!");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cLösche dein 4. Home")) {
							p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
							
							Home.delHome(p, 4);
							p.closeInventory();
							p.sendMessage(Main.pr + "Du hast dein 4. Home gelöscht!");
						}
					}
				}
			}catch(NullPointerException e6) {
				
			}
		}
		
		if(e.getView().getTitle().equals("§8» §6Home 5.")) {
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getItemMeta() != null) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aSpringe zu deinem 5. Home")) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
							
							p.teleport(Home.getLocation(p, 5));
							p.closeInventory();
							p.sendMessage(Main.pr + "Du hast dich zu deinem 5. Home teleportiert!");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cLösche dein 5. Home")) {
							p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
							
							Home.delHome(p, 5);
							p.closeInventory();
							p.sendMessage(Main.pr + "Du hast dein 5. Home gelöscht!");
						}
					}
				}
			}catch(NullPointerException e6) {
				
			}
		}
		
		if(e.getView().getTitle().equals("§8» §6Homes")){
			e.setCancelled(true);
			if(e.getCurrentItem() != null){
				if(e.getCurrentItem().getItemMeta() != null){
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aErstelle dein 1. Home")){
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
						
						Home.addHome(p, 1);
						
						p.closeInventory();
						p.sendMessage(Main.pr + "Du hast dein 1. Home gesetzt!");
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aErstelle dein 2. Home")){
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
						
						Home.addHome(p, 2);
						
						p.closeInventory();
						p.sendMessage(Main.pr + "Du hast dein 2. Home gesetzt!");
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aErstelle dein 3. Home")){
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
						
						Home.addHome(p, 3);
						
						p.closeInventory();
						p.sendMessage(Main.pr + "Du hast dein 3. Home gesetzt!");
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aErstelle dein 4. Home")){
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
						
						Home.addHome(p, 4);
						
						p.closeInventory();
						p.sendMessage(Main.pr + "Du hast dein 4. Home gesetzt!");
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aErstelle dein 5. Home")){
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
						
						Home.addHome(p, 5);
						
						p.closeInventory();
						p.sendMessage(Main.pr + "Du hast dein 5. Home gesetzt!");
					}
					
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §a1. Home")) {
						Inventar.Home1Gui(p);
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §a2. Home")) {
						Inventar.Home2Gui(p);
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §a3. Home")) {
						Inventar.Home3Gui(p);
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §a4. Home")) {
						Inventar.Home4Gui(p);
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §a5. Home")) {
						Inventar.Home5Gui(p);
					}
				}
			}
		}
		
		if(e.getView().getTitle().equals("§cWillst du dein Plot löschen?")){
			e.setCancelled(true);
			try {
				if(e.getCurrentItem() != null){
					if(e.getCurrentItem().getItemMeta() != null){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aJa")){
							
							p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK,1, 1);
							p.closeInventory();
							if(pl.getString(p.getUniqueId().toString() + ".Name") != null && pl.getString(p.getUniqueId().toString() + ".Name1") == null && pl.getString(p.getUniqueId().toString() + ".Name2") == null) {
								PlotManager.delPlot(p);
								PlotManager.delPlayerList(p);
							}else {
								PlotManager.delPlot(p);
							}
							p.sendMessage(Main.pr + "Dein Grundstück wurde erfolgreich gelöscht!");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cNein")){
							Inventar.PlotGUISettings(p, "Einstellungen");
						}
					}
				}
			}catch(NullPointerException e11) {
				
			}
		}
		
		if(e.getView().getTitle().equals("§8» §6Verwaltung")){
			e.setCancelled(true);
			
			File logs = new File("plugins/GS", "Plots.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);	
			
			
			try {

				if(e.getCurrentItem() != null){
					if(e.getCurrentItem().getItemMeta() != null){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cZurück")){
							Inventar.PlotGUI(p, "Plot");
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aHinzufügen")){
							Main.member.add(p);
							p.closeInventory();
							p.sendMessage(Main.pr + "Bitte tippe den Namen des Spielers ein!");
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cEntfernen")){
							Main.delmember.add(p);
							p.closeInventory();
							p.sendMessage(Main.pr + "Bitte tippe den Namen des Spielers ein!");
						}
					}
				}
			}catch(NullPointerException e12) {
				
			}
		}
		
	}
}
