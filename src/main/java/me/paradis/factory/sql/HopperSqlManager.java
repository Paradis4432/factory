package me.paradis.factory.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.paradis.factory.Factory;

public interface HopperSqlManager {

    public abstract Connection getConnection() throws SQLException;

    public final String createHoppersTable = "CREATE TABLE IF NOT EXISTS hoppers (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "target_x INTEGER," +
            "target_y INTEGER," +
            "target_z INTEGER," +
            "target_world VARCHAR," +
            "target_id INTEGER," +
            "target_name VARCHAR," +
            "location_x INTEGER NOT NULL," +
            "location_y INTEGER NOT NULL," +
            "location_z INTEGER NOT NULL," +
            "location_world TEXT NOT NULL," +
            "enabled INTEGER NOT NULL," +
            "line_id INTEGER," +
            "FOREIGN KEY(line_id) REFERENCES lines(id)" +
            ");";

    /*
     * This method will save a new hopper in the database
     * 
     * @return true if the hopper was saved successfully
     */
    public default boolean saveNewHopper(Hoppers hopper, Player player) {

        Bukkit.getScheduler().runTaskAsynchronously(Factory.getInstance(), () -> {
            try {
                String saveQuery = "INSERT INTO hoppers (target_x, target_y, target_z, target_world, target_id," +
                        " target_name, location_x, location_y, location_z, location_world, enabled, line_id)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement pstmt = getConnection().prepareStatement(saveQuery);

                // Check if target_loc is null and handle accordingly
                if (hopper.target_loc == null) {
                    pstmt.setNull(1, Types.INTEGER);
                    pstmt.setNull(2, Types.INTEGER);
                    pstmt.setNull(3, Types.INTEGER);
                    pstmt.setNull(4, Types.VARCHAR);
                } else {
                    pstmt.setInt(1, hopper.target_loc.getBlockX());
                    pstmt.setInt(2, hopper.target_loc.getBlockY());
                    pstmt.setInt(3, hopper.target_loc.getBlockZ());
                    pstmt.setString(4, hopper.target_loc.getWorld().getName());
                }

                pstmt.setInt(5, hopper.target_id);
                pstmt.setString(6, hopper.target_name);
                pstmt.setInt(7, hopper.loc.getBlockX());
                pstmt.setInt(8, hopper.loc.getBlockY());
                pstmt.setInt(9, hopper.loc.getBlockZ());
                pstmt.setString(10, hopper.loc.getWorld().getName());
                pstmt.setInt(11, 1);
                pstmt.setInt(12, hopper.lineID);

                pstmt.executeUpdate();
                pstmt.close();

                player.sendMessage("hopper saved with target");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return true;
    }

    public default List<Location> getAllTargetedHoppersLocations() throws SQLException {
        String query = "SELECT * FROM hoppers WHERE target_x IS NOT NULL AND target_y IS NOT NULL AND target_z IS NOT NULL;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();
        List<Location> hoppers = new ArrayList<>();
        while (rs.next()) {
            System.out.println("adding hopper to list values: " + rs.getString("target_world") + " "
                    + rs.getInt("target_x") + " " + rs.getInt("target_y") + " " + rs.getInt("target_z"));
            Location loc = new Location(
                    null,
                    rs.getInt("location_x"), rs.getInt("location_y"), rs.getInt("location_z"));

            hoppers.add(loc);
        }

        pstmt.close();

        return hoppers;
    }

    public default void getAllTargetedHoppersLocationsAsync(LocationCallback callback) {
        Bukkit.getScheduler().runTaskAsynchronously(Factory.getInstance(), () -> {
            try {
                List<Location> locations = getAllTargetedHoppersLocations();
                callback.onLocationsReceived(locations);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public interface LocationCallback {
        void onLocationsReceived(List<Location> locations);
    }

}
