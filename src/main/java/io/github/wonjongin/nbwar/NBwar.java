package io.github.wonjongin.nbwar;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.FileIO.createFile;
import static io.github.wonjongin.nbwar.FileIO.readFile;
import static io.github.wonjongin.nbwar.GiveItem.giveItem;
import static io.github.wonjongin.nbwar.Hardware.checkram;
import static io.github.wonjongin.nbwar.Money.moneyCommands;
import static io.github.wonjongin.nbwar.Print.printLongLine;


public final class NBwar extends JavaPlugin implements Listener {

    Player player;
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
                    "power(p) - 공격력 제어 (준비중)",
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
                sender.sendMessage(ChatColor.GREEN + "당신의 파워는 " + damage + " 입니다.");
            } else if (args[0].equalsIgnoreCase("state") || args[0].equalsIgnoreCase("st")) {
                sender.sendMessage(ChatColor.BLACK + "당신의 래벨은 " + player.getLevel() + " 입니다.");
            } else if (args[0].equalsIgnoreCase("critical") || args[0].equalsIgnoreCase("cri")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("drain") || args[0].equalsIgnoreCase("dr")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("defend") || args[0].equalsIgnoreCase("df")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("heal")) {
                sender.sendMessage(ChatColor.GREEN + "/heal <amount>");
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
            } else {
                sender.sendMessage(ChatColor.RED + "Command Not Found!!");
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
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
        double plusdamage = event.getDamage() + damage;
        double minusdamage = event.getDamage() - armor;
        if (minusdamage < 0) {
            minusdamage = 0;
        }

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            event.setDamage(plusdamage);
            player.sendMessage((int) plusdamage + "의 피해를 입혔습니다.");
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            event.setDamage(minusdamage);
            player.sendMessage((int) minusdamage + "의 피해를 입었습니다.");
        }
    }

    public void showMemory(CommandSender sender) {
        MemoryMXBean membean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = membean.getHeapMemoryUsage();
        MemoryUsage nonheap = membean.getNonHeapMemoryUsage();
        String res = nonheap + "\n" + heap;
        sender.sendMessage(ChatColor.AQUA + "---------< RAM USAGE >---------");
        sender.sendMessage(ChatColor.AQUA + res);
        sender.sendMessage(ChatColor.AQUA + "-------------------------------");
//        SystemInfo systemInfo = new SystemInfo();
//        final HardwareAbstractionLayer hw = systemInfo.getHardware();
//        final GlobalMemory memory = hw.getMemory();
//        double totalMemory = ((double)memory.getTotal())/1024/1024;
//        double availableMemory = ((double)memory.getAvailable())/1024/1024;
//        double usageMemory = totalMemory - availableMemory;
//        String res = usageMemory + "MB/" + totalMemory + "MB";
//        player.sendMessage(ChatColor.AQUA + res);
    }
}