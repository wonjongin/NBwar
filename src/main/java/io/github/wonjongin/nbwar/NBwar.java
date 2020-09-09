package io.github.wonjongin.nbwar;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class NBwar extends JavaPlugin {
    Runtime runtime = Runtime.getRuntime();
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin enabled");
        getLogger().info("Thanks for using our plugin!!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin disabled");
    }

    @EventHandler
    public void join(PlayerJoinEvent event){
        event.setJoinMessage(ChatColor.GOLD+"입장하신 것을 환영합니다!!");
    }

    @Override
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
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("critical")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("drain")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("defend")) {
                sender.sendMessage(ChatColor.GREEN + "준비중...");
            } else if (args[0].equalsIgnoreCase("heal")) {
                sender.sendMessage(ChatColor.GREEN + "/heal <amount>");
            } else if (args[0].equalsIgnoreCase("ram")) {
                runtime.gc();
                long totalMemory = runtime.totalMemory();
                long memoryUsage = runtime.totalMemory() - runtime.freeMemory();
                String resRamUsage = Long.toString(memoryUsage)+"/"+Long.toString(totalMemory);
                sender.sendMessage(ChatColor.GREEN + resRamUsage);
            } else {
                sender.sendMessage(ChatColor.RED + "Command Not Found");
            }
            return true;
        }
        return false;
    }
}