package io.github.Leonardo0013YT.UltraCTW.listeners;

import io.github.Leonardo0013YT.UltraCTW.customevents.CTWPlayerJoinGameEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onJoin(CTWPlayerJoinGameEvent event) {
        Player p = event.getPlayer();
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().setArmorContents(null);
        p.getInventory().clear();
        //p.teleport(p.getLocation().add(0, 20, 0));
        p.setAllowFlight(true);
        p.setFlying(true);
        p.setHealth(p.getMaxHealth());
    }

}
