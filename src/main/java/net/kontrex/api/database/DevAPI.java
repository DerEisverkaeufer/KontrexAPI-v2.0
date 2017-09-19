package net.kontrex.api.database;

import net.kontrex.api.chatapi.ChatAPI;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 19.09.2017
 * - TIME: 14:07
 * - PROJECT: KontrexAPI-Spigot
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class DevAPI {

    private static MySQL mySQL;

    public static void notify(Player p, String msg) {
        p.sendMessage(ChatAPI.generatePrefix(ChatColor.AQUA, "DevMode") + msg);
        p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 2);
    }

    public static void notifyServer(Class clazz, String msg) {
        for(Player p : DevMode.getDevModeList()) {
            p.sendMessage(ChatAPI.generatePrefix(ChatColor.AQUA, "DevMode") + "§a" + clazz.getName() + "§8: §7" + msg);
            p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 2);
        }
    }

    public static void notify(Player p, Class clazz, String msg) {
        p.sendMessage(ChatAPI.generatePrefix(ChatColor.AQUA, "DevMode") + "§a" + clazz.getName() + "§8: §7" +  msg);
        p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 2);
    }

    public static void setMySQL(MySQL mySQL) {
        DevAPI.mySQL = mySQL;

        if(!mySQL.isConnected())
            mySQL.connect(true);

        if(!mySQL.doesTableExists("DevMode"))
            mySQL.createTable("DevMode", "UUID Varchar(100), enabled INTEGER(3)");

    }

    private static boolean isExists(UUID uuid) {
        try {
            ResultSet rs = mySQL.getConnection().prepareStatement("SELECT * FROM DevMode WHERE UUID = '" + uuid.toString() + "'").executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    public static void setDevMode(UUID uuid, boolean enabled) {
        int toInt = (enabled ? 1 : 0);

        if(isExists(uuid)) {
            mySQL.update("UPDATE DevMode SET enabled = '" + toInt + "' WHERE UUID = '" + uuid.toString() + "'");
        } else {
            mySQL.update("INSERT INTO DevMode (UUID,enabled) VALUES ('" + uuid.toString() + "','" + toInt + "')");
        }

    }

    public static boolean isInDevMode(UUID uuid) {
        try {
            ResultSet rs = mySQL.get("SELECT * FROM DevMode WHERE UUID = '" + uuid.toString() + "'");
            while (rs.next()) {
                return (rs.getInt("enabled") == 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
