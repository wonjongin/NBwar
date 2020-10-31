package io.github.wonjongin.nbwar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.Print.printLongLine;

public class ControlPlayerStats {
    public static void healthCommands(Player player, String[] args) {
        String[] healthCommandList = {
                "Health Commands",
                "Type /n heal <command>",
                "add(a) - 생명력 추가 ",
                "setlim(sl) - 생명력 한계 설정",
                "send(s) - 생명력 보내기 (준비중)",
                "subs(u) - 생명력 감소 (준비중)",
                "set(st) - 생명력 설정 (준비중)",
        };
        if (args.length == 1) {
            //            for (String s : itemCommandList) {
            //                player.sendMessage(ChatColor.GOLD + s);
            //            }
            printLongLine(player, healthCommandList, 1);
        } else if (isInteger(args[1])) {
            int nowPage = Integer.parseInt(args[1]);
            printLongLine(player, healthCommandList, nowPage);
        } else if (args[1].equalsIgnoreCase("setlim") || args[1].equalsIgnoreCase("sl")) {
            if (args.length < 3) {
                player.sendMessage(ChatColor.RED + "유저이름과 양을 입력하세요");
            } else if (args.length == 3) {
                setLimitHealth(player, player, Double.parseDouble(args[2]));
            } else {
                setLimitHealth(player, Bukkit.getServer().getPlayer(args[2]), Double.parseDouble(args[3]));
            }
        } else if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("a")) {
            if (args.length < 3) {
                player.sendMessage(ChatColor.RED + "유저이름과 양을 입력하세요");
            } else if (args.length == 3) {
                addHealthDouble(player, player, Double.parseDouble(args[2]));
            } else {
                addHealthDouble(player, Bukkit.getServer().getPlayer(args[2]), Double.parseDouble(args[3]));
            }
        } else {
            player.sendMessage("Commads Not Found!!");
        }
    }

    public static void addHealthDouble(Player sender, Player receiver, double amountofHeal) {
        double maxHealth = receiver.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        double initialHealth = receiver.getHealth();
        double finalHealth = initialHealth + amountofHeal;
        if (finalHealth >= maxHealth) {
            finalHealth = maxHealth;
            amountofHeal = maxHealth - initialHealth;
        }
        receiver.setHealth(finalHealth);
        sender.sendMessage(ChatColor.GOLD + receiver.getName() + "의 체력을 " + Double.toString(amountofHeal) + " 만큼 더했습니다.");
        receiver.sendMessage(ChatColor.YELLOW + sender.getName() + "이 당신의 체력을 " + Double.toString(amountofHeal) + " 만큼 더했습니다.");
    }

    public static void setLimitHealth(Player sender, Player receiver, double value) {
        receiver.setHealthScaled(false);
        // receiver.setHealthScale(value);
        receiver.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(value);
        sender.sendMessage(ChatColor.GOLD + receiver.getName() + "의 한계체력을 " + Double.toString(value) + " 로 설정했습니다.");
        receiver.sendMessage(ChatColor.YELLOW + sender.getName() + "이 당신의 한계체력을 " + Double.toString(value) + " 로 설정했습니다.");
    }
}
