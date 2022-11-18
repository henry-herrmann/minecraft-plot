package de.blitzmc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.blitzmc.Nick.*;
import de.blitzmc.chestshop.ChestShopListener;
import de.blitzmc.cmds.*;
import de.blitzmc.listeners.*;
import de.blitzmc.trade.CommandTrade;
import de.blitzmc.trade.Lang;
import de.blitzmc.trade.TradeUtils;
import de.blitzmc.trade.WannaTradeListener;
import de.blitzmc.utils.*;


public class Main extends JavaPlugin{

	public static String pr = "§8 §6Plot §8× §a";
	
	public static String plot_not_exists = "§8» §6Plot §8× §cDas angebene Plot ist nicht vorhanden!";
	
	public static String noperm = "§8» §6Plot §8× §7Unbekannter Befehl!";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<Player> Bergarbeiter = new ArrayList();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<Player> Holzfäller = new ArrayList();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<Player> Jäger = new ArrayList();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<Player> Auftragskiller = new ArrayList();
	
	public static ArrayList<Player> Trusted = new ArrayList<Player>();
	
	
	public static HashMap<Player, Player> tpa = new HashMap<Player, Player>();
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static HashMap<Player, Player> msgs = new HashMap<Player, Player>();
	
	public static ArrayList<Player> inputed = new ArrayList<>();
	
	public static HashMap<String, Player> message = new HashMap<>();
	
	public static HashMap<Player, Player> requestTrade = new HashMap<>();
	
	public static ArrayList<String> names = new ArrayList<>();
	
	public static ArrayList<String> plots = new ArrayList<>();
	
	public static ArrayList<Player> member = new ArrayList<>();
	public static ArrayList<Player> delmember = new ArrayList<>();
	
	public static ArrayList<Player> member2 = new ArrayList<>();
	public static ArrayList<Player> delmember2 = new ArrayList<>();
	
	public static ArrayList<Player> member3 = new ArrayList<>();
	public static ArrayList<Player> delmember3 = new ArrayList<>();
	
	public static ArrayList<Player> entered = new ArrayList<>();
	public static ArrayList<Entity> combatlog = new ArrayList<>();
	public static ArrayList<Player> chestinfo = new ArrayList<>();
	
	public static int xp;
	public static int xp2;
	public static int sched2;
	public static int sched;
	
	public static Main instance;
	
	public static World world;
	
	public static int rst;
	
	public static Field f;
	
	private static Lang lang;
	private static TradeUtils tradeUtils;
	
	public static List<String> plugins = new ArrayList<>();
	public static  List<String> version = new ArrayList<>();
	public static  List<String> about = new ArrayList<>();
	public static  List<String> question = new ArrayList<>();
	public static  List<String> me = new ArrayList<>();
	public static  List<String> kill = new ArrayList<>();
	public static  List<String> plugman = new ArrayList<>();
	


