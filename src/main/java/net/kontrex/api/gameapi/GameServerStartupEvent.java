package net.kontrex.api.gameapi;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 07.09.2017
 * - TIME: 15:58
 * - PROJECT: KontrexAPI
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class GameServerStartupEvent extends Event implements Cancellable {

    public boolean isCancelled() {
        return false;
    }

    public void setCancelled(boolean b) {
        if(b) {

        }
    }

    public HandlerList getHandlers() {
        return null;
    }
}
