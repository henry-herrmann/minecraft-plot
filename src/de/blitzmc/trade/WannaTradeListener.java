/*    */ package de.blitzmc.trade;
/*    */ 

/*    */ 
import de.blitzmc.Main;


/*    
/*    */ import java.util.HashMap;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class WannaTradeListener implements org.bukkit.event.Listener
/*    */ {
/*    */   static Main plugin;
/*    */   
/*    */   public WannaTradeListener(Main main)
/*    */   {
/* 19 */     plugin = main;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void PlayerQuitEvent(PlayerQuitEvent e)
/*    */   {
/* 25 */     if ((CommandTrade.tradeList.containsKey(e.getPlayer())) || (CommandTrade.tradeList.containsValue(e.getPlayer())))
/*    */     {
/* 27 */       CommandTrade.tradeListKey(e.getPlayer()).sendMessage(Main.pr + "§cDer Spieler mit dem du gehandelt hast, hat den Server verlassen.");
/* 28 */       CommandTrade.tradeCancel(e.getPlayer());
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   @EventHandler
/*    */   public void InventoryClickEvent(InventoryClickEvent e)
/*    */   {
/* 36 */     if (e.getView().getTitle().equalsIgnoreCase("§aHandel - §6Dein Angebot")) {
/* 37 */       if (e.getRawSlot() == 30) {
/* 38 */         e.setCancelled(true);
/* 39 */         CommandTrade.tradeDone((Player)e.getWhoClicked());
/*    */       }
/* 41 */       else if (e.getRawSlot() == 32) {
/* 42 */         e.setCancelled(true);
/*    */         
/* 44 */         CommandTrade.tradeCancel((Player)e.getWhoClicked());
/* 45 */       } else if (e.getRawSlot() < 36)
/*    */       {
/* 47 */         if (e.getRawSlot() >= 27)
/* 48 */           e.setCancelled(true);
/*    */       }
/*    */     }
/* 51 */     if (e.getView().getTitle().equalsIgnoreCase("§aHandel - §6Sein Angebot"))
/*    */     {
/* 53 */       e.setCancelled(true);
/* 54 */       if (e.getRawSlot() == 30) {
/* 55 */         e.setCancelled(true);
/*    */         
/* 57 */         CommandTrade.approvedTrade((Player)e.getWhoClicked());
/* 58 */       } else if (e.getRawSlot() == 32) {
/* 59 */         e.setCancelled(true);
/*    */         
/* 61 */         CommandTrade.tradeCancel((Player)e.getWhoClicked());
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void InventoryCloseEvent(InventoryCloseEvent e)
/*    */   {
/* 69 */     Player p = (Player)e.getPlayer();
/* 70 */     if (CommandTrade.finalizedTrades.containsKey(p))
/* 71 */       return;
/* 72 */     if (((CommandTrade.tradeList.containsKey(p)) || (CommandTrade.tradeList.containsValue(p))) && 
/* 73 */       (e.getInventory().equals(CommandTrade.currentlyTrading.get(p))))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 80 */       p.sendMessage(Main.pr + "§cDu hast einen aktiven Handel verlassen.");
/* 81 */       tellRawMessage(p);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   private void tellRawMessage(Player p)
/*    */   {
	/*TextComponent tc = new TextComponent();

	tc.setText("§aZurück");
	tc.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/trade return"));
	tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Kehre zum Trade zurück").create()));


	TextComponent ta = new TextComponent();
	ta.setText("§cAbbruch");
	ta.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/trade cancel"));
	ta.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Breche den Trade ab").create()));

	p.spigot().sendMessage(tc);
	p.sendMessage("§7-----------");
	p.spigot().sendMessage(ta);*/
}
}


