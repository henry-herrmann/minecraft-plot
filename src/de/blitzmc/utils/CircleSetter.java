package de.blitzmc.utils;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CircleSetter {

	@SuppressWarnings("deprecation")
	public static void makeCircle(Location loc, Integer r, int amount) {
        double x;
        double y = loc.getBlockY();
        double z;
        double angle = 2*Math.PI/amount;
        for (int i = 0; i < amount; i++) {
            x = loc.getX() + r * Math.cos(i*angle);
            z = loc.getZ() + r * Math.sin(i*angle);
                       Location loc1 = new Location(loc.getWorld(), x, y, z);
        for(Player all : Bukkit.getOnlinePlayers()){
        	all.playEffect(loc1, Effect.SMOKE, 1);
        }
    }
	}
}
