package io.github.wonjongin.nbwar;

import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.Map;

public class Basic {
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean randomOfPercent(int percent) {
        int random = (int) Math.floor(Math.random()*100);
        return random <= percent;
    }

}
