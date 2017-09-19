package net.kontrex.api.database;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 19.09.2017
 * - TIME: 14:35
 * - PROJECT: KontrexAPI-Spigot
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class DevMode {

    private static ArrayList<Player> inDevMode = new ArrayList<>();

    public static ArrayList<Player> getDevModeList() {
        return inDevMode;
    }

    public static boolean isInDevMode(Player p) {
        return inDevMode.contains(p);
    }
}
