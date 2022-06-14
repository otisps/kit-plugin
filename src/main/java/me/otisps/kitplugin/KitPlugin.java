package me.otisps.kitplugin;

import me.otisps.kitplugin.commands.KitCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class KitPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[KitPlugin] Kit Plugin Enabled ...");
        getCommand("kit").setExecutor(new KitCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("[KitPlugin] Kit Plugin Disabled  ...");
    }
}
