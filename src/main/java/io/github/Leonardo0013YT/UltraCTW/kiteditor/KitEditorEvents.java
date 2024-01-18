package io.github.Leonardo0013YT.UltraCTW.kiteditor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class KitEditorEvents implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        EditorManager.getInstance().addOrRemovePlayer(event.getPlayer(), true);
    }

    @EventHandler
    public void onPlace(PlayerInteractEvent event) {
        if (!EditorManager.getInstance().playerIsEdit(event.getPlayer())) { return; }
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            event.setCancelled(true);
            Block block = event.getClickedBlock();
            if (block.getType().equals(Material.ENCHANTMENT_TABLE)) {
                Bukkit.dispatchCommand(player, "savekit");
                return;
            }
            if (block.getType().equals(Material.ENDER_PORTAL_FRAME)) {
                Bukkit.dispatchCommand(player, "resetkit");
                return;
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!EditorManager.getInstance().playerIsEdit(event.getPlayer())) { return; }
        event.setCancelled(true);

    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (!EditorManager.getInstance().playerIsEdit(event.getPlayer())) { return; }
        event.setCancelled(true);

    }
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (!EditorManager.getInstance().playerIsEdit(event.getPlayer())) { return; }
        if (event.getMessage().contains("kit")) { return; }
        event.setCancelled(true);

    }

}
