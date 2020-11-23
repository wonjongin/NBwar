package io.github.wonjongin.nbwar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.ControlLoreStats.setLorePower;
import static io.github.wonjongin.nbwar.ControlPlayerStats.addHealthDouble;
import static io.github.wonjongin.nbwar.ControlPlayerStats.healthCommands;
import static io.github.wonjongin.nbwar.Develop.devCommand;
import static io.github.wonjongin.nbwar.FileIO.createFile;
import static io.github.wonjongin.nbwar.FileIO.readFile;
import static io.github.wonjongin.nbwar.GiveItem.giveItem;
import static io.github.wonjongin.nbwar.Hardware.checkram;
import static io.github.wonjongin.nbwar.Money.moneyCommands;
import static io.github.wonjongin.nbwar.Money.moneySetup;
import static io.github.wonjongin.nbwar.Op.*;
import static io.github.wonjongin.nbwar.Print.printLongLine;


public class NBwar extends JavaPlugin implements Listener {

    public static double damage = 0;
    public static double armor = 0;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin enabled");
        getLogger().info("Thanks for using our plugin!!");
        File file = new File("./plugins/NBwar/Stat");
        if (!file.exists()) {
            boolean result = file.mkdirs();
            if (result) {
                getLogger().info("Stat directory was created in plugin");
            } else {
                getLogger().warning("Fail to make directory in plugin");
            }
        }
        File moneyFile = new File("./plugins/NBwar/Money/Money.yml");
        if (!moneyFile.exists()) {
            createFile("./plugins/NBwar/Money/Money.yml", "{ uuid: money }");
        }
        File opFile = new File("./plugins/NBwar/Op.yml");
        if (!opFile.exists()) {
            createFile("./plugins/NBwar/Op.yml", "{ uuid: boolean}");
        }
        getServer().getPluginManager().registerEvents(this, this);
        // 이벤트 핸들링 하려면 반드시 필요함
        getLogger().info("EventHandler is enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin disabled");
    }

    @Override
