package me.otisps.kitplugin.commands;

import me.otisps.kitplugin.KitPlugin;
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
            MessageFactory.messageSender(sender, "console-error-message", "");
            return true;
        }
        Player player = (Player) sender;
        if((args.length == 0)) {
            String[] kits = KitsSearch.search(String.valueOf(player.getUniqueId()));
            String list = String.valueOf(kits);
            MessageFactory.messageKitsSender(sender,list);
            return true;
        }

        SubCommand subCommand;
        String subLabel = args[0];

        for (String name: names) { if(name.equalsIgnoreCase(subLabel)){
            subCommand = commandFromString(subLabel);
            if (args.length == 2) { // valid args
                String[] search = KitsSearch.search(String.valueOf(player.getUniqueId()));
                if (subLabel.equalsIgnoreCase("delete") || subLabel.equalsIgnoreCase("load")) {
                    boolean found = isKitPresent(args, search);
                    if (!found) { // Not found!
                        MessageFactory.messageSender(sender, "not-found-message", name);
                    }
                }
                if(subLabel.equalsIgnoreCase("save")){
                    boolean found = isKitPresent(args, search);
                    if(found){
                        MessageFactory.messageSender(sender, "already-exists-message", name);
                    }
                }
                subCommand.perform(sender, args);
                return true;
            }

            //  Invalid number of arguments (usage)
            sender.sendMessage(MessageFactory.hexFormat("&c Error, incorrect usage, proper usage: " + subCommand.getUsage()));
            return true;
        }}
       // Invalid Sub Command
        subCommands.get(1).perform(sender, args);
        return true;
    }

    private boolean isKitPresent(String[] args, String[] search) {
        boolean found = false;
        String kitName = args[1];
        for (String string: search) { if (string.contains(kitName)){
            found = true;
        }}
        return found;
    }

    /**
     * for getting subcommands
     * @param label /kit {label}
     * @return subcommand or null if it doesn't exist so check
     */
    public SubCommand commandFromString(String label) {
            for (String name : names) {
                if (name.equalsIgnoreCase(label)) {
                    int index = names.indexOf(name);
                    return subCommands.get(index);
                }
            }
        return null;
    }

}
