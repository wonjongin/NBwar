package io.github.wonjongin.nbwar;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Holograpic {
    public boolean checkHolographicIsEnable() {
        return Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays");
    }

    public static void showSimpleHologram(Player player, ArrayList<String> texts) {
        Plugin plugin = NBwar.getPlugin();
        final Hologram hologram = HologramsAPI.createHologram(plugin, player.getEyeLocation());
        for (String text : texts) {
            hologram.appendTextLine(text);
        }
    }
}
