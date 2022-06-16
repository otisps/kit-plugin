package me.otisps.kitplugin.utils;

import me.otisps.kitplugin.KitPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageFactory {

    private static final Pattern hexPattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String formatMessage(String message, String kitName){
            message = hexFormat(message);
            message = placeholderReplacer(message, kitName);
        return message;
    }


    public static String hexFormat(String message){ // Credit to CodedRed
        Matcher match = hexPattern.matcher(message);
        while (match.find()){
            String colour = message.substring(match.start(), match.end());
            message = message.replace(colour, ChatColor.valueOf(colour) + "");
            match = hexPattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String placeholderReplacer(String message, String kitName){
        message.replace("{KIT-NAME}", kitName);
        message.replace("{PREFIX}", String.valueOf(KitPlugin.getInstance().getConfig().get("prefix")));
        return message;
    }

    public static void messageSender(CommandSender sender, String path, String kitName){
        String message = String.valueOf(KitPlugin.getInstance().getConfig().get(path));
        sender.sendMessage(MessageFactory.formatMessage(message, kitName));
    }

    public static void messageKitsSender(CommandSender sender, String list){
        sender.sendMessage(MessageFactory.formatMessage(list, ""));
    }

}
