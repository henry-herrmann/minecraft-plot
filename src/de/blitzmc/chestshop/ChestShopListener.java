package de.blitzmc.chestshop;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.Player.Spigot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import de.blitzmc.Main;
import de.blitzmc.utils.Currency;
import de.blitzmc.utils.ShopManager;

public class ChestShopListener implements Listener{

	public static File Sign = new File("plugins/GS/Signs.yml");
	public static YamlConfiguration ySign = YamlConfiguration.loadConfiguration(Sign);
	
	@EventHandler
	  public void onCreateSign(SignChangeEvent e)
	  {
	    Player p = e.getPlayer();
	    double x = p.getLocation().getBlockX();
	    double z = p.getLocation().getBlockZ();
	    
	    if(ShopManager.isCreated(x, z) != null) {
	    	if(p.getUniqueId().toString().equals(ShopManager.isOwner(x, z))) {
	    		if (e.getLine(0).equalsIgnoreCase("[Shop]"))
	    	    {
	    	      if ((e.getBlock().getRelative(BlockFace.WEST).getType() == Material.CHEST) || (e.getBlock().getRelative(BlockFace.NORTH).getType() == Material.CHEST) || (e.getBlock().getRelative(BlockFace.SOUTH).getType() == Material.CHEST) || (e.getBlock().getRelative(BlockFace.EAST).getType() == Material.CHEST))
	    	      {
	    	        if (e.getLine(1).toLowerCase().startsWith("buy:"))
	    	        {
	    	          String[] line1 = e.getLine(1).substring(1).split(" ");
	    	          if (line1[1].matches("[0-9]+"))
	    	          {
	    	            if (e.getLine(2).matches("[0-9]+"))
	    	            {
	    	            	e.setLine(0, "§0§l[Shop]");
	    		            e.setLine(1, "§0Buy:§l " + line1[1]);
	    		            e.setLine(2, "§0" + e.getLine(2) + "$");
	    		            e.setLine(3, p.getName());
	    	              
	    	              Block chest = null;
	    	              if (e.getBlock().getRelative(BlockFace.NORTH).getType() == Material.CHEST) {
	    	                chest = e.getBlock().getRelative(BlockFace.NORTH).getLocation().getBlock();
	    	              } else if (e.getBlock().getRelative(BlockFace.WEST).getType() == Material.CHEST) {
	    	                chest = e.getBlock().getRelative(BlockFace.WEST).getLocation().getBlock();
	    	              } else if (e.getBlock().getRelative(BlockFace.EAST).getType() == Material.CHEST) {
	    	                chest = e.getBlock().getRelative(BlockFace.EAST).getLocation().getBlock();
	    	              } else if (e.getBlock().getRelative(BlockFace.SOUTH).getType() == Material.CHEST) {
	    	                chest = e.getBlock().getRelative(BlockFace.SOUTH).getLocation().getBlock();
	    	              }
	    	              p.sendMessage(Main.pr + "Dein Shop wurde registriert! §lBitte baue einen Rahmen oder eine Schild mit dem Item, das du verkaufst, über die Kiste. §cDer Verkauf von Items, die nicht gekennzeichnet sind, ist nicht gestattet.");
	    	              
	    	              List<String> Signs = ySign.getStringList("Locations");
	    	              
	    	              Signs.add(locToString(e.getBlock().getLocation()));
	    	              
	    	              ySign.set("Locations", Signs);
	    	              
	    	              List<String> TruhenLoc = ySign.getStringList("LocationsT");
	    	              
	    	              TruhenLoc.add(locToString(chest.getLocation()));
	    	              
	    	              ySign.set("LocationsT", TruhenLoc);
	    	              try
	    	              {
	    	                ySign.save(Sign);
	    	              }
	    	              catch (IOException e1)
	    	              {
	    	                e1.printStackTrace();
	    	              }
	    	            }
	    	            else
	    	            {
	    	              e.setCancelled(true);
	    	              p.closeInventory();
	    	              e.getBlock().setType(Material.AIR);
	    	              e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new org.bukkit.inventory.ItemStack(Material.SPRUCE_SIGN));
	    	              p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
	    	              p.sendMessage(Main.pr + "§cBitte gib einen Preis an!");
	    	            }
	    	          }
	    	          else
	    	          {
	    	            e.setCancelled(true);
	    	            p.closeInventory();
	    	            e.getBlock().setType(Material.AIR);
	    	            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new org.bukkit.inventory.ItemStack(Material.SPRUCE_SIGN));
	    	            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
	    	            p.sendMessage(Main.pr + "§cBitte gib eine Anzahl an.");
	    	          }
	    	        }else {
	    	        	p.sendMessage(Main.pr + "§cFalsche Anordnung");
	    	        	p.sendMessage("[Shop]");
	    	        	p.sendMessage("Buy: <Anzahl>");
	    	        	p.sendMessage("Preis ohne $ Zeichen!");
	    	        }
	    	      }  
	    	    }
	    	}
	    }
	  }
	
	@EventHandler
	  public void onBreak(BlockBreakEvent e)
	  {
	    Player p = e.getPlayer();
	    double x = p.getLocation().getBlockX();
	    double z = p.getLocation().getBlockZ();
	    
	    if (e.getBlock().getType() == Material.CHEST)
	    {
	      List<String> Signs = ySign.getStringList("Locations");
	      
	      if(ShopManager.isCreated(x, z) != null) {
	    	  if(p.getUniqueId().toString().equals(ShopManager.isOwner(x, z))) {
	    		  if ((Signs.contains(locToString(e.getBlock().getRelative(BlockFace.WEST).getLocation()))) || (Signs.contains(locToString(e.getBlock().getRelative(BlockFace.SOUTH).getLocation()))) || (Signs.contains(locToString(e.getBlock().getRelative(BlockFace.NORTH).getLocation()))) || (Signs.contains(locToString(e.getBlock().getRelative(BlockFace.EAST).getLocation()))))
	    	      {
	    	        e.setCancelled(true);
	    	        p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
	    	        p.sendMessage(Main.pr + "§cBaue erst das Shop-Schild ab!");
	    	      }
	    	  }else {
	    		  e.setCancelled(true);
	    	  }
	      }
	    }
	    else if (e.getBlock().getType() == Material.SPRUCE_WALL_SIGN || e.getBlock().getType() == Material.BIRCH_WALL_SIGN || e.getBlock().getType() == Material.JUNGLE_WALL_SIGN || e.getBlock().getType() == Material.ACACIA_WALL_SIGN  || e.getBlock().getType() == Material.OAK_WALL_SIGN  || e.getBlock().getType() == Material.DARK_OAK_WALL_SIGN)
	    {
	      List<String> Signs = ySign.getStringList("Locations");
	      if (Signs.contains(locToString(e.getBlock().getLocation())))
	      {
	        Block chest = null;
	        if (e.getBlock().getRelative(BlockFace.NORTH).getType() == Material.CHEST) {
	          chest = e.getBlock().getRelative(BlockFace.NORTH).getLocation().getBlock();
	        } else if (e.getBlock().getRelative(BlockFace.WEST).getType() == Material.CHEST) {
	          chest = e.getBlock().getRelative(BlockFace.WEST).getLocation().getBlock();
	        } else if (e.getBlock().getRelative(BlockFace.EAST).getType() == Material.CHEST) {
	          chest = e.getBlock().getRelative(BlockFace.EAST).getLocation().getBlock();
	        } else if (e.getBlock().getRelative(BlockFace.SOUTH).getType() == Material.CHEST) {
	          chest = e.getBlock().getRelative(BlockFace.SOUTH).getLocation().getBlock();
	        }
	        Iterator localIterator2;
	        for (Iterator localIterator1 = Bukkit.getWorlds().iterator(); localIterator1.hasNext(); localIterator2.hasNext())
	        {
	          World w = (World)localIterator1.next();
	          localIterator2 = w.getEntities().iterator(); continue;
	        }
	        Signs.remove(locToString(e.getBlock().getLocation()));
	        
	        ySign.set("Locations", Signs);
	        
	        List<String> TruhenLoc = ySign.getStringList("LocationsT");
	        
	        TruhenLoc.remove(locToString(chest.getLocation()));
	        
	        ySign.set("LocationsT", TruhenLoc);
	        try
	        {
	          ySign.save(Sign);
	        }
	        catch (IOException e1)
	        {
	          e1.printStackTrace();
	        }
	      }
	    }
	  }
	@SuppressWarnings("unused")
	@EventHandler
	  public void onClick(PlayerInteractEvent e)
	  
	  {
	    Player p = e.getPlayer();
	    try
	    {
	      if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
	      {

	        if (e.getClickedBlock().getType() == Material.SPRUCE_WALL_SIGN || e.getClickedBlock().getType() == Material.BIRCH_WALL_SIGN || e.getClickedBlock().getType() == Material.JUNGLE_WALL_SIGN || e.getClickedBlock().getType() == Material.ACACIA_WALL_SIGN  || e.getClickedBlock().getType() == Material.OAK_WALL_SIGN  || e.getClickedBlock().getType() == Material.DARK_OAK_WALL_SIGN)
	        {
	          Sign sign = (Sign)e.getClickedBlock().getState();
	          
	          String[] line1 = sign.getLine(1).substring(1).split(" ");
	          Integer preis;
	          Integer moneyvonkäufer;
	          BigDecimal neuesgeld4käufer;
	          
	          if (sign.getLine(0).toLowerCase().contains("[shop]"))
	          {
	            org.bukkit.inventory.ItemStack Abfrage;
	            if (sign.getLine(1).toLowerCase().startsWith("buy:"))
	            {
	              List<String> Signs = ySign.getStringList("Locations");
	              if (Signs.contains(locToString(e.getClickedBlock().getLocation())))
	              {
	                Block chest = null;
	                if (e.getClickedBlock().getRelative(BlockFace.NORTH).getType() == Material.CHEST) {
	                  chest = e.getClickedBlock().getRelative(BlockFace.NORTH).getLocation().getBlock();
	                } else if (e.getClickedBlock().getRelative(BlockFace.WEST).getType() == Material.CHEST) {
	                  chest = e.getClickedBlock().getRelative(BlockFace.WEST).getLocation().getBlock();
	                } else if (e.getClickedBlock().getRelative(BlockFace.EAST).getType() == Material.CHEST) {
	                  chest = e.getClickedBlock().getRelative(BlockFace.EAST).getLocation().getBlock();
	                } else if (e.getClickedBlock().getRelative(BlockFace.SOUTH).getType() == Material.CHEST) {
	                  chest = e.getClickedBlock().getRelative(BlockFace.SOUTH).getLocation().getBlock();
	                }
	                Inventory c = ((Chest)chest.getState()).getInventory();
	                
	                org.bukkit.inventory.ItemStack i2 = null;
	                ItemStack[] moneyvonkäufer1;
	                
	                for(ItemStack stack : c.getContents()) {
	                	if(stack != null) {
	                		i2 = stack;
	                	}
	                }
	                
	                if (i2 != null)
	                {
	                  if (!sign.getLine(3).equalsIgnoreCase(p.getName()))
	                  {
	                    Integer Amount = Integer.valueOf(line1[1]);
	                    
	                    Abfrage = new org.bukkit.inventory.ItemStack(i2);
	                    if (hasItem(c, Abfrage.getType(), Amount.intValue()))
	                    {
	                      
	                      preis = Integer.valueOf(sign.getLine(2).replaceAll("§0", "").replaceAll("\\$", ""));
	                      if (Currency.getDollar(p) >= Integer.valueOf(((Integer)preis).intValue()).intValue())
	                      {
	                        
	                        Currency.removeDollar(p, preis);
	                        if(Bukkit.getPlayer(sign.getLine(3)) != null) {
		                        Currency.addDollar(Bukkit.getPlayer(sign.getLine(3)), preis);
	                        }else {
		                        Currency.addOfflineDollar(Bukkit.getOfflinePlayer(sign.getLine(3)), preis);
	                        }
	                        org.bukkit.inventory.ItemStack give = new org.bukkit.inventory.ItemStack(i2);
	                        give.setAmount(Amount.intValue());
	                        
	                        p.getInventory().addItem(new org.bukkit.inventory.ItemStack[] { give });
	                        
	                        org.bukkit.inventory.ItemStack neuechest = new org.bukkit.inventory.ItemStack(i2);
	                        neuechest.setAmount(1);
	                        
	                        removeItems(c, neuechest, Amount.intValue());
	                        
	                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
	                        p.playEffect(e.getClickedBlock().getLocation(), Effect.VILLAGER_PLANT_GROW, 10);
	                        p.sendMessage(Main.pr + "Du hast beim Spieler §6" + sign.getLine(3) + " §6" + Amount + " §6" + give.getType() + " §afür §6" + preis + "$ §agekauft.");
	                        
	                        Bukkit.getConsoleSender().sendMessage("§a[§6CHESTSHOP§a] Der Spieler §e" + p.getName() + "§a hat bei §e" + sign.getLine(3) + "§e " + Amount + " " +   give.getType() + "§a für §e" + preis +"§a gekauft.");
	                        
	                        String verkäufer = sign.getLine(3);
	                        Player vp = Bukkit.getPlayer(verkäufer);
	                        if (vp != null) {
	                          vp.sendMessage(Main.pr + "Der Spieler §6" + p.getName() + " §ahat bei dir" + " §6" + Amount + " §6" + give.getType() + " §afür §6" + preis + "$ §agekauft.");
	                        }
	                      }
	                      else
	                      {
	                        p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
	                        p.playEffect(e.getClickedBlock().getLocation(), Effect.SMOKE, 10);
	                        p.sendMessage(Main.pr + "§cDu hast nicht genug Geld!");
	                      }
	                    }
	                    else
	                    {
	                      p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
	                      p.sendMessage(Main.pr + "§cIm Shop sind zu wenige Items vorhanden!");
	                    }
	                  }
	                  else
	                  {
	                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
	                    p.sendMessage(Main.pr + "§cDu darfst selber bei dir nichts kaufen!");
	                  }
	                }
	                else
	                {
	                  p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
	                  p.sendMessage(Main.pr + "§cDer Shop ist leer!");
	                }
	              }
	            }
	            
	            
	          }
	        }
	        
	        
	      }
	    }
	    catch (Exception localException) {}
	  }
	public static void removeItems(Inventory inv, org.bukkit.inventory.ItemStack itemStack, int amount)
	  {
	    int removedItems = 0;
	    int num = -1;
	    org.bukkit.inventory.ItemStack[] arrayOfItemStack;
	    int j = (arrayOfItemStack = inv.getContents()).length;
	    for (int i = 0; i < j; i++)
	    {
	      org.bukkit.inventory.ItemStack im = arrayOfItemStack[i];
	      
	      num++;
	      if ((removedItems != amount) && 
	        (im != null) && 
	        (im.getType().equals(itemStack.getType())))
	      {
	        inv.clear(num);
	        
	        removedItems += im.getAmount();
	      }
	    }
	    if (removedItems > amount)
	    {
	      int sub = removedItems - amount;
	      for (int i = sub; i != 0; i--) {
	        inv.addItem(new org.bukkit.inventory.ItemStack[] { itemStack });
	      }
	    }
	    else if (removedItems < amount)
	    {
	      for (int i = removedItems; i != 0; i--) {
	        inv.addItem(new org.bukkit.inventory.ItemStack[] { itemStack });
	      }
	    }
	  }
	  
	  public static boolean hasItem(Inventory inv, Material m, int amount)
	  {
	    org.bukkit.inventory.ItemStack[] arrayOfItemStack;
	    int j = (arrayOfItemStack = inv.getContents()).length;
	    for (int i = 0; i < j; i++)
	    {
	      org.bukkit.inventory.ItemStack is = arrayOfItemStack[i];
	      if ((is != null) && (is.getType() == m) && (is.getAmount() >= amount)) {
	        return true;
	      }
	    }
	    return false;
	  }
	  
	  
	  
	  public static void onChestUpdate()
	  {
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable()
	    {
	      public void run()
	      {
	        List<String> Signs = ySign.getStringList("LocationsT");
	        for (String all : Signs)
	        {
	          Location loc = stringToLocT(all);
	          if (loc != null)
	          {
	            Iterator localIterator3;
	            for (Iterator localIterator2 = Bukkit.getWorlds().iterator(); localIterator2.hasNext(); localIterator3.hasNext())
	            {
	              World w = (World)localIterator2.next();
	              localIterator3 = w.getEntities().iterator(); continue;
	            }
	          }
	          Location loc2 = stringToLoc(all);
	          
	          Block chest = loc2.getBlock();
	          if (chest != null)
	          {
	            Inventory c = ((Chest)chest.getState()).getInventory();
	            
	            org.bukkit.inventory.ItemStack i2 = null;
	            org.bukkit.inventory.ItemStack[] arrayOfItemStack;
	            int j = (arrayOfItemStack = c.getContents()).length;
	            for (int i = 0; i < j; i++)
	            {
	              org.bukkit.inventory.ItemStack im = arrayOfItemStack[i];
	              if (im != null)
	              {
	                i2 = im;
	                break;
	              }
	            }
	            if (i2 != null)
	            {
	            	if(Bukkit.getOnlinePlayers().size() >= 1) {
	            		if(i2.getType() == Material.INK_SAC && i2.getData().getData() == (byte) 4) {
	            			Location lo = new Location(loc.getWorld(), loc.getX(), loc.getY()- 0.5, loc.getZ());
	            			ArmorStand am = (ArmorStand) lo.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		            		am.setVisible(false);
		            		am.setGravity(false);
		            		am.setCustomNameVisible(true);
		            		am.setCustomName("§1§lLapis");  
		                        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
		            			
		            			@Override
		            			public void run() {
		            				am.remove();
		            				
		            			}
		            		},30L, 0);
	            				
	            		}
	            	}
	              
	            }
	          }
	        }
	      }
	    }, 0L, 30L);
	  }
	  
	  public static String locToString(Location l)
	  {
	    String ret = l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
	    return ret;
	  }
	  
	  public static Location stringToLoc(String s)
	  {
	    String[] a = s.split("\\,");
	    
	    World w = Bukkit.getWorld(a[0]);
	    
	    float x = Float.parseFloat(a[1]);
	    float y = Float.parseFloat(a[2]);
	    float z = Float.parseFloat(a[3]);
	    
	    return new Location(w, x, y, z);
	  }
	  
	  public static Location stringToLocT(String s)
	  {
	    String[] a = s.split("\\,");
	    
	    World w = Bukkit.getWorld(a[0]);
	    
	    float x = Float.parseFloat(a[1]);
	    float y = Float.parseFloat(a[2]);
	    float z = Float.parseFloat(a[3]);
	    
	    return new Location(w, x, y, z).subtract(-0.5D, 0.0D, -0.5D);
	  }
	  
	  public boolean isFull(Inventory inv)
	  {
	    org.bukkit.inventory.ItemStack[] arrayOfItemStack;
	    int j = (arrayOfItemStack = inv.getContents()).length;
	    for (int i = 0; i < j; i++)
	    {
	      org.bukkit.inventory.ItemStack im = arrayOfItemStack[i];
	      if ((im == null) || (im.getType() == Material.AIR)) {
	        return false;
	      }
	    }
	    return true;
	  }
}
