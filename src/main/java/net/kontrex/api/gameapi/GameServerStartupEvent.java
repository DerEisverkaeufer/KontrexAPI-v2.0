package net.kontrex.api.gameapi;

import net.kontrex.api.KontrexAPI;
import net.kontrex.api.Var;
import net.kontrex.api.network.Datapackage;
import net.kontrex.api.network.NetworkException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

    private boolean cancelled;

    public GameServerStartupEvent() {
        try {
            KontrexAPI.getApi().getNetworkMessenger().sendMessage(new Datapackage("GAMESERVER_STARTED", Bukkit.getServerName()));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(Var.eventPrefix + "Beim Aufrufen des §aGameServerStartupEvent §7ist ein Fehler aufgetreten§8: §e" + e.getMessage());
        }
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean b)  {
        cancelled = b;
        if(b) {
            Bukkit.broadcast(Var.prefix + "§cDieser Server wird in §45 Sekunden §cgestoppt§8!", null);
            try {
                KontrexAPI.getApi().getNetworkMessenger().sendMessage(new Datapackage("GAMESERVER_STOPPING", Bukkit.getServerName(), "StopResult.STARTUP_EVENT_CANCELLED"));
            } catch (NetworkException e) {
                Bukkit.getConsoleSender().sendMessage(Var.eventPrefix + "Beim Aufrufen des §aGameServerStartupEvent §7ist ein Fehler aufgetreten§8: §e" + e.getMessage());
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
             }
            if(isCancelled()) {
                Bukkit.shutdown();
            }
        }
    }

    public HandlerList getHandlers() {
        return null;
    }
}
