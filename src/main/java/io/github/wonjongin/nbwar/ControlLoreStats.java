package io.github.wonjongin.nbwar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ControlLoreStats {
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

    public static void initLoreStats(ItemStack item) {
        LoreStats loreStats = new LoreStats();
        ItemMeta itemMeta = item.getItemMeta();
        if (!itemMeta.hasLore()) {
            itemMeta.setLore(Arrays.asList("init"));
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
