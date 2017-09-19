package net.kontrex.api.database;

import net.kontrex.api.Var;
import org.bukkit.Bukkit;

import java.sql.*;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 14.09.2017
 * - TIME: 16:51
 * - PROJECT: KontrexAPI-Spigot
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class MySQL {

    private String host,database,username,password;
    private Connection con;

    public MySQL(String host, String database, String username, String password) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.host = host;
        Bukkit.getConsoleSender().sendMessage(Var.mySql + "Neue MySQL-Instanz erstellt§8: §c" + host + "§8, §a" + database + "§8, §b" + username + "§8, §e" + password + "§8.");
        DevAPI.notifyServer(getClass(), "Neue MySQL-Instanz erstellt§8: §c" + host + "§8, §a" + database + "§8, §b" + username + "§8, §e" + password + "§8.");
        MySQLConnections.connections.add(this);
    }

    public boolean doesTableExists(String table) {
        try {
            ResultSet set = con.prepareStatement("SELECT * FROM " + table).executeQuery();
            return set.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public void createTable(String table, String pattern) {
        update("CREATE TABLE IF NOT EXISTS " + table + "(" + pattern + ")");
        Bukkit.getConsoleSender().sendMessage(Var.mySql + "Die Verbindung mit §c" + host + " §7hat die Tabelle §b" + table + " §7erstellt§8!");
        DevAPI.notifyServer(getClass(), "Die Verbindung mit §c" + host + " §7hat die Tabelle §b" + table + " §7erstellt§8!");
    }

    public void reconnect(boolean autoReconnect) {
        Bukkit.getConsoleSender().sendMessage(Var.mySql + "Versuche Verbindung mit §c" + host + " §7neu zu starten§8...");
        DevAPI.notifyServer(getClass(), "Versuche Verbindung mit §c" + host + " §7neu zu starten§8...");
        disconnect();
        connect(autoReconnect);
    }

    public void disconnect() {
        if(!isConnected())
            return;
        try {
            con.close();
            Bukkit.getConsoleSender().sendMessage(Var.mySql + "Die Verbindung mit §c" + host + " §7wurde erfolgreich §ageschlossen§8!");
            DevAPI.notifyServer(getClass(), "Die Verbindung mit §c" + host + " §7wurde erfolgreich §ageschlossen§8!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String command) {
        try {
            PreparedStatement ps = con.prepareStatement(command);
            ps.executeUpdate();
            Bukkit.getConsoleSender().sendMessage(Var.mySql + "Die Verbindung mit §c" + host + " §7führte eine §bUpdate §7aus§8: §e" + command);
            DevAPI.notifyServer(getClass(), "Die Verbindung mit §c" + host + " §7führte eine §bUpdate §7aus§8: §e" + command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet get(String command) {
        try {
            PreparedStatement ps = con.prepareStatement(command);
            Bukkit.getConsoleSender().sendMessage(Var.mySql + "Die Verbindung mit §c" + host + " §7führte einen §bQuery §7aus§8: §e" + command);
            DevAPI.notifyServer(getClass(), "Die Verbindung mit §c" + host + " §7führte einen §bQuery §7aus§8: §e" + command);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return con;
    }

    public void connect(boolean autoReconnect) {
        if(isConnected())
            return;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?user=" + username + "&password=" + password);
            Bukkit.getConsoleSender().sendMessage(Var.mySql + "Die Verbindung mit §c" + host + " §7wurde erfolgreich §ahergestellt§8!");
            DevAPI.notifyServer(getClass(), "Die Verbindung mit §c" + host + " §7wurde erfolgreich §ahergestellt§8!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return (con != null);
    }

}
