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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.Basic.randomOfPercent;
import static io.github.wonjongin.nbwar.ControlLoreStats.setLoreMaster;
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
import static io.github.wonjongin.nbwar.Permit.*;
import static io.github.wonjongin.nbwar.Potion.isPotion;
import static io.github.wonjongin.nbwar.Potion.rightClickPotion;
import static io.github.wonjongin.nbwar.Print.printLongLine;
import static io.github.wonjongin.nbwar.hangul.addNumO;


public class NBwar extends JavaPlugin implements Listener {

    public static double damage = 0;
    public static double armor = 0;
    private static NBwar instance;

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
        File permitDir = new File("./plugins/NBwar/Permit");
        if (!permitDir.exists()) {
            boolean result = permitDir.mkdirs();
        }
        File breakPermitFile = new File("./plugins/NBwar/Permit/break.yml");
        if (!breakPermitFile.exists()) {
            createFile("./plugins/NBwar/Permit/break.yml", "{ uuid: boolean }");
        }
        File helpFile = new File("./plugins/NBwar/help/init.txt");
        if (!helpFile.exists()) {
            createFile("./plugins/NBwar/help/init.txt", "Help messages of NBwar");
        }
        getServer().getPluginManager().registerEvents(this, this);
        // 이벤트 핸들링 하려면 반드시 필요함
        getLogger().info("EventHandler is enabled");

