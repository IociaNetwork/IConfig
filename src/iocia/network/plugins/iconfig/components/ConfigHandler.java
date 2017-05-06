package iocia.network.plugins.iconfig.components;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Manages the configuration data which is saved to and accessed from the system file.
 */
public abstract class ConfigHandler extends FileLoader {

    /*---Constructors---*/
    public ConfigHandler(File baseDirectory, String subDirectories, String fileName) throws IOException, InvalidConfigurationException {
        super(baseDirectory, subDirectories, fileName);
    }

    public ConfigHandler(String baseDirectory, String subDirectories, String fileName) throws IOException, InvalidConfigurationException {
        super(baseDirectory, subDirectories, fileName);
    }

    public ConfigHandler(JavaPlugin plugin, String subDirectories, String fileName) throws IOException, InvalidConfigurationException {
        super(plugin, subDirectories, fileName);
    }

    public ConfigHandler(File baseDirectory, String fileName) throws IOException, InvalidConfigurationException {
        super(baseDirectory, fileName);
    }

    public ConfigHandler(String baseDirectory, String fileName) throws IOException, InvalidConfigurationException {
        super(baseDirectory, fileName);
    }

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
     * @throws IOException If an I/O error occurs while reading or writing.
     */
    public void copyPremadeConfig(InputStream internalFile) throws IOException, InvalidConfigurationException {
        if (!isFirstLoad())
            return;
        Files.copy(internalFile, Paths.get(systemFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
        load(systemFile);
    }

}
