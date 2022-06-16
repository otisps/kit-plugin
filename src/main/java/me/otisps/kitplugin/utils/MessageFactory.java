package me.otisps.kitplugin.utils;

import me.otisps.kitplugin.KitPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageFactory {

    private final Pattern hexPattern = Pattern.compile("#[a-fA-F0-9]{6}");

    /***
     * Formats a message so you can send to a player.
     * @param message
     * @param kitName
     * @return
     */
    public String formatMessage(String message, String kitName){
            message = hexFormat(message);
            message = placeholderReplacer(message, kitName);
        return message;
    }

    /***
     * Formats a message so you can send to a player.
     * @param message
     * @return
     */
    public String formatMessage(String message){
        message = hexFormat(message);
        message = placeholderReplacer(message);
        return message;
    }


    public String hexFormat(String message){ // Credit to CodedRed
        Matcher match = hexPattern.matcher(message);
        while (match.find()){
            String colour = message.substring(match.start(), match.end());
            message = message.replace(colour, ChatColor.valueOf(colour) + "");
            match = hexPattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String placeholderReplacer(String message, String kitName){

       message = placeholderReplacer(message);
       message = message.replace("{KIT-NAME}", kitName);
        return message;
    }

    public String placeholderReplacer(String message){
        String prefix = String.valueOf(KitPlugin.getInstance().getConfig().get("prefix"));
        message = message.replace("{PREFIX}", formatMessage(prefix));
        return message;
    }
    public String getMessage(String path){
        String message = String.valueOf(KitPlugin.getInstance().getConfig().get(path));
        return message;
    }
    public void messageSender(CommandSender sender, String path, String kitName){
        String message = formatMessage(getMessage(path), kitName);
        sender.sendMessage(message);
    }

    public void messageSender(CommandSender sender, String path){
        String message = formatMessage(getMessage(path));
        sender.sendMessage(message);
    }


}
