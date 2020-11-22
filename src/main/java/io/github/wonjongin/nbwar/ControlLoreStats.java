package io.github.wonjongin.nbwar;

import lombok.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ControlLoreStats {
    public static void setLorePower(Player player, String powerStr){
        int power = Integer.parseInt(powerStr);
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.getLore();
        LoreStats loreStats = new LoreStats();

    }
    public static void setLoreStats(LoreStats loreStats, Item item){
        ItemMeta itemMeta = item.getItemStack().getItemMeta();
        List<String> lore = itemMeta.getLore();
        for (int i = 0; i < lore.size(); i++) {
            lore.add(loreStats.getStatsNames()[i]+": "+String.valueOf(loreStats.getAllLore()[i]));
        }
        itemMeta.setLore(lore);
        item.getItemStack().setItemMeta(itemMeta);
    }
}
