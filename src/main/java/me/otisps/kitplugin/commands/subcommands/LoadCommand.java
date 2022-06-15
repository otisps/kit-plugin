package me.otisps.kitplugin.commands.subcommands;

import me.otisps.kitplugin.KitPlugin;
import me.otisps.kitplugin.commands.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

import static me.otisps.kitplugin.utils.BukkitSerialization.fromBase64;
import static me.otisps.kitplugin.utils.BukkitSerialization.itemStackArrayFromBase64;

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

        try {
            player.getInventory().setContents(fromBase64(
                    dataFile.getString(playerUUID + "." + name + ".inventory"))
                    .getContents());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            player.getInventory().setArmorContents(itemStackArrayFromBase64
                    (dataFile.getString(playerUUID + "." + name + ".armor")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player.updateInventory();

        //TODO: MESSAGE
    }
}
