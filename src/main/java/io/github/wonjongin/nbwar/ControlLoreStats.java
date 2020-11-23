package io.github.wonjongin.nbwar;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.Arrays;
import java.util.List;

public class ControlLoreStats {
    public static void setLorePower(Player player, String powerStr) {
        int power = Integer.parseInt(powerStr);
        player.sendMessage("아이템 공격력을 " + power + "로 설정을 시도합니다...");
        player.sendMessage("손에 든 아이템 불러오는 중...");
        ItemStack item = player.getInventory().getItemInMainHand();
        player.sendMessage("아이템 속성 불러오는 중...");
        ItemMeta itemMeta = item.getItemMeta();
        player.sendMessage("아이템 속성 읽는 중...");
        LoreStats loreStats = new LoreStats().parseToLoreStats(item);
        player.sendMessage("1값: "+String.valueOf(loreStats.getPower()));
        for (int i = 0; i < loreStats.getAllLore().size(); i++) {
            player.sendMessage("값: "+String.valueOf(loreStats.getAllLore().get(i)));
        }
        player.sendMessage("아이템 속성 설정 중...");
        loreStats.setPower(power);
        itemMeta.setLore(loreStats.toLoreList());
    }

    public static void initLoreStats(ItemStack item) {
        LoreStats loreStats = new LoreStats();
        ItemMeta itemMeta = item.getItemMeta();
        if (!itemMeta.hasLore()) {
            itemMeta.setLore(Arrays.asList(""));
        }
        List<String> lore = itemMeta.getLore();
        for (int i = 0; i < loreStats.getStatsNames().size(); i++) {
            lore.add(loreStats.getStatsNames().get(i) + ": " + "0");
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
}
