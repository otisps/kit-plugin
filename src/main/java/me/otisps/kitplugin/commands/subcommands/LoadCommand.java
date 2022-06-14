package me.otisps.kitplugin.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoadCommand implements SubCommand{
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public void perform(CommandSender sender, Command command, String[] args) {
        // do stuff
        Player player = (Player) sender;
        player.sendMessage(getDescription());

    }
}
