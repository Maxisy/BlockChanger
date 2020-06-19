package me.Maxisy.BlockChanger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BlockChangerPlugin extends JavaPlugin {

    public static BlockChangerPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.getLogger().info(prefixTrue + "Starting BlockChanger plugin...");
        getConfig().options().copyDefaults(true);
        saveConfig();
        loadConfig(this);
        new BlockChangerJoinListener(this);
        this.getLogger().info(prefixTrue + "Plugin created by Maksymilian Sybicki");
    }

    @Override
    public void onDisable() {
        this.getLogger().info(prefixFalse + "Stopping BlockChanger plugin...");
    }

    private static final String prefix = ChatColor.AQUA + "[";
    static String prefixTrue;
    private String prefixFalse;

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
            } else if (args[0].equalsIgnoreCase("reload")) {
                loadConfig(this);
                sender.sendMessage(prefixTrue + "Successfully reloaded config.yml!");
            }
        }
        return true;
    }

    public void loadConfig(Plugin plugin) {
        File cfile = new File(plugin.getDataFolder().getAbsolutePath() + "/config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(cfile);
        prefixTrue = prefix + config.getString("prefix") + "]" + ChatColor.GREEN + " ";
        prefixFalse = prefix + config.getString("prefix") + "]" + ChatColor.RED + " ";
    }
}
