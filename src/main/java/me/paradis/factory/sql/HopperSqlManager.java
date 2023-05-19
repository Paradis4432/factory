package me.paradis.factory.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
    public default boolean saveNewHopper(Hoppers hopper) {
        Bukkit.getScheduler().runTaskAsynchronously(Factory.getInstance(), () -> {
            String saveQuery = "INSERT INTO hoppers (target_x, target_y, target_z, target_world, target_id," +
                    " target_name, location_x, location_y, location_z, location_world, enabled, line_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try {
                System.out.println("conn: " + getConnection());

                try (PreparedStatement pstmt = getConnection().prepareStatement(saveQuery)) {
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

                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        return true;
    }

    public default void getAllTargetedHoppersLocationsAsync(LocationCallback callback) {
        Bukkit.getScheduler().runTaskAsynchronously(Factory.getInstance(), () -> {
            try {
                String query = "SELECT * FROM hoppers WHERE target_x IS NOT NULL AND target_y IS NOT NULL AND target_z IS NOT NULL;";
                try (PreparedStatement pstmt = getConnection().prepareStatement(query);
                        ResultSet rs = pstmt.executeQuery()) {
                    List<Location> hoppers = new ArrayList<>();
                    while (rs.next()) {
                        String worldName = rs.getString("location_world");
                        int locationX = rs.getInt("location_x");
                        int locationY = rs.getInt("location_y");
                        int locationZ = rs.getInt("location_z");
                        Location loc = new Location(null, locationX, locationY, locationZ);
                        hoppers.add(loc);
                    }
                    callback.onLocationsReceived(hoppers);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public interface LocationCallback {
        void onLocationsReceived(List<Location> locations);
    }

    public default void updateAllTargetedHoppers() {
        getAllTargetedHoppersLocationsAsync(locations -> {
            Bukkit.getScheduler().runTask(Factory.getInstance(), () -> {
                for (Location loc : locations) {
                    loc.setWorld(Factory.getInstance().getServer().getWorld("world"));
                    System.out.println(loc);
    
                    // the location should be a hopper, get the inventory of this and print the
                    // content
    
                    try {
                        Block block = loc.getBlock();
                        if (!(block.getState() instanceof Hopper))
                            continue;
                        Hopper hopper = (Hopper) block.getState();
                        for(ItemStack item : hopper.getInventory().getContents()) {
                            if(item != null) {
                                System.out.println(item.getType());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("error");
                        e.printStackTrace();
                    }
                }
            });
        });
    }
}
