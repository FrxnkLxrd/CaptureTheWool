package io.github.Leonardo0013YT.UltraCTW.cmds.kits;

import io.github.Leonardo0013YT.UltraCTW.kiteditor.EditorManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class KitEditor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 0) {

            Player player = (Player) sender;

            if (!player.getWorld().getName().equalsIgnoreCase("lobby")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7¡No puedes usar este comando si no estás en el lobby"));
                return false;
            }

            if (EditorManager.getInstance().playerIsEdit(player)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7¡Ya estás editando un kit!"));
                return false;
            }
            Location spawn = EditorManager.getInstance().getEditSpawn();
            if (spawn == null) { return false; }
            player.teleport(spawn);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999, 1));
            EditorManager.getInstance().addOrRemovePlayer(player, false);

            if (EditorManager.getInstance().playerExist(player)) {
                EditorManager.getInstance().giveSavedKit(player);
                return false;
            }

            EditorManager.getInstance().giveDefaultKit(player);
            return true;
        }


        if (!sender.hasPermission("ultractw.admin")) {
            return false;
        }

        if (args[0].equalsIgnoreCase("setspawn")) {
            Player player = (Player) sender;
            EditorManager.getInstance().saveSpawn(player);
            player.sendMessage("You haven set spawn of kit editor");
            return false;
        }

        if (args[0].equalsIgnoreCase("setkit")) {
            Player player = (Player) sender;
            EditorManager.getInstance().setKitDefault(player);
            player.sendMessage("You haven set default of kit editor");
            return false;
        }

        return false;
    }
}
