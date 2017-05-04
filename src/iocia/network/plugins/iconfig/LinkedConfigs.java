package iocia.network.plugins.iconfig;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Allows creating a set of similar configuration files which can be grouped
 * together for easier management.
 * @param <T> Type used to store and access the configuration files with.
 */
public class LinkedConfigs<T> {

    /*---Data---*/
    private Map<T, IConfig> configMap;
    private final File directory;
    private Map<String, Object> defaults;

    /*---Constructors---*/
    /**
     * Sets up a mapping system to allow a set of configs to be easily managed under a single object.
     * Using this constructor allows the configuration files to be located somewhere other than
     * the directory created by the Spigot server.
     * @param directory Directory which will hold the newly created configuration files.
     */
    public LinkedConfigs(File directory) {
        configMap = new HashMap<T, IConfig>();
        this.directory = directory;
        defaults = new HashMap<>();
    }

    /**
     * Sets up a mapping system to allow a set of configs to be easily managed under a single object.
     * Using this constructor allows the configuration files to be located somewhere other than
     * the directory created by the Spigot server.
     * @param directory Directory which will hold the newly created configuration files.
     */
    public LinkedConfigs(String directory) {
        this(new File(directory));
    }

    /**
     * Sets up a mapping system to allow a set of configs to be easily managed under a single object.
     * Using this constructor will ensure the configuration files will be located within the
     * automatically generated directory from the Spigot server. As well as any sub-folders defined.
     * This constructor will automatically add the required file separator character between the
     * plugin's data folder and the given sub-folders, however, the String representation of the sub-folders
     * must have their own, correct file separator characters between each sub-folder as needed.
     * @param plugin Main class which extends JavaPlugin.
     * @param subFolders String representation of any sub-folders with which the configuration files
     *                   will be contained in.
     */
    public LinkedConfigs(JavaPlugin plugin, String subFolders) {
        this(new File(plugin.getDataFolder() + File.separator + subFolders));
    }

    /**
     * Sets up a mapping system to allow a set of configs to be easily managed under a single object.
     * Using this constructor will ensure the configuration files will be located within the
     * automatically generated directory from the Spigot server.
     * @param plugin Main class which extends JavaPlugin.
     */
    public LinkedConfigs(JavaPlugin plugin) {
        this(plugin.getDataFolder());
    }

    /*---Methods---*/
    /**
     * Check to see if the given key has a configuration file mapped to it.
     * @param key Key to check.
     * @return true if a mapping has been found; false if not.
     */
    public boolean isRegistered(T key) {
        return configMap.containsKey(key);
    }

    /**
     * Attempts to register a new configuration file under the given filename.
     * @param key Key to access the configuration.
     * @param filename Name of the system file.
     * @param overwrite Whether or not to overwrite an already existing configuration.
     *                  If true, there can be unpredictable behaviour of data being stored
     *                  to the shared system file.
     * @return true if registered; false if not.
     * @throws IOException If an I/O error occurs while reading or writing.
     * @throws InvalidConfigurationException Thrown if file has an invalid configuration.
     */
    public boolean registerConfig(T key, String filename, boolean overwrite) throws IOException, InvalidConfigurationException {
        if (configMap.containsKey(key) && !overwrite)
            return false;
        configMap.put(key, new IConfig(directory, filename));
        configMap.get(key).addDefaults(defaults);
        return true;
    }

    /**
     * Attempts to register a new configuration file under the given filename.
     * Will NOT overwrite an already registered configuration.
     * @param key Key to access the configuration.
     * @param filename Name of the system file.
     * @return true if registered; false if not.
     * @throws IOException If an I/O error occurs while reading or writing.
     * @throws InvalidConfigurationException Thrown if file has an invalid configuration.
     */
    public boolean registerConfig(T key, String filename) throws IOException, InvalidConfigurationException {
        return registerConfig(key, filename, false);
    }

    /**
     * Attempts to deregister the configuration stored under the given key.
     * @param key Key with which the desired configuration to be deregistered
     *            is mapped to.
     * @return IConfig object of the deregistered configuration, or null if no mapping existed.
     */
    public IConfig deregisterConfig(T key) {
        return configMap.remove(key);
    }

    /**
     * Saves all, currently registered configs.
     * @throws IOException If there is an I/O problem with any of the registered configs.
     */
    public void saveAll() throws IOException {
        for (IConfig config : configMap.values())
            config.save();
    }

    /**
     * Adds a default configuration option that will be automatically added
     * to each newly registered configuration file. Will NOT overwrite an
     * already existing configuration value over multiple reloads.
     * @param key Configuration key.
     * @param value Configuration value.
     */
    public void addDefault(String key, Object value) {
        defaults.put(key, value);
    }

}
