package iocia.network.plugins.iconfig;

import iocia.network.plugins.iconfig.components.ConfigHandler;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Handles individual configuration files and specializes in
 * methods optimized for unique, non-grouped configuration files.
 */
public class IConfig extends ConfigHandler {

    /*---Constructors---*/
    /**
     * Attempts to create a new configuration file under the given file name.
     * Will attempt create any required parent folders based on the given base-folder
     * and sub-folders. Will NOT delete an already existing file or directory.
     *
     * @param baseDirectory  Base-directory which acts as the master parent directory. Usually is the
     *                       directory given to each plugin by the Spigot server, but can be
     *                       altered to unify plugins under a single directory.
     * @param subDirectories Any sub-directories within the base-directory. Useful for organizing
     *                       many configuration files.
     * @param fileName       File name of the configuration file. WILL automatically add a '.yml' extension
     *                       to the file. Any file extensions put in the file name will have no effect on the
     *                       file type of the created files; this is to ensure all configuration files are created
     *                       as YAML files.
     * @throws IOException                   Thrown when either a directory or the file cannot be created.
     * @throws InvalidConfigurationException Thrown if file has an invalid configuration.
     */
    public IConfig(File baseDirectory, String subDirectories, String fileName) throws IOException, InvalidConfigurationException {
        super(baseDirectory, subDirectories, fileName);
    }

    /**
     * Attempts to create a new configuration file under the given file name.
     * Will attempt create any required parent folders based on the given base-folder
     * and sub-folders. Will NOT delete an already existing file or directory.
     *
     * @param baseDirectory  Base-directory which acts as the master parent directory. Usually is the
     *                       directory given to each plugin by the Spigot server, but can be
     *                       altered to unify plugins under a single directory.
     * @param subDirectories Any sub-directories within the base-directory. Useful for organizing
     *                       many configuration files.
     * @param fileName       File name of the configuration file. WILL automatically add a '.yml' extension
     *                       to the file. Any file extensions put in the file name will have no effect on the
     *                       file type of the created files; this is to ensure all configuration files are created
     *                       as YAML files.
     * @throws IOException                   Thrown when either a directory or the file cannot be created.
     * @throws InvalidConfigurationException Thrown if file has an invalid configuration.
     */
    public IConfig(String baseDirectory, String subDirectories, String fileName) throws IOException, InvalidConfigurationException {
        super(baseDirectory, subDirectories, fileName);
    }

    /**
     * Attempts to create a new configuration file under the given file name.
     * Will attempt create any required parent folders based on the given base-folder
     * and sub-folders. Will NOT delete an already existing file or directory.
     *
     * @param plugin         Instance of main class which extends JavaPlugin. Will ensure the
     *                       file is located within the folder created for the plugin.
     * @param subDirectories Any sub-directories within the base-directory. Useful for organizing
     *                       many configuration files.
     * @param fileName       File name of the configuration file. WILL automatically add a '.yml' extension
     *                       to the file. Any file extensions put in the file name will have no effect on the
     *                       file type of the created files; this is to ensure all configuration files are created
     *                       as YAML files.
     * @throws IOException                   Thrown when either a directory or the file cannot be created.
     * @throws InvalidConfigurationException Thrown if file has an invalid configuration.
     */
    public IConfig(JavaPlugin plugin, String subDirectories, String fileName) throws IOException, InvalidConfigurationException {
        super(plugin, subDirectories, fileName);
    }

    /**
     * Attempts to create a new configuration file under the given file name.
     * Will attempt create any required parent folders based on the given base-folder
     * and sub-folders. Will NOT delete an already existing file or directory.
     *
     * @param baseDirectory Base-directory which acts as the master parent directory. Usually is the
     *                      directory given to each plugin by the Spigot server, but can be
     *                      altered to unify plugins under a single directory.
     * @param fileName      File name of the configuration file. WILL automatically add a '.yml' extension
     *                      to the file. Any file extensions put in the file name will have no effect on the
     *                      file type of the created files; this is to ensure all configuration files are created
     *                      as YAML files.
     * @throws IOException                   Thrown when either a directory or the file cannot be created.
     * @throws InvalidConfigurationException Thrown if file has an invalid configuration.
     */
    public IConfig(File baseDirectory, String fileName) throws IOException, InvalidConfigurationException {
        super(baseDirectory, fileName);
    }

    /**
     * Attempts to create a new configuration file under the given file name.
     * Will attempt create any required parent folders based on the given base-folder
     * and sub-folders. Will NOT delete an already existing file or directory.
     *
     * @param baseDirectory Base-directory which acts as the master parent directory. Usually is the
     *                      directory given to each plugin by the Spigot server, but can be
     *                      altered to unify plugins under a single directory.
     * @param fileName      File name of the configuration file. WILL automatically add a '.yml' extension
     *                      to the file. Any file extensions put in the file name will have no effect on the
     *                      file type of the created files; this is to ensure all configuration files are created
     *                      as YAML files.
     * @throws IOException                   Thrown when either a directory or the file cannot be created.
     * @throws InvalidConfigurationException Thrown if file has an invalid configuration.
     */
    public IConfig(String baseDirectory, String fileName) throws IOException, InvalidConfigurationException {
        super(baseDirectory, fileName);
    }

    /**
     * Attempts to create a new configuration file under the given file name.
     * Will attempt create any required parent folders based on the given base-folder
     * and sub-folders. Will NOT delete an already existing file or directory.
     *
     * @param plugin   Instance of main class which extends JavaPlugin. Will ensure the
     *                 file is located within the folder created for the plugin.
     * @param fileName File name of the configuration file. WILL automatically add a '.yml' extension
     *                 to the file. Any file extensions put in the file name will have no effect on the
     *                 file type of the created files; this is to ensure all configuration files are created
     *                 as YAML files.
     * @throws IOException                   Thrown when either a directory or the file cannot be created.
     * @throws InvalidConfigurationException Thrown if file has an invalid configuration.
     */
    public IConfig(JavaPlugin plugin, String fileName) throws IOException, InvalidConfigurationException {
        super(plugin, fileName);
    }

}
