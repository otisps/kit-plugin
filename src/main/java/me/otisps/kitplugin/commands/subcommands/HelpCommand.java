package me.otisps.kitplugin.commands.subcommands;

import me.otisps.kitplugin.KitPlugin;
import me.otisps.kitplugin.commands.SubCommand;
import me.otisps.kitplugin.utils.MessageFactory;
import org.bukkit.command.CommandSender;

public class HelpCommand implements SubCommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Explains how to use the /kit command";
    }

    @Override
    public String getUsage() {
        return "/kit help";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        MessageFactory.messageSender(sender, "help-message");
    }
}
