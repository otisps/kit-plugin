package me.otisps.kitplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class KitPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[KitPlugin] Kit Plugin Enabled ...");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("[KitPlugin] Kit Plugin Disabled  ...");
    }
}
