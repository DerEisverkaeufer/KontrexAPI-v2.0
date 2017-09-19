package net.kontrex.api.gameapi;

import net.kontrex.api.KontrexAPI;
import net.kontrex.api.Var;
import net.kontrex.api.network.Datapackage;
import net.kontrex.api.network.NetworkException;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 13.09.2017
 * - TIME: 17:34
 * - PROJECT: KontrexAPI-Spigot
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class GameServerShutdownEvent extends Event {

    public GameServerShutdownEvent(String result) {
        try {
            KontrexAPI.getApi().getNetworkMessenger().sendMessage(new Datapackage("GAMESERVER_STOPPED", Bukkit.getServerName()));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(Var.eventPrefix + "Beim Aufrufen des §aGameServerShutdownEvent §7ist ein Fehler aufgetreten§8: §e" + e.getMessage());
        }
    }

    public HandlerList getHandlers() {
        return null;
    }

}
