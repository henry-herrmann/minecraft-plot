package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class BanManager {

	private FileManager fb;
	@SuppressWarnings("unused")
	private UUID uuid;
	
	public BanManager(UUID uuid) {
		fb = new FileManager("plugins/GS/Playerdata", uuid.toString() + ".yml");
		fb.save();
		this.uuid = uuid;
	}
	
	public boolean exist() {
		return fb.exist();
	}
	
	public void setTempbanned(String from, String reason, long time) {
		fb.setValue("tempban.istempbanned", true);
		fb.setValue("tempban.from", from);
		fb.setValue("tempban.reason", reason);
		fb.setValue("tempban.duration", time + System.currentTimeMillis());
		fb.setValue("tempban.timestamp", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
		fb.save();
		
		
	}
	
	
	public void setPermBanned(String reason) {
		fb.setValue("permban.isPermBanned", true);
		fb.setValue("permban.reason", reason);
		fb.save();
	}
	
	public boolean isPermBanned() {
		return fb.getBoolean("permban.isPermBanned");
	}
	
	public void unPermBan() {
		fb.setValue("permban", null);
		fb.save();
	}
	
	public String getPermbanReason() {
		return fb.getString("permban.reason");
	}
	
	public boolean isTempbanned() {
		return fb.getBoolean("tempban.istempbanned");
	}
	
	public void setunTempbanned() {
		fb.setValue("tempban", null);
		fb.save();
	}
	
	public String getTempbanReason() {
		return fb.getString("tempban.reason");
	}
	
	public String getTempbanFrom() {
		return fb.getString("tempban.from");
	}
	
	public long getTempbanMilliseconds() {
		return fb.getLong("tempban.duration");
	}
	
	public String getTempbanTimestamp() {
		return fb.getString("tempban.timestamp");
	}
	
	@SuppressWarnings("deprecation")
	public static UUID getUUIDFromName(String playername) {
		Player p = Bukkit.getPlayer(playername);
		
		if(p != null) {
			return p.getUniqueId();
		}else {
			OfflinePlayer op = Bukkit.getOfflinePlayer(playername);
			
			if(op != null) {
				return op.getUniqueId();
			}
		}
		return null;
	}
}
