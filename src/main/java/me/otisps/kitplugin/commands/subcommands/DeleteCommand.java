package me.otisps.kitplugin.commands.subcommands;

import me.otisps.kitplugin.KitPlugin;
import me.otisps.kitplugin.commands.SubCommand;
import me.otisps.kitplugin.utils.MessageFactory;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class DeleteCommand implements SubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Deletes a players kit";
    }

    @Override
    public String getUsage() {
        return "/kit delete {name}";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        // initialization
        Player player = (Player) sender;
        String playerUUID = player.getUniqueId().toString();
        String name = args[1];
        FileConfiguration dataFile = KitPlugin.getInstance().getDataConfig();

        dataFile.set(playerUUID + "." + name, null); // delete kit
        try { // save kit
            KitPlugin.getInstance().saveDataConfig(KitPlugin.getInstance().getDataConfigFile(), dataFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // message for success!
        MessageFactory.messageSender(sender, "delete-message", name);
    }
}
