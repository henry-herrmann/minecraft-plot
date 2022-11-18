package de.blitzmc.listeners;


import org.bukkit.Bukkit;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import de.blitzmc.cmds.CMD_Build;
import de.blitzmc.utils.PlotManager;
import de.blitzmc.utils.RegionManager;
import de.blitzmc.utils.ShopManager;


public class MobListener implements Listener{

	
	@EventHandler
    public void damager(EntityDamageByEntityEvent event) throws EventException {
		  double x = event.getEntity().getLocation().getX();
		  double z = event.getEntity().getLocation().getZ();
		  
		  double x1 = event.getEntity().getLocation().getBlockX();
		  double z1 = event.getEntity().getLocation().getBlockZ();
		  
		  double xe = event.getDamager().getLocation().getX();
		  double ze = event.getDamager().getLocation().getZ();
		  
		  try {
			  
			  if(PlotManager.isCreated(x1, z1) != null) {
				  if(event.getDamager() instanceof Player) {
					  if(event.getEntity() instanceof Player) {
						  Player p = (Player) event.getDamager();
						  String uuid = PlotManager.isCreated(x1, z1);
						  if(uuid.equals(p.getUniqueId().toString()) || PlotManager.isMember(x1, z1).contains(p.getUniqueId().toString())) {
							  event.setCancelled(false); 
						  }else {
							 if(CMD_Build.buildmode.contains(p)) {
								 event.setCancelled(false);
							 }else {
								 
								 if(!PlotManager.getFlag(uuid, "pvp")) {
									  event.setCancelled(true);      
								 } 
							 }
						  }
					  }
					  if(event.getEntity() instanceof Monster) {
						  event.setCancelled(false);
					  }
					  if(event.getEntity() instanceof Animals) {
						  
						  
						  Player p = (Player) event.getDamager();
						  if(PlotManager.isCreated(xe, ze).equals(p.getUniqueId().toString()) || PlotManager.isMember(xe, ze).contains(p.getUniqueId().toString())) {
							  event.setCancelled(false);      
						  }else {
							  if(!CMD_Build.buildmode.contains(p)) {
								  event.setCancelled(true);
							  }
						  }
					  }
					  
				  }
				  if(event.getDamager() instanceof Mob) {
					  event.setCancelled(true);
				  }
			  }
			  if(event.getDamager() instanceof Mob) {
				  if(event.getEntity() instanceof Player) {
					  Player p = (Player) event.getEntity();
					  if(!PlotManager.isCreated(x, z).equals(p.getUniqueId().toString()) && !PlotManager.isMember(x, z).contains(p.getUniqueId().toString())) {
						  event.setCancelled(false);
						  
					  }else {
						  event.setCancelled(true);
					  }
					  
				  }
			  }
			  
			  if(event.getDamager() instanceof Enderman) {
				  if(RegionManager.isCreated(x, z, event.getEntity().getWorld().getName()) != null) {
					  event.setCancelled(true);
				  }
			  }
			  if(event.getDamager() instanceof Phantom) {
				  if(event.getEntity() instanceof Player) {
					  Player p = (Player) event.getEntity();
					  if(!PlotManager.isCreated(x, z).equals(p.getUniqueId().toString()) && !PlotManager.isMember(x, z).contains(p.getUniqueId().toString())) {
						  event.setCancelled(false);
						  
					  }else {
						  event.setCancelled(true);
					  }
				  }
			  }
			  if(event.getDamager() instanceof Arrow) {
				  Arrow a = (Arrow) event.getDamager();
				  
				  if(a.getShooter() instanceof Player) {
					  if(event.getEntity() instanceof Player) {
						  Player p = (Player) a.getShooter();
						  
						  if(!CMD_Build.buildmode.contains(p)) {
							  String uuid = PlotManager.isCreated(xe, ze);
							  
							  if(!uuid.equals(p.getUniqueId().toString()) && !PlotManager.isMember(xe, ze).contains(p.getUniqueId().toString())) {
								  if(!PlotManager.getFlag(uuid, "pvp")) {
									  event.setCancelled(true);
								  }
							  
							  } else if(RegionManager.isCreated(x, z, p.getWorld().getName()) != null) {
								  event.setCancelled(true);
							   
							  }else {
								   
								  event.setCancelled(false);
							  }
						  }
							  
						  
						  
					  }
					  if(event.getEntity() instanceof Animals) {
						  Player p = (Player) a.getShooter();
						  
						  if(!CMD_Build.buildmode.contains(p)) {
							  String uuid = PlotManager.isCreated(xe, ze);
							  
							  if(!uuid.equals(p.getUniqueId().toString()) && !PlotManager.isMember(xe, ze).contains(p.getUniqueId().toString())) {
								  event.setCancelled(true);
							  
							  }
						  }  
					  }
				  }
				  
			  }
			  
			  
			  if(ShopManager.isCreated(x1, z1) != null) {
				  if(event.getDamager() instanceof Player) {
					  Player p = (Player) event.getDamager();
					  if(event.getEntity() instanceof ItemFrame) {
						  if(!p.getUniqueId().toString().equals(ShopManager.isOwner(x1, z1))) {
							 if(!CMD_Build.buildmode.contains(p)) {
								 event.setCancelled(true);
							 }
						  }else {
							 event.setCancelled(false);
						  } 
					  }
				  }
		      }
			  
			  
		  }catch(NullPointerException e) {
			  
		  }
    }
	
	
	@EventHandler
	  public void entityDamageEvent(EntityDamageEvent event){
	    if(event.getEntity().getLocation().getWorld().getName().equals("world")) {
	    	double x = event.getEntity().getLocation().getX();
  		    double z = event.getEntity().getLocation().getZ();	
  		    if(event.getEntityType() == EntityType.PLAYER){
  			   Player p = (Player) event.getEntity();
  			   /*String uuid = PlotManager.isCreated(x, z);
	          
    		
			     if(uuid != null){
    		       if(!uuid.equals(p.getUniqueId().toString()) && !PlotManager.isMember(x, z).contains(p.getUniqueId().toString())) {
    		    	   event.setCancelled(false);
    		       }else {
    		    	   Bukkit.getConsoleSender().sendMessage("This");
    		    	   event.setCancelled(true);
    		       }
    	 	     }*/
    	 	     if(RegionManager.isCreated(x, z, p.getWorld().getName()) != null){
    	 			  event.setCancelled(true);
    	 	     }
    		  
    		  
    		  
  	        }
	    	
	    	if(event.getCause() == DamageCause.FIRE) {
	    		System.out.println("Yes");
	    		if(event.getEntity() instanceof Pig) {
		    		 event.setCancelled(true);  
		 		    }
		       if(event.getEntity() instanceof Sheep) {
		    		  event.setCancelled(true);  
		 	   }
		       if(event.getEntity() instanceof Cow) {
		    		 event.setCancelled(true);  
		 	   }
		       if(event.getEntity() instanceof Villager) {
		    		 event.setCancelled(true);  
		 	   }
		       if(event.getEntity() instanceof Chicken) {
		    		 event.setCancelled(true);  
		 	   }
		       if(event.getEntity() instanceof Rabbit) {
       	          event.setCancelled(true);  
		 	   }
		       if(event.getEntity() instanceof Llama) {
		    		 event.setCancelled(true);  
		 	   }
		       if(event.getEntity() instanceof Ocelot) {
		    		 event.setCancelled(true);  
		 	   }
		       if(event.getEntity() instanceof Horse) {
		    		 event.setCancelled(true);  
		 	   }
	    	} 	
	    }
	  }
	
	@EventHandler
	public void onSpawn(CreatureSpawnEvent e) {
		
		if(e.getEntity() instanceof Monster) {
			double x = e.getLocation().getX();
	  		double z = e.getLocation().getZ();	
	  		
	  		String uuid = PlotManager.isCreated(x, z);
	  		
	  		if(uuid != null) {
	  			if(!PlotManager.getFlag(uuid, "mob-spawn")) {
	  				e.setCancelled(true);
	  			}
	  		}
		}
	}
	
}
