package io.github.wonjongin.nbwar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Hardware {
    public static void checkram(Player player){
        player.sendMessage(ChatColor.GOLD + "========< View Ram >==========");
        player.sendMessage(ChatColor.GOLD + "Total: " + ChatColor.RED + (Runtime.getRuntime().totalMemory() / 1048576L)+" MB");
        player.sendMessage(ChatColor.GOLD + "Availvable: " + ChatColor.RED + (Runtime.getRuntime().freeMemory() / 1048576L)+" MB");
        player.sendMessage(ChatColor.GOLD + "Number of threads: " + ChatColor.RED + Runtime.getRuntime().availableProcessors());
    }
}