//    sender = 친 사람 cmd 명령 시작단어, args 명령본문 인
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("n")) {
            Player player = (Player) sender;
            String[] commandsHelp = {
                    "Help!! Type /n <page> ",
                    "info - 플러그인 정보 ",
                    "item(it) - 아이템 관리 [얻기, 지우기...]",
                    "ram - 램 정보 보기 ",
                    "money(mn) - 돈 제어",
                    "power(p) - 공격력 제어",
                    "state(st) - 레벨 제어 (준비중)",
                    "critical(cri) - 크리티컬 제어(준비중)",
                    "drain(dr) - 체력흡수 제어(준비중)",
                    "defend(df) - 방어력 제어(준비중)",
                    "heal - 체력 제어(준비중)",
            };
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Type the command to execute.");
                printLongLine(player, commandsHelp, 1);
            } else if (isInteger(args[0])) {
                int pageNum = Integer.parseInt(args[0]);
                printLongLine(player, commandsHelp, pageNum);
            } else if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.YELLOW + "A PVP plugin for minecraft 1.12.2");
                printLongLine(player, commandsHelp, 1);
            } else if (args[0].equalsIgnoreCase("info")) {
                sender.sendMessage(ChatColor.YELLOW + "A PVP plugin for minecraft 1.12.2");
            } else if (args[0].equalsIgnoreCase("hello")) {
                sender.sendMessage(ChatColor.GREEN + "Hello World!");
            } else if (args[0].equalsIgnoreCase("java")) {
                sender.sendMessage(ChatColor.GREEN + "Java is programming language!!");
            } else if (args[0].equalsIgnoreCase("power") || args[0].equalsIgnoreCase("p")) {
                if (isOpPlayer(player)) {
                    setLorePower(player, args[1]);
                } else {
                    player.sendMessage(ChatColor.RED + "권한이 없습니다.");
                }
            } else if (args[0].equalsIgnoreCase("state") || args[0].equalsIgnoreCase("st")) {
                sender.sendMessage(ChatColor.BLACK + "당신의 래벨은 " + player.getLevel() + " 입니다.");
            } else if (args[0].equalsIgnoreCase("critical") || args[0].equalsIgnoreCase("cri")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("drain") || args[0].equalsIgnoreCase("dr")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("defend") || args[0].equalsIgnoreCase("df")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("heal") || args[0].equalsIgnoreCase("hl")) {
                healthCommands(player, args);
            } else if (args[0].equalsIgnoreCase("item") || args[0].equalsIgnoreCase("it")) {
                giveItem(player, args);
            } else if (args[0].equalsIgnoreCase("test") || args[0].equalsIgnoreCase("tt")) {
                createFile("./plugins/NBwar/test/test.txt", "test code \r\nhello world\r\nthree line");
                ArrayList<String> testread = readFile("./plugins/NBwar/test/test.txt");
                for (int i = 0; i < testread.size(); i++) {
                    player.sendMessage(ChatColor.GREEN + testread.get(i));
                    getLogger().info(testread.get(i));
                }
            } else if (args[0].equalsIgnoreCase("money") || args[0].equalsIgnoreCase("mn")) {
                moneyCommands(player, args);
            } else if (args[0].equalsIgnoreCase("ram")) {
                checkram(player);
//                showMemory(sender);
//                runtime.gc();
//                double totalMemory = runtime.totalMemory()/1048576;
//                double memoryUsage = (runtime.totalMemory() - runtime.freeMemory())/1048576;
//                String resRamUsage = Double.toString(memoryUsage)+"MB/"+Double.toString(totalMemory)+"MB";
//                sender.sendMessage(ChatColor.GREEN + resRamUsage);
            } else if (args[0].equalsIgnoreCase("dev")) {
                devCommand(player, args);
            } else if (args[0].equalsIgnoreCase("op")) {
                setOp(player, player.getServer().getPlayer(args[1]), Boolean.parseBoolean(args[2]));
            } else {
                sender.sendMessage(ChatColor.RED + "Command Not Found!!");
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        moneySetup(player);
        opSetup(player);
        event.setJoinMessage(ChatColor.AQUA + "Welcome to NBwar!");
        getLogger().info(player.getName() + " came into server!");
        damage = player.getLevel();
        armor = player.getLevel();
    }

    @EventHandler
    public void PlayerLevelup(PlayerLevelChangeEvent event) {
        Player player = event.getPlayer();
        damage = player.getLevel();
        armor = player.getLevel();
    }

    @EventHandler
    public void PlayerStat(EntityDamageByEntityEvent event) {
//        double plusdamage = event.getDamage() + damage;
//        double minusdamage = event.getDamage() - armor;
//        if (minusdamage < 0) {
//            minusdamage = 0;
//        }
//
//        if (event.getDamager() instanceof Player) {
//            Player player = (Player) event.getDamager();
//            event.setDamage(plusdamage);
//            player.sendMessage((int) plusdamage + "의 피해를 입혔습니다.");
//        }
//
//        if (event.getEntity() instanceof Player) {
//            Player player = (Player) event.getEntity();
//            event.setDamage(minusdamage);
//            player.sendMessage((int) minusdamage + "의 피해를 입었습니다.");
//        }
        double totalDamage = 0;
        boolean loreSender = false;
        boolean loreReceiver = false;
        int ignoreDefend = 0;
        EntityType entityType = event.getEntityType();
        Player sender = (Player) event.getDamager();
        Player receiver = (Player) event.getEntity();
        ItemStack itemOfSender = sender.getInventory().getItemInMainHand();
        ItemStack itemOfReceiver = receiver.getInventory().getItemInMainHand();
        ItemMeta itemMetaOfSender = itemOfSender.getItemMeta();
        ItemMeta itemMetaOfReceiver = itemOfReceiver.getItemMeta();

        try {
            loreSender = true;
            LoreStats loreStatsOfSender = new LoreStats().parseToLoreStats(itemOfSender);
            totalDamage += loreStatsOfSender.getPower();
            addHealthDouble(sender, sender, (double) loreStatsOfSender.getDrain());
            ignoreDefend = loreStatsOfSender.getIgnoreDefend();
        } catch (Exception e) {
            loreSender = false;
        }

        try {
            loreReceiver = true;
            LoreStats loreStatsOfReceiver = new LoreStats().parseToLoreStats(itemOfReceiver);
            if (loreStatsOfReceiver.getDefend() - ignoreDefend >= 0) {
                totalDamage -= loreStatsOfReceiver.getDefend() - ignoreDefend;
            }
        } catch (Exception e) {
            loreReceiver = false;
        }

        if (loreSender || loreReceiver) {
            event.setDamage(totalDamage);
        } else {
            event.setDamage(event.getDamage());

        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack breakitem = event.getPlayer().getInventory().getItemInMainHand();
        if (breakitem.getType() != Material.WOOD_AXE && breakitem.getType() != Material.GOLD_AXE) {
            ItemStack itemStack = new ItemStack(block.getType());
            player.sendMessage(ChatColor.GREEN + "Block Num: " + ChatColor.YELLOW + block.getTypeId());
        }
        Location location = block.getLocation();

        // player.getWorld().getBlockAt(location).setType(block.getType());


        // player.getInventory().addItem(itemStack);
        // player.sendMessage(ChatColor.GREEN + "Get block twice!!");

    }


}