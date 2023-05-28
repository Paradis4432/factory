package me.paradis.factory.events;

import me.paradis.factory.Factory;
import me.paradis.factory.sql.Hoppers;
import me.paradis.factory.sql.SQLManager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Fence;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.sql.Connection;
import java.sql.SQLException;

public class OnHopperPlace implements Listener {

    SQLManager sqlManager = Factory.getSqlManager();

    @EventHandler
    public void test(BlockMultiPlaceEvent event) {
        System.out.println("multi block placed");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block placedBlock = event.getBlock();



        if (placedBlock.getType() == Material.OAK_FENCE) {


            Bukkit.getScheduler().runTaskLater(Factory.getInstance(), new Runnable() {
                @Override
                public void run() {
                    Fence fence = (Fence) Material.OAK_FENCE.createBlockData();
                    fence.setFace(BlockFace.EAST, false);
                    fence.setFace(BlockFace.WEST, false);
                    fence.setFace(BlockFace.NORTH, false);
                    fence.setFace(BlockFace.SOUTH, false);
                    placedBlock.setBlockData(fence);
                }
            }, 20L);
        } else if (placedBlock.getType() == Material.SPRUCE_FENCE) {
            Fence fence = (Fence) Material.SPRUCE_FENCE.createBlockData();
            fence.setFace(BlockFace.NORTH, true);
            placedBlock.setBlockData(fence);
        }
    }

    @EventHandler
    public void onHopperPlace(BlockPlaceEvent e) {
        System.out.println("block placed");
        if (!(e.getBlock().getType() == Material.HOPPER))
            return;

        if (!e.getItemInHand().getItemMeta().getDisplayName().equals("factory hopper"))
            return;

        Block against = e.getBlockAgainst();
        // if (!(against.getType() == Material.WHITE_WOOL)) return;

        Location loc = e.getBlockAgainst().getLocation();
        System.out.println("saving hopper");
        Hoppers hopper = new Hoppers(e.getBlock().getLocation());
        // replace with isFactoryBlock
        if (isBelt(loc) && against.getType() == Material.WHITE_WOOL) {
            System.out.println("is belt");

            hopper.setTarget(against.getLocation(), getID(loc), "BELT")
                    .setLineID(getLineID(loc))
                    .save();

            e.getPlayer().sendMessage("saving hopper with given target");
        } else {
            System.out.println("is not belt");
            hopper.setLineID(getLineID(loc))
                    .save();
            e.getPlayer().sendMessage("saving hopper with no target");
        }
    }

    private int getLineID(Location loc) {
        // select lineID from database where location = loc
        return 0;
    }

    // get id of target block from database based on location of target block
    private int getID(Location loc) {
        // select id from database where location = loc
        return 0;
    }

    private boolean isBelt(Location loc) {
        // check if the block is a belt in database
        return true;
    }

}
