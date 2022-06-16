package me.otisps.kitplugin.commands;

import me.otisps.kitplugin.KitPlugin;

import java.util.List;

public class KitsSearch {
    public static String[] search(String playerId){
        List<String> stringList = KitPlugin.getInstance().getDataConfig().getStringList(playerId);
        String[] kits = stringList.toArray(new String[stringList.size()]);
        return kits;
    }
}
