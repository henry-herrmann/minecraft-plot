        package de.blitzmc.trade;
/*     */ 
/*     *
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;

import de.blitzmc.Main;

/*     */ 
/*     */
 public class CommandTrade implements org.bukkit.command.CommandExecutor
/*     */ {
/*     */   static Main plugin;
/*  25 */   public static HashMap<Player, Player> tradeList = new HashMap();
/*  26 */   public static HashMap<Player, Inventory> currentlyTrading = new HashMap();
/*  27 */   public static HashMap<Player, Inventory> finalizedTrades = new HashMap();
/*  28 */   public static List<Player> approvedTradePlayers = new ArrayList();
/*     */   
/*  30 */   public static HashMap<Player, Integer> tradeRequestTimer = new HashMap();
/*  31 */   public static HashMap<Player, Integer> tradeTimer = new HashMap();
/*  32 */   public HashMap<String, List<String>> ignoreList = new HashMap();
/*     */   
/*     */   public CommandTrade(Main main) {
/*  35 */     plugin = main;
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command command, String string, String[] args)
/*     */   {
/*  40 */     if ((sender instanceof Player)) {
/*  41 */       Player tradeFrom = (Player)sender;
/*     */       
/*  43 */       if (args.length == 0) {
/*  45 */         tradeFrom.sendMessage(Main.pr + "/trade <Spieler>");
                  tradeFrom.sendMessage(Main.pr + "/trade accept");
                  tradeFrom.sendMessage(Main.pr + "/trade decline");
                  tradeFrom.sendMessage(Main.pr + "/trade return");
                  tradeFrom.sendMessage(Main.pr + "/trade cancel");
/*  52 */         if (tradeFrom.hasPermission("*")) {
/*  53 */           tradeFrom.sendMessage(Main.pr + "/trade reload");
/*     */         }
/*  55 */         return true;
/*     */       }
/*  57 */       if (args[0].equalsIgnoreCase("reload")) {
/*  58 */         if (tradeFrom.hasPermission("*")) {
/*  59 */           Main.getPlugin().reloadConfig();
/*  60 */           Main.getUtils().reloadConfig();
/*  61 */           plugin.getLang().reloadCustomConfig();
/*  62 */           tradeFrom.sendMessage(Main.pr + "Konfiguration neugeladen.");
/*     */         }
/*  64 */         return true; }
/*  65 */       if (args[0].equalsIgnoreCase("accept")) {
/*  66 */         tradeAccepted(tradeFrom);
/*  67 */         return true; }
/*  68 */       if (args[0].equalsIgnoreCase("decline")) {
/*  69 */         tradeDeclined(tradeFrom);
/*  70 */         return true; }
/*  71 */      
/*  87 */       if (args[0].equalsIgnoreCase("return")) {
/*  88 */         tradeReturn(tradeFrom);
/*  89 */         return true; }
/*  90 */       if (args[0].equalsIgnoreCase("cancel")) {
/*  91 */         tradeCancel(tradeFrom);
/*  92 */         return true;
/*     */       }
/*  94 */       if (!tradeFrom.hasPermission("wannatrade.trade")) {
/*  95 */         tradeFrom.sendMessage(Main.noperm);
/*  96 */         return false;
/*     */       }
/*  98 */       if ((tradeList.containsKey(tradeFrom)) || (tradeList.containsValue(tradeFrom)))
/*     */       {
/* 100 */         tradeFrom.sendMessage(Main.pr + "§aDu hast schon eine Anfrage an jemanden gestellt.");
/* 101 */         return false;
/*     */       }
/* 103 */       Player tradeTo = Bukkit.getPlayer(args[0]);
/* 104 */       if ((tradeTo == tradeFrom) && (!tradeFrom.getName().equalsIgnoreCase("ugleh"))) {
/* 105 */         tradeFrom.sendMessage(Main.pr + "§cDu kannst dir nicht selbst eine Anfrage senden!");;
/* 106 */         return false;
/*     */       }
/*     */       
/* 109 */       if (tradeTo == null) {
/* 110 */         tradeFrom.sendMessage(Main.pr + "§cDieser Spieler ist offline.");
/* 111 */         return false;
/*     */       }
/*     */       
/* 114 */       
/*     */       
/* 143 */       sendTradeRequest(tradeFrom, tradeTo);
/* 144 */       return true;
/*     */     }
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   private void tradeUnignore(Player trader, String name) {
/* 150 */     if (currentlyTrading.containsKey(trader))
/*     */     {
/* 152 */       trader.sendMessage(plugin.getLang().LangCongfig("lang.error.invalidignore", Boolean.valueOf(true)));
/* 153 */       return;
/*     */     }
/*     */     
/* 156 */     Player trader2 = Bukkit.getPlayer(name);
/*     */     
/* 158 */     if (trader2 == null) {
/* 159 */       trader.sendMessage(plugin.getLang().LangCongfig("lang.error.invalidplayeroffline", Boolean.valueOf(true)));
/* 160 */       return;
/*     */     }
/* 162 */     unignoreUser(trader, trader2);
/*     */   }
/*     */   
/*     */   private void unignoreUser(Player ignorer, Player ignored)
/*     */   {
/* 167 */     List<String> ignorerList = new ArrayList();
/* 168 */     if (this.ignoreList.containsKey(ignorer.getUniqueId().toString())) {
/* 169 */       ignorerList = (List)this.ignoreList.get(ignorer.getUniqueId().toString());
/*     */     }
/* 171 */     if (ignorerList.contains(ignored.getUniqueId().toString())) {
/* 172 */       ignorerList.remove(ignored.getUniqueId().toString());
/* 173 */       this.ignoreList.put(ignorer.getUniqueId().toString(), ignorerList);
/* 174 */       
/* 175 */       ignorer.sendMessage(plugin.getLang().LangCongfigReplace("lang.chat.unignoretrade", "{player}", 
/* 176 */         ignored.getName(), Boolean.valueOf(true)));
/*     */       
/* 178 */       ignored.sendMessage(plugin.getLang().LangCongfigReplace("lang.chat.unignoretrade2", "{player}", 
/* 179 */         ignorer.getName(), Boolean.valueOf(true)));
/*     */     } else {
/* 181 */       ignorer.sendMessage(plugin.getLang().LangCongfig("lang.error.notignored", Boolean.valueOf(true)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void tradeIgnore(Player trader, String name)
/*     */   {
/* 188 */     boolean wasnull = false;
/* 189 */     if (name == null) {
/* 190 */       wasnull = true;
/*     */     }
/* 192 */     if (currentlyTrading.containsKey(trader))
/*     */     {
/* 194 */       trader.sendMessage(plugin.getLang().LangCongfig("lang.error.invalidignore", Boolean.valueOf(true)));
/* 195 */       return;
/*     */     }
/*     */     
/* 198 */     Player trader2 = null;
/* 199 */     if (name == null) {
/* 200 */       if (tradeList.containsKey(trader)) {
/* 201 */         trader2 = (Player)tradeList.get(trader);
/* 202 */       } else if (tradeList.containsValue(trader)) {
/* 203 */         trader2 = tradeListKey(trader);
/*     */       }
/*     */     } else {
/* 206 */       trader2 = Bukkit.getPlayer(name);
/*     */     }
/* 208 */     if (trader2 == null) {
/* 209 */       if (wasnull) {
/* 210 */         trader.sendMessage(plugin.getLang().LangCongfig("lang.error.invalidnotrade", Boolean.valueOf(true)));
/* 211 */         trader.sendMessage(plugin.getLang().LangCongfigRaw("/trade ignore (user)", Boolean.valueOf(true)));
/*     */       } else {
/* 213 */         trader.sendMessage(plugin.getLang().LangCongfig("lang.error.invalidplayeroffline", Boolean.valueOf(true)));
/*     */       }
/* 215 */       return;
/*     */     }
/* 217 */     if (wasnull) {
/* 218 */       tradeDeclined(trader);
/*     */     }
/* 220 */     ignoreUser(trader, trader2);
/*     */   }
/*     */   
/*     */   private void ignoreUser(Player ignorer, Player ignored)
/*     */   {
/* 225 */     List<String> ignorerList = new ArrayList();
/* 226 */     if (this.ignoreList.containsKey(ignorer.getUniqueId().toString())) {
/* 227 */       ignorerList = (List)this.ignoreList.get(ignorer.getUniqueId().toString());
/*     */     }
/* 229 */     
/* 233 */     if (!ignorerList.contains(ignored.getUniqueId().toString())) {
/* 234 */       ignorerList.add(ignored.getUniqueId().toString());
/* 235 */       this.ignoreList.put(ignorer.getUniqueId().toString(), ignorerList);
/* 236 */       
/* 237 */       ignorer.sendMessage(
/* 238 */         plugin.getLang().LangCongfigReplace("lang.chat.ignoretrade", "{player}", ignored.getName(), Boolean.valueOf(true)));
/* 239 */       ignored.sendMessage(
/* 240 */         plugin.getLang().LangCongfigReplace("lang.chat.ignoretrade2", "{player}", ignorer.getName(), Boolean.valueOf(true)));
/*     */     }
/*     */     else {
/* 243 */       ignorer.sendMessage(plugin.getLang().LangCongfig("lang.error.alreadyignored", Boolean.valueOf(true)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static void removeTradersFromObjects(Player trader, Player trader2)
/*     */   {
/* 250 */     if (finalizedTrades.containsKey(trader)) {
/* 251 */       ((Inventory)finalizedTrades.get(trader)).clear();
/*     */     }
/* 253 */     if (finalizedTrades.containsKey(trader2)) {
/* 254 */       ((Inventory)finalizedTrades.get(trader2)).clear();
/*     */     }
/* 256 */     finalizedTrades.remove(trader);
/* 257 */     finalizedTrades.remove(trader2);
/* 258 */     approvedTradePlayers.remove(trader);
/* 259 */     approvedTradePlayers.remove(trader2);
/* 260 */     if (currentlyTrading.containsKey(trader)) {
/* 261 */       ((Inventory)currentlyTrading.get(trader)).clear();
/*     */     }
/* 263 */     if (currentlyTrading.containsKey(trader2)) {
/* 264 */       ((Inventory)currentlyTrading.get(trader2)).clear();
/*     */     }
/* 266 */     currentlyTrading.remove(trader);
/* 267 */     currentlyTrading.remove(trader2);
/* 268 */     tradeRequestTimer.remove(trader);
/* 269 */     tradeRequestTimer.remove(trader2);
/* 270 */     tradeList.remove(trader);
/* 271 */     tradeList.remove(trader2);
/* 272 */     tradeTimer.remove(trader);
/* 273 */     tradeTimer.remove(trader2);
/*     */   }
/*     */   
/*     */   public static void tradeCancel(Player trader)
/*     */   {
/* 278 */     if (currentlyTrading.containsKey(trader))
/*     */     {
/* 280 */       Player trader2 = null;
/* 281 */       if (tradeList.containsKey(trader)) {
/* 282 */         trader2 = (Player)tradeList.get(trader);
/* 283 */       } else if (tradeList.containsValue(trader)) {
/* 284 */         trader2 = tradeListKey(trader);
/*     */       }
/* 286 */       trader.sendMessage(Main.pr  + "§cHandel abgebrochen.");
/* 287 */       trader2.sendMessage(Main.pr + "§e" + trader.getName() +" §chat den Handel abgebrochen.");
/* 289 */       tradeList.remove(trader);
/* 290 */       tradeList.remove(trader2);
/* 291 */       trader.closeInventory();
/* 292 */       trader2.closeInventory();
/*     */       
/* 294 */       giveInventory(trader, (Inventory)currentlyTrading.get(trader));
/* 295 */       giveInventory(trader2, (Inventory)currentlyTrading.get(trader2));
/*     */       
/* 297 */       removeTradersFromObjects(trader, trader2);
/*     */     }
/*     */     else {
/* 300 */       trader.sendMessage(Main.pr  +"§cDu hast keinen Aktiven Handel.");
/*     */     }
/*     */   }
/*     */   
/*     */   private static void giveInventory(Player trader, Inventory inventory) {
/* 305 */     int index = 0;
/* 306 */     for (ItemStack items : inventory) {
/* 307 */       if (index >= 27)
/*     */         break;
/* 309 */       if (items != null) {
/* 310 */         if (trader.getInventory().firstEmpty() == -1) {
/* 311 */           trader.getWorld().dropItem(trader.getLocation(), items);
/*     */         } else {
/* 313 */           trader.getInventory().addItem(new ItemStack[] { items });
/*     */         }
/*     */       }
/* 316 */       index++;
/*     */     }
/*     */   }
/*     */   
/*     */   private void tradeReturn(Player trader) {
/* 321 */     if (finalizedTrades.containsKey(trader)) {
/* 322 */       trader.sendMessage(Main.pr + "§cDas letzte Angebot wurde bereits gestellt. Bitte nutze §e/trade cancel§c.");
/* 323 */       return;
/*     */     }
/* 325 */     if (currentlyTrading.containsKey(trader))
/*     */     {
/* 327 */       trader.openInventory((Inventory)currentlyTrading.get(trader));
/*     */     }
/*     */     else
/*     */     {
/* 331 */       trader.sendMessage(Main.pr  +"§cDu hast keinen Aktiven Handel.");
/*     */     }
/*     */   }
/*     */   
/*     */   private void tradeDeclined(Player tradeTo)
/*     */   {
/* 337 */     if (currentlyTrading.containsKey(tradeTo))
/*     */     {
/* 339 */       tradeCancel(tradeTo);
/* 340 */       return;
/*     */     }
/* 342 */     if (tradeList.containsValue(tradeTo))
/*     */     {
/* 344 */       Player tradeFrom = tradeListKey(tradeTo);
/* 345 */       tradeListRemove(tradeTo);
/* 346 */       tradeRequestTimer.remove(tradeTo);
/* 347 */       tradeRequestTimer.remove(tradeListKey(tradeTo));
/*     */       
/* 349 */       tradeTo.sendMessage(Main.pr + "§cHandel abgelehnt.");
/* 350 */       tradeFrom.sendMessage(Main.pr + "§e"+ tradeTo.getName()+" §chat den Handel abgelehnt.");
/*     */     } else {
/* 353 */       tradeTo.sendMessage(Main.pr + "§cDu hast noch keine Anfrage erhalten.");
/*     */     }
/*     */   }
/*     */   
/*     */   private void tradeAccepted(Player tradeTo) {
/* 358 */     if (!currentlyTrading.containsKey(tradeTo)) {
/* 359 */       if (tradeList.containsValue(tradeTo))
/*     */       {
/* 361 */         tradeRequestTimer.remove(tradeTo);
/* 362 */         tradeRequestTimer.remove(tradeListKey(tradeTo));
/* 363 */         tradeTo.sendMessage(Main.pr +"§aHandel angenommen");
/* 364 */         tradeListKey(tradeTo).sendMessage(Main.pr + "§e" + tradeTo.getName() + "§a hat den Handel angenommen.");
/* 366 */         initTrade(tradeListKey(tradeTo), tradeTo);
/*     */       } else {
/* 368 */         tradeTo.sendMessage(Main.pr + "§cDu hast noch keine Anfrage erhalten.");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void initTrade(Player tradeFrom, Player tradeTo)
/*     */   {
/* 375 */     tradeTimer.put(tradeFrom, Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
/* 376 */     tradeTimer.put(tradeTo, Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
/* 377 */     Inventory tradeFromInv = Bukkit.createInventory(null, 36, "§aHandel - §6Dein Angebot");
/* 379 */     Inventory tradeToInv = Bukkit.createInventory(null, 36, "§aHandel - §6Dein Angebot");
/* 381 */     TradeTimer cooldown = new TradeTimer(tradeFrom, Main.getUtils().tradeTimer);
/* 382 */     cooldown.setCancelTask(Bukkit.getScheduler().runTaskTimer(plugin, cooldown, 0L, 2L));
/* 383 */     ItemStack stainedGlass = getStainedGlass();
/* 384 */     for (int i = 27; i < 36; i++) {
/* 385 */       tradeFromInv.setItem(i, stainedGlass);
/*     */     }
/* 387 */     ItemStack accept = new ItemStack(Material.INK_SAC, 1, (short)10);
/* 388 */     ItemMeta acceptMeta = accept.getItemMeta();
/* 389 */     List<String> lore = new ArrayList();
/* 390 */     lore.add("§cHabe genug Platz!");
/* 391 */     acceptMeta.setLore(lore);
/* 392 */     acceptMeta.setDisplayName("§aFertig");
/* 393 */     accept.setItemMeta(acceptMeta);
/* 394 */     tradeFromInv.setItem(30, accept);
/*     */     
/* 396 */     tradeFromInv.setItem(31, getSkull(tradeTo, tradeFrom));
/* 397 */     ItemStack decline = new ItemStack(Material.INK_SAC, 1, (short)8);
/* 398 */     ItemMeta declineMeta = decline.getItemMeta();
/* 399 */     declineMeta.setLore(lore);
/* 400 */     declineMeta.setDisplayName("§cAbbruch");
/* 401 */     decline.setItemMeta(declineMeta);
/* 402 */     tradeFromInv.setItem(32, decline);
/*     */     
/* 404 */     tradeFromInv.setMaxStackSize(240);
/* 405 */     tradeToInv.setContents(tradeFromInv.getContents());
/*     */     
/* 407 */     tradeToInv.setItem(31, getSkull(tradeFrom, tradeTo));
/*     */     
/* 409 */     currentlyTrading.put(tradeFrom, tradeFromInv);
/* 410 */     currentlyTrading.put(tradeTo, tradeToInv);
/* 411 */     tradeFrom.openInventory(tradeFromInv);
/* 412 */     tradeTo.openInventory(tradeToInv);
/*     */   }
/*     */   
/*     */   private ItemStack getSkull(Player tradeTo, Player tradeFrom) {
/* 416 */     SkullMeta meta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(Material.LEGACY_SKULL_ITEM);
/* 417 */     meta.setOwner(tradeTo.getName());
/* 418 */     meta.setDisplayName("§aHandel mit§6 " +tradeTo.getName());
/* 420 */     ItemStack skullStack = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short)3);
/* 421 */     skullStack.setItemMeta(meta);
/* 422 */     return skullStack;
/*     */   }
/*     */   
/*     */   private ItemStack getStainedGlass() {
/* 426 */     ItemStack ret = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
/* 427 */     ItemMeta im = ret.getItemMeta();
/* 428 */     im.setDisplayName("§c---");
/* 429 */     ret.setItemMeta(im);
/* 430 */     return ret;
/*     */   }
/*     */   
/*     */   public static void tradeListRemove(Player tradeTo) {
/* 434 */     Player tradeFrom = null;
/* 435 */     for (Map.Entry<Player, Player> entry : tradeList.entrySet()) {
/* 436 */       if (((Player)entry.getValue()).equals(tradeTo)) {
/* 437 */         tradeFrom = (Player)entry.getKey();
/* 438 */         break;
/*     */       }
/*     */     }
/* 441 */     tradeList.remove(tradeFrom);
/*     */   }
/*     */   
/*     */   public static Player tradeListKey(Player value)
/*     */   {
/* 446 */     for (Map.Entry<Player, Player> entry : tradeList.entrySet()) {
/* 447 */       if (((Player)entry.getValue()).equals(value)) {
/* 448 */         return (Player)entry.getKey();
/*     */       }
/*     */     }
/* 451 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   private void sendTradeRequest(Player tradeFrom, Player tradeTo)
/*     */   {
/* 457 */     tradeList.put(tradeFrom, tradeTo);
/* 458 */     tradeRequestTimer.put(tradeFrom, Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
/* 459 */     tradeFrom.sendMessage(
/* 460 */       Main.pr + "Handels Anfrage an §e" +tradeTo.getName() + "§a gesendet.");
/* 461 */     tradeTo.sendMessage(
/* 462 */       Main.pr + "§e" + tradeFrom.getName() + "§a würde gerne mit dir handeln.");
/*     */     
/* 464 */     sendTradeRequestMessageTellRaw(tradeTo);
/*     */     
/* 466 */     Bukkit.getScheduler().runTaskLater(plugin, new Runnable()
/*     */     {
/*     */       public void run() {
/* 469 */         CommandTrade.this.tradeExpiredCheck();
/*     */       }
/* 471 */     }, Main.getUtils().tradeRequestTimer * 20L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void sendTradeRequestMessageTellRaw(Player tradeTo)
/*     */   {
/* 514 */     /*TextComponent tc = new TextComponent();

tc.setText("§aAnnehmen");
tc.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/trade accept"));
tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Nehme den §6Trade §7an").create()));


TextComponent ta = new TextComponent();
ta.setText("§cAblehnen");
ta.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/trade decline"));
ta.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Lehne den §6Trade §7ab").create()));

tradeTo.spigot().sendMessage(tc);
tradeTo.sendMessage("§7-----------");
tradeTo.spigot().sendMessage(ta);*/
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void tradeExpiredCheck()
/*     */   {
/* 550 */     for (Player s : tradeList.keySet()) {
/* 551 */       if (tradeRequestTimer.containsKey(s))
/*     */       {
/*     */ 
/* 554 */         int time = (int)(System.currentTimeMillis() / 1000L) - ((Integer)tradeRequestTimer.get(s)).intValue();
/* 555 */         if (time >= 19) {
/* 556 */           tradeExpired(s, (Player)tradeList.get(s));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void tradeExpired(Player tradeFrom, Player tradeTo) {
/* 563 */     tradeRequestTimer.remove(tradeFrom);
/* 564 */     tradeList.remove(tradeFrom);
/* 565 */     tradeFrom.sendMessage(Main.pr + "§cAnfrage abgelaufen");
/* 566 */     tradeTo.sendMessage(Main.pr + "§cAnfrage abgelaufen");
/*     */   }
/*     */   
/*     */   public static void cancelTradeExpired(Player trader) {
/* 570 */     if (currentlyTrading.containsKey(trader))
/*     */     {
/* 572 */       Player trader2 = null;
/* 573 */       if (tradeList.containsKey(trader)) {
/* 574 */         trader2 = (Player)tradeList.get(trader);
/* 575 */       } else if (tradeList.containsValue(trader)) {
/* 576 */         trader2 = tradeListKey(trader);
/*     */       }
/* 578 */       trader.sendMessage(Main.pr + "§cAnfrage abgelaufen");
/* 579 */       trader2.sendMessage(Main.pr + "§cAnfrage abgelaufen");
/* 580 */       trader.closeInventory();
/* 581 */       trader2.closeInventory();
/* 582 */       giveInventory(trader, (Inventory)currentlyTrading.get(trader));
/* 583 */       giveInventory(trader2, (Inventory)currentlyTrading.get(trader2));
/* 584 */       removeTradersFromObjects(trader, trader2);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void tradeDone(Player trader) {
/* 589 */     if (finalizedTrades.containsKey(trader))
/* 590 */       return;
/* 591 */     if (currentlyTrading.containsKey(trader))
/*     */     {
/* 593 */       Player trader2 = null;
/* 594 */       if (tradeList.containsKey(trader)) {
/* 595 */         trader2 = (Player)tradeList.get(trader);
/* 596 */       } else if (tradeList.containsValue(trader)) {
/* 597 */         trader2 = tradeListKey(trader);
/*     */       }
/* 599 */       finalizedTrades.put(trader, (Inventory)currentlyTrading.get(trader));
/*     */       
/* 601 */       trader.closeInventory();
/* 602 */       if ((finalizedTrades.containsKey(trader2)) && (finalizedTrades.containsKey(trader))) {
/* 603 */         trader.sendMessage(Main.pr + "Du hast dein Angebot gestellt.");
/* 604 */         trader2.sendMessage(Main.pr + "§e" + trader.getName() + "§a hat sein Angebot gestellt.");
/* 606 */         modifyInventoryForFinalTradeApproval(trader, trader2);
/*     */       } else {
/* 608 */         trader.sendMessage(Main.pr + "Du hast dein Angebot gestellt, warten auf §e" + trader2.getName() + "§a.");
/* 610 */         trader2.sendMessage(Main.pr + "§e" + trader.getName() + "§a hat sein Angebot gestellt, er wartet nun auf dich.");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static void modifyInventoryForFinalTradeApproval(Player trader1, Player trader2)
/*     */   {
/* 618 */     Inventory trader1Inventory = (Inventory)finalizedTrades.get(trader1);
/* 619 */     Inventory trader2Inventory = (Inventory)finalizedTrades.get(trader2);
/* 620 */     ItemStack acceptItem = trader1Inventory.getItem(30);
/* 621 */     ItemMeta acceptItemMeta = acceptItem.getItemMeta();
/* 622 */     acceptItemMeta.setDisplayName("§aHandel annehmen");
/* 623 */     acceptItem.setItemMeta(acceptItemMeta);
/*     */     
/* 625 */     ItemStack declineItem = trader1Inventory.getItem(32);
/* 626 */     ItemMeta declineItemMeta = declineItem.getItemMeta();
/* 627 */     declineItemMeta.setDisplayName("§cHandel ablehnen");
/* 628 */     declineItem.setItemMeta(declineItemMeta);
/*     */     
/* 630 */     ItemStack trader1Skull = trader1Inventory.getItem(31);
/* 631 */     ItemMeta trader1SkullMeta = trader1Skull.getItemMeta();
/* 632 */     trader1SkullMeta.setDisplayName("§aAnfrage von §6" + trader1.getName());
/* 634 */     trader1Skull.setItemMeta(trader1SkullMeta);
/*     */     
/* 636 */     ItemStack trader2Skull = trader2Inventory.getItem(31);
/* 637 */     ItemMeta trader2SkullMeta = trader2Skull.getItemMeta();
/* 638 */     trader2SkullMeta.setDisplayName("§aAnfrage von §6" + trader1.getName());
/* 640 */     trader2Skull.setItemMeta(trader2SkullMeta);
/*     */     
/* 642 */     trader1Inventory.setItem(30, acceptItem);
/* 643 */     trader1Inventory.setItem(31, trader1Skull);
/* 644 */     trader1Inventory.setItem(32, declineItem);
/* 645 */     trader2Inventory.setItem(30, acceptItem);
/* 646 */     trader2Inventory.setItem(31, trader2Skull);
/* 647 */     trader2Inventory.setItem(32, declineItem);
/*     */     
/* 649 */     Inventory t1Inv = Bukkit.createInventory(null, 36, 
/* 650 */       "§aHandel - §6Sein Angebot");
/* 651 */     Inventory t2Inv = Bukkit.createInventory(null, 36, 
/* 652 */       "§aHandel - §6Sein Angebot");
/* 653 */     t2Inv.setContents(trader2Inventory.getContents());
/* 654 */     t1Inv.setContents(trader1Inventory.getContents());
/* 655 */     finalizedTrades.put(trader1, t1Inv);
/* 656 */     finalizedTrades.put(trader2, t2Inv);
/*     */     
/* 658 */     showTradeOfferFinalized(trader1, trader2, t1Inv, t2Inv);
/*     */   }
/*     */   
/*     */ 
/*     */   private static void showTradeOfferFinalized(Player trader1, Player trader2, Inventory t1Inv, Inventory t2Inv)
/*     */   {
/* 664 */     trader1.openInventory(t2Inv);
/* 665 */     trader2.openInventory(t1Inv);
/*     */   }
/*     */   
/*     */   private static void exchangeItemsFinalized(Player trader1, Player trader2, Inventory trader1Inventory, Inventory trader2Inventory)
/*     */   {
/* 670 */     giveInventory(trader1, trader2Inventory);
/* 671 */     giveInventory(trader2, trader1Inventory);
/*     */     
/* 673 */     trader1.sendMessage(
/* 674 */       Main.pr + "§e" + trader2.getName() + "§a bedankt sich für den Handel mit dir!");
/* 675 */     trader2.sendMessage(
/* 676 */       Main.pr + "§e" + trader1.getName() + "§a bedankt sich für den Handel mit dir!");
/* 677 */     ((Inventory)finalizedTrades.get(trader1)).clear();
/* 678 */     ((Inventory)finalizedTrades.get(trader2)).clear();
/* 679 */     removeTradersFromObjects(trader1, trader2);
/* 680 */     trader1.closeInventory();
/* 681 */     trader2.closeInventory();
/*     */   }
/*     */   
/*     */   public static void approvedTrade(Player trader1) {
/* 685 */     if (!approvedTradePlayers.contains(trader1)) {
/* 686 */       approvedTradePlayers.add(trader1);
/* 687 */       Player trader2 = null;
/* 688 */       if (tradeList.containsKey(trader1)) {
/* 689 */         trader2 = (Player)tradeList.get(trader1);
/* 690 */       } else if (tradeList.containsValue(trader1)) {
/* 691 */         trader2 = tradeListKey(trader1);
/*     */       }
/* 693 */       trader1.closeInventory();
/* 694 */       if (approvedTradePlayers.contains(trader2)) {
/* 695 */         
/* 698 */         exchangeItemsFinalized(trader1, trader2, (Inventory)finalizedTrades.get(trader1), (Inventory)finalizedTrades.get(trader2));
/*     */       }
/*     */       else {
/* 701 */         trader1.sendMessage(Main.pr + "Angebot angenommen, warten auf §e" + trader2.getName());
/* 703 */         trader2.sendMessage(Main.pr + "§e" + trader1.getName() + "§a hat dein Angebot angenommen, er wartet nun auf dich!");
/*     */       }
/*     */     }
/*     */   }
/*     */ }

