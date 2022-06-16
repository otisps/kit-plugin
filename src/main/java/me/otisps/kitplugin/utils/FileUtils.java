package me.otisps.kitplugin.utils;

import me.otisps.kitplugin.KitPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;


public class FileUtils {
    private FileConfiguration dataFile = KitPlugin.getInstance().getDataConfig();

    public ItemStack[] getInv(String playerUUID, String name) {
        BukkitSerialization bukkitSerialization = new BukkitSerialization();
        String stringInventory = dataFile.getString(playerUUID + "." + name + ".inventory");
        ItemStack[] inv;
        try {
            inv = bukkitSerialization.itemStackArrayFromBase64(stringInventory);
        } catch (IOException e) {throw new RuntimeException(e);
        }
        return inv;
    }

    public ItemStack[] getOutfit(String playerUUID, String name) {
        String stringArmour = dataFile.getString(playerUUID + "." + name + ".armour");
        ItemStack[] outfit;
        try {
            BukkitSerialization bukkitSerialization = new BukkitSerialization();
            outfit = bukkitSerialization.itemStackArrayFromBase64(stringArmour);
        } catch (IOException e) {throw new RuntimeException(e);}
        return outfit;
    }

    public void dataFileSaver(FileConfiguration dataFile) {
        try {
            KitPlugin.getInstance().saveDataConfig(KitPlugin.getInstance().getDataConfigFile(), dataFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } // Save file
    }

}
