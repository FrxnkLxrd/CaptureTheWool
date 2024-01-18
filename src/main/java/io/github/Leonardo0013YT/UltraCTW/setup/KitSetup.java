package io.github.Leonardo0013YT.UltraCTW.setup;

import io.github.Leonardo0013YT.UltraCTW.UltraCTW;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Getter
@Setter
public class KitSetup {

    private UltraCTW plugin;
    private String name, permission;
    private int slot = 10, page = 1;
    private HashMap<Integer, KitLevelSetup> levels = new HashMap<>();
    private KitLevelSetup kls;
    private boolean flag = false;

    public KitSetup(UltraCTW plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        this.permission = "ultractw.kits." + name;
    }

    public void saveKitLevel() {
        levels.put(kls.getLevel(), kls);
        kls = null;
    }

    public void save() {
        String n = "kits." + name;
        plugin.getKits().set(n + ".id", plugin.getKm().getNextID());
        plugin.getKits().set(n + ".name", name);
        plugin.getKits().set(n + ".permission", permission);
        plugin.getKits().set(n + ".slot", slot);
        plugin.getKits().set(n + ".page", page);
        plugin.getKits().set(n + ".flag", flag);
        for (KitLevelSetup kls : levels.values()) {
            String nl = "kits." + name + ".levels." + kls.getLevel();
            plugin.getKits().set(nl + ".level", kls.getLevel());
            plugin.getKits().set(nl + ".price", kls.getPrice());
            plugin.getKits().set(nl + ".slot", kls.getSlot());
            plugin.getKits().set(nl + ".page", kls.getPage());
            ItemStack defIcon = kls.getIcon();
            ItemMeta dM = defIcon.getItemMeta();
            dM.setDisplayName("§aDefault");
            dM.setLore(new ArrayList<>(Arrays.asList("§7This is a default icon:", "§7Change this in kits.yml")));
            defIcon.setItemMeta(dM);
            plugin.getKits().set(nl + ".icon", defIcon);
            plugin.getKits().set(nl + ".armor", kls.getArmor());
            plugin.getKits().set(nl + ".inv", kls.getInv());
            plugin.getKits().set(nl + ".permission", permission + "." + kls.getLevel());
        }
        plugin.getKits().save();
    }

    public String getName() {
        return this.name;
    }

    public String getPermission() {
        return this.permission;
    }

    public int getSlot() {
        return this.slot;
    }

    public int getPage() {
        return this.page;
    }

    public HashMap<Integer, KitLevelSetup> getLevels() {
        return this.levels;
    }

    public KitLevelSetup getKls() {
        return this.kls;
    }

    public boolean isFlag() {
        return this.flag;
    }

    public void setPlugin(UltraCTW var1) {
        this.plugin = var1;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public void setPermission(String var1) {
        this.permission = var1;
    }

    public void setSlot(int var1) {
        this.slot = var1;
    }

    public void setPage(int var1) {
        this.page = var1;
    }

    public void setLevels(HashMap<Integer, KitLevelSetup> var1) {
        this.levels = var1;
    }

    public void setKls(KitLevelSetup var1) {
        this.kls = var1;
    }

    public void setFlag(boolean var1) {
        this.flag = var1;
    }
}