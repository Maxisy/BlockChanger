package me.Maxisy.BlockChanger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BlockChangerJoinListener implements Listener {

    public BlockChangerJoinListener(BlockChangerPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    FileConfiguration config = BlockChangerPlugin.plugin.getConfig();

    private String prefix = BlockChangerPlugin.prefixTrue;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        Player player = evt.getPlayer();
        player.sendMessage(prefix +
                "Type /startblockchange to start changing blocks\n" +
                prefix +
                "Type /stopblockchange to stop changing blocks");
    }
}
