package io.github.Leonardo0013YT.UltraCTW.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
public class Selection {

    private Location pos1;
    private Location pos2;

    public Selection() {
    }

    public Location getPos1() {
        return this.pos1;
    }

    public Location getPos2() {
        return this.pos2;
    }

    public void setPos1(Location var1) {
        this.pos1 = var1;
    }

    public void setPos2(Location var1) {
        this.pos2 = var1;
    }
}