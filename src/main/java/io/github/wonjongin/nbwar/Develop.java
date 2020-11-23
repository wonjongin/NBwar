package io.github.wonjongin.nbwar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.Print.printLongLine;

public class Develop {
    public static void devCommand(Player player, String[] args) {
        String[] devCommandList = {
                "Dev Commands",
                "Type /n dev <command>",
                "color(cl) - 색 보기 ",
                "deco(dc) - 꾸밈효과 보기",
                "location(lo) - 플레이어 위치보기",
                "lore(l) - 로어보기",
                "addlore(al) - 로어추가",
        };
        if (args.length == 1) {
            printLongLine(player, devCommandList, 1);
        } else if (isInteger(args[1])) {
            int nowPage = Integer.parseInt(args[1]);
            printLongLine(player, devCommandList, nowPage);
        } else if (args[1].equalsIgnoreCase("color") || args[1].equalsIgnoreCase("cl")) {
            viewColor(player);
        } else if (args[1].equalsIgnoreCase("deco") || args[1].equalsIgnoreCase("dc")) {
            viewDecorate(player);
        } else if (args[1].equalsIgnoreCase("location") || args[1].equalsIgnoreCase("lo")) {
            viewLocation(player, args[2]);
        } else if (args[1].equalsIgnoreCase("lore") || args[1].equalsIgnoreCase("l")) {
            viewLore(player);
        } else if (args[1].equalsIgnoreCase("addlore") || args[1].equalsIgnoreCase("al")) {
            addLore(player, args[2]);
        }else {
            player.sendMessage(ChatColor.RED + "Command Not Found!");
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

    private static void viewDecorate(Player player) {
        player.sendMessage(ChatColor.BOLD + "Bold #l");
        player.sendMessage(ChatColor.STRIKETHROUGH + "Strikethrough #m");
        player.sendMessage(ChatColor.ITALIC + "Italic #o");
        player.sendMessage(ChatColor.RESET + "Reset #r");
        player.sendMessage(ChatColor.MAGIC + "Magic #???");
    }

    private static void viewLocation(Player player, String targetPlayerName) {
        Player targetPlayer = Bukkit.getServer().getPlayer(targetPlayerName);

        // 대상의 위치
        double x = targetPlayer.getLocation().getX();
        double y = targetPlayer.getLocation().getY();
        double z = targetPlayer.getLocation().getZ();

        //플레이어 위치
        double px = player.getLocation().getX();
        double py = player.getLocation().getY();
        double pz = player.getLocation().getZ();

        // 버림
        int ix = (int) Math.floor(x);
        int iy = (int) Math.floor(y);
        int iz = (int) Math.floor(z);

        // 버림
        int pix = (int) Math.floor(px);
        int piy = (int) Math.floor(py);
        int piz = (int) Math.floor(pz);

        // 상대위치
        int sx = ix - pix;
        int sy = iy - piy;
        int sz = iz - piz;

        // 메시지 보내기
        player.sendMessage(ChatColor.YELLOW + "x: " + sx + ", y: " + sy + ", z: " + sz);
    }

    public static void viewLore(Player player) {
        ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
        player.sendMessage("Title: "+ itemMeta.getDisplayName());
        List<String> lore = itemMeta.getLore();
        for (int i = 0; i < lore.size(); i++) {
            player.sendMessage("Lore: " + lore.get(i));
        }
    }public static void addLore(Player player, String str) {
        ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
        if(!itemMeta.hasLore()){
            itemMeta.setLore(Arrays.asList(""));
        }
        List<String> lore = itemMeta.getLore();
        lore.add(str);
        itemMeta.setLore(lore);
        player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
        player.sendMessage("Title: "+ itemMeta.getDisplayName());
        for (int i = 0; i < lore.size(); i++) {
            player.sendMessage("Lore: " + lore.get(i));
        }
    }
}
