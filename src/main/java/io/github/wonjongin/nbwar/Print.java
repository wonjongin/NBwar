package io.github.wonjongin.nbwar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class Print {
    public static void printLongLine(Player player, String[] desc, int nowPage) {
        int amount = desc.length - 1;
        getLogger().info("amount: " + amount);
        int amountOfPage = 8;
        double rate = ((double) amount) / ((double) amountOfPage);
        int pages = (int) Math.ceil(rate);
        int startPoint = (nowPage - 1) * amountOfPage + 1;
        int left = amount - startPoint;
        if (nowPage < 1 || nowPage > pages) {
            player.sendMessage("Page Not Found!");
        } else {
            player.sendMessage(ChatColor.GREEN + "---------< " + ChatColor.WHITE + desc[0] + ChatColor.GREEN + " >---------------- ");
            if (left < amountOfPage) {
                for (int i = startPoint; i <= amount; i++) {
                    if (desc[i].contains("-")) {
                        String[] tmp = desc[i].split("-");
                        String a = tmp[0] + ": ";
                        String b = " " + tmp[1];
                        player.sendMessage(ChatColor.GREEN + a + ChatColor.WHITE + b);
                    } else {
                        player.sendMessage(ChatColor.WHITE + desc[i]);
                    }
                }
            } else {
                for (int i = startPoint; i < startPoint + amountOfPage; i++) {
                    if (desc[i].contains("-")) {
                        String[] tmp = desc[i].split("-");
                        String a = tmp[0] + ": ";
                        String b = " " + tmp[1];
                        //                    getLogger().info("tmp[0]: "+tmp[0]);
                        //                    getLogger().info("tmp[1]: "+tmp[1]);
                        player.sendMessage(ChatColor.GREEN + a + ChatColor.WHITE + b);
                    } else {
                        player.sendMessage(ChatColor.WHITE + desc[i]);
                    }
                }
            }
            String printPage = "---------< "+nowPage+" / "+pages+" >----------------";
            player.sendMessage(ChatColor.GREEN+printPage);
        }
    }
}
