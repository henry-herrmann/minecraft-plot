package de.blitzmc.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.blitzmc.Main;



public class TPSpawn {

	public static void tpSpawn(Player p) {
		try{
		Location loc = p.getLocation();
		loc.setX(SetSpawn.locscfg.getDouble("Spawn.X"));
		loc.setY(SetSpawn.locscfg.getDouble("Spawn.Y"));
		loc.setZ(SetSpawn.locscfg.getDouble("Spawn.Z"));
		loc.setYaw((float)SetSpawn.locscfg.getDouble("Spawn.Yaw"));
		loc.setPitch((float)SetSpawn.locscfg.getDouble("Spawn.Pitch"));
		loc.setWorld(Bukkit.getWorld(SetSpawn.locscfg.getString("Spawn.World")));
		p.teleport(loc);
		p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
		} catch (Exception e){
			p.sendMessage(Main.pr + " §cDer Spawn wurde noch nicht gesetzt!");
		}
	}
	
	public static void tpFarmwelt(Player p){
		Location loc = p.getLocation();
		loc.setX(SetSpawn.locscfg.getDouble("Farmwelt.X"));
		loc.setY(SetSpawn.locscfg.getDouble("Farmwelt.Y"));
		loc.setZ(SetSpawn.locscfg.getDouble("Farmwelt.Z"));
		loc.setPitch((float)SetSpawn.locscfg.getDouble("Farmwelt.Pitch"));
		loc.setYaw((float) SetSpawn.locscfg.getDouble("Farmwelt.Yaw"));
		loc.setWorld(Bukkit.getWorld(SetSpawn.locscfg.getString("Farmwelt.World")));
		p.teleport(loc);
		p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
	}
	
	public static void tpEnd(Player p){
		Location loc = p.getLocation();
		loc.setX(SetSpawn.locscfg.getDouble("End.X"));
		loc.setY(SetSpawn.locscfg.getDouble("End.Y"));
		loc.setZ(SetSpawn.locscfg.getDouble("End.Z"));
		loc.setPitch((float)SetSpawn.locscfg.getDouble("End.Pitch"));
		loc.setYaw((float) SetSpawn.locscfg.getDouble("End.Yaw"));
		loc.setWorld(Bukkit.getWorld(SetSpawn.locscfg.getString("End.World")));
		p.teleport(loc);
		p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
	}
	
	public static void tpNether(Player p){
		Location loc = p.getLocation();
		loc.setX(SetSpawn.locscfg.getDouble("Nether.X"));
		loc.setY(SetSpawn.locscfg.getDouble("Nether.Y"));
		loc.setZ(SetSpawn.locscfg.getDouble("Nether.Z"));
		loc.setPitch((float)SetSpawn.locscfg.getDouble("Nether.Pitch"));
		loc.setYaw((float) SetSpawn.locscfg.getDouble("Nether.Yaw"));
		loc.setWorld(Bukkit.getWorld(SetSpawn.locscfg.getString("Nether.World")));
		p.teleport(loc);
		p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
	}
	public static void tpMarkt(Player p){
		Location loc = p.getLocation();
		loc.setX(SetSpawn.locscfg.getDouble("Markt.X"));
		loc.setY(SetSpawn.locscfg.getDouble("Markt.Y"));
		loc.setZ(SetSpawn.locscfg.getDouble("Markt.Z"));
		loc.setPitch((float)SetSpawn.locscfg.getDouble("Markt.Pitch"));
		loc.setYaw((float) SetSpawn.locscfg.getDouble("Markt.Yaw"));
		loc.setWorld(Bukkit.getWorld(SetSpawn.locscfg.getString("Markt.World")));
		p.teleport(loc);
		p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
	}
	
	public static Location getSpawn() {
		Location loc = new Location(Bukkit.getWorld(SetSpawn.locscfg.getString("Spawn.World")), SetSpawn.locscfg.getDouble("Spawn.X"), SetSpawn.locscfg.getDouble("Spawn.Y"), SetSpawn.locscfg.getDouble("Spawn.Z"), (float)SetSpawn.locscfg.getDouble("Spawn.Yaw"), (float)SetSpawn.locscfg.getDouble("Spawn.Pitch"));
		return loc;
	}
}
