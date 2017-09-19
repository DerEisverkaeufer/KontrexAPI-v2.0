package net.kontrex.api.database;

import java.util.ArrayList;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 19.09.2017
 * - TIME: 11:07
 * - PROJECT: KontrexAPI-Spigot
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class MySQLConnections {

    public static ArrayList<MySQL> connections = new ArrayList<>();

    public static void closeAllConnections() {
        for(MySQL mysql : connections) {
            mysql.disconnect();
        }
    }

}
