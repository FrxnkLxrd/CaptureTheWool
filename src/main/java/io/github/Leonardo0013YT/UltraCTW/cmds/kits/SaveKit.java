package io.github.Leonardo0013YT.UltraCTW.cmds.kits;

import io.github.Leonardo0013YT.UltraCTW.UltraCTW;
import io.github.Leonardo0013YT.UltraCTW.kiteditor.EditorManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class SaveKit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 0) {

            Player player = (Player) sender;
            if (!EditorManager.getInstance().playerIsEdit(player)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7¡No estas editando ningun kit!"));
                return false;
            }

            EditorManager.getInstance().saveKit(player);
            player.getInventory().clear();
            player.teleport(UltraCTW.get().getCm().getMainLobby());
            for (PotionEffect activePotionEffect : player.getActivePotionEffects()) {
                player.removePotionEffect(activePotionEffect.getType());
            }
            EditorManager.getInstance().addOrRemovePlayer(player, true);
            return false;
        }

        return false;
    }
}
