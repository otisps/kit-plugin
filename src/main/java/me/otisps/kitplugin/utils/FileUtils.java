package me.otisps.kitplugin.utils;

import me.otisps.kitplugin.KitPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;


public class FileUtils {
    private static FileConfiguration dataFile = KitPlugin.getInstance().getDataConfig();

    public static ItemStack[] getInv(String playerUUID, String name) {
        String stringInventory = dataFile.getString(playerUUID + "." + name + ".inventory");
        ItemStack[] inv;
        try {
            inv = BukkitSerialization.itemStackArrayFromBase64(stringInventory);
        } catch (IOException e) {throw new RuntimeException(e);
        }
        return inv;
    }

    public static ItemStack[] getOutfit(String playerUUID, String name) {
        String stringArmour = dataFile.getString(playerUUID + "." + name + ".armour");
        ItemStack[] outfit;
        try {
            outfit = BukkitSerialization.itemStackArrayFromBase64(stringArmour);
        } catch (IOException e) {throw new RuntimeException(e);}
        return outfit;
    }

    public static void dataFileSaver(FileConfiguration dataFile) {
        try {
            KitPlugin.getInstance().saveDataConfig(KitPlugin.getInstance().getDataConfigFile(), dataFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } // Save file
    }

}
