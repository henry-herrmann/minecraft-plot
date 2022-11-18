/*    */ package de.blitzmc.trade;
/*    */ 
/*    */ 
/*    */ 
import de.blitzmc.Main;

/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TradeUtils
/*    */ {
/*    */   public double tradeTimer;
/*    */   public long tradeRequestTimer;
/*    */   public int tradeRange;
/*    */   public boolean sameWorld;
/*    */   
/* 15 */   public TradeUtils() { loadConfigVariables(); }
/*    */   
/*    */   private void loadConfigVariables() {
/* 18 */     this.tradeTimer = Main.getInstance().getConfig().getDouble("wannatrade.settings.tradetimer");
/* 19 */     this.tradeRequestTimer = Main.getInstance().getConfig().getInt("wannatrade.settings.traderequesttimer");
/* 20 */     this.tradeRange = Main.getInstance().getConfig().getInt("wannatrade.settings.range");
/* 21 */     this.sameWorld = Main.getInstance().getConfig().getBoolean("wannatrade.settings.sameworld");
/*    */   }
/*    */   
/*    */   public static String timeAgo(long time)
/*    */   {
/* 26 */     String[] periods = { "second", "minute", "hour", "day", "week", "month", "year", "decade" };
/* 27 */     Long[] lengths = { Long.valueOf(60L), Long.valueOf(60L), Long.valueOf(24L), Long.valueOf(7L), Long.valueOf(4L), Long.valueOf(12L), Long.valueOf(10L) };
/* 28 */     long now = new java.util.Date().getTime();
/* 29 */     long difference = now - time;
/* 30 */     int i = 0;
/* 31 */     for (i = 0; (difference >= lengths[i].longValue()) && (i < lengths.length - 1); i++)
/*    */     {
/* 33 */       difference /= lengths[i].longValue();
/*    */     }
/* 35 */     difference = Math.round((float)difference);
int tmp201_199 = i; 
/* 36 */     if (difference != 1L)
/*    */     {
/* 38 */       String[] tmp201_198 = periods;tmp201_198[tmp201_199] = (tmp201_198[tmp201_199] + "s");
/*    */     }
/* 40 */     return String.valueOf(tmp201_199) + periods[i] + " ago";
/*    */   }
/*    */   
/*    */   public void reloadConfig() {
/* 44 */     this.tradeTimer = Main.getInstance().getConfig().getDouble("wannatrade.settings.tradetimer");
/* 45 */     this.tradeRequestTimer = Main.getInstance().getConfig().getInt("wannatrade.settings.traderequesttimer");
/* 46 */     this.tradeRange = Main.getInstance().getConfig().getInt("wannatrade.settings.range");
/* 47 */     this.sameWorld = Main.getInstance().getConfig().getBoolean("wannatrade.settings.sameworld");
/*    */   }
/*    */ }

