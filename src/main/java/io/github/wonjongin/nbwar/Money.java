package io.github.wonjongin.nbwar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.github.wonjongin.nbwar.Basic.isInteger;
import static io.github.wonjongin.nbwar.FileIO.readFileOnce;
import static io.github.wonjongin.nbwar.Print.printLongLine;
import static org.bukkit.Bukkit.getLogger;

public class Money {
    // Money.yml
    // <uuid>: 1000
    // <uuid>: 3999
    public static void moneyCommands(Player player, String[] args) {
        String[] moneyCommandList = {
                "Money Commands",
                "Type /n mn <command>",
                "view(v) - 돈 보기 ",
                // "set(st) - 지갑 생성 ",
                "send(s) - 보내기 ",
                "withdraw(w) - 인출 ",
                "deposit(d) - 예금 ",

        };
        if (args.length == 1) {
            // for (String s : itemCommandList) {
            //     player.sendMessage(ChatColor.GOLD + s);
            // }
            printLongLine(player, moneyCommandList, 1);
        } else if (isInteger(args[1])) {
            int nowPage = Integer.parseInt(args[1]);
            printLongLine(player, moneyCommandList, nowPage);
        } else if (args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("st")) {
            moneySetup(player);
        } else if (args[1].equalsIgnoreCase("view") || args[1].equalsIgnoreCase("v")) {
            moneyView(player);
        } else if (args[1].equalsIgnoreCase("withdraw") || args[1].equalsIgnoreCase("w")) {
            if (args.length == 2) {
                player.sendMessage("인출 하실 돈의 양을 입력하세요!");
            } else {
                moneyWithdraw(player, Integer.parseInt(args[2]));
            }

        } else if (args[1].equalsIgnoreCase("deposit") || args[1].equalsIgnoreCase("d")) {
            if (args.length == 2) {
                player.sendMessage("예금 하실 돈의 양을 입력하세요!");
            } else {
                moneyDeposit(player, Integer.parseInt(args[2]));
            }

        } else if (args[1].equalsIgnoreCase("send") || args[1].equalsIgnoreCase("s")) {
            if (args.length == 2 || args.length == 3) {
                player.sendMessage("보낼 사람과 보내실 돈을 입력하세요. 원한다면 뒤에 메시지도 입력하세요(띄어쓰기 불가)");
            } else {
                Player receiver = Bukkit.getServer().getPlayer(args[2]);
                if (args.length == 4) {
                    moneySend(player, receiver, Integer.parseInt(args[3]), "null");
                } else if (args.length == 5) {
                    moneySend(player, receiver, Integer.parseInt(args[3]), args[4]);
                }
            }

        } else {
            player.sendMessage("Not Found!!");
        }
    }

