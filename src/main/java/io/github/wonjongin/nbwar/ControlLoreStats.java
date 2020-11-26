package io.github.wonjongin.nbwar;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static io.github.wonjongin.nbwar.Op.isOpPlayer;
import static io.github.wonjongin.nbwar.hangul.*;

public class ControlLoreStats {
    @Deprecated
    public static void setLorePower(Player player, String powerStr) {
        int power = Integer.parseInt(powerStr);
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();
        LoreStats loreStats = new LoreStats().parseToLoreStats(item);
        loreStats.setPower(power);
        itemMeta.setLore(loreStats.toLoreList());
        item.setItemMeta(itemMeta);
        player.sendMessage(ChatColor.YELLOW + "공격력이 " + loreStats.getPower() + "(으)로 설정되었습니다.");
    }

    public static void setLoreMaster(Player player, String stat, String valueStr) {
        if (isOpPlayer(player)) {
            int value = Integer.parseInt(valueStr);
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();
            LoreStats loreStats = new LoreStats().parseToLoreStats(item);
            String headMsg = "";
            switch (stat) {
                case "power":
                    loreStats.setPower(value);
                    headMsg = "공격력";
                    break;
                case "defend":
                    loreStats.setDefend(value);
                    headMsg = "방어력";
                    break;
                case "drain":
                    loreStats.setDrain(value);
                    headMsg = "피흡";
                    break;
                case "critical":
                    loreStats.setCritical(value);
                    headMsg = "크리데미지";
                    break;
                case "criticalPercent":
                    loreStats.setCriticalPercent(value);
                    headMsg = "크리확률";
                    break;
                case "health":
                    loreStats.setHealth(value);
                    headMsg = "체력";
                    break;
                case "ignoreDefend":
                    loreStats.setIgnoreDefend(value);
                    headMsg = "방어력 무시";
                    break;
                default:
                    return;
            }

            itemMeta.setLore(loreStats.toLoreList(getColorOfItem(item)));
            item.setItemMeta(itemMeta);
            player.sendMessage(ChatColor.YELLOW + String.format("%s %s 설정되었습니다.", addS(headMsg), addNumAdv(value)));

        } else {
            player.sendMessage(ChatColor.RED + "권한이 없습니다.");
        }
    }

    public static void setNBTPower(Player player, String powerStr) {
        int power = Integer.parseInt(powerStr);
        ItemStack item = player.getInventory().getItemInMainHand();
        NBTItem nbti = new NBTItem(item);
        NBTCompound itemData = NBTItem.convertItemtoNBT(item);


    }

    public static void initLoreStats(ItemStack item) {
        LoreStats loreStats = new LoreStats();
        ItemMeta itemMeta = item.getItemMeta();
        if (!itemMeta.hasLore()) {
            itemMeta.setLore(Arrays.asList("init"));
        }
        List<String> lore = itemMeta.getLore();
        for (int i = 0; i < loreStats.getStatsNames().size(); i++) {
            lore.add(ChatColor.YELLOW + loreStats.getStatsNames().get(i) + ": " + "0");
        }
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    public static void setLoreStats(LoreStats loreStats, Item item) {
        ItemMeta itemMeta = item.getItemStack().getItemMeta();
        List<String> lore = itemMeta.getLore();
        for (int i = 0; i < lore.size(); i++) {
            lore.add(loreStats.getStatsNames().get(i) + ": " + String.valueOf(loreStats.getAllLore().get(i)));
        }
        itemMeta.setLore(lore);
        item.getItemStack().setItemMeta(itemMeta);
    }

    public static String getColorOfItem(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        String name = itemMeta.getDisplayName();
        HashMap<String, String> colors = new HashMap<String, String>() {{
            put("§8§lJack§7§lthe§f§lRipper", "§8");
        }};
        if (colors.containsKey(name)) {
            return colors.get(name);
        } else {
            return "§6";
        }
    }
}