	@SuppressWarnings("static-access")
	public void onEnable(){
		
		instance = this;
		
		
		tradeUtils = new TradeUtils();
		lang = new Lang(this, "lang.yml");
	    
		
	    
		
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§7Das §eGS Plugin §7 wurde §aaktiviert");
		Bukkit.getConsoleSender().sendMessage("§7Version§8: §a1.1");
		Bukkit.getConsoleSender().sendMessage("§7Author§8: §eHenry Herrmann");
		Bukkit.getConsoleSender().sendMessage("");
		
		
		getCommand("plot").setExecutor(new CMD_Plots());
		getCommand("build").setExecutor(new CMD_Build());
		//getCommand("vanish").setExecutor(new CMD_Vanish());
		//getCommand("v").setExecutor(new CMD_Vanish());
		//getCommand("money").setExecutor(new CMD_Money());
		//getCommand("dollar").setExecutor(new CMD_Money());
		//getCommand("job").setExecutor(new CMD_jobcenter());
		//getCommand("fly").setExecutor(new CMD_Fly());
		//getCommand("invsee").setExecutor(new CMD_InvSee());
		//getCommand("tp").setExecutor(new CMD_Tp());
		//getCommand("tpa").setExecutor(new CMD_Tpa());
		//getCommand("tphere").setExecutor(new CMD_Tphere());
		//getCommand("wb").setExecutor(new CMD_WB()); 
		//getCommand("ec").setExecutor(new CMD_WB());
		//getCommand("home").setExecutor(new CMD_Home());
		//getCommand("set").setExecutor(new CMD_Set());
		//getCommand("spawn").setExecutor(new CMD_Spawn());
		//getCommand("crash").setExecutor(new CMD_crash());
		//getCommand("rg").setExecutor(new CMD_Region());
		//getCommand("visit").setExecutor(new CMD_Visit());
		//getCommand("gm").setExecutor(new CMD_GM());
		//getCommand("warp").setExecutor(new CMD_Warp());
		getCommand("wartung").setExecutor(new CMD_wartung());
		//getCommand("unban").setExecutor(new CMD_unban());
		//getCommand("tempban").setExecutor(new CMD_tempban());
		//getCommand("permban").setExecutor(new CMD_permban());
		//getCommand("tutorial").setExecutor(new CMD_tutorial());
		//getCommand("world").setExecutor(new CMD_world());
		//getCommand("tpaaccept").setExecutor(new CMD_Tpa());
		//getCommand("msg").setExecutor(new CMD_Msg());
		//getCommand("pl").setExecutor(new CMD_pl());
		//getCommand("plugins").setExecutor(new CMD_pl());
		//getCommand("?").setExecutor(new CMD_pl());
		//getCommand("ptime").setExecutor(new CMD_ptime());
		//getCommand("shop").setExecutor(new CMD_shop());
		//getCommand("me").setExecutor(new CMD_Build());
		getCommand("sperren").setExecutor(new CMD_sperren());
		//getCommand("trade").setExecutor(new CommandTrade(this));
		//getCommand("event").setExecutor(new CMD_event());
		//getCommand("banlist").setExecutor(new CMD_Banlist());
		//getCommand("regeln").setExecutor(new CMD_regeln());
		
		//Home.createFile();
		CMD_wartung.createFile();
		//WorldManager.importWorlds();
		//count();
		//setDollar();
		registerEvents();
		//expire();
		addWords();
	}
	public void onDisable(){
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§7Das §eGS Plugin §7 wurde §cdeaktiviert");
		Bukkit.getConsoleSender().sendMessage("§7Version§8: §c1.1");
		Bukkit.getConsoleSender().sendMessage("§7Author§8: §eHenry Herrmann");
		Bukkit.getConsoleSender().sendMessage("");
		
	}
	
	public void registerEvents(){
		PluginManager p = Bukkit.getPluginManager();
		p.registerEvents(new ProtectionListener(), this);
		p.registerEvents(new JoinListener(this), this);
		p.registerEvents(new MobListener(), this);
		p.registerEvents(new KillListener(), this);
		p.registerEvents(new InventoryClick(), this);
		p.registerEvents(new QuitListener(), this);
		p.registerEvents(new ChatListener(), this);
		p.registerEvents(new MoveListener(), this);
		p.registerEvents(new RespawnListener(), this);
		p.registerEvents(new InteractEvent(), this);
		p.registerEvents(new WarpListener(), this);
		p.registerEvents(new LoginListener(), this);
		p.registerEvents(new RepeaterListener(), this);
		p.registerEvents(new RedstoneListener(), this);
		p.registerEvents(new ChestShopListener(), this);
		p.registerEvents(new SignChange(), this);
		p.registerEvents(new EnderPortalEvent(), this);
		p.registerEvents(new WannaTradeListener(this), this);
		p.registerEvents(new BedEvent(), this);
	}
	public Lang getLang() {
		return lang;
	}
	public static Main getInstance(){
		return instance;
	}
	public static TradeUtils getUtils() {
		return tradeUtils;
	}
	
	public static Main getPlugin() {
		return instance;
	}
	
	@SuppressWarnings("static-access")
	public void addWords() {
		this.plugins.add("pl");
	    this.plugins.add("bukkit:pl");
	    this.plugins.add("plugins");
	    this.plugins.add("bukkit:plugins");
	    this.plugins.add("ver");
	    this.plugins.add("bukkit:ver");
	    this.plugins.add("version");
	    this.plugins.add("bukkit:version");
	    this.plugins.add("about");
	    this.plugins.add("bukkit:about");
	    this.plugins.add("?");
	    this.plugins.add("bukkit:?");
	    this.plugins.add("me");
	    this.plugins.add("bukkit:me");
	    this.plugins.add("kill");
	    this.plugins.add("bukkit:kill");
	    this.plugins.add("plugman");
	    this.plugins.add("plugman:plugman");
	    this.plugins.add("icanhasbukkit");
	    this.plugins.add("bukkit:help");
	    this.plugins.add("demote");
	}
	
