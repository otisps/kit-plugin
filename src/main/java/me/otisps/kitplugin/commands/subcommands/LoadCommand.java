package me.otisps.kitplugin.commands.subcommands;

import me.otisps.kitplugin.KitPlugin;
import me.otisps.kitplugin.commands.SubCommand;
import me.otisps.kitplugin.utils.BukkitSerialization;
import me.otisps.kitplugin.utils.MessageFactory;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class LoadCommand implements SubCommand {
    @Override
    public String getName() {
        return "load";
    }

    @Override
    public String getDescription() {
        return "load a kit into your inventory";
    }

    @Override
    public String getUsage() {
        return "/kit load {name}";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        String playerUUID = player.getUniqueId().toString();
        String name = args[1];
        FileConfiguration dataFile = KitPlugin.getInstance().getDataConfig();

        String stringInventory = dataFile.getString(playerUUID + "." + name + ".inventory");
        String stringArmour = dataFile.getString(playerUUID + "." + name + ".armour");


        ItemStack[] inv;

        try {
            inv = BukkitSerialization.itemStackArrayFromBase64(stringInventory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ItemStack[] outfit;

        try {
            outfit = BukkitSerialization.itemStackArrayFromBase64(stringArmour);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        player.getInventory().setContents(inv);
        player.getInventory().setArmorContents(outfit);

        player.updateInventory();

        //TODO
        player.sendMessage(MessageFactory.formatMessage("success", name));
    }
}