    public static void moneySetup(Player player) {
        String uuid = player.getUniqueId().toString();
        // createFile("./plugins/NBwar/Money/Money.yml", "uuid: money");
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("./plugins/NBwar/Money/Money.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Object> obj = yaml.load(inputStream);
        String list = readFileOnce("./plugins/NBwar/Money/Money.yml");
        if (!obj.containsKey(uuid)) {
            FileWriter writer = null;
            try {
                writer = new FileWriter("./plugins/NBwar/Money/Money.yml");
                obj.put(uuid, 1);
                yaml.dump(obj, writer);
                // player.sendMessage(yaml.dump(obj));
                getLogger().info("Money of " + player.getName() + " is set up");
                getLogger().info("UUID is " + uuid);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // playersList.add(uuid);
            // obj.put("players", new String[]{uuid});


            // createFile("./plugins/NBwar/Money/Money.yml", uuid+": 100000");
        }
    }

    public static void moneyYamlUpdate(Player player, int money) {
        String uuid = player.getUniqueId().toString();
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("./plugins/NBwar/Money/Money.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Object> obj = yaml.load(inputStream);
        FileWriter writer = null;
        try {
            writer = new FileWriter("./plugins/NBwar/Money/Money.yml");
            obj.put(uuid, money);
            yaml.dump(obj, writer);
            // player.sendMessage(yaml.dump(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moneyView(Player player) {
        String uuid = player.getUniqueId().toString();
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("./plugins/NBwar/Money/Money.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Object> obj = yaml.load(inputStream);
        String list = readFileOnce("./plugins/NBwar/Money/Money.yml");
        String money = obj.get(uuid).toString();
        player.sendMessage(ChatColor.YELLOW + money + " 달러가 있습니다.");
        getLogger().info(player.getName() + "'s money is " + money + " $");
    }

    public static int moneyYamlRead(Player player) {
        String uuid = player.getUniqueId().toString();
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("./plugins/NBwar/Money/Money.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Object> obj = yaml.load(inputStream);
        String money = obj.get(uuid).toString();
        return Integer.parseInt(money);
    }

    public static void moneyWithdraw(Player player, int money) {
        PlayerInventory pi = player.getInventory();
        int i = money;
        int userMoney = moneyYamlRead(player);
        if (money > userMoney) {
            player.sendMessage("돈이 부족합니다.");
        } else {
            ItemStack moneyPaper = new ItemStack(Material.PAPER, i);
            ItemMeta moneyPaperMeta = moneyPaper.getItemMeta();
            moneyPaperMeta.setDisplayName("1 달러");
            ArrayList<String> lores = new ArrayList<>();
            lores.add("1 달러 입니다. 거래에 사용하세요");
            moneyPaper.setItemMeta(moneyPaperMeta);
            pi.addItem(new ItemStack[]{moneyPaper});
            player.sendMessage(ChatColor.AQUA + Integer.toString(money) + " 달러가 인출되었습니다.");
            moneyYamlUpdate(player, userMoney - money);
            getLogger().info(player.getName() + " withdrawed " + Integer.toString(money) + " $");
            getLogger().info(player.getName() + " has " + Integer.toString(userMoney) + " $");
        }


    }

    public static void moneyDeposit(Player player, int money) {
        PlayerInventory pi = player.getInventory();
        int i = money;
        int userMoney = moneyYamlRead(player);
        if (pi.getItemInMainHand().getItemMeta().getDisplayName().contains("1 달러")) {
            int inventoryMoney = pi.getItemInMainHand().getAmount();
            if (inventoryMoney < money) {
                player.sendMessage("돈이 부족합니다.");
            } else {
                moneyYamlUpdate(player, moneyYamlRead(player) + money);
                pi.getItemInMainHand().setAmount(pi.getItemInMainHand().getAmount() - i);
                player.sendMessage(ChatColor.AQUA + Integer.toString(money) + " 달러를 예금 하였습니다.");
                getLogger().info(player.getName() + " deposited " + Integer.toString(money) + " $");
                getLogger().info(player.getName() + " has " + Integer.toString(moneyYamlRead(player)) + " $");
            }
        } else {
            player.sendMessage("돈을 들고 하세요");
        }

    }

    public static void moneySend(Player sender, Player receiver, int money, String msg) {
        if (moneyYamlRead(sender) < money) {
            sender.sendMessage("돈이 부족합니다.");
        } else {
            if (money >= 0) {
                moneyYamlUpdate(sender, moneyYamlRead(sender) - money);
                moneyYamlUpdate(receiver, moneyYamlRead(receiver) + money);
                String moneyStr = Integer.toString(money);
                sender.sendMessage(ChatColor.AQUA + moneyStr + " 달러가 " + receiver.getName() + "에게 보내졌습니다. ");
                if (msg.equals("null")) {
                    receiver.sendMessage(ChatColor.GREEN + sender.getName() + "가 " + moneyStr + " 달러를 보냈습니다.");
                    getLogger().info(sender.getName() + " sent " + receiver.getName() + " " + moneyStr + " $");
                } else {
                    receiver.sendMessage(ChatColor.GREEN + sender.getName() + "가 " + moneyStr + " 달러를 보냈습니다.");
                    receiver.sendMessage(ChatColor.GREEN + "송금 메시지: " + msg);
                    getLogger().info(sender.getName() + " sent " + receiver.getName() + " " + moneyStr + " $ and " + msg);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "돈은 양수로 입력해야합니다.");
            }


        }
    }

    public static HashMap<String, Integer> moneyCounter(int totalMoney) {
        int d100 = 0; // 100 달러
        int d50 = 0;
        int d20 = 0;
        int d10 = 0;
        int d5 = 0;
        int d2 = 0;
        int d1 = 0;
        int tmpMoney = totalMoney;
        while (tmpMoney >= 100) {
            tmpMoney -= 100;
            d100++;
        }
        while (tmpMoney >= 50) {
            tmpMoney -= 50;
            d50++;
        }
        while (tmpMoney >= 20) {
            tmpMoney -= 20;
            d20++;
        }
        while (tmpMoney >= 10) {
            tmpMoney -= 10;
            d10++;
        }
        while (tmpMoney >= 5) {
            tmpMoney -= 5;
            d5++;
        }
        while (tmpMoney >= 2) {
            tmpMoney -= 2;
            d2++;
        }
        while (tmpMoney >= 1) {
            tmpMoney -= 1;
            d1++;
        }

        final int[] res = {d100, d50, d20, d10, d5, d2, d1};
        // return new int[]{d100, d50, d20, d10, d5, d2, d1};
        return new HashMap<String, Integer>() {{
            put("d100", res[0]);
            put("d50", res[1]);
            put("d20", res[2]);
            put("d10", res[3]);
            put("d5", res[4]);
            put("d2", res[5]);
            put("d1", res[6]);
        }};
    }
}
