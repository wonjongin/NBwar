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
import org.bukkit.plugin.java.JavaPlugin;


public final class NBwar extends JavaPlugin implements Listener {

    Player player;
    public static double damage = 0;
    public static double armor = 0;

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
                sender.sendMessage(ChatColor.GREEN + "준비중...");
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
}