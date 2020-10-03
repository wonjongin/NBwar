package io.github.wonjongin.nbwar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.Print.printLongLine;

public class Develop {
    public static void devCommand(Player player, String[] args) {
        String[] devCommandList = {
                "Dev Commands",
                "Type /n mn <command>",
                "color(cl) - 색 보기 ",
        };
        if (args.length == 1) {
            printLongLine(player, devCommandList, 1);
        } else if (isInteger(args[1])) {
            int nowPage = Integer.parseInt(args[1]);
            printLongLine(player, devCommandList, nowPage);
        } else if (args[1].equalsIgnoreCase("color") || args[1].equalsIgnoreCase("cl")) {
            viewColor(player);
        }
    }

    private static void viewColor(Player player) {
        player.sendMessage(ChatColor.BLACK + "Black #0");
        player.sendMessage(ChatColor.DARK_BLUE + "Dark Blue #1");
        player.sendMessage(ChatColor.DARK_GREEN + "Dark Green #2");
        player.sendMessage(ChatColor.DARK_AQUA + "Dark Aqua #3");
        player.sendMessage(ChatColor.DARK_RED + "Dark Red #4");
        player.sendMessage(ChatColor.DARK_PURPLE + "Dark Purple #5");
        player.sendMessage(ChatColor.GOLD + "Gold #6");
        player.sendMessage(ChatColor.GRAY + "Gray #7");
        player.sendMessage(ChatColor.DARK_GRAY + "Dark Gray #8");
        player.sendMessage(ChatColor.BLUE + "Blue #9");
        player.sendMessage(ChatColor.GREEN + "Green #a");
        player.sendMessage(ChatColor.AQUA + "Aqua #b");
        player.sendMessage(ChatColor.RED + "Red #c");
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Light Purple #d");
        player.sendMessage(ChatColor.YELLOW + "Yellow #e");
        player.sendMessage(ChatColor.WHITE + "White #f");
    }
}
