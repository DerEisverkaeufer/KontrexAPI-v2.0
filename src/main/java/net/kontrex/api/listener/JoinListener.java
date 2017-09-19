package net.kontrex.api.listener;

import net.kontrex.api.database.DevAPI;
import net.kontrex.api.database.DevMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 19.09.2017
 * - TIME: 14:36
 * - PROJECT: KontrexAPI-Spigot
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class JoinListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent e) {
        if(DevAPI.isInDevMode(e.getPlayer().getUniqueId()))
            DevMode.getDevModeList().add(e.getPlayer());
    }

}
