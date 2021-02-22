package io.github.wonjongin.nbwar;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.Holograpic.showSimpleHologram;
import static io.github.wonjongin.nbwar.Print.printLongLine;
import static org.bukkit.Bukkit.getLogger;

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
                "loreStats(ls) - 로어스탯보기",
                "setName(sn) - 이름 설정(색상은&)",
                "NBT(nbt) - NBT 보기",
                "holo(h) - hologram 보기"
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
        } else if (args[1].equalsIgnoreCase("loreStats") || args[1].equalsIgnoreCase("ls")) {
            viewLoreStats(player);
        } else if (args[1].equalsIgnoreCase("NBT") || args[1].equalsIgnoreCase("nbt")) {
            viewNBT(player);
        } else if (args[1].equalsIgnoreCase("holo") || args[1].equalsIgnoreCase("h")) {
            viewSimpleHologram(player);
        } else if (args[1].equalsIgnoreCase("test") || args[1].equalsIgnoreCase("tt")) {
            viewTest(player);
        } else {
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
        player.sendMessage("Title: " + itemMeta.getDisplayName());
        List<String> lore = itemMeta.getLore();
        for (int i = 0; i < lore.size(); i++) {
            player.sendMessage("Lore: " + lore.get(i));
        }
    }

    public static void addLore(Player player, String str) {
        ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
        if (!itemMeta.hasLore()) {
            itemMeta.setLore(Arrays.asList(""));
        }
        List<String> lore = itemMeta.getLore();
        lore.add(str);
        itemMeta.setLore(lore);
        player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
        player.sendMessage("Title: " + itemMeta.getDisplayName());
        for (int i = 0; i < lore.size(); i++) {
            player.sendMessage("Lore: " + lore.get(i));
        }
    }

    public static void viewNBT(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        NBTItem nbti = new NBTItem(itemStack);

        player.sendMessage(ChatColor.AQUA + nbti.getKeys().toString());

    }

    public static void viewLoreStats(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        LoreStats loreStats = new LoreStats().parseToLoreStats(item);

        player.sendMessage("공격력 : " + loreStats.getPower());
        player.sendMessage("크리확률 : " + loreStats.getCriticalPercent());
        player.sendMessage("크리데미지 : " + loreStats.getCritical());
        player.sendMessage("체력 : " + loreStats.getHealth());
        player.sendMessage("피흡 : " + loreStats.getDrain());
        player.sendMessage("방어력 : " + loreStats.getDefend());
        player.sendMessage("방어력무시 : " + loreStats.getIgnoreDefend());

    }

    public static void viewTest(Player player) {
        player.sendMessage("§bHello");
        String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        player.sendMessage(name);
        getLogger().info(name);
        switch (name) {
            case "JacktheRipper":
                player.sendMessage(ChatColor.GREEN + "is JacktheRipper not color code");
                break;
            case "&8&lJack&7&lthe&f&lRipper":
                player.sendMessage(ChatColor.GREEN + "엠퍼센트");
                break;
            case "§8§lJack§7§lthe§f§lRipper":
                player.sendMessage(ChatColor.GREEN + "옵션6");
                break;
        }
    }

    public static void setName(Player player, String str) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(str.replace("&", "§"));
        itemStack.setItemMeta(itemMeta);
    }

    public static void viewSimpleHologram(Player player) {
        ArrayList<String> texts = new ArrayList<>(Arrays.asList(
                "hello",
                "world"
        ));
        showSimpleHologram(player, texts);
    }
}
