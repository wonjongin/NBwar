package io.github.wonjongin.nbwar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.FileIO.readFileOnce;
import static io.github.wonjongin.nbwar.Print.printLongLine;
import static org.bukkit.Bukkit.getLogger;

public class Permit {
    public static void permitCommands(Player player, String[] args) {
        String[] moneyCommandList = {
                "Permit Commands",
                "Type /n pr <command>",
                "tbreak(tb) - 블록부수기 가능 ",
                "fbreakf(fb) - 블록부수기 불가능 ",

        };
        if (args.length == 1) {
            // for (String s : itemCommandList) {
            //     player.sendMessage(ChatColor.GOLD + s);
            // }
            printLongLine(player, moneyCommandList, 1);
        } else if (isInteger(args[1])) {
            int nowPage = Integer.parseInt(args[1]);
            printLongLine(player, moneyCommandList, nowPage);
        } else if (args[1].equalsIgnoreCase("tbreak") || args[1].equalsIgnoreCase("tb")) {
            enableBreak(Bukkit.getServer().getPlayer(args[2]));
        } else if (args[1].equalsIgnoreCase("fbreak") || args[1].equalsIgnoreCase("fb")) {
            disableBreak(Bukkit.getServer().getPlayer(args[2]));
        } else {
            player.sendMessage("Not Found!!");
        }
    }

    public static boolean canBreak(Player player){
        String uuid = player.getUniqueId().toString();
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("./plugins/NBwar/Permit/break.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Boolean> obj = yaml.load(inputStream);
        return obj.get(uuid);
    }

    public static void enableBreak(Player player){
        String uuid = player.getUniqueId().toString();
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("./plugins/NBwar/Permit/break.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Boolean> obj = yaml.load(inputStream);
        String list = readFileOnce("./plugins/NBwar/Permit/break.yml");

        FileWriter writer = null;
        try {
            writer = new FileWriter("./plugins/NBwar/Permit/break.yml");
            obj.put(uuid, true);
            yaml.dump(obj, writer);
            getLogger().info(player.getName() + " is now can break blocks");
            getLogger().info("UUID is " + uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void disableBreak(Player player){
        String uuid = player.getUniqueId().toString();
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("./plugins/NBwar/Permit/break.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Boolean> obj = yaml.load(inputStream);
        String list = readFileOnce("./plugins/NBwar/Permit/break.yml");

        FileWriter writer = null;
        try {
            writer = new FileWriter("./plugins/NBwar/Permit/break.yml");
            obj.put(uuid, false);
            yaml.dump(obj, writer);
            getLogger().info(player.getName() + " is now can't break blocks");
            getLogger().info("UUID is " + uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
