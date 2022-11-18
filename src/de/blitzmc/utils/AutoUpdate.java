package de.blitzmc.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


 
public class AutoUpdate {
 
        private Plugin plugin;
        private String urlVersionCheck = "http://appstudios.eu/Updater/version.txt";
        private String urlDownload = "http://appstudios.eu/Updater/GS.jar";
        private String currentVersion;
 
       
 
        public AutoUpdate(Plugin plugin) {
                this.plugin = plugin;
                this.currentVersion = plugin.getDescription().getVersion();
                start();
        }
 
        private void start() {
        	
            Thread th = new Thread((Runnable) new BukkitRunnable() {
                public void run() {
                	String v = null;
                    try {
                            v = getText(urlVersionCheck);
                            //plugin.getLogger().info("Online version:" + v);
                    } catch (Exception e) {
                            //plugin.getLogger().info("Failed to get Version!!!");
                            e.printStackTrace();
                    }
                    if (!v.equalsIgnoreCase(currentVersion)) {
                    	startDownload();
                    	Bukkit.shutdown();
                    }
                }
        }.runTaskTimerAsynchronously(plugin, 0L, 9600L));
        th.setName("WorldGuard Thread");
        th.start();
        }
 
        private boolean startDownload() {
                try {
                        URL download = new URL(this.urlDownload);
                        BufferedInputStream in = null;
                        FileOutputStream fout = null;
                        try {
                                //plugin.getLogger().info("Trying to download from: " + download.toString());
                                in = new BufferedInputStream(download.openStream());
                                fout = new FileOutputStream("plugins" + System.getProperty("file.separator") + plugin.getName() + ".jar");
 
                                final byte data[] = new byte[1024];
                                int count;
                                while ((count = in.read(data, 0, 1024)) != -1) {
                                        fout.write(data, 0, count);
                                }
                                sendMSG();
                        }catch (Exception e){
                                //plugin.getLogger().info("Failed to Download new Version!!!");
                                //plugin.getLogger().info("---------- Stack Trace ----------");
                                e.printStackTrace();
                                //plugin.getLogger().info("---------- Stack Trace ----------");
                                return false;
                        } finally {
                                if (in != null) {
                                        in.close();
                                }
                                if (fout != null) {
                                        fout.close();
                                }
                        }
                        //plugin.getLogger().info("Succesfully downloaded update: " + download.getFile().toString());
                        sendMSG();
                        

                        
                        return true;
                } catch (IOException e) {
                        //plugin.getLogger().info("Failed to Download new Version!!!");
                        //plugin.getLogger().info("---------- Stack Trace ----------");
                        e.printStackTrace();
                        //plugin.getLogger().info("---------- Stack Trace ----------");
                        return false;
                }
                
                
        }
 
        private String getText(String url) throws Exception {
                URL website = new URL(url);
                URLConnection connection = website.openConnection();
                connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                }
                in.close();
                return response.toString();
        }
        
        public static  void sendMSG() {
    		
    		
    		try {
    			URL url = new URL("https://google.com");
    			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    			con.addRequestProperty("Content-Type", "application/json");
    			con.addRequestProperty("User-Agent", "Spigot");
    			con.setDoOutput(true);
    			con.setRequestMethod("POST");
    			
    			String json = "{\"content\":\"@everyone Das Plugin wurde geupdated!\"}";
    		    
    			
    			OutputStream stream = con.getOutputStream();
    			
    			stream.write(json.getBytes());
    			
    			stream.flush();
    			stream.close();
    			
    			con.getInputStream().close();
    			con.disconnect();
    			
    			
    			
    			
    	        
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
}
