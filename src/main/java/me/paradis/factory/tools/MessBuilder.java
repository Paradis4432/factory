package me.paradis.factory.tools;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessBuilder {

    private String msg;
    private Player p;
    private boolean toAllPlayers = false;

    public String translate(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public MessBuilder() {
    }

    public MessBuilder(Player p) {
        this.p = p;
    }

    public MessBuilder(String msg, Player p) {
        this.msg = msg;
        this.p = p;

        build();
    }

    public MessBuilder setP(Player p) {
        this.p = p;
        return this;
    }

    public MessBuilder setMsg(String msg) {
        this.msg = msg;
        build();
        return this;
    }

    public MessBuilder toAllPlayers(boolean toAllPlayers) {
        this.toAllPlayers = toAllPlayers;
        return this;
    }

    public void build() {
        if (toAllPlayers) {
            for (Player p : p.getServer().getOnlinePlayers()) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            }
            return;
        }
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
