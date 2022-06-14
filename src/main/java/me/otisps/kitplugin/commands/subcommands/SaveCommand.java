package me.otisps.kitplugin.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class SaveCommand implements SubCommand{
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "Save everything in your inventory including armour slots and off-hand to kit.";
    }

    @Override
    public String getUsage() {
        return "/kit save {name}";
    }

    @Override
    public void perform(CommandSender sender, Command command, String[] args) {
        Player player = (Player) sender;
        PlayerInventory items = player.getInventory();
        // do stuff
        player.sendMessage(getDescription());

    }
}
