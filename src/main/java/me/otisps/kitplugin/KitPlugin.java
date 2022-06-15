package me.otisps.kitplugin;

import me.otisps.kitplugin.commands.KitCommand;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class KitPlugin extends JavaPlugin {
    private File dataConfigFile;
    private FileConfiguration dataConfig;
    private static KitPlugin instance;

    public static KitPlugin getInstance() {
        return instance;
    }

    public File getDataConfigFile() {
        return dataConfigFile;
    }

    public FileConfiguration getDataConfig() {
        return dataConfig;
    }

    public void saveDataConfig(File dataConfigFile, FileConfiguration dataConfig) throws IOException {
        dataConfig.save(dataConfigFile);
    }

    private void createCustomConfig() {
        dataConfigFile = new File(getDataFolder(), "data.yml");
        if (!dataConfigFile.exists()) {
            dataConfigFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }
        dataConfig = new YamlConfiguration();
        try {
            dataConfig.load(dataConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        System.out.println("[KitPlugin] Kit Plugin Enabled ...");
        getCommand("kit").setExecutor(new KitCommand());

        this.saveDefaultConfig();
        createCustomConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[KitPlugin] Kit Plugin Disabled  ...");
    }
}
