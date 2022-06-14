package me.otisps.kitplugin.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteCommand implements SubCommand{
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
    public void perform(CommandSender sender, Command command, String[] args) {
        // do stuff
        Player player = (Player) sender;
        player.sendMessage(getDescription());

    }
}
