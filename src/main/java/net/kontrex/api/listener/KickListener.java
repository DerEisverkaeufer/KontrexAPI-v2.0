package net.kontrex.api.listener;

import net.kontrex.api.KontrexAPI;
import net.kontrex.api.chatapi.ChatAPI;
import net.kontrex.api.chatapi.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import net.kontrex.api.Var;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class KickListener implements Listener {

	@EventHandler
	public void on(PlayerKickEvent e) {

		String tmpreason = e.getReason();

		if(!e.getReason().contains(Var.prefix)) {
			e.setReason(Var.prefix + e.getReason());
		} else {
			tmpreason = tmpreason.replace(Var.prefix, "");
		}

		e.setLeaveMessage(null);

		if(e.getReason().contains("disconnect.spam")) {
			e.setCancelled(true);
			return;
		}

		tmpreason = tmpreason.replace("§a", "").replace("§b", "")
				.replace("§c", "").replace("§d", "")
				.replace("§e", "").replace("§f", "")
				.replace("§0", "").replace("§1", "")
				.replace("§2", "").replace("§3", "")
				.replace("§4", "").replace("§5", "")
				.replace("§6", "").replace("§7", "")
				.replace("§8", "").replace("§9", "");

		if(tmpreason.contains(Var.prefix)) {
			tmpreason = (tmpreason.replace(Var.prefix, ""));
		}

		for(Player p : Bukkit.getOnlinePlayers())
			p.sendMessage(Var.prefix + "§b" + e.getPlayer().getName() + " §7wurde vom Server gekickt§8: §6" + tmpreason);

	}

}
