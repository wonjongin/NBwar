package io.github.wonjongin.nbwar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.Print.printLongLine;

public class Potion {
    public void potionCommand(Player player, String[] args){
        String[] portionCommandList = {
                "portion Commands",
                "Type /n heal <command>",
                "add(a) - 포션 추가 ",
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
            printLongLine(player, portionCommandList, 1);
        } else if (isInteger(args[1])) {
            int nowPage = Integer.parseInt(args[1]);
            printLongLine(player, portionCommandList, nowPage);
        } else if (args[1].equalsIgnoreCase("setlim") || args[1].equalsIgnoreCase("sl")) {

        } else {
            player.sendMessage("Commads Not Found!!");
        }
    }

    public static ArrayList<String> getPotions(){
        return new ArrayList<>(Arrays.asList(
                "StdTea"
        ));
    }


    public static void potionMaster(Player player, String potionName, int amount){

    }

    public static boolean isPotion(String name){
        return getPotions().contains(name);
    }

    public static void rightClickPotion(Player player, String potionName){
        
    }
}
