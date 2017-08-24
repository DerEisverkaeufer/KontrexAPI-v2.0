package net.kontrex.api.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

	@EventHandler
	public void on(PlayerLoginEvent e) {
		final Player p = e.getPlayer();
		
		
		
	}
	
}
