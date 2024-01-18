package io.github.Leonardo0013YT.UltraCTW.customevents;

import io.github.Leonardo0013YT.UltraCTW.interfaces.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CTWPlayerJoinGameEvent extends Event implements Cancellable {

    private final Player player;
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;
    private final Game game;
    public CTWPlayerJoinGameEvent(Player p, Game game) {
        player = p;
        this.game = game;
    }

    public Player getPlayer() { return this.player; }

    public Game getGame() { return game; }

    public boolean isCancelled() { return this.isCancelled; }

    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    @Override
    public HandlerList getHandlers() { return HANDLERS_LIST; }
}
