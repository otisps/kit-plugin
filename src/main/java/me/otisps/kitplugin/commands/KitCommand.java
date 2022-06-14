package me.otisps.kitplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       sender.sendMessage("thanks, for all.");

       if(!(sender instanceof Player)) return true;
       Player player = (Player) sender;

       String subCommand = args[0];
       String target = args[1];

       switch (subCommand) {
           case "save":
               break;
           case "delete":
               break;
           case "load":
               break;
        }

        return true;
    }
}
