package me.otisps.kitplugin.commands.subcommands;

import me.otisps.kitplugin.KitPlugin;
import me.otisps.kitplugin.commands.SubCommand;
import me.otisps.kitplugin.utils.BukkitSerialization;
import me.otisps.kitplugin.utils.MessageFactory;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.IOException;
import java.util.Arrays;

import static me.otisps.kitplugin.utils.FileUtils.dataFileSaver;

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
        //Initialize
        Player player = (Player) sender;
        String playerUUID = player.getUniqueId() + "";
        PlayerInventory playerInventory = player.getInventory();
        String name = args[1];
        FileConfiguration dataFile = KitPlugin.getInstance().getDataConfig();

        //save inv and armour
        inventorySaver(playerUUID, playerInventory, name, dataFile);
        armourSaver(playerUUID, playerInventory, name, dataFile);

        // save file
        dataFileSaver(dataFile);

        // msg success
        MessageFactory.messageSender(sender, "save-message", name);
    }

    private void armourSaver(String playerUUID, PlayerInventory playerInventory, String name, FileConfiguration dataFile) {
        ItemStack[] serializedArmour = playerInventory.getArmorContents();
        String stringArmour = BukkitSerialization.itemStackArrayToBase64(serializedArmour);
        dataFile.set(playerUUID + "." + name + ".armour", stringArmour);
    }

    private void inventorySaver(String playerUUID, PlayerInventory playerInventory, String name, FileConfiguration dataFile) {
        ItemStack[] serializedInventory = playerInventory.getContents();
        String stringInv = BukkitSerialization.itemStackArrayToBase64(serializedInventory); // Convert that array of ItemStacks to a String
        dataFile.set(playerUUID + "." + name + ".inventory", stringInv);
    }
}
