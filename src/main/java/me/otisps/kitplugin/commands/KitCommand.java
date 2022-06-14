package me.otisps.kitplugin.commands;

import me.otisps.kitplugin.commands.subcommands.DeleteCommand;
import me.otisps.kitplugin.commands.subcommands.LoadCommand;
import me.otisps.kitplugin.commands.subcommands.SaveCommand;
import me.otisps.kitplugin.commands.subcommands.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class KitCommand implements CommandExecutor {
    ArrayList<SubCommand> subCommands = new ArrayList<>();
    public KitCommand() {
        subCommands.add(new SaveCommand());
        subCommands.add(new DeleteCommand());
        subCommands.add(new LoadCommand());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       sender.sendMessage("thanks, for all.");

       if(!(sender instanceof Player)) return true;
       Player player = (Player) sender;

       String subCommand = args[0];

        for (SubCommand potentialSubCommand:
             subCommands) {
            if(potentialSubCommand.getName() == subCommand){
                potentialSubCommand.perform(sender, command, args);
                break;
            }
        }
        return true;
    }
}
