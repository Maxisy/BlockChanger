package me.Maxisy.BlockChanger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class BlockChangerListener implements Listener {

    public BlockChangerListener(BlockChangerPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location loc = event.getPlayer().getLocation();
        loc.setY(loc.getY() + 2);
        Block b = loc.getBlock();
        b.setType(Material.BEDROCK);
    }


}
