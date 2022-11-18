package de.blitzmc.chestshop;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder
{
  public static ItemStack createItem(Material material, String displayName, Integer amount, String[] lore)
  {
    ItemStack itemStack = new ItemStack(material, amount.intValue());
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(displayName);
    itemMeta.setLore(Arrays.asList(lore));
    itemStack.setItemMeta(itemMeta);
    
    return itemStack;
  }
  
  public static ItemStack createItemOL(Material material, String displayName, Integer amount)
  {
    ItemStack itemStack = new ItemStack(material, amount.intValue());
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(displayName);
    itemStack.setItemMeta(itemMeta);
    
    return itemStack;
  }
}

