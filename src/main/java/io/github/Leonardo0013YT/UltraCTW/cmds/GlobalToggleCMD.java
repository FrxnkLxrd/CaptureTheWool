package io.github.Leonardo0013YT.UltraCTW.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GlobalToggleCMD implements CommandExecutor {

    private static final List<Player> toggles = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        } else {
            Player player = ((Player) sender);
            if (!toggles.contains(player.getPlayer())) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have just activated the permanent global chat"));
                toggles.add(player);
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have just deactivate the permanent global chat"));
                toggles.remove(player);
            }
        }
        return false;
    }

    public static boolean isGlobalToggle(Player player) {
        return toggles.contains(player);
    }

}
