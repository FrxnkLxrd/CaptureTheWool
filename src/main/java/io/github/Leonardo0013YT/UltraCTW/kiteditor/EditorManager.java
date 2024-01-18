package io.github.Leonardo0013YT.UltraCTW.kiteditor;

import io.github.Leonardo0013YT.UltraCTW.UltraCTW;
import io.github.Leonardo0013YT.UltraCTW.cmds.kits.KitEditor;
import io.github.Leonardo0013YT.UltraCTW.cmds.kits.ResetKit;
import io.github.Leonardo0013YT.UltraCTW.cmds.kits.SaveKit;
import io.github.Leonardo0013YT.UltraCTW.config.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EditorManager {

    private final Settings config;
    public static EditorManager instance;
    private final List<UUID> edit;

    public EditorManager() {
        instance = this;
        config = new Settings(UltraCTW.get(), "kiteditor", false, false);
        edit = new ArrayList<>();
        UltraCTW.get().getCommand("kiteditor").setExecutor(new KitEditor());
        UltraCTW.get().getCommand("savekit").setExecutor(new SaveKit());
        UltraCTW.get().getCommand("resetkit").setExecutor(new ResetKit());
        Bukkit.getPluginManager().registerEvents(new KitEditorEvents(), UltraCTW.get());
    }

    public Settings getYaml() {
        return config;
    }

    public YamlConfiguration getConfig() {
        return config.getConfig();
    }

    public Location getEditSpawn() {
        return (Location) getConfig().get("KitEditor.Location");
    }

    public boolean existKitDefault() {
        return getConfig().contains("KitDefault");
    }

    public void saveSpawn(Player player) {
        getConfig().set("KitEditor.Location", player.getLocation());
        getYaml().save();
    }

    public void giveDefaultKit(Player player) {
        List<ItemStack> items = (List<ItemStack>) getConfig().getList("KitDefault");
        for (int i = 0; i < 36; i++) {
            ItemStack item = items.get(i);
            player.getInventory().setItem(i, item);
        }
    }

    public void saveKit(Player player) {
        List<ItemStack> items = new ArrayList<>();

        for (int i = 0; i < 36; i++) {
            ItemStack item = player.getInventory().getItem(i);
            items.add(item);
        }
        getConfig().set("SaveKits."+player.getUniqueId()+".Inventory", items);
        getYaml().save();
    }

    public void addOrRemovePlayer(Player player, boolean remove) {
        if (remove) {
            edit.remove(player.getUniqueId());
            return;
        }
        edit.add(player.getUniqueId());
    }

    public boolean playerIsEdit(Player player) {
        if (edit.contains(player.getUniqueId())) { return true; }
        return false;
    }

    public boolean playerExist(Player player) {
        return getConfig().contains("SaveKits."+player.getUniqueId());
    }

    public void giveSavedKit(Player player) {

        List<ItemStack> items = (List<ItemStack>) getConfig().getList("SaveKits."+player.getUniqueId()+".Inventory");
        for (int i = 0; i < 36; i++) {
            ItemStack item = items.get(i);
            if (item == null) {
                player.getInventory().setItem(i, new ItemStack(Material.AIR));
                continue;
            }
            player.getInventory().setItem(i, item);
        }
    }


    public void setKitDefault(Player player) {
        List<ItemStack> items = new ArrayList<>();

        for (int i = 0; i < 36; i++) {
            ItemStack item = player.getInventory().getItem(i);
            items.add(item);
        }
        getConfig().set("KitDefault", items);
        getYaml().save();

    }

    public static EditorManager getInstance() {
        return instance;
    }
}
