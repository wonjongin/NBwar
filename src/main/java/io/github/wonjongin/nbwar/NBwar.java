package io.github.wonjongin.nbwar;

import io.github.wonjongin.nbwar.Stat.Stat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class NBwar extends JavaPlugin implements Listener {

    Stat stat = new Stat();
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
                sender.sendMessage(ChatColor.GREEN + "준비중...");
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
    public void Chat(PlayerChatEvent event){
        Player player = event.getPlayer();
        long[] stat1 = new long[4];
        stat1 = stat.getStat(player.getUniqueId().toString());
        player.sendMessage("레벨: "+stat1[0]+"\n"+"공격력: "+stat1[1]+"크리티컬 확률: "+stat1[2]+"생명 흡혈"+stat1[3]);
    }

}