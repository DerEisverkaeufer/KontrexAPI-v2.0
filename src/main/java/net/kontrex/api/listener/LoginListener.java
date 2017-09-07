package net.kontrex.api.listener;

import net.kontrex.api.KontrexAPI;
import net.kontrex.api.Var;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.HashMap;
import java.util.Random;

public class LoginListener implements Listener {

	private static HashMap<Player, Integer> trys = new HashMap<Player, Integer>();

	@EventHandler
	public void on(PlayerLoginEvent e) {
		final Player p = e.getPlayer();

		if(KontrexAPI.getApi().isBungeeCord() && !p.getAddress().getHostName().equals("127.0.0.1")) {
			if(trys.containsKey(p)) {
				trys.put(p, trys.get(p) + 1);
			} else {
				trys.put(p, 1);
			}

			String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			String randomId = "";
			for(int i = 0; i < 10; i++) {
				randomId += chars.charAt(new Random().nextInt(chars.length()));
			}

			e.disallow(PlayerLoginEvent.Result.KICK_OTHER, Var.prefix + "\nBitte betrete die Gameserver über der IP §bKontrex§8.§b.net§8!\n§7Sollte dies ein Fehler sein§8, §7melde diesen Bitte mit der ID §6" + trys.get(p) + "#" + randomId + "§8.");
		}

	}
	
}
