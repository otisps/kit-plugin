package me.otisps.kitplugin.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {
    /**
     * Getter for name of SubCommand
     * @return SubCommand Label
     */
    String getName();
    /**
     * Getter for description of SubCommand
     * @return SubCommand Description
     */
    String getDescription();
    /**
     * Usage for SubCommand
     * @return /kit subcommand args
     */
    String getUsage();

    /**
     * SubCommand needs all same details as Command ...
     *
     * @param sender sender
     * @param command command
     * @param args args incl. sub command label
     */
    void perform(CommandSender sender, Command command, String[] args);
}
