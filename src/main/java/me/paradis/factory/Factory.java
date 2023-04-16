package me.paradis.factory;

import me.paradis.factory.events.OnHopperPlace;
import me.paradis.factory.sql.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sun.security.util.Debug;

import java.sql.SQLException;

public final class Factory extends JavaPlugin {

    private static SQLManager sqlManager;
    private static Factory instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Factory plugin is enabled");

        try{
            sqlManager = new SQLManager("jdbc:sqlite:factory.db");
            sqlManager.setup();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // register new event
        Bukkit.getPluginManager().registerEvents(new OnHopperPlace(), this);
    }

    public static SQLManager getSqlManager() {
        return sqlManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
