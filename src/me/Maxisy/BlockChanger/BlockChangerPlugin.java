package me.Maxisy.BlockChanger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockChangerPlugin extends JavaPlugin {

    public static BlockChangerPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.getLogger().info("Starting BlockChanger plugin...");
        new BlockChangerJoinListener(this);
        this.getLogger().info("Plugin created by Maksymilian Sybicki");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Stopping BlockChanger plugin...");
    }

    private final String prefixTrue = ChatColor.AQUA + "[" + getConfig().getString("prefix") + "]" + ChatColor.GREEN + " ";
    private final String prefixFalse = ChatColor.AQUA + "[" + getConfig().getString("prefix") + "]" + ChatColor.RED + " ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("startblockchange")) {
            if (sender instanceof Player) {
                sender.sendMessage(prefixTrue + "Started block changing.");
                new BlockChangerListener(this);
            } else {
                sender.sendMessage(prefixFalse + "This command can only be executed by a player.");
            }
        }
        if (command.getName().equalsIgnoreCase("stopblockchange")) {
            if (sender instanceof Player) {
                sender.sendMessage(prefixFalse + "Stopped block changing.");
                PlayerMoveEvent.getHandlerList().unregister(this);
            } else {
                sender.sendMessage(prefixFalse + "This command can only be executed by a player.");
            }
        }
        if (command.getName().equalsIgnoreCase("blockchanger")) {
            if (args[0].equalsIgnoreCase("info")) {
                sender.sendMessage(prefixTrue + "Commands: \n" +
                        prefixTrue + "/startblockchange: starts blocks changing\n" +
                        prefixTrue + "/stopblockchange: stops blocks changing\n" +
                        prefixTrue + "/blockchanger [info]: shows info");
                return true;
            }
        }
        return true;
    }
}
