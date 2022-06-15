package me.otisps.kitplugin.commands;

import me.otisps.kitplugin.commands.subcommands.DeleteCommand;
import me.otisps.kitplugin.commands.subcommands.HelpCommand;
import me.otisps.kitplugin.commands.subcommands.LoadCommand;
import me.otisps.kitplugin.commands.subcommands.SaveCommand;
import me.otisps.kitplugin.utils.MessageFactory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;

//TODO: TAB-COMPLETE SubCommands
//TODO: TAB-COMPLETE NAMES
public class KitCommand implements CommandExecutor {
    ArrayList<SubCommand> subCommands = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();

    public KitCommand() { // constructor for initializing subcommands
        subCommands.add(new DeleteCommand());
        subCommands.add(new HelpCommand());
        subCommands.add(new LoadCommand());
        subCommands.add(new SaveCommand());
        for (SubCommand cmd : subCommands) {
            names.add(cmd.getName());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            // if sender not a player abort!
            sender.sendMessage("Player only command!");
            return true;
        }

        sender.sendMessage(MessageFactory.formatMessage("partial success"));
        if((args.length == 0)) { // invalid command usage sub command
            subCommands.get(1).perform(sender, args);
            return true;
        }

        SubCommand subCommand;
        String subLabel = args[0];

        for (String name:
             names) {
            if(name.equalsIgnoreCase(subLabel)){
                subCommand = commandFromString(subLabel);

                if (args.length == 2) { // valid args
                    subCommand.perform(sender, args);
                    sender.sendMessage(MessageFactory.formatMessage("success"));
                    return true;
                }

                //  invalid args
                sender.sendMessage(subCommand.getUsage()); // TODO: CONFIG
                return true;
            }
        }
       // Invalid Sub Command
        subCommands.get(1).perform(sender, args);
        return true;
    }

    /**
     * for getting subcommands
     * @param label /kit {label}
     * @return subcommand or null if it doesn't exist so check
     */
    public SubCommand commandFromString(String label) {
            for (String name :
                    names) {
                if (name.equalsIgnoreCase(label)) {
                    int index = names.indexOf(name);
                    return subCommands.get(index);
                }
            }
        return null;
    }

}
