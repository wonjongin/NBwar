package io.github.wonjongin.nbwar;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

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

    public static void setLoreMaster(Player player, String stat, String valueStr){
        int value = Integer.parseInt(valueStr);
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();
        LoreStats loreStats = new LoreStats().parseToLoreStats(item);
        String headMsg = "";
        switch (stat){
            case "power":
                loreStats.setPower(value);
                headMsg = "공격력이";
                break;
            case "defend":
                loreStats.setDefend(value);
                headMsg = "방어력이";
                break;
            default:
                return;
        }

        itemMeta.setLore(loreStats.toLoreList());
        item.setItemMeta(itemMeta);
        player.sendMessage(ChatColor.YELLOW + String.format("%s %d (으)로 설정되었습니다.", headMsg,value));
    }
    public static void setNBTPower(Player player, String powerStr){
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
            lore.add(ChatColor.YELLOW+loreStats.getStatsNames().get(i) + ": " + "0");
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
