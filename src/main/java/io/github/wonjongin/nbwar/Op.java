package io.github.wonjongin.nbwar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

import static io.github.wonjongin.nbwar.FileIO.readFileOnce;
import static org.bukkit.Bukkit.getLogger;

public class Op {
    public static boolean isOpPlayer(Player player){
        String uuid = player.getUniqueId().toString();
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("./plugins/NBwar/Op.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String,Boolean> obj = yaml.load(inputStream);
        return obj.get(uuid);
    }
    public static void opSetup(Player player) {
        String uuid = player.getUniqueId().toString();
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("./plugins/NBwar/Op.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Boolean> obj = yaml.load(inputStream);
        String list = readFileOnce("./plugins/NBwar/Op.yml");
        if (!obj.containsKey(uuid)) {
            FileWriter writer = null;
            try {
                writer = new FileWriter("./plugins/NBwar/Op.yml");
                obj.put(uuid, false);
                yaml.dump(obj, writer);
//                player.sendMessage(yaml.dump(obj));
                getLogger().info("Op of " + player.getName() + " is set up");
                getLogger().info("UUID is " + uuid);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void setOp(Player sender, Player receiver, boolean isOp){
        if(sender.isOp()||isOpPlayer(sender)){
            String uuid = receiver.getUniqueId().toString();
            Yaml yaml = new Yaml();
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(new File("./plugins/NBwar/Op.yml"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Map<String, Boolean> obj = yaml.load(inputStream);
            if (!obj.containsKey(uuid)) {
                FileWriter writer = null;
                try {
                    writer = new FileWriter("./plugins/NBwar/Op.yml");
                    obj.put(uuid, isOp);
                    yaml.dump(obj, writer);
                    sender.sendMessage(ChatColor.YELLOW+receiver.getName()+"에게 오피를 "+String.valueOf(isOp)+"로 설정했습니다. ");
                    receiver.sendMessage(ChatColor.YELLOW+sender.getName()+"(이)가 오피를 "+String.valueOf(isOp)+"로 설정했습니다. ");
                    getLogger().info("Op of " + receiver.getName() + " is set up");
                    getLogger().info("UUID is " + uuid);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
