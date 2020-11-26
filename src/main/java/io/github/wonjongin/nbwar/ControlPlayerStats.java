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
                "full(f) - 생명력 가득 채우기",
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
                addHealthDouble(player, player, Double.parseDouble(args[2]), 1);
            } else {
                addHealthDouble(player, Bukkit.getServer().getPlayer(args[2]), Double.parseDouble(args[3]), 2);
            }
        } else if (args[1].equalsIgnoreCase("full") || args[1].equalsIgnoreCase("f")) {
            fullHealth(player);
        } else {
            player.sendMessage("Commads Not Found!!");
        }
    }

    public static void addHealthDouble(Player sender, Player receiver, double amountofHeal, int mode) {
        // mode - 2: 둘다 메시지, 1: 피흡용
        double maxHealth = receiver.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        double initialHealth = receiver.getHealth();
        double finalHealth = initialHealth + amountofHeal;
        if (finalHealth >= maxHealth) {
            finalHealth = maxHealth;
            amountofHeal = maxHealth - initialHealth;
        }
        receiver.setHealth(finalHealth);
        if (mode == 1) {
            sender.sendMessage(ChatColor.YELLOW+"체력이 "+amountofHeal+" 만큼 더해졌습니다.");
        } else if (mode == 2){
            sender.sendMessage(ChatColor.GOLD + receiver.getName() + "의 체력을 " + amountofHeal + " 만큼 더했습니다.");
            receiver.sendMessage(ChatColor.YELLOW + sender.getName() + "이 당신의 체력을 " + amountofHeal + " 만큼 더했습니다.");
        }
    }

    public static void fullHealth(Player player) {
        String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if (name.equals("§4§lLeo§6§lni§f§ldas")) {
            double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            player.setHealth(maxHealth);
            player.sendMessage(ChatColor.GREEN + "생명력을 모두 채웠습니다.");
        } else {
            player.sendMessage("§4§lLeo§6§lni§f§ldas" + ChatColor.RED + " 를 들고 하십쇼");
        }
    }

    public static void setLimitHealth(Player sender, Player receiver, double value) {
        receiver.setHealthScaled(false);
        // receiver.setHealthScale(value);
        receiver.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(value);
        sender.sendMessage(ChatColor.GOLD + receiver.getName() + "의 한계체력을 " + Double.toString(value) + " 로 설정했습니다.");
        receiver.sendMessage(ChatColor.YELLOW + sender.getName() + "이 당신의 한계체력을 " + Double.toString(value) + " 로 설정했습니다.");
    }
}
