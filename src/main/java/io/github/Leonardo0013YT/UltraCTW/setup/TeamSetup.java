package io.github.Leonardo0013YT.UltraCTW.setup;

import io.github.Leonardo0013YT.UltraCTW.objects.Selection;
import io.github.Leonardo0013YT.UltraCTW.objects.Squared;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
public class TeamSetup {
    private ChatColor color;
    private Location spawn;
    private HashMap<ChatColor, Location> spawners = new HashMap();
    private HashMap<ChatColor, Location> wools = new HashMap();
    private ArrayList<ChatColor> colors = new ArrayList();
    private ArrayList<Squared> squareds = new ArrayList();

    public TeamSetup(ChatColor var1) {
        this.color = var1;
    }

    public void addSquared(Selection var1) {
        this.squareds.add(new Squared(var1.getPos2(), var1.getPos1(), false, true));
    }

    public ChatColor getColor() {
        return this.color;
    }

    public Location getSpawn() {
        return this.spawn;
    }

    public HashMap<ChatColor, Location> getSpawners() {
        return this.spawners;
    }

    public HashMap<ChatColor, Location> getWools() {
        return this.wools;
    }

    public ArrayList<ChatColor> getColors() {
        return this.colors;
    }

    public ArrayList<Squared> getSquareds() {
        return this.squareds;
    }

    public void setColor(ChatColor var1) {
        this.color = var1;
    }

    public void setSpawn(Location var1) {
        this.spawn = var1;
    }

    public void setSpawners(HashMap<ChatColor, Location> var1) {
        this.spawners = var1;
    }

    public void setWools(HashMap<ChatColor, Location> var1) {
        this.wools = var1;
    }

    public void setColors(ArrayList<ChatColor> var1) {
        this.colors = var1;
    }

    public void setSquareds(ArrayList<Squared> var1) {
        this.squareds = var1;
    }

}