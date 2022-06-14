package me.otisps.kitplugin.commands.subcommands;

public interface SubCommand {
    String getName();
    String getDescription();
    String getUsage();
    void perform();
}
