package me.paradis.factory.tools;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class MessReplacer {

    public static void replace(String msg, Player p, String... args) {
        // this.msg = msg.replace("%arg0%", arg0);
        msg = replaceArgs(msg, args);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));

    }

    public static void replace(String msg, Player p) {
        // this.msg = msg.replace("%arg0%", arg0);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    private static String replaceArgs(String msg, String[] args) {
        for (int i = 0; i < args.length; i++) {
            msg = msg.replace("%arg" + i + "%", args[i]);
        }
        return msg;
    }
}
