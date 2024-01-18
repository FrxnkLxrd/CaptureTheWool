package io.github.Leonardo0013YT.UltraCTW.utils;

import io.github.Leonardo0013YT.UltraCTW.xseries.XMaterial;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemUtils {

    private final ItemStack item;

    public ItemUtils(XMaterial material) {
        this.item = new ItemStack(material.parseMaterial(), 1, material.getData());
    }

    public ItemUtils(XMaterial material, int amount) {
        this.item = new ItemStack(material.parseMaterial(), amount, material.getData());
    }

    public ItemUtils(Material material, int amount, int data) {
        this.item = new ItemStack(material, amount, (short) data);
    }

    public ItemUtils setDisplayName(String displayName) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(Utils.getColor(displayName));
        item.setItemMeta(im);
        return this;
    }

    public ItemUtils setLore(String lore) {
        ItemMeta im = item.getItemMeta();
        im.setLore(lore.isEmpty() ? new ArrayList<>() : Arrays.asList(lore.split("\\n")));
        item.setItemMeta(im);
        return this;
    }

    public ItemUtils setLore(List<String> lore) {
        ItemMeta im = item.getItemMeta();
        im.setLore(lore);
        item.setItemMeta(im);
        return this;
    }
    public ItemUtils setLoreWithPAPI(Player player, List<String> lore) {
        ItemMeta im = item.getItemMeta();
        lore.replaceAll(var1 -> Utils.getColor(PlaceholderAPI.setPlaceholders(player, var1)));
        im.setLore(lore);
        item.setItemMeta(im);
        return this;
    }

    public ItemUtils setUnbreakable(boolean unbreakable) {
        ItemMeta im = item.getItemMeta();
        im.spigot().setUnbreakable(unbreakable);
        item.setItemMeta(im);
        return this;
    }

    public ItemUtils addEnchant(Enchantment enchantment, int level) {
        ItemMeta im = item.getItemMeta();
        im.addEnchant(enchantment, level, true);
        item.setItemMeta(im);
        return this;
    }

    public ItemUtils applyAttributes() {
        ItemMeta im = item.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(im);
        return this;
    }

    public ItemStack build() {
        return item;
    }

}