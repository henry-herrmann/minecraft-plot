package de.blitzmc.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.blitzmc.Main;
import de.blitzmc.utils.Currency;
import de.blitzmc.utils.ItemBuilder;
import de.blitzmc.utils.TPSpawn;


public class KillListener implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void onKill(PlayerDeathEvent e) {
		
		/*Player p = e.getEntity().getPlayer();
		Player k = e.getEntity().getKiller();
		
		TPSpawn.tpSpawn(p);
		
		e.setDeathMessage(null);
		
		if(k != null) {
			if(k instanceof Player) {
				
				k.sendMessage(Main.pr + "§aDu hast " + p.getName() + " getötet!");
				p.sendMessage(Main.pr + "§cDu wurdest von " + k.getName() + " getötet!");
				
				if(Main.Auftragskiller.contains(k)) {
					Currency.addDollar(k, 45);
					TitleManager.sendTitle(k, 0, 40, 0, "§a", "§8» §a+ 45 Dollar");
				}
				
			} else {
				p.sendMessage(Main.pr + "§cDu bist gestorben!");
			}
		} else {
			p.sendMessage(Main.pr + "§cDu bist gestorben!");
		}*/
	}
	
	@EventHandler
	public void onEntittyDeath(EntityDeathEvent e) {
		/*Entity victim = e.getEntity();
		Player p = e.getEntity().getKiller();
		
		File logs = new File("plugins/GS", "Wartungen.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logs);
		
		try {
			if(!p.getWorld().getName().equals("world")) {
				if(cfg.getBoolean("end_event" + ".status") == true) {
					if(!(victim instanceof Player) && e.getEntity().getKiller() instanceof Player) {
						if(victim instanceof EnderDragon) {
							Currency.addDollar(p, 18000);
							TitleManager.sendTitle(p, 0, 40, 0, "§aGlückwunsch!", "§a+ §61800");
							p.getInventory().addItem(ItemBuilder.getItem(Material.DRAGON_HEAD, "§eDrachenkopf", 0, 1));
							
							for(Player all : Bukkit.getOnlinePlayers()) {
								all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1F, 1F);
								all.sendMessage(" ");
		                        all.sendMessage("          §a§lDer Ender Drache wure getötet!");
		                        all.sendMessage("                   §6" + p.getName() + "§a hat gewonnen!");
		                        all.sendMessage(" ");
							}
							
							
							cfg.set("end_event"+ ".status", false);
							try {
								cfg.save(logs);
							} catch (IOException e1) {
								
							}
						}
					}
				}
            	if(Main.Jäger.contains(p)) {
            		if(!(victim instanceof Player) && e.getEntity().getKiller() instanceof Player)  {

                        if(victim instanceof Cow ) {
                            if(Main.Jäger.contains(p)) {
                            	Currency.addDollar(p, 2);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 2 Dollar");
                            }
                        }
                        if(victim instanceof Sheep ) {
                            if(Main.Jäger.contains(p)) {
                            	Currency.addDollar(p, 2);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 2 Dollar");
                            }
                        }
                        if(victim instanceof Pig ) {
                            if(Main.Jäger.contains(p)) {
                            	Currency.addDollar(p, 2);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 2 Dollar");
                            }
                        }
                        if(victim instanceof Chicken ) {
                            if(Main.Jäger.contains(p)) {
                            	Currency.addDollar(p, 1);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 1 Dollar");
                            }
                        }
                        if(victim instanceof Zombie ) {
                            if(Main.Jäger.contains(p)) {
                            	Currency.addDollar(p, 1);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 1 Dollar");
                            }
                        }
                        if(victim instanceof Witch ) {
                            if(Main.Jäger.contains(p)) {
                            	Currency.addDollar(p, 8);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 8 Dollar");
                            }
                        }
                        if(victim instanceof Creeper ) {
                            if(Main.Jäger.contains(p)) {
                            	Currency.addDollar(p, 2);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 2 Dollar");
                            }
                        }
                        if(victim instanceof Spider ) {
                            if(Main.Jäger.contains(p)) {
                            	Currency.addDollar(p, 1);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 1 Dollar");
                            }
                        }
                        if(victim instanceof Slime ) {
                            if(Main.Jäger.contains(p)) {
                            	Currency.addDollar(p, 4);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 4 Dollar");
                            }
                        } 
                	}
            	}
			}
		}catch(NullPointerException e1) {
			
		}
		*/
	}
}
