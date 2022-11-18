package de.blitzmc.chestshop;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class ItemManager
{
  private ArmorStand armorstand;
  private Location location;
  private String text = null;
  private Boolean h = Boolean.valueOf(false);
  private Material material;
  private double height = -1.3D;
  
  public void setLocation(Location location)
  {
    this.location = location;
  }
  
  public void setText(String text)
  {
    this.text = text;
  }
  
  public void setMaterial(Material material)
  {
    this.material = material;
  }
  
  public void setHeight(double height)
  {
    this.height = (height - 1.3D);
    if (this.location != null)
    {
      this.location.setY(this.location.getY() + this.height);
      this.h = Boolean.valueOf(true);
    }
  }
  
  public void remove()
  {
    this.location = null;
    this.armorstand.remove();
    this.armorstand.getPassenger().remove();
    this.armorstand = null;
    this.h = Boolean.valueOf(false);
    this.height = 0.0D;
    this.text = null;
    this.material = null;
  }
  
  public void teleport(Location location)
  {
    if (this.location != null)
    {
      this.armorstand.teleport(location);
      this.location = location;
    }
  }
  
  public void spawn()
  {
    if (!this.h.booleanValue())
    {
      this.location.setY(this.location.getY() + this.height);
      this.h = Boolean.valueOf(true);
    }
    
    this.armorstand = ((ArmorStand)this.location.getWorld().spawn(this.location, ArmorStand.class));
    this.armorstand.setGravity(false);
    this.armorstand.setVisible(false);
    Item i = this.location.getWorld().dropItem(getLocation(), new ItemStack(getMaterial()));
    i.setPickupDelay(Integer.MAX_VALUE);
    if (this.text != null)
    {
      i.setCustomName(this.text);
      i.setCustomNameVisible(true);
    }
    this.armorstand.setPassenger(i);
  }
  
  public void spawnLapis()
  {
    if (!this.h.booleanValue())
    {
      this.location.setY(this.location.getY() + this.height);
      this.h = Boolean.valueOf(true);
    }
    this.armorstand = ((ArmorStand)this.location.getWorld().spawn(this.location, ArmorStand.class));
    this.armorstand.setGravity(false);
    this.armorstand.setVisible(false);
    Item i = this.location.getWorld().dropItem(getLocation(), new ItemStack(Material.INK_SAC, (short) 4));
    i.setPickupDelay(Integer.MAX_VALUE);
    i.getItemStack().setDurability( (short)4);
    
    if (this.text != null)
    {
      i.setCustomName(this.text);
      i.setCustomNameVisible(true);
    }
    this.armorstand.setPassenger(i);
  }
  
  
  public Location getLocation()
  {
    return this.location;
  }
  
  public Material getMaterial()
  {
    return this.material;
  }
  
  public double getHeight()
  {
    return this.height;
  }
  
  public String getText()
  {
    return this.text;
  }
}

