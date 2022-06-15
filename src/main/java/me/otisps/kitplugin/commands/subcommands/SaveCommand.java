package me.otisps.kitplugin.commands.subcommands;

import me.otisps.kitplugin.KitPlugin;
import me.otisps.kitplugin.commands.SubCommand;
import me.otisps.kitplugin.utils.BukkitSerialization;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.IOException;
import java.util.Arrays;

public class SaveCommand implements SubCommand {
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "Save everything in your inventory including armour slots and off-hand to kit.";
    }

    @Override
    public String getUsage() {
        return "/kit save {name}";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        String playerUUID = player.getUniqueId().toString();
        PlayerInventory playerInventory = player.getInventory();
        String name = args[1];

        FileConfiguration dataFile = KitPlugin.getInstance().getDataConfig();

        ItemStack[] serializedInventory = playerInventory.getContents();
        ItemStack[] serializedArmour = playerInventory.getArmorContents();

        String stringInv = BukkitSerialization.itemStackArrayToBase64(serializedInventory); // Convert that array of ItemStacks to a String
        String stringArmour = BukkitSerialization.itemStackArrayToBase64(serializedArmour);

        dataFile.set(playerUUID + "." + name + ".inventory", stringInv);
        dataFile.set(playerUUID + "." + name + ".armour", stringArmour);


        try {
            KitPlugin.getInstance().saveDataConfig(KitPlugin.getInstance().getDataConfigFile(), dataFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } // Save file

        //TODO: MESSAGE
    }
}