        boolean useHolographicDisplays = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays");
        // call holographic display

        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin disabled");
    }

    @Override
    // sender = 친 사람 cmd 명령 시작단어, args 명령본문 인
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("n")) {
            Player player = (Player) sender;
            String[] commandsHelp = {
                    "Help!! Type /n <page> ",
                    "info - 플러그인 정보 ",
                    "money(mn) - 돈 제어",
                    "heal(hl) - 체력 제어",
                    "potion(pt) - 포션 제어",
                    "power(p) - 공격력 제어",
                    "drain(dr) - 체력흡수 제어",
                    "critical(cri) - 크리티컬 제어",
                    "criticalPer(criper) - 크리티컬 확률 제어",
                    "defend(df) - 방어력 제어",
                    "igDefend(id) - 방어무시 제어",
                    "state(st) - 레벨 제어 (준비중)",
                    "item(it) - 아이템 관리 [얻기, 지우기...]",
                    "permit(pr) - 권한 관리",
                    "op - 오피 관리",
                    "ram - 램 정보 보기 ",
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
                setLoreMaster(player, "power", args[1]);
            } else if (args[0].equalsIgnoreCase("state") || args[0].equalsIgnoreCase("st")) {
                sender.sendMessage(ChatColor.BLACK + "당신의 래벨은 " + player.getLevel() + " 입니다.");
            } else if (args[0].equalsIgnoreCase("critical") || args[0].equalsIgnoreCase("cri")) {
                setLoreMaster(player, "critical", args[1]);
            } else if (args[0].equalsIgnoreCase("criticalPer") || args[0].equalsIgnoreCase("criper")) {
                setLoreMaster(player, "criticalPercent", args[1]);
            } else if (args[0].equalsIgnoreCase("drain") || args[0].equalsIgnoreCase("dr")) {
                setLoreMaster(player, "drain", args[1]);
            } else if (args[0].equalsIgnoreCase("defend") || args[0].equalsIgnoreCase("df")) {
                setLoreMaster(player, "defend", args[1]);
                // setLoreDefend(player, args[1]);
            } else if (args[0].equalsIgnoreCase("igDefend") || args[0].equalsIgnoreCase("id")) {
                setLoreMaster(player, "ignoreDefend", args[1]);
            } else if (args[0].equalsIgnoreCase("heal") || args[0].equalsIgnoreCase("hl")) {
                healthCommands(player, args);
            } else if (args[0].equalsIgnoreCase("potion") || args[0].equalsIgnoreCase("pt")) {
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
                // showMemory(sender);
                // runtime.gc();
                // double totalMemory = runtime.totalMemory()/1048576;
                // double memoryUsage = (runtime.totalMemory() - runtime.freeMemory())/1048576;
                // String resRamUsage = Double.toString(memoryUsage)+"MB/"+Double.toString(totalMemory)+"MB";
                // sender.sendMessage(ChatColor.GREEN + resRamUsage);
            } else if (args[0].equalsIgnoreCase("dev")) {
                devCommand(player, args);
            } else if (args[0].equalsIgnoreCase("permit") || args[0].equalsIgnoreCase("pr")) {
                if (isOpPlayer(player)) permitCommands(player, args);
                else player.sendMessage(ChatColor.RED + "권한이 없습니다.");
            } else if (args[0].equalsIgnoreCase("op")) {
                if (args.length == 1 || args.length == 2) {
                    player.sendMessage(ChatColor.RED + "/n op <userName> <0 or 1>");
                } else {
                    setOp(player, player.getServer().getPlayer(args[1]), args[2]);
                }
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
        disableBreak(player);
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
        // double plusdamage = event.getDamage() + damage;
        // double minusdamage = event.getDamage() - armor;
        // if (minusdamage < 0) {
        //     minusdamage = 0;
        // }
        //
        // if (event.getDamager() instanceof Player) {
        //     Player player = (Player) event.getDamager();
        //     event.setDamage(plusdamage);
        //     player.sendMessage((int) plusdamage + "의 피해를 입혔습니다.");
        // }
        //
        // if (event.getEntity() instanceof Player) {
        //     Player player = (Player) event.getEntity();
        //     event.setDamage(minusdamage);
        //     player.sendMessage((int) minusdamage + "의 피해를 입었습니다.");
        // }
        double totalDamage = 0;
        boolean receiverisPlayer = event.getEntityType() == EntityType.PLAYER;
        boolean loreSender = false;
        boolean loreReceiver = false;
        int ignoreDefend = 0;

        Player sender = (Player) event.getDamager();

        ItemStack itemOfSender = sender.getInventory().getItemInMainHand();


        try {
            loreSender = true;
            LoreStats loreStatsOfSender = new LoreStats().parseToLoreStats(itemOfSender);
            totalDamage += loreStatsOfSender.getPower();
            addHealthDouble(sender, sender, (double) loreStatsOfSender.getDrain(), 1);
            ignoreDefend = loreStatsOfSender.getIgnoreDefend();
            if (randomOfPercent(loreStatsOfSender.getCriticalPercent())) {
                totalDamage += loreStatsOfSender.getCritical();
                sender.sendMessage(ChatColor.DARK_RED + "크리티컬!!");
            }
        } catch (Exception e) {
            loreSender = false;
        }

        try {
            if (receiverisPlayer) {
                Player receiver = (Player) event.getEntity();
                ItemStack itemOfReceiver = receiver.getInventory().getItemInMainHand();
                loreReceiver = true;
                LoreStats loreStatsOfReceiver = new LoreStats().parseToLoreStats(itemOfReceiver);
                if (loreStatsOfReceiver.getDefend() - ignoreDefend >= 0) {
                    receiver.sendMessage(ChatColor.GREEN + String.format("%s 방어했습니다.", addNumO(loreStatsOfReceiver.getDefend())));
                    totalDamage -= loreStatsOfReceiver.getDefend();
                    totalDamage += ignoreDefend;
                }
            }
            loreReceiver = true;
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
        if (breakitem.getType() != Material.WOOD_AXE) {
            ItemStack itemStack = new ItemStack(block.getType());
            player.sendMessage(ChatColor.GREEN + "Block Num: " + ChatColor.YELLOW + block.getTypeId());
            if (breakitem.getType().equals(Material.GOLD_AXE)) {
                Location location = block.getLocation();
                event.setCancelled(true);
            }
        }
        Location location = block.getLocation();

        if (!canBreak(player)) {
            event.setCancelled(true);
        }

        // player.getWorld().getBlockAt(location).setType(block.getType());


        // player.getInventory().addItem(itemStack);
        // player.sendMessage(ChatColor.GREEN + "Get block twice!!");

    }

    @EventHandler
    public void rightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        String itemName = itemStack.getItemMeta().getDisplayName();
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (isPotion(itemName)) {
                rightClickPotion(player, itemName);
            }
        }
    }

    public static NBwar getPlugin() {
        return instance;
    }
}