package io.github.Leonardo0013YT.UltraCTW.cosmetics.trails;

import io.github.Leonardo0013YT.UltraCTW.UltraCTW;
import io.github.Leonardo0013YT.UltraCTW.enums.TrailType;
import io.github.Leonardo0013YT.UltraCTW.interfaces.CTWPlayer;
import io.github.Leonardo0013YT.UltraCTW.interfaces.Purchasable;
import io.github.Leonardo0013YT.UltraCTW.utils.ItemBuilder;
import io.github.Leonardo0013YT.UltraCTW.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Trail implements Purchasable {

    private final ItemStack icon;
    private final TrailType type;
    private final String name;
    private final String permission;
    private final String particle;
    private final String autoGivePermission;
    private final float offsetX;
    private final float offsetY;
    private final float offsetZ;
    private final int id;
    private final int amount;
    private final int slot;
    private final int page;
    private final int price;
    private final double range;
    private final double speed;
    private final boolean isBuy;
    private final boolean needPermToBuy;

    public Trail(UltraCTW plugin, String path) {
        this.id = plugin.getTrail().getInt(path + ".id");
        this.type = TrailType.valueOf(plugin.getTrail().getOrDefault(path + ".type", "NORMAL"));
        this.name = plugin.getTrail().get(null, path + ".name");
        this.particle = plugin.getTrail().get(null, path + ".particle");
        this.slot = plugin.getTrail().getInt(path + ".slot");
        this.page = plugin.getTrail().getInt(path + ".page");
        this.amount = plugin.getTrail().getInt(path + ".amount");
        this.price = plugin.getTrail().getInt(path + ".price");
        this.isBuy = plugin.getTrail().getBoolean(path + ".isBuy");
        this.needPermToBuy = plugin.getTrail().getBooleanOrDefault(path + ".needPermToBuy", false);
        this.autoGivePermission = plugin.getTrail().getOrDefault(path + ".autoGivePermission", "ultractw.trails.autogive." + name);
        this.permission = plugin.getTrail().get(null, path + ".permission");
        this.icon = plugin.getTrail().getConfig().getItemStack(path + ".icon");
        this.range = plugin.getTrail().getConfig().getDouble(path + ".range");
        this.speed = plugin.getTrail().getConfig().getDouble(path + ".speed");
        this.offsetX = (float) plugin.getTrail().getConfig().getDouble(path + ".offsetX");
        this.offsetY = (float) plugin.getTrail().getConfig().getDouble(path + ".offsetY");
        this.offsetZ = (float) plugin.getTrail().getConfig().getDouble(path + ".offsetZ");
        plugin.getTlm().setLastPage(page);
    }

    public int getId() {
        return id;
    }

    @Override
    public String getAutoGivePermission() {
        return autoGivePermission;
    }

    public ItemStack getIcon(Player p) {
        if (!icon.hasItemMeta()) {
            return icon;
        }
        CTWPlayer sw = UltraCTW.get().getDb().getCTWPlayer(p);
        ItemStack icon = this.icon.clone();
        if (!p.hasPermission(autoGivePermission)) {
            if (price > 0) {
                if (UltraCTW.get().getCm().isRedPanelInLocked()) {
                    if (!sw.getTrails().contains(id)) {
                        icon = ItemBuilder.item(XMaterial.matchXMaterial(UltraCTW.get().getCm().getRedPanelMaterial().name(), (byte) UltraCTW.get().getCm().getRedPanelData()).orElse(XMaterial.RED_STAINED_GLASS_PANE), 1, icon.getItemMeta().getDisplayName(), icon.getItemMeta().getLore());
                    }
                }
            }
        }
        ItemMeta iconM = icon.getItemMeta();
        List<String> lore = icon.getItemMeta().getLore();
        for (int i = 0; i < lore.size(); i++) {
            String s = lore.get(i);
            switch (s) {
                case "<price>":
                    if (!p.hasPermission(autoGivePermission)) {
                        if (isBuy && !sw.getTrails().contains(id)) {
                            lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.price").replaceAll("<price>", String.valueOf(price)));
                        } else if (!isBuy && !sw.getTrails().contains(id)) {
                            if (needPermToBuy && p.hasPermission(permission)) {
                                lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.price").replaceAll("<price>", String.valueOf(price)));
                            } else {
                                lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.noBuyable"));
                            }
                        } else if (sw.getTrails().contains(id) || !needPermToBuy) {
                            lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.buyed"));
                        }
                    } else {
                        lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.buyed"));
                    }
                    break;
                case "<status>":
                    if (!p.hasPermission(autoGivePermission)) {
                        if (sw.getTrails().contains(id)) {
                            lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.hasBuy"));
                        } else if (isBuy) {
                            if (UltraCTW.get().getAdm().getCoins(p) > price) {
                                lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.buy"));
                            } else {
                                lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.noMoney"));
                            }
                        } else if (needPermToBuy) {
                            if (UltraCTW.get().getAdm().getCoins(p) > price) {
                                lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.buy"));
                            } else {
                                lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.noMoney"));
                            }
                        } else {
                            lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.noPermission"));
                        }
                    } else {
                        lore.set(i, UltraCTW.get().getLang().get(p, "menus.trailsselector.hasBuy"));
                    }
                    break;
            }
        }
        iconM.setLore(lore);
        icon.setItemMeta(iconM);
        return icon;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    public String getParticle() {
        return particle;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public float getOffsetZ() {
        return offsetZ;
    }

    public int getAmount() {
        return amount;
    }

    public int getSlot() {
        return slot;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public int getPage() {
        return page;
    }

    public double getSpeed() {
        return speed;
    }

    public double getRange() {
        return range;
    }

    @Override
    public boolean isBuy() {
        return isBuy;
    }

    @Override
    public boolean needPermToBuy() {
        return needPermToBuy;
    }

    public TrailType getType() {
        return type;
    }
}