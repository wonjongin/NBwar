package io.github.wonjongin.nbwar;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.Print.printLongLine;


public class GiveItem {
    public static void giveItem(Player player, String[] args){
        PlayerInventory pi = player.getInventory();
        String[] itemCommandList = {
                "Item Commands",
                "Type /n item <command>",
                "preset(p) - 아이템 프리셋 ",
                "search(s) - 아이템 검색 ",
                "list(l) - 아이템 리스트 ",
                "remove(rm) - 아이템 삭제 ",
        };
        if(args.length==1){
//            for (String s : itemCommandList) {
//                player.sendMessage(ChatColor.GOLD + s);
//            }
            printLongLine(player, itemCommandList,1);
        } else if(isInteger(args[1])){
            int nowPage = Integer.parseInt(args[1]);
            printLongLine(player, itemCommandList, nowPage);
        } else if(args[1].equalsIgnoreCase("list")||args[1].equalsIgnoreCase("l")){
            if(args.length==2){
                player.sendMessage(ChatColor.GOLD + "--< Item Classes >--");
                player.sendMessage(ChatColor.GOLD + "1: 건축 / 2: 장식 / 3: 식료품 / 4: 도구");
                player.sendMessage(ChatColor.GOLD + "5: 무기 / 6: 레드스톤 / 7: 기타 / 8: 물약");
                player.sendMessage(ChatColor.GOLD + "--------------------");
                player.sendMessage(ChatColor.GOLD + "Type /n item list <classNum>");
            }
        } else if(args[1].equalsIgnoreCase("search")||args[1].equalsIgnoreCase("s")){
            if(args.length==2){
                player.sendMessage(ChatColor.GOLD+"Type search argument");
            } else {
                searchItem(args[2]);
            }
        } else if(args[1].equalsIgnoreCase("preset")||args[1].equalsIgnoreCase("p")){
            if(args.length==2){
                player.sendMessage(ChatColor.GOLD+"Type preset name");
                player.sendMessage(ChatColor.GOLD+"Type /n item presets <presetName>");
                player.sendMessage(ChatColor.GOLD+"Presets list: /n item presets list");
            } else {
                presetItem(player, args[2]);
            }
        } else if(args[1].equalsIgnoreCase("remove")||args[1].equalsIgnoreCase("rm")){
            if(args.length==2){
                player.sendMessage(ChatColor.GOLD+"Type rm name");
            } else if(args[2].equalsIgnoreCase("all")){
                removeAllItems(player);
            }

        } else {
            ItemStack cobblestone = new ItemStack(Material.COBBLESTONE,64);
            ItemStack bread = new ItemStack(Material.BREAD,64);
            pi.addItem(cobblestone);
            pi.addItem(bread);
            player.sendMessage(ChatColor.YELLOW + "Check inventory!!");
        }

    }
    public static void searchItem(String arg){
        String res = "None....";
    }
    public static void presetItem(Player player, String arg){
        PlayerInventory pi = player.getInventory();
            String[] presetsList = {
                    "Preset Lists",
                    "boom - 폭발물 ",
                    "harvest - 농사 ",
                    "war - 전쟁 ",
                    "heal - 힐 ",
                    "comb - 커맨드 블록 ",
                    "fire - 불 피우기 ",
                    "iron - 철 ",
                    "train - 기차 ",
                    "house - 집짓기 ",
                    "minor - 광질 ",
                    "egg - 목축 ",
            };
        if(arg.equalsIgnoreCase("list")||arg.equalsIgnoreCase("l")){
//            for (String s : presetsList) {
//                player.sendMessage(ChatColor.GOLD + s);
//            }
            printLongLine(player,  presetsList, 1);
        } else if(isInteger(arg)){
            int nowPage = Integer.parseInt(arg);
            printLongLine(player, presetsList, nowPage);
        } else if(arg.equalsIgnoreCase("boom")){
            ItemStack tnt = new ItemStack(Material.TNT, 64);
            ItemStack charger = new ItemStack(Material.FLINT_AND_STEEL);
            pi.addItem(tnt);
            pi.addItem(charger);
        } else if(arg.equalsIgnoreCase("harvest")){
            ItemStack[] harvest = {
                    new ItemStack(Material.SEEDS,64),
                    new ItemStack(Material.BEETROOT_SEEDS,64),
                    new ItemStack(Material.MELON_SEEDS,64),
                    new ItemStack(Material.PUMPKIN_SEEDS,64),
                    new ItemStack(Material.IRON_HOE,2),
                    new ItemStack(Material.WATER_BUCKET,4)
            };
            giveItemsFor(player, harvest);
        } else if(arg.equalsIgnoreCase("war")){
            ItemStack[] weapons = {
                    new ItemStack(Material.IRON_SWORD,2),
                    new ItemStack(Material.SHIELD,2),
                    new ItemStack(Material.BOW,2),
                    new ItemStack(Material.ARROW,64),
                    new ItemStack(Material.COOKED_CHICKEN,64),
                    new ItemStack(Material.BREAD,64),
                    new ItemStack(Material.SPECTRAL_ARROW,64),
            };
            ItemStack[] looks = {
                    new ItemStack(Material.IRON_HELMET),
                    new ItemStack(Material.IRON_CHESTPLATE),
                    new ItemStack(Material.IRON_LEGGINGS),
                    new ItemStack(Material.IRON_BOOTS)
            };
            giveItemsFor(player, weapons);
            wearItemsFor(player, looks);
        } else if(arg.equalsIgnoreCase("fire")){
            ItemStack[] fire = {
                    new ItemStack(Material.FLINT_AND_STEEL,2),
                    new ItemStack(Material.FIRE,64)
            };
            giveItemsFor(player, fire);
        } else if(arg.equalsIgnoreCase("iron")){
            ItemStack[] iron = {
                    new ItemStack(Material.IRON_INGOT,64),
                    new ItemStack(Material.FURNACE,64),
                    new ItemStack(Material.COAL,64),
                    new ItemStack(Material.STICK,64),
                    new ItemStack(Material.ENCHANTMENT_TABLE,20),
                    new ItemStack(Material.IRON_ORE,64),
            };
            giveItemsFor(player, iron);
        } else if(arg.equalsIgnoreCase("train")){
            ItemStack[] train = {
                    new ItemStack(Material.RAILS,64),
                    new ItemStack(Material.MINECART,3),
                    new ItemStack(Material.POWERED_RAIL,64),
                    new ItemStack(Material.DETECTOR_RAIL,64),
                    new ItemStack(Material.LEVER,20),
                    new ItemStack(Material.REDSTONE_TORCH_ON,64),
            };
            giveItemsFor(player, train);
        } else if(arg.equalsIgnoreCase("house")){
            ItemStack[] house = {
                    new ItemStack(Material.DIRT,64),
                    new ItemStack(Material.COBBLESTONE,64),
                    new ItemStack(Material.GLASS,64),
                    new ItemStack(Material.CONCRETE,64),
                    new ItemStack(Material.WOOD_DOOR,64),
                    new ItemStack(Material.WOOD_STAIRS,64),
            };
            giveItemsFor(player, house);
        } else if(arg.equalsIgnoreCase("minor")){
            ItemStack[] minor = {
                    new ItemStack(Material.TNT,64),
                    new ItemStack(Material.FLINT_AND_STEEL,3),
                    new ItemStack(Material.IRON_PICKAXE,5),
                    new ItemStack(Material.COOKED_CHICKEN,64),
                    new ItemStack(Material.BREAD,64),
                    new ItemStack(Material.IRON_SWORD ,2),
                    new ItemStack(Material.TORCH ,64),
            };
            giveItemsFor(player, minor);
        } else if(arg.equalsIgnoreCase("comb")){
            ItemStack[] comb = {
                    new ItemStack(Material.COMMAND,64),
                    new ItemStack(Material.STONE_BUTTON,64),
                    new ItemStack(Material.STONE_PLATE,64),
                    new ItemStack(Material.REDSTONE_TORCH_ON ,64),
                    new ItemStack(Material.REDSTONE_BLOCK ,64),
                    new ItemStack(Material.LEVER,64),
                    new ItemStack(Material.WOOD_BUTTON,64),
                    new ItemStack(Material.IRON_PLATE,64),
                    new ItemStack(Material.BEACON,64),
            };
            giveItemsFor(player, comb);
        } else if(arg.equalsIgnoreCase("egg")){
            ItemStack[] egg = {
                    new ItemStack(Material.MOB_SPAWNER,64),
                    new ItemStack(Material.MONSTER_EGG,64),
                    new ItemStack(Material.MONSTER_EGGS,64),
                    new ItemStack(Material.FENCE ,64),
                    new ItemStack(Material.FENCE_GATE ,64),
                    new ItemStack(Material.WHEAT ,64),
                    new ItemStack(Material.CARROT,5),
                    new ItemStack(Material.SEEDS,64),
                    new ItemStack(Material.BUCKET,64),
                    new ItemStack(Material.DRAGON_EGG,64),
                    new ItemStack(Material.EGG,64),
                    new ItemStack(Material.SHEARS,64),
            };
            giveItemsFor(player, egg);
        } else if(arg.equalsIgnoreCase("heal")){
            ItemStack[] heal = {
                    new ItemStack(Material.MOB_SPAWNER,64),
                    new ItemStack(Material.MONSTER_EGG,64),
                    new ItemStack(Material.MONSTER_EGGS,64),
                    new ItemStack(Material.FENCE ,64),
                    new ItemStack(Material.FENCE_GATE ,64),
                    new ItemStack(Material.WHEAT ,64),
                    new ItemStack(Material.CARROT,5),
                    new ItemStack(Material.SEEDS,64),
                    new ItemStack(Material.BUCKET,64),
                    new ItemStack(Material.DRAGON_EGG,64),
                    new ItemStack(Material.EGG,64),
                    new ItemStack(Material.SHEARS,64),
            };
            giveItemsFor(player, heal);
        } else {
            player.sendMessage(ChatColor.RED + "Preset Not Found!!");
        }
    }
    public static void giveItemsFor(Player player, ItemStack[] itemStacks){
        PlayerInventory pi = player.getInventory();
        for (ItemStack itemStack : itemStacks) {
            pi.addItem(itemStack);
            String res = itemStack + " is given!";
            player.sendMessage(ChatColor.GREEN + res);
        }
    }
    public static void wearItemsFor(Player player, ItemStack[] itemStacks){
        PlayerInventory pi = player.getInventory();
        pi.setHelmet(itemStacks[0]);
        pi.setChestplate(itemStacks[1]);
        pi.setLeggings(itemStacks[2]);
        pi.setBoots(itemStacks[3]);
        for (ItemStack itemStack : itemStacks) {
            String res = "You wear " + itemStack + " !";
            player.sendMessage(ChatColor.GREEN + res);
        }
    }
    public static void removeAllItems(Player player){
        PlayerInventory pi = player.getInventory();
        int size = pi.getSize();
        for(int i = 0;i<size;i++){
            ItemStack itemStack = pi.getItem(i);
            if(itemStack==null) {
                continue;
            } else {
                pi.clear(i);
            }
        }
    }
}
