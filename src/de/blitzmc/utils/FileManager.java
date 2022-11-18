package de.blitzmc.utils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {

	private File f;
	private YamlConfiguration c;
	
	public FileManager(String Path, String name){
		f = new File(Path, name);
		c = YamlConfiguration.loadConfiguration(f);
	}
	
	public FileManager setValue(String Path, Object Value){
		c.set(Path, Value);
		save();
		return this;
	}
	
	public boolean exist(){
		return f.exists();
	}
	
	public boolean contains(String Path){
		return c.contains(Path);
	}
	
	public void delete(){
		f.delete();
		save();
	}
	
	public Object getObject(String path){
		return c.get(path);
	}
	
	public int getInt(String Path){
		return c.getInt(Path);
	}
	
	public double getDouble(String Path){
		return c.getDouble(Path);
	}
	
	public long getLong(String Path){
		return c.getLong(Path);
	}
	
	public String getString(String Path){
		return c.getString(Path);
	}
	
	public boolean getBoolean(String Path) {
		return c.getBoolean(Path);
	}
	
	public Set<String> getKeys(boolean deep){
		return c.getKeys(deep);
	}
	
	public ConfigurationSection getConfigSection(String section){
		return c.getConfigurationSection(section);
	}
	
	public FileManager save(){
		try {
			c.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
}
