package iocia.network.plugins.iconfig.components;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Manages the configuration data which is saved to and accessed from the system file.
 */
public abstract class ConfigHandler extends FileLoader {

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
    public ConfigHandler(File baseDirectory, String subDirectories, String fileName) throws IOException, InvalidConfigurationException {
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
    public ConfigHandler(String baseDirectory, String subDirectories, String fileName) throws IOException, InvalidConfigurationException {
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
    public ConfigHandler(JavaPlugin plugin, String subDirectories, String fileName) throws IOException, InvalidConfigurationException {
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
    public ConfigHandler(File baseDirectory, String fileName) throws IOException, InvalidConfigurationException {
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
    public ConfigHandler(String baseDirectory, String fileName) throws IOException, InvalidConfigurationException {
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
    public ConfigHandler(JavaPlugin plugin, String fileName) throws IOException, InvalidConfigurationException {
        super(plugin, fileName);
    }

    /*----Methods---*/

    /**
     * Saves the current configuration state to the system file.
     * @throws IOException Thrown when the file cannot be written to.
     */
    public void save() throws IOException {
        save(systemFile);
    }

    /**
     * Copies the exact Byte data of the given stream to the system file.
     * Used to pre-define the exact look and layout of a configuration files.
     * This means initial configuration files can be created with commented lines
     * anywhere and the exact order of the configuration options can be controlled.
     * @param internalFile InputStream of the internal jar file to copy.
     * @param options Used to specify extra options when copying the file.
     * @throws IOException If an I/O error occurs while reading or writing.
     * @see CopyOption for information regarding the extra options.
     */
    public void copyPremadeConfig(InputStream internalFile, CopyOption... options) throws IOException, InvalidConfigurationException {
        Files.copy(internalFile, Paths.get(systemFile.getAbsolutePath()), options);
        load(systemFile);
    }

}
