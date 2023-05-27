package me.paradis.factory.tools;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;

import me.paradis.factory.Factory;

public class Schedulers {
    
    public static void async(@Nonnull Runnable runnable){
        Bukkit.getScheduler().runTaskAsynchronously(Factory.getInstance(), runnable);
    }
    
}
