package net.kontrex.api.network;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.net.Socket;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 07.09.2017
 * - TIME: 21:57
 * - PROJECT: KontrexAPI
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class NetworkCommandReceiveEvent extends Event {

    private String servername;
    private String command;
    private Socket socket;

    public NetworkCommandReceiveEvent(String servername, String command, Socket socket) {
        this.servername = servername;
        this.command = command;
        this.socket = socket;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getServername() {
        return servername;
    }

    public String getCommand() {
        return command;
    }
}
