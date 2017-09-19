package net.kontrex.api.database;

import net.kontrex.api.KontrexAPI;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 19.09.2017
 * - TIME: 11:21
 * - PROJECT: KontrexAPI-Spigot
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class CoinsAPI {

    private MySQL mySQL;

    public CoinsAPI(@Nonnull MySQL mySQL) {
        this.mySQL = mySQL;
    }

    public void connect() {
        if(!mySQL.isConnected())
            mySQL.connect(true);

        if(!mySQL.doesTableExists("CoinsDB"))
            mySQL.createTable("CoinsDB", "UUID VARCHAR(100), Coins INTEGER(10)");
    }

    public boolean doesExist(UUID uuid) {
        try {
            ResultSet rs = mySQL.get("SELECT * FROM CoinsDB WHERE UUID = '" + uuid.toString() + "'");
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void add(UUID uuid, int coins) {
        int old = get(uuid);
        set(uuid, old + coins);
    }

    public void remove(UUID uuid, int coins) {
        int old = get(uuid);
        if(old - coins < 0) {
            set(uuid, 0);
            return;
        }
        set(uuid, old - coins);

    }

    public void set(UUID uuid, int coins) {
        if(!doesExist(uuid)) {
            mySQL.update("INSERT INTO CoinsDB (UUID, Coins) VALUES ('" + uuid.toString() + "','" + coins + "')");
        } else {
            mySQL.update("UPDATE CoinsDB SET Coins = '" + coins + "' WHERE UUID = '" + uuid.toString() + "'");
        }
    }

    public int get(UUID uuid) {

        if(!doesExist(uuid))
            return -1;

        try {
            ResultSet rs = mySQL.get("SELECT Coins FROM CoinsDB WHERE UUID = '" + uuid.toString() + "'");
            while(rs.next()) {
                return rs.getInt("Coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
