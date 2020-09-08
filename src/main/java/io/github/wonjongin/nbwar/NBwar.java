package io.github.wonjongin.nbwar;

import org.bukkit.plugin.java.JavaPlugin;

public final class NBwar extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin disabled");
    }
}
