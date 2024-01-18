package io.github.Leonardo0013YT.UltraCTW.customevents;

import io.github.Leonardo0013YT.UltraCTW.game.GameWin;
import io.github.Leonardo0013YT.UltraCTW.interfaces.Game;
import io.github.Leonardo0013YT.UltraCTW.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CTWGameWinEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;
    private final Game game;
    private final Team team;
    private final GameWin gameWin;
    public CTWGameWinEvent(Game game, GameWin win, Team team) {
        this.game = game;
        this.team = team;
        gameWin = win;
    }

    public Game getGame() { return game; }

    public Team getTeam() {
        return team;
    }

    public GameWin getGameWin() {
        return gameWin;
    }

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
