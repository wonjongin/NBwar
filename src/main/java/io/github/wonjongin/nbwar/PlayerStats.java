package io.github.wonjongin.nbwar;

import org.bukkit.entity.Player;

public class PlayerStats {
    private int power; // 공격력
    private static double health; // 체력
//    private int
    public static double getHealth(Player player){
        health = player.getHealth();
        return health;
    }
}
