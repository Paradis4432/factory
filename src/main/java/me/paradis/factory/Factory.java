package me.paradis.factory;

import org.bukkit.plugin.java.JavaPlugin;
import sun.security.util.Debug;

public final class Factory extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Factory plugin is enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
