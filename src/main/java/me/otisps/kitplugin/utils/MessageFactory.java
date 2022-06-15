package me.otisps.kitplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageFactory {

    public String formatMessage(String message){
        message = ChatColor.translateAlternateColorCodes('&', message);
        // TODO : SEE IF BUKKIT OR HEX
        // TODO : TRANSLATE HEX
        // TODO : REPLACE SPECIAL SHITE
        return message;
    }
}
