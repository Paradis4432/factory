package me.paradis.factory;

import me.paradis.factory.commands.FactoryCommand;
import me.paradis.factory.events.OnHopperPlace;
import me.paradis.factory.sql.HopperSqlManager.LocationCallback;
import me.paradis.factory.sql.HopperSqlManager;
import me.paradis.factory.sql.Hoppers;
import me.paradis.factory.sql.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import co.aikar.commands.PaperCommandManager;
import sun.security.util.Debug;

import java.sql.SQLException;
import java.util.List;

public final class Factory extends JavaPlugin {

    private static SQLManager sqlManager;
    private static Factory instance;
    public static final String version = "1.0";

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        System.out.println("Factory plugin is enabled");

        try {
            sqlManager = new SQLManager("jdbc:sqlite:factory.db");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // register new event
        Bukkit.getPluginManager().registerEvents(new OnHopperPlace(), this);

        // register commands
        PaperCommandManager manager = new PaperCommandManager(this);    
        manager.enableUnstableAPI("help");  
        manager.registerCommand(new FactoryCommand());

        startLoops();
    }

    public static SQLManager getSqlManager() {
        return sqlManager;
    }

    public static Factory getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void startLoops() {
        System.out.println("starting loop");
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            //doesnt acctually get them
            sqlManager.updateAllTargetedHoppers();
        }, 0, 20);
    }
}
