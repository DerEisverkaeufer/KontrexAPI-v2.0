package net.kontrex.api.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import net.kontrex.api.Var;

public class KickListener implements Listener {

	@EventHandler
	public void on(PlayerKickEvent e) {
		
		if(e.getReason().contains("disconnect.spam")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(Var.prefix + "§cKomm mal runter§8!");
			return;
		}

		e.setLeaveMessage(null);
		for(Player p : Bukkit.getOnlinePlayers())
			p.sendMessage(Var.prefix + "§b" + e.getPlayer().getName() + " §7wurde vom Server gekickt§8: §6" + e.getReason());
		e.setReason(Var.prefix + e.getReason());
	}

}
