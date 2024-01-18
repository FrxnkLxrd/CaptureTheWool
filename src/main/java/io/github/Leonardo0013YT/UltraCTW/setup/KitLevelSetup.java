package io.github.Leonardo0013YT.UltraCTW.setup;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class KitLevelSetup {

    private ItemStack icon = new ItemStack(Material.DIAMOND_SWORD);
    private ItemStack[] inv = new ItemStack[36], armor = {null, null, null, null};
    private double price;
    private boolean buy;
    private int level, slot, page;

    public KitLevelSetup(int level) {
        this.level = level;
        this.slot = 10;
        this.page = 1;
        this.buy = true;
        this.price = 500;
    }

    public ItemStack getIcon() {
        return this.icon;
    }

    public ItemStack[] getInv() {
        return this.inv;
    }

    public ItemStack[] getArmor() {
        return this.armor;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean isBuy() {
        return this.buy;
    }

    public int getLevel() {
        return this.level;
    }

    public int getSlot() {
        return this.slot;
    }

    public int getPage() {
        return this.page;
    }

    public void setIcon(ItemStack var1) {
        this.icon = var1;
    }

    public void setInv(ItemStack[] var1) {
        this.inv = var1;
    }

    public void setArmor(ItemStack[] var1) {
        this.armor = var1;
    }

    public void setPrice(double var1) {
        this.price = var1;
    }

    public void setBuy(boolean var1) {
        this.buy = var1;
    }

    public void setLevel(int var1) {
        this.level = var1;
    }

    public void setSlot(int var1) {
        this.slot = var1;
    }

    public void setPage(int var1) {
        this.page = var1;
    }

}