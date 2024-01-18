package io.github.Leonardo0013YT.UltraCTW.cmds.kits;

import io.github.Leonardo0013YT.UltraCTW.UltraCTW;
import io.github.Leonardo0013YT.UltraCTW.kiteditor.EditorManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 0) {

            Player player = (Player) sender;
            if (!EditorManager.getInstance().playerIsEdit(player)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7¡No estas editando ningun kit!"));
                return false;
            }
            EditorManager.getInstance().giveDefaultKit(player);
            return false;
        }
        return false;
    }
}
