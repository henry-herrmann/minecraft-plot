/*     */ package de.blitzmc.trade;
/*     */ 
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.plugin.Plugin;

import de.blitzmc.Main;
/*     */ 
/*     */ public class Lang
/*     */ {
/*     */   String name;
/*     */   Plugin cc;
/*  20 */   private FileConfiguration customConfig = null;
/*  21 */   private File customConfigFile = null;
/*     */   
/*     */   public Lang(Plugin cc, String name)
/*     */   {
/*  25 */     this.name = name;
/*  26 */     this.cc = cc;
/*  27 */     saveDefaultConfig();
/*     */   }
/*     */   
/*     */   public String chatPrefix()
/*     */   {
/*  32 */     return ChatColor.translateAlternateColorCodes('&', getCustomConfig().getString("lang.chat.prefix"));
/*     */   }
/*     */   
/*     */   public String LangCongfigReplace(String source, String before, Object after, Boolean prefix)
/*     */   {
/*  37 */     String s = "";
/*  38 */     if (prefix.booleanValue())
/*  39 */       s = chatPrefix();
/*  40 */     return s + colorText(getCustomConfig().getString(source)).replace(before, String.valueOf(after));
/*     */   }
/*     */   
/*     */   public String LangCongfig(String source, Boolean prefix)
/*     */   {
/*  45 */     String s = "";
/*  46 */     if (prefix.booleanValue())
/*  47 */       s = chatPrefix();
/*  48 */     return s + colorText(getCustomConfig().getString(source));
/*     */   }
/*     */   
/*     */   public String LangCongfigRaw(String source, Boolean prefix) {
/*  52 */     String s = "";
/*  53 */     if (prefix.booleanValue())
/*  54 */       s = chatPrefix();
/*  55 */     return s + source;
/*     */   }
/*     */   
/*     */   public String colorText(String src) {
/*  59 */     return ChatColor.translateAlternateColorCodes('&', src);
/*     */   }
/*     */   
/*     */   public void reloadCustomConfig() {
/*  63 */     if (this.customConfigFile == null) {
/*  64 */       this.customConfigFile = new File(Main.getInstance().getDataFolder(), this.name);
/*     */     }
/*  66 */     this.customConfig = YamlConfiguration.loadConfiguration(this.customConfigFile);
/*     */     
/*     */ 
/*  69 */     Reader defConfigStream = null;
/*     */     try {
/*  71 */       defConfigStream = new InputStreamReader(Main.getInstance().getResource(this.name), "UTF8");
/*     */     } catch (UnsupportedEncodingException e) {
/*  73 */       e.printStackTrace();
/*     */     }
/*  75 */     if (defConfigStream != null) {
/*  76 */       YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/*  77 */       this.customConfig.setDefaults(defConfig);
/*     */     }
/*     */   }
/*     */   
/*     */   public FileConfiguration getCustomConfig() {
/*  82 */     if (this.customConfig == null) {
/*  83 */       reloadCustomConfig();
/*     */     }
/*  85 */     return this.customConfig;
/*     */   }
/*     */   
/*  88 */   public void saveCustomConfig() { if ((this.customConfig == null) || (this.customConfigFile == null)) {
/*  89 */       return;
/*     */     }
/*     */     try {
/*  92 */       getCustomConfig().save(this.customConfigFile);
/*     */     } catch (IOException ex) {
/*  94 */       Main.getInstance().getLogger().log(Level.SEVERE, "Could not save config to " + this.customConfigFile, ex);
/*     */     }
/*     */   }
/*     */   
/*  98 */   public void saveDefaultConfig() { if (this.customConfigFile == null) {
/*  99 */       this.customConfigFile = new File(Main.getInstance().getDataFolder(), this.name);
/*     */     }
/* 101 */     if (!this.customConfigFile.exists()) {
/* 102 */       Main.getInstance().saveResource(this.name, false);
/*     */     }
/*     */   }
/*     */ }


