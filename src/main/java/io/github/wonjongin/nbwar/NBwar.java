package io.github.wonjongin.nbwar;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.File;



public final class NBwar extends JavaPlugin implements Listener {

    Runtime runtime = Runtime.getRuntime();
    Player player;
    public static double damage = 0;
    public static double armor = 0;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin enabled");
        getLogger().info("Thanks for using our plugin!!");
        File file = new File("./plugins/NBwar/Stat");
        if(!file.exists()){
            boolean result = file.mkdirs();
            if(result){
                getLogger().info("Make directory in plugin");
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

    @EventHandler
    public void join(PlayerJoinEvent event){
        event.setJoinMessage(ChatColor.GOLD+"입장하신 것을 환영합니다!!");
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.YELLOW + "입장하신 것을 환영합니다!!");
    }

    @Override
//    sender = 친 사람 cmd 명령 시작단어, args 명령본문 인
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("n")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Type the command to execute.");
            } else if (args[0].equalsIgnoreCase("info")) {
                sender.sendMessage(ChatColor.YELLOW + "A PVP plugin for minecraft 1.12.2");
            } else if (args[0].equalsIgnoreCase("hello")) {
                sender.sendMessage(ChatColor.GREEN + "Hello World!");
            } else if (args[0].equalsIgnoreCase("java")) {
                sender.sendMessage(ChatColor.GREEN + "Java is programming language!!");
            } else if (args[0].equalsIgnoreCase("power")) {
                sender.sendMessage(ChatColor.GREEN + "당신의 파워는 " + damage + " 입니다.");
            }else if(args[0].equalsIgnoreCase("state")){
                sender.sendMessage(ChatColor.BLACK+"당신의 래벨은 "+player.getLevel()+" 입니다.");
            } else if (args[0].equalsIgnoreCase("critical")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("drain")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("defend")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("heal")) {
                sender.sendMessage(ChatColor.GREEN + "/heal <amount>");
            } else if (args[0].equalsIgnoreCase("ram")) {
                showMemory();
//                runtime.gc();
//                double totalMemory = runtime.totalMemory()/1048576;
//                double memoryUsage = (runtime.totalMemory() - runtime.freeMemory())/1048576;
//                String resRamUsage = Double.toString(memoryUsage)+"MB/"+Double.toString(totalMemory)+"MB";
//                sender.sendMessage(ChatColor.GREEN + resRamUsage);
            } else {
                sender.sendMessage(ChatColor.RED + "Command Not Found!");
            }
            return true;
        }
        return false;
    }
    @EventHandler
    public void breakBlock(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack itemStack = new ItemStack(block.getType());
        player.getInventory().addItem(itemStack);
        player.sendMessage(ChatColor.GREEN + "Get block twice!!");
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
    public void giveItem(String args[]){

    }
    public void showMemory(){
//        MemoryMXBean membean = ManagementFactory.getMemoryMXBean();
//        MemoryUsage heap = membean.getHeapMemoryUsage();
//        MemoryUsage nonheap = membean.getNonHeapMemoryUsage();
        runtime.gc();
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        GlobalMemory globalMemory = hardwareAbstractionLayer.getMemory();
        double totalMemory = ((double)globalMemory.getTotal())/1024/1024;
        double availableMemory = ((double)globalMemory.getAvailable())/1024/1024;
        double usageMemory = totalMemory - availableMemory;
        String res = usageMemory + "MB/" + totalMemory + "MB";
        player.sendMessage(ChatColor.AQUA + res);
    }
}