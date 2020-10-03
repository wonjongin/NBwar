package io.github.wonjongin.nbwar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class Hardware {
    public static void checkram(Player player) {
        player.sendMessage(ChatColor.GOLD + "========< View Ram >==========");
        player.sendMessage(ChatColor.GOLD + "Max: " + ChatColor.YELLOW + (Runtime.getRuntime().maxMemory() / 1048576L) + " MB");
        player.sendMessage(ChatColor.GOLD + "Total: " + ChatColor.YELLOW + (Runtime.getRuntime().totalMemory() / 1048576L) + " MB");
        player.sendMessage(ChatColor.GOLD + "Availvable: " + ChatColor.YELLOW + (Runtime.getRuntime().freeMemory() / 1048576L) + " MB");
        player.sendMessage(ChatColor.GOLD + "Number of threads: " + ChatColor.YELLOW + Runtime.getRuntime().availableProcessors());
        getLogger().info("Max: " + (Runtime.getRuntime().maxMemory() / 1048576L) + " MB");
        getLogger().info("Total: " + (Runtime.getRuntime().totalMemory() / 1048576L) + " MB");
        getLogger().info("Availvable: " + (Runtime.getRuntime().freeMemory() / 1048576L) + " MB");
        getLogger().info("Number of threads: " + Runtime.getRuntime().availableProcessors());
    }
}
