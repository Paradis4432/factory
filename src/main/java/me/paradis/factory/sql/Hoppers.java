package me.paradis.factory.sql;

import me.paradis.factory.Factory;
import org.bukkit.Location;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Hoppers {

    private SQLManager sqlManager = Factory.getSqlManager();
    private Location target_loc;
    private int target_id;
    private String target_name;
    private Location loc;
    private int lineID;

    public Hoppers(){

    }

    public Hoppers(Location placedHopperLoc){
        this.loc = placedHopperLoc;
    }

    public Hoppers setTargetLoc(Location loc){
        this.target_loc = loc;
        return this;
    }

    public Hoppers setTargetID(int id){
        this.target_id = id;
        return this;
    }

    public Hoppers setTargetName(String name){
        this.target_name = name;
        return this;
    }

    public Hoppers setTarget(Location loc, int id, String name){
        this.target_loc = loc;
        this.target_id = id;
        this.target_name = name;
        return this;
    }

    public Hoppers setLocation(Location loc){
        this.loc = loc;
        return this;
    }

    public Hoppers setLineID(int id){
        this.lineID = id;
        return this;
    }

    @SuppressWarnings("DataFlowIssue")
    public boolean save(){
        if (loc == null) {
            return false;
        }
        try {
            String saveQuery = "INSERT INTO hoppers (target_x, target_y, target_z, target_world, target_id," +
                    " target_name, location_x, location_y, location_z, location_world, line_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement pstmt = sqlManager.getConnection().prepareStatement(saveQuery);
            pstmt.setInt(1, target_loc.getBlockX());
            pstmt.setInt(2, target_loc.getBlockY());
            pstmt.setInt(3, target_loc.getBlockZ());
            pstmt.setString(4, target_loc.getWorld().getName());
            pstmt.setInt(5, target_id);
            pstmt.setString(6, target_name);
            pstmt.setInt(7, loc.getBlockX());
            pstmt.setInt(8, loc.getBlockY());
            pstmt.setInt(9, loc.getBlockZ());
            pstmt.setString(10, loc.getWorld().getName());
            pstmt.setInt(11, lineID);

            pstmt.executeUpdate();
            pstmt.close();


        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
