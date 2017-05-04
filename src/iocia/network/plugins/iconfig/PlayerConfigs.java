package iocia.network.plugins.iconfig;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Used to automatically manage configuration files of player
 * specific configuration files.
 * This class also automatically loads and unloads player's configuration files
 * as each player joins and leaves the server. However, this class cannot
 * save the configuration files for players still online when the server is shutdown.
 */
public class PlayerConfigs extends LinkedConfigs<UUID> implements Listener {

    /*---Constructors---*/
    /**
     * Sets up a mapping system to easily manage player configuration files.
     * Using this constructor allows the configuration files to be located somewhere other than
     * the directory created by the Spigot server.
     *
     * @param directory Directory which will hold the newly created configuration files.
     */
    public PlayerConfigs(File directory) {
        super(directory);
    }

    /**
     * Sets up a mapping system to easily manage player configuration files.
     * Using this constructor allows the configuration files to be located somewhere other than
     * the directory created by the Spigot server.
     *
     * @param directory Directory which will hold the newly created configuration files.
     */
    public PlayerConfigs(String directory) {
        super(directory);
    }

    /**
     * Sets up a mapping system to easily manage player configuration files.
     * Using this constructor will ensure the configuration files will be located within the
     * automatically generated directory from the Spigot server. As well as any sub-folders defined.
     * This constructor will automatically add the required file separator character between the
     * plugin's data folder and the given sub-folders, however, the String representation of the sub-folders
     * must have their own, correct file separator characters between each sub-folder as needed.
     *
     * @param plugin     Main class which extends JavaPlugin.
     * @param subFolders String representation of any sub-folders with which the configuration files
     */
    public PlayerConfigs(JavaPlugin plugin, String subFolders) {
        super(plugin, subFolders);
    }

    /**
     * Sets up a mapping system to easily manage player configuration files.
     * Using this constructor will ensure the configuration files will be located within the
     * automatically generated directory from the Spigot server.
     *
     * @param plugin Main class which extends JavaPlugin.
     */
    public PlayerConfigs(JavaPlugin plugin) {
        super(plugin);
    }

    /*---Methods---*/
    /**
     * Must be called to allow automatic loading and unloading of player configuration files.
     * If not called, manual control can be used instead.
     * @param main Main class which extends {@link JavaPlugin}.
     */
    public void initEvents(JavaPlugin main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    /**
     * Called automatically once the events have been initialized.
     * Automatically loads a player's configuration file upon joining the server.
     * @param event PlayerJoinEvent created by the Spigot server.
     * @see PlayerConfigs#initEvents(JavaPlugin) for information regarding event initialization.
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        try {
            registerConfig(event.getPlayer().getUniqueId(), event.getPlayer().getUniqueId().toString());
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            deregisterConfig(event.getPlayer().getUniqueId());
        }
    }

    /**
     * Called automatically once the events have been initialized.
     * Automatically saves and will deregister a player's configuration file upon leaving the server.
     * @param event {@link PlayerQuitEvent} created by the Spigot server.
     * @see PlayerConfigs#initEvents(JavaPlugin) for information regarding event initialization.
     */
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        try {
            get(event.getPlayer().getUniqueId()).save();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            deregisterConfig(event.getPlayer().getUniqueId());
        }
    }

}
