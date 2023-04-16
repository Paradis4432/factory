package me.paradis.factory.events;

import me.paradis.factory.Factory;
import me.paradis.factory.sql.Hoppers;
import me.paradis.factory.sql.SQLManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.sql.Connection;

public class OnHopperPlace implements Listener {

    SQLManager sqlManager = Factory.getSqlManager();

    @EventHandler
    public void onHopperPlace(BlockPlaceEvent e){
        System.out.println("block placed");
        if (!(e.getBlock().getType() == Material.HOPPER)) return;

        if (!e.getItemInHand().getItemMeta().getDisplayName().equals("factory hopper")) return;

        Block against = e.getBlockAgainst();
        if (!(against.getType() == Material.WHITE_WOOL)) return;

        Location loc = e.getBlockAgainst().getLocation();
        System.out.println("saving hopper");
        Hoppers hopper = new Hoppers(e.getBlock().getLocation());
        // replace with isFactoryBlock
        if (isBelt(loc)){
            System.out.println("is belt");
            // save new hopper in database
            // check id of target block before saving
            hopper.setTarget(against.getLocation(), getID(loc), "BELT")
                    .setLineID(getLineID(loc))
                    .save();
        } else {
            // save new hopper in database with no target
            hopper.save();
        }
    }

    private int getLineID(Location loc){
        // select lineID from database where location = loc
        return 0;
    }

    // get id of target block from database based on location of target block
    private int getID(Location loc){
        // select id from database where location = loc
        return 0;
    }

    private boolean isBelt(Location loc){
        // check if the block is a belt in database
        return true;
    }

}