	public static int getGroupID(Player p){
		int id = 0;
	    if (p.hasPermission("system.prefix.admin")) {
	      id = 1;
	    } else if (p.hasPermission("system.prefix.dev")) {
	      id = 2;
	    } else if (p.hasPermission("system.prefix.mod")) {
	      id = 3;
	    } else if (p.hasPermission("system.prefix.sup")) {
	      id = 4;
	    } else if (p.hasPermission("system.prefix.testsup")) {
	      id = 5;
	    } else if (p.hasPermission("system.prefix.headbuilder")) {
	      id = 6;
	    } else if (p.hasPermission("system.prefix.builder")) {
	      id = 7;
	    } else if (p.hasPermission("system.prefix.yt")) {
	      id = 8;
	    } else if (p.hasPermission("system.prefix.experte")) {
	      id = 9;
	    } else {
	      id = 10;
	    }
	    return id;
	}
	
	
	
	public static void count(){
		
		 
		 
		xp = 600;
		new BukkitRunnable() {
			
			@Override
			public void run() {
xp--;
				
				
				if(xp == 600){
					for (Player all : Bukkit.getOnlinePlayers()){
						all.sendMessage(" ");
                        all.sendMessage("          §aUnser §6Dicord§a:");
                        all.sendMessage("                §6discord.blitzmc.de");
                        all.sendMessage(" ");
					}
				}else if(xp == 400){
					for (Player all : Bukkit.getOnlinePlayers()){
						all.sendMessage(" ");
                        all.sendMessage("          §aErfahre alle Befehle mit:");
                        all.sendMessage("                  §6/tutorial");
                        all.sendMessage(" ");
						
					}
					
				}else if(xp == 250){
					for (Player all : Bukkit.getOnlinePlayers()){
						all.sendMessage(" ");
                        all.sendMessage("          §aMit dem Beitritt akzeptierst du die Regeln!");
                        all.sendMessage("                              §6/regeln");
                        all.sendMessage(" ");
						
					}
					
					
				}else if(xp <= 0){
					
					xp = 600;
					
				}
				
			}
		}.runTaskTimer(getInstance(), 20, 20);
		
	}
	
	@SuppressWarnings("deprecation")
	public void expire() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				ShopManager.checkForExpiration();
				
			}
		}.runTaskTimer(getInstance(), 132, 132);
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void setHelix(){
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(ParticleManager.getHelixLocation() != null){
					ParticleManager.createHelix(Particle.VILLAGER_HAPPY);
					
					
				}else{
					this.cancel();
				}
				
			}
		}.runTaskTimer(getInstance(), 132,132);
		
	}
	
	@SuppressWarnings("deprecation")
	public void setDollar(){
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()){
					String bar = "§8» §aDeine Dollar: §6" + Currency.getDollar(all);
					
					

				}
				
			}
		}.runTaskTimerAsynchronously(getInstance(), 40, 40);
	}
	
	
	/*public static void increasingRst(final BlockRedstoneEvent er) {
		Main.rst += 1;
		if (Main.rst > 300) {
			er.getBlock().setType(Material.LEGACY_SIGN_POST);
			Sign sign = (Sign)er.getBlock().getState();
			sign.setLine(0, "Redstone Clocks");
			sign.setLine(1, "sind nicht");
			sign.setLine(2,"§cgestattet");
			sign.setLine(3, " ");
			sign.update();
		}
			Main.rst = 0;
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(all.hasPermission("system.clocknotes")) {
					all.sendMessage("[" + ChatColor.RED + "RCP" + ChatColor.WHITE + "] " + ChatColor.GRAY + "Clock detected and stopped at " + ChatColor.WHITE
							+ "x" + er.getBlock().getLocation().getBlockX() + " y" + er.getBlock().getLocation().getBlockY() + " z" + er.getBlock().getLocation().getBlockZ()
							+ ChatColor.GRAY + " in " + ChatColor.WHITE + er.getBlock().getWorld().getName());
				}
			
		    }
	}*/
	
}
