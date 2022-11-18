package de.blitzmc.listeners;


import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import de.blitzmc.Main;
import de.blitzmc.cmds.CMD_Build;
import de.blitzmc.nms.Title;
import de.blitzmc.utils.Currency;
import de.blitzmc.utils.MessageManager;
import de.blitzmc.utils.PlotManager;
import de.blitzmc.utils.RegionManager;
import de.blitzmc.utils.ShopManager;

public class ProtectionListener implements Listener{
	PlotManager pm;
	Title title;
			
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		double x = p.getLocation().getBlockX();
		double z = p.getLocation().getBlockZ();
		
		if(PlotManager.isCreated(x, z) != null) {
			if(PlotManager.checkSperrgebiet(x, z) == null) {
				if(!Main.entered.contains(p)) {
					p.sendMessage(Main.pr + "Willkommen auf §6" + PlotManager.getOwnerName(x, z) + "§6's §aPlot!");
					Main.entered.add(p);
				}
			}else {
				if(!CMD_Build.buildmode.contains(p)) {
					if(p.getLocation().getX() < 0 ) {
						p.teleport(new Location(p.getWorld(), p.getLocation().getX() +1,p.getLocation().getY(), p.getLocation().getZ()));
					}else {
						p.teleport(new Location(p.getWorld(), p.getLocation().getX() -1,p.getLocation().getY(), p.getLocation().getZ()));
					}
					title.sendTitle(p, 0, 50, 0, "§c§lSPERRGEBIET", "§7Dieses Plot wurde zum Sperrgebiet erklärt");
				}
			}
		}else {
			if(Main.entered.contains(p)) {
				p.sendMessage(Main.pr + "Auf Wiedersehen!");
				Main.entered.remove(p);
			}
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		
		if(!CMD_Build.buildmode.contains(p)){
			
			  if(p.getWorld().getName().equals("world")){
				  List<String> members = PlotManager.getMembers(e.getBlock());
				  String uuid = PlotManager.getBlockOwner(e.getBlock());
				  
				  double x = e.getBlock().getLocation().getX();
				  double z = e.getBlock().getLocation().getZ();
				  
					
						if(!p.getUniqueId().toString().equals(uuid) && !members.contains(p.getUniqueId().toString())){
							if(Main.Trusted.contains(p)){
								e.setCancelled(false);
							}else{
								if(e.getBlock().getType() == Material.LEGACY_SIGN) {
									Sign s = (Sign) e.getBlock().getState();
									if(s.getLine(0).equals("[ChestShop]")) {
										e.setCancelled(false);
									}
								}
								if(e.getBlock().getType() == Material.LEGACY_SIGN_POST) {
									Sign s = (Sign) e.getBlock().getState();
									if(s.getLine(0).equals("[ChestShop]")) {
										e.setCancelled(true);
									}
								}
								if(PlotManager.isCreated(x, z) != null){
									
									if(!PlotManager.getFlag(uuid, "block-break")) {
										e.setCancelled(true);
										
										String bar = "§cPlot von:§e " + PlotManager.getOwnerName(x, z);
										
										//p.spigot().sendMessage(ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(bar));
									}else {
										e.setCancelled(false);
									}
								}
								double x1 = e.getBlock().getLocation().getBlockX();
								double z1 = e.getBlock().getLocation().getBlockZ();
								if(ShopManager.isCreated(x1, z1) != null) {
									 if(p.getUniqueId().toString().equals(ShopManager.isOwner(x1, z1))) {
										if(e.getBlock().getType() == Material.CHEST) {
											e.setCancelled(false);
										}
										if(e.getBlock().getType() == Material.SPRUCE_SIGN) {
											e.setCancelled(false);
										}
										if(e.getBlock().getType() == Material.ACACIA_WALL_SIGN) {
											e.setCancelled(false);
										}
										if(e.getBlock().getType() == Material.SPRUCE_WALL_SIGN) {
											e.setCancelled(false);
										}
										if(e.getBlock().getType() == Material.ACACIA_SIGN) {
											e.setCancelled(false);
										}
										if(e.getBlock().getType() == Material.BIRCH_SIGN) {
											e.setCancelled(false);
										}
										if(e.getBlock().getType() == Material.BIRCH_WALL_SIGN) {
											e.setCancelled(false);
										}
										if(e.getBlock().getType() == Material.ITEM_FRAME) {
											e.setCancelled(false);
										}
										if(e.getBlock().getType() != Material.CHEST && e.getBlock().getType() != Material.SPRUCE_SIGN && e.getBlock().getType() != Material.SPRUCE_WALL_SIGN && e.getBlock().getType() != Material.ACACIA_SIGN && e.getBlock().getType() != Material.ACACIA_WALL_SIGN && e.getBlock().getType() != Material.JUNGLE_SIGN && e.getBlock().getType() != Material.JUNGLE_WALL_SIGN && e.getBlock().getType() != Material.BIRCH_SIGN && e.getBlock().getType() != Material.BIRCH_WALL_SIGN && e.getBlock().getType() != Material.ITEM_FRAME) {
											e.setCancelled(true);
										}
									 }else {
										 e.setCancelled(true);
									 }
									 if(RegionManager.isCreated(x, z, p.getWorld().getName()) != null){
										 e.setCancelled(true);
										 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
									 }
								}else {
									if(RegionManager.isCreated(x, z, p.getWorld().getName()) != null){
										 e.setCancelled(true);
										 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
									}
								}
							}
						}else{
							e.setCancelled(false);
						}
			  }else{
				  /*if(Main.Bergarbeiter.contains(p)) {
					  double x = e.getBlock().getLocation().getX();
					  double z = e.getBlock().getLocation().getZ();
					  
					  
						if(!p.getWorld().getName().equals("world")) {
							if(RegionManager.isCreated(x, z, p.getWorld().getName()) != null){
								 e.setCancelled(true);
								 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
							}else {
								if(e.getBlock().getType().equals(Material.STONE)) {
									
									Currency.addDollar(p, 1);
									if(MessageManager.getMessageStatus(p) == true) {
										TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 1 Dollar");
									}
								}
								if(e.getBlock().getType().equals(Material.IRON_ORE)) {
									if(Main.blocks.contains(e.getBlock())) {
										Main.blocks.remove(e.getBlock());
									}else {
										Currency.addDollar(p,4);
										if(MessageManager.getMessageStatus(p)== true) {
											TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 4 Dollar");
										}
										Experience.changeExp(p, 8);
									}
								}
								if(e.getBlock().getType().equals(Material.GOLD_ORE)) {
									if(Main.blocks.contains(e.getBlock())) {
										Main.blocks.remove(e.getBlock());
									}else {
										Currency.addDollar(p, 5);
										if(MessageManager.getMessageStatus(p)== true) {
											TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 5 Dollar");
										}
										Experience.changeExp(p, 10);
									}
								}
								if(e.getBlock().getType().equals(Material.LAPIS_ORE)) {
									if(Main.blocks.contains(e.getBlock())) {
										Main.blocks.remove(e.getBlock());
									}else {
										Currency.addDollar(p, 7);
										if(MessageManager.getMessageStatus(p)== true) {
											TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 7 Dollar");
										}
										Experience.changeExp(p, 12);
									}
								}
								if(e.getBlock().getType().equals(Material.COAL_ORE)) {
									if(Main.blocks.contains(e.getBlock())) {
										Main.blocks.remove(e.getBlock());
									}else {
										Currency.addDollar(p, 3);
										if(MessageManager.getMessageStatus(p)== true) {
											TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 3 Dollar");
										}
									}
								}
								if(e.getBlock().getType().equals(Material.DIAMOND_ORE)) {
									if(Main.blocks.contains(e.getBlock())) {
										Main.blocks.remove(e.getBlock());
									}else {
										Currency.addDollar(p, 21);
										if(MessageManager.getMessageStatus(p)== true) {
											TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 21 Dollar");
										}
										Experience.changeExp(p, 15);
									}
								}
							}
							
						}
					} else if(Main.Holzfäller.contains(p)){
						
						double x = e.getBlock().getLocation().getX();
						  double z = e.getBlock().getLocation().getZ();
						if(RegionManager.isCreated(x, z, p.getWorld().getName()) == null) {
							if(e.getBlock().getType().equals(Material.SPRUCE_LOG) || e.getBlock().getType().equals(Material.BIRCH_LOG) || e.getBlock().getType().equals(Material.ACACIA_LOG) || e.getBlock().getType().equals(Material.OAK_LOG) ) {
								if(!p.getWorld().getName().equals("world")) {
									Currency.addDollar(p, 2);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 2 Dollar");
								}	
							}
							if(e.getBlock().getType().equals(Material.JUNGLE_LOG)) {
								if(!p.getWorld().getName().equals("world")) {
									Currency.addDollar(p, 4);TitleManager.sendTitle(p, 0, 40, 0, "§a", "§8» §a+ 4 Dollar");
								}
							}
						}
						
					}
				  if ((e.getPlayer().getWorld().getName().toString().equals("world")) && 
				          (e.getBlock().getType().equals(Material.LEGACY_MOB_SPAWNER)))
				        {
				          e.setCancelled(true);
				          e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(e.getBlock().getType(), 1, (short)e.getBlock().getData()));
				          e.getBlock().setType(Material.AIR);
				        }
				   */
				  
				  double x = e.getBlock().getLocation().getX();
				  double z = e.getBlock().getLocation().getZ();
				  
				  if(RegionManager.isCreated(x, z, p.getWorld().getName()) != null){
						 e.setCancelled(true);
						 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
					}
			  }
				
				
		}else{
			e.setCancelled(false);
		}
		
		
		
	}
	/*@EventHandler
	public void onBurn(BlockBurnEvent e) {
		double x = e.getBlock().getLocation().getX();
		double z = e.getBlock().getLocation().getZ();
		
		if(PlotManager.isCreated(x, z) != null){
			e.setCancelled(true);
		}
		if(RegionManager.isCreated(x, z, e.getBlock().getWorld().getName()) != null){
			 e.setCancelled(true);
		}
	}*/
	@EventHandler
	public void onSpread(BlockSpreadEvent e) {
		double x = e.getBlock().getLocation().getX();
		double z = e.getBlock().getLocation().getZ();
		
		String uuid = PlotManager.isCreated(x, z);
		if(uuid != null) {
			if(!PlotManager.getFlag(uuid, "fire-spread")) {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		
		Entity en = e.getRightClicked();
		
		double x = en.getLocation().getX();
		double z = en.getLocation().getZ();
		
		double x1 = en.getLocation().getBlockX();
		double z1 = en.getLocation().getBlockZ();
		
		if(en instanceof ItemFrame) {
			if(ShopManager.isCreated(x1, z1) != null) {
				 if(p.getUniqueId().toString().equals(ShopManager.isOwner(x1, z1))) {
					e.setCancelled(false);
				 }else {
					 if(!CMD_Build.buildmode.contains(p)) {
						 e.setCancelled(true);
					 }
				 }
				 if(RegionManager.isCreated(x, z, p.getWorld().getName()) != null){
					 e.setCancelled(true);
					 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
				 }
			}
		}
		if(en instanceof Animals) {
			String uuid = PlotManager.isCreated(x, z);
			if(uuid != null) {
				if(!uuid.equals(p.getUniqueId().toString()) && !PlotManager.isMember(x, z).contains(p.getUniqueId().toString())) {
					if(CMD_Build.buildmode.contains(p)) {
						e.setCancelled(false);
					}else {
						if(!PlotManager.getFlag(uuid, "interact-animals")) {
							e.setCancelled(true);
						}
					}
					
				}
			}
		}
	}
	
	@EventHandler
	public void onEntity(EntityInteractEvent e) {
		if(e.getBlock().getType() == Material.STONE_PRESSURE_PLATE || e.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE || e.getBlock().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE || e.getBlock().getType() == Material.OAK_PRESSURE_PLATE || e.getBlock().getType() == Material.SPRUCE_PRESSURE_PLATE || e.getBlock().getType() == Material.BIRCH_PRESSURE_PLATE || e.getBlock().getType() == Material.JUNGLE_PRESSURE_PLATE || e.getBlock().getType() == Material.SPRUCE_PRESSURE_PLATE || e.getBlock().getType() == Material.ACACIA_PRESSURE_PLATE || e.getBlock().getType() == Material.DARK_OAK_PRESSURE_PLATE || e.getBlock().getType() == Material.CRIMSON_PRESSURE_PLATE || e.getBlock().getType() == Material.WARPED_PRESSURE_PLATE || e.getBlock().getType() == Material.POLISHED_BLACKSTONE_PRESSURE_PLATE) {
			if(e.getEntity() instanceof Item) {
				
				double x = e.getBlock().getLocation().getX();
				double z = e.getBlock().getLocation().getZ();
				
				if(PlotManager.isCreated(x, z) != null) {
					e.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
		if(e.getAction().equals(Action.PHYSICAL)) {
			if(e.getClickedBlock().getType() == Material.STONE_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.OAK_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.SPRUCE_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.BIRCH_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.JUNGLE_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.SPRUCE_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.ACACIA_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.DARK_OAK_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.CRIMSON_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.WARPED_PRESSURE_PLATE || e.getClickedBlock().getType() == Material.POLISHED_BLACKSTONE_PRESSURE_PLATE) {
				  List<String> members = PlotManager.getMembers(e.getClickedBlock());
 				  String uuid = PlotManager.getBlockOwner(e.getClickedBlock());
 				  
 				  double x = e.getClickedBlock().getLocation().getX();
 				  double z = e.getClickedBlock().getLocation().getZ();
 				  
 				 if(!p.getUniqueId().toString().equals(uuid) && !members.contains(p.getUniqueId().toString())){
						if(CMD_Build.buildmode.contains(p)){
							e.setCancelled(false);
						}else{
							if(PlotManager.isCreated(x, z) != null){
								if(!PlotManager.getFlag(uuid, "interact")) {
									e.setCancelled(true);
									
									String bar = "§cPlot von:§e " + PlotManager.getOwnerName(x, z);
									
									//p.spigot().sendMessage(ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(bar));
								}else {
									e.setCancelled(false);
								}
							}
							
							
							if(RegionManager.isCreated(x, z, e.getClickedBlock().getWorld().getName()) != null){
								 e.setCancelled(true);
								 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
							}
						}
					}else{
						e.setCancelled(false);
					}
			}
		}
		
		
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
        	//if(e.getClickedBlock().getType() == Material.CHEST || e.getClickedBlock().getType() == Material.ACACIA_FENCE_GATE || e.getClickedBlock().getType() == Material.BIRCH_FENCE_GATE  || e.getClickedBlock().getType() == Material.CRIMSON_FENCE_GATE || e.getClickedBlock().getType() == Material.DARK_OAK_FENCE_GATE ||  e.getClickedBlock().getType() == Material.COMPARATOR ||  e.getClickedBlock().getType() == Material.BIRCH_TRAPDOOR ||  e.getClickedBlock().getType() == Material.JUNGLE_TRAPDOOR ||  e.getClickedBlock().getType() == Material.ACACIA_TRAPDOOR ||  e.getClickedBlock().getType() == Material.CRIMSON_TRAPDOOR ||  e.getClickedBlock().getType() == Material.WARPED_TRAPDOOR ||  e.getClickedBlock().getType() == Material.REPEATER ||  e.getClickedBlock().getType() == Material.DISPENSER ||  e.getClickedBlock().getType() == Material.TRIPWIRE_HOOK || e.getClickedBlock().getType() == Material.JUNGLE_FENCE_GATE || e.getClickedBlock().getType() == Material.SPRUCE_FENCE_GATE || e.getClickedBlock().getType() == Material.LEVER || e.getClickedBlock().getType() == Material.OAK_FENCE_GATE || e.getClickedBlock().getType() == Material.DARK_OAK_FENCE_GATE || e.getClickedBlock().getType() == Material.OAK_BUTTON || e.getClickedBlock().getType() == Material.STONE_BUTTON || e.getClickedBlock().getType() == Material.BIRCH_BUTTON || e.getClickedBlock().getType() == Material.ACACIA_BUTTON || e.getClickedBlock().getType() == Material.JUNGLE_BUTTON || e.getClickedBlock().getType() == Material.DARK_OAK_BUTTON || e.getClickedBlock().getType() == Material.CRIMSON_BUTTON || e.getClickedBlock().getType() == Material.OAK_BUTTON || e.getClickedBlock().getType() == Material.WARPED_BUTTON || e.getClickedBlock().getType() == Material.POLISHED_BLACKSTONE_BUTTON ||  e.getClickedBlock().getType() == Material.OAK_DOOR ||  e.getClickedBlock().getType() == Material.WARPED_DOOR ||  e.getClickedBlock().getType() == Material.CRIMSON_DOOR ||  e.getClickedBlock().getType() == Material.DARK_OAK_DOOR ||  e.getClickedBlock().getType() == Material.JUNGLE_DOOR ||  e.getClickedBlock().getType() == Material.SPRUCE_BUTTON || e.getClickedBlock().getType() == Material.SPRUCE_DOOR || e.getClickedBlock().getType() == Material.ACACIA_DOOR || e.getClickedBlock().getType() == Material.JUNGLE_FENCE || e.getClickedBlock().getType() == Material.BIRCH_FENCE || e.getClickedBlock().getType() == Material.SPRUCE_FENCE  || e.getClickedBlock().getType() == Material.IRON_DOOR || e.getClickedBlock().getType() == Material.BIRCH_DOOR || e.getClickedBlock().getType() == Material.DAYLIGHT_DETECTOR || e.getClickedBlock().getType() == Material.OAK_TRAPDOOR || e.getClickedBlock().getType() == Material.DAYLIGHT_DETECTOR || e.getClickedBlock().getType() == Material.FURNACE || e.getClickedBlock().getType() == Material.LEGACY_BURNING_FURNACE|| e.getClickedBlock().getType() == Material.HOPPER || e.getClickedBlock().getType() == Material.HOPPER_MINECART || e.getClickedBlock().getType() == Material.ITEM_FRAME){
        		if(!CMD_Build.buildmode.contains(p)){
        			
        		  List<String> members = PlotManager.getMembers(e.getClickedBlock());
   				  String uuid = PlotManager.getBlockOwner(e.getClickedBlock());
   				  
   				  double x = e.getClickedBlock().getLocation().getX();
   				  double z = e.getClickedBlock().getLocation().getZ();
   					
   						if(!p.getUniqueId().toString().equals(uuid) && !members.contains(p.getUniqueId().toString())){
   							if(CMD_Build.buildmode.contains(p)){
   								e.setCancelled(false);
   							}else{
   								if(PlotManager.isCreated(x, z) != null){
   									if(!PlotManager.getFlag(uuid, "interact")) {
										e.setCancelled(true);
										
										String bar = "§cPlot von:§e " + PlotManager.getOwnerName(x, z);
										
										//p.spigot().sendMessage(ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(bar));
									}else {
										e.setCancelled(false);
									}
   								}
   								double x1 = e.getClickedBlock().getLocation().getBlockX();
								double z1 = e.getClickedBlock().getLocation().getBlockZ();
								if(ShopManager.isCreated(x1, z1) != null) {
									 if(p.getUniqueId().toString().equals(ShopManager.isOwner(x1, z1))) {
										 if(e.getClickedBlock().getType() == Material.CHEST) {
												e.setCancelled(false);
											}
											if(e.getClickedBlock().getType() == Material.SPRUCE_SIGN) {
												e.setCancelled(false);
											}
											if(e.getClickedBlock().getType() == Material.ACACIA_WALL_SIGN) {
												e.setCancelled(false);
											}
											if(e.getClickedBlock().getType() == Material.SPRUCE_WALL_SIGN) {
												e.setCancelled(false);
											}
											if(e.getClickedBlock().getType() == Material.ACACIA_SIGN) {
												e.setCancelled(false);
											}
											if(e.getClickedBlock().getType() == Material.BIRCH_SIGN) {
												e.setCancelled(false);
											}
											if(e.getClickedBlock().getType() == Material.BIRCH_WALL_SIGN) {
												e.setCancelled(false);
											}
											if(e.getClickedBlock().getType() == Material.ITEM_FRAME) {
												e.setCancelled(false);
											}
											if(e.getClickedBlock().getType() != Material.CHEST && e.getClickedBlock().getType() != Material.SPRUCE_SIGN && e.getClickedBlock().getType() != Material.SPRUCE_WALL_SIGN && e.getClickedBlock().getType() != Material.ACACIA_SIGN && e.getClickedBlock().getType() != Material.ACACIA_WALL_SIGN && e.getClickedBlock().getType() != Material.JUNGLE_SIGN && e.getClickedBlock().getType() != Material.JUNGLE_WALL_SIGN && e.getClickedBlock().getType() != Material.BIRCH_SIGN && e.getClickedBlock().getType() != Material.BIRCH_WALL_SIGN && e.getClickedBlock().getType() != Material.ITEM_FRAME) {
												e.setCancelled(true);
											}
									 }else {
										 e.setCancelled(true);
									 }
									 if(p.getWorld().getName().equals("world") || p.getWorld().getName().equals("world_nether") || p.getWorld().getName().equals("Farmwelt")) {
											if(RegionManager.isCreated(x, z, e.getClickedBlock().getWorld().getName()) != null){
												 e.setCancelled(true);
												 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
											}
										}
								}else {
									if(RegionManager.isCreated(x, z, e.getClickedBlock().getWorld().getName()) != null){
										 e.setCancelled(true);
										 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
									}
								}
   							}
   						}else{
   							e.setCancelled(false);
   						}
    				
    		//}else{
    			//e.setCancelled(false);
    		//}
           
         }
        }
	}
	
	@EventHandler
	public void onMobgrief(EntityBreakDoorEvent e){
		String uuid = PlotManager.isCreated(e.getEntity().getLocation().getX(), e.getEntity().getLocation().getZ());
		if(uuid != null) {
			if(!PlotManager.getFlag(uuid, "mob-grief")) {
				e.setCancelled(true);
			}
		}
		if(RegionManager.isCreated(e.getEntity().getLocation().getX(), e.getEntity().getLocation().getZ(), e.getEntity().getWorld().getName()) != null) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void damage(EntityExplodeEvent e){
		String uuid = PlotManager.isCreated(e.getEntity().getLocation().getX(), e.getEntity().getLocation().getZ());
		if(uuid != null) {
			if(!PlotManager.getFlag(uuid, "tnt")) {
				e.setCancelled(true);
			}
		}
		if(RegionManager.isCreated(e.getEntity().getLocation().getX(), e.getEntity().getLocation().getZ(), e.getEntity().getWorld().getName()) != null) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onHangPlace(HangingPlaceEvent e) {
		Player p = e.getPlayer();
		
		if(e.getEntity() instanceof ItemFrame) {
			double x1 = e.getEntity().getLocation().getBlockX();
			double z1 = e.getEntity().getLocation().getBlockZ();
			
			if(!p.getUniqueId().toString().equals(ShopManager.isOwner(x1, z1))) {
				 if(!CMD_Build.buildmode.contains(p)) {
					 e.setCancelled(true);
				 }
			  }else {
				 e.setCancelled(false);
			  } 
		}
	}
	
	@EventHandler
	public void onEmpty(PlayerBucketEmptyEvent e) {
		Player p = e.getPlayer();
		
		double x = p.getLocation().getX();
		double z = p.getLocation().getZ();
		String uuid = PlotManager.isCreated(x, z);
		
		if(uuid != null) {
			if(!uuid.equals(p.getUniqueId().toString()) && !PlotManager.isMember(x, z).contains(p.getUniqueId().toString())) {
				if(CMD_Build.buildmode.contains(p)) {
					e.setCancelled(false);
				}else {
					if(!PlotManager.getFlag(uuid, "block-place")) {
						e.setCancelled(true);
					}
				}
				
			}
		}
		
		if(ShopManager.isCreated(x, z) != null) {
			 e.setCancelled(true);
		}else {
			if(!CMD_Build.buildmode.contains(p)) {
				if(RegionManager.isCreated(x, z, p.getWorld().getName()) != null){
					 e.setCancelled(true);
					 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
				}
			}
		}
		
		
	}
	

	
	
	@EventHandler
	public void onFill(PlayerBucketFillEvent e) {
        Player p = e.getPlayer();
		
		double x = p.getLocation().getX();
		double z = p.getLocation().getZ();
		String uuid = PlotManager.isCreated(x, z);
		
		if(uuid != null) {
			if(!uuid.equals(p.getUniqueId().toString()) && !PlotManager.isMember(x, z).contains(p.getUniqueId().toString())) {
				if(CMD_Build.buildmode.contains(p)) {
					e.setCancelled(false);
				}else {
					if(!PlotManager.getFlag(uuid, "block-break")) {
						e.setCancelled(true);
					}
				}
			}
		}
		
		if(ShopManager.isCreated(x, z) != null) {
			 e.setCancelled(true);
		}else {
			if(RegionManager.isCreated(x, z, p.getWorld().getName()) != null){
				 e.setCancelled(true);
				 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
			}
		}
		
		
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        
        if(!CMD_Build.buildmode.contains(p)){
			
        	if(p.getWorld().getName().equals("world")){
        		
        		
        		  List<String> members = PlotManager.getMembers(e.getBlock());
				  String uuid = PlotManager.getBlockOwner(e.getBlock());
				  
				  double x = e.getBlock().getLocation().getX();
				  double z = e.getBlock().getLocation().getZ();
				  
				  double x1 = e.getBlock().getLocation().getBlockX();
					double z1 = e.getBlock().getLocation().getBlockZ();
					
						if(!p.getUniqueId().toString().equals(uuid) && !members.contains(p.getUniqueId().toString())){
							if(Main.Trusted.contains(p)){
								e.setCancelled(false);
							}else{
								if(PlotManager.isCreated(x, z) != null){
									if(!PlotManager.getFlag(uuid, "block-place")) {
										e.setCancelled(true);
	                                    String bar = "§cPlot von:§e " + PlotManager.getOwnerName(x, z);
										
										//p.spigot().sendMessage(ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(bar));
									}
								}
								if(ShopManager.isCreated(x1, z1) != null) {
									 if(p.getUniqueId().toString().equals(ShopManager.isOwner(x, z))) {
										 if(e.getBlock().getType() == Material.CHEST) {
												e.setCancelled(false);
											}
											if(e.getBlock().getType() == Material.SPRUCE_SIGN) {
												e.setCancelled(false);
											}
											if(e.getBlock().getType() == Material.ACACIA_WALL_SIGN) {
												e.setCancelled(false);
											}
											if(e.getBlock().getType() == Material.SPRUCE_WALL_SIGN) {
												e.setCancelled(false);
											}
											if(e.getBlock().getType() == Material.ACACIA_SIGN) {
												e.setCancelled(false);
											}
											if(e.getBlock().getType() == Material.BIRCH_SIGN) {
												e.setCancelled(false);
											}
											if(e.getBlock().getType() == Material.BIRCH_WALL_SIGN) {
												e.setCancelled(false);
											}
											if(e.getBlock().getType() == Material.ITEM_FRAME) {
												e.setCancelled(false);
											}
											if(e.getBlock().getType() != Material.CHEST && e.getBlock().getType() != Material.SPRUCE_SIGN && e.getBlock().getType() != Material.SPRUCE_WALL_SIGN && e.getBlock().getType() != Material.ACACIA_SIGN && e.getBlock().getType() != Material.ACACIA_WALL_SIGN && e.getBlock().getType() != Material.JUNGLE_SIGN && e.getBlock().getType() != Material.JUNGLE_WALL_SIGN && e.getBlock().getType() != Material.BIRCH_SIGN && e.getBlock().getType() != Material.BIRCH_WALL_SIGN && e.getBlock().getType() != Material.ITEM_FRAME) {
												e.setCancelled(true);
											}
									 }else {
										 e.setCancelled(true);
									 }
									 if(RegionManager.isCreated(x, z, e.getBlock().getWorld().getName()) != null){
										 e.setCancelled(true);
										 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
									 }
								}else {
									if(RegionManager.isCreated(x, z, e.getBlock().getWorld().getName()) != null){
										 e.setCancelled(true);
										 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
									}
								}
							}
						}else{
							e.setCancelled(false);
						}
			}else {
				double x = e.getBlock().getLocation().getX();
				double z = e.getBlock().getLocation().getZ();
				
				/*if(e.getBlock().getType() == Material.DIAMOND_ORE) {
					Main.blocks.add(e.getBlock());
					
				}
				if(e.getBlock().getType() == Material.LAPIS_ORE) {
					Main.blocks.add(e.getBlock());
				}
				if(e.getBlock().getType() == Material.JUNGLE_LOG) {
					Main.blocks.add(e.getBlock());
				}*/
				if(RegionManager.isCreated(x, z, e.getBlock().getWorld().getName()) != null){
					 e.setCancelled(true);
					 p.sendMessage(Main.pr + "§cDu darfst diese Region nicht verändern!");
				}
			}
			
	}else{
		e.setCancelled(false);
	}
	}

}
