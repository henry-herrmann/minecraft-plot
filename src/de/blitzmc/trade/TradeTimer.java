/*    */ package de.blitzmc.trade;
/*    */ 
/*    */ 
/*    */ 
/*    */ 

import de.blitzmc.Main;
/*    */ 
/*    */ public class TradeTimer implements Runnable
/*    */ {
/*    */   private org.bukkit.scheduler.BukkitTask toCancel;
/*    */   private double cooldown;
/*    */   private org.bukkit.entity.Player tradeFrom;
/*    */   private Integer divide;
/*    */   
/*    */   public TradeTimer(org.bukkit.entity.Player tradeFrom, double cooldown)
/*    */   {
/* 15 */     this.cooldown = cooldown;
/* 16 */     this.tradeFrom = tradeFrom;
/* 17 */     if (Main.getUtils().tradeTimer < 64.0D)
/*    */     {
/* 19 */       this.divide = Integer.valueOf(1);
/*    */     } else {
/* 21 */       this.divide = Integer.valueOf((int)Math.round(Main.getUtils().tradeTimer / 64.0D));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void run()
/*    */   {
/* 28 */     this.cooldown -= 0.1D;
/* 29 */     int amount = (int)Math.round(this.cooldown) / this.divide.intValue();
/* 30 */     if (amount < 1) amount = 1;
/* 31 */     if ((int)Math.round(this.cooldown) >= 1) {
/* 32 */       org.bukkit.entity.Player trader1 = this.tradeFrom;
/* 33 */       org.bukkit.entity.Player trader2 = (org.bukkit.entity.Player)CommandTrade.tradeList.get(trader1);
/* 34 */       if ((!CommandTrade.currentlyTrading.containsKey(trader1)) || (!CommandTrade.currentlyTrading.containsKey(trader2)))
/*    */       {
/* 36 */         this.toCancel.cancel();
/* 37 */         return;
/*    */       }
/* 39 */       if ((CommandTrade.finalizedTrades.containsKey(trader1)) || 
/* 40 */         (CommandTrade.finalizedTrades.containsKey(trader2))) {
/* 41 */         if (CommandTrade.finalizedTrades.containsKey(trader1)) {
/* 42 */           org.bukkit.inventory.ItemStack newItem = ((org.bukkit.inventory.Inventory)CommandTrade.finalizedTrades.get(trader1)).getItem(31);
/* 43 */           newItem.setAmount(amount);
/* 44 */           ((org.bukkit.inventory.Inventory)CommandTrade.finalizedTrades.get(trader1)).setItem(31, newItem);
/*    */         }
/* 46 */         if (CommandTrade.finalizedTrades.containsKey(trader2)) {
/* 47 */           org.bukkit.inventory.ItemStack newItem2 = ((org.bukkit.inventory.Inventory)CommandTrade.finalizedTrades.get(trader2)).getItem(31);
/* 48 */           newItem2.setAmount(amount);
/* 49 */           ((org.bukkit.inventory.Inventory)CommandTrade.finalizedTrades.get(trader2)).setItem(31, newItem2);
/*    */         }
/* 51 */       } else if ((CommandTrade.currentlyTrading.containsKey(trader1)) || (CommandTrade.currentlyTrading.containsKey(trader2))) {
/* 52 */         org.bukkit.inventory.ItemStack newItem = ((org.bukkit.inventory.Inventory)CommandTrade.currentlyTrading.get(trader1)).getItem(31);
/* 53 */         newItem.setAmount(amount);
/* 54 */         ((org.bukkit.inventory.Inventory)CommandTrade.currentlyTrading.get(trader1)).setItem(31, newItem);
/*    */         
/* 56 */         org.bukkit.inventory.ItemStack newItem2 = ((org.bukkit.inventory.Inventory)CommandTrade.currentlyTrading.get(trader2)).getItem(31);
/* 57 */         newItem2.setAmount((int)Math.round(this.cooldown) / this.divide.intValue());
/* 58 */         ((org.bukkit.inventory.Inventory)CommandTrade.currentlyTrading.get(trader2)).setItem(31, newItem2);
/*    */       } else {
/* 60 */         this.toCancel.cancel();
/* 61 */         CommandTrade.cancelTradeExpired(trader1);
/*    */       }
/*    */     }
/* 64 */     if (this.cooldown <= 0.0D) {
/* 65 */       this.toCancel.cancel();
/* 66 */       CommandTrade.cancelTradeExpired(this.tradeFrom);
/*    */     }
/*    */   }
/*    */   
/*    */   public void setCancelTask(org.bukkit.scheduler.BukkitTask task) {
/* 71 */     this.toCancel = task;
/*    */   }
/*    */ }

