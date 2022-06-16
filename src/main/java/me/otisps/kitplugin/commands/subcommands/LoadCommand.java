package me.otisps.kitplugin.commands.subcommands;

import me.otisps.kitplugin.KitPlugin;
import me.otisps.kitplugin.commands.SubCommand;
import me.otisps.kitplugin.utils.MessageFactory;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.otisps.kitplugin.utils.FileUtils.getInv;
import static me.otisps.kitplugin.utils.FileUtils.getOutfit;

public class LoadCommand implements SubCommand {
    @Override
    public String getName() {
        return "load";
    }

    @Override
    public String getDescription() {
        return "load a kit into your inventory";
    }

    @Override
    public String getUsage() {
        return "/kit load {name}";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        String playerUUID = player.getUniqueId() + "";
        String name = args[1];
        FileConfiguration dataFile = KitPlugin.getInstance().getDataConfig();

        ItemStack[] inv = getInv(playerUUID, name);
        ItemStack[] outfit = getOutfit(playerUUID, name);

        player.getInventory().setContents(inv);
        player.getInventory().setArmorContents(outfit);

        player.updateInventory();

        MessageFactory.messageSender(sender, "load-message", name);

    }




}
