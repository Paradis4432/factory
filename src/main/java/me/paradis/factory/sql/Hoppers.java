package me.paradis.factory.sql;

import me.paradis.factory.Factory;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Hoppers {

    public SQLManager sqlManager = Factory.getSqlManager();
    public Location target_loc;
    public int target_id;
    public String target_name;
    public Location loc;
    public int lineID;

    public Hoppers() {

    }

    public Hoppers(Location placedHopperLoc) {
        this.loc = placedHopperLoc;
    }

    public Hoppers setTargetLoc(Location loc) {
        this.target_loc = loc;
        return this;
    }

    public Hoppers setTargetID(int id) {
        this.target_id = id;
        return this;
    }

    public Hoppers setTargetName(String name) {
        this.target_name = name;
        return this;
    }

    public Hoppers setTarget(Location loc, int id, String name) {
        this.target_loc = loc;
        this.target_id = id;
        this.target_name = name;
        return this;
    }

    public Hoppers setTarget(Integer x, Integer y, Integer z, String world) {
        this.target_loc = new Location(null, x, y, z);
        this.target_loc.setWorld(Factory.getInstance().getServer().getWorld(world));
        return this;
    }

    public Hoppers setLocation(Location loc) {
        this.loc = loc;
        return this;
    }

    public Hoppers setLocation(Integer x, Integer y, Integer z, String world) {
        this.loc = new Location(null, x, y, z);
        this.loc.setWorld(Factory.getInstance().getServer().getWorld(world));
        return this;
    }

    public Hoppers setLineID(int id) {
        this.lineID = id;
        return this;
    }

    public boolean save() {
        if (loc == null) {
            return false;
        }
        System.out.println("saving hopper 1");
        sqlManager.saveNewHopper(this);
        return true;
    }
}
