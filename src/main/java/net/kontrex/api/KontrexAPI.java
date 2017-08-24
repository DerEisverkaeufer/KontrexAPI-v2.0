package net.kontrex.api;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.kontrex.api.command.GameModeCommand;
import net.kontrex.api.listener.KickListener;
import net.kontrex.api.system.CommandSystem;

public class KontrexAPI extends JavaPlugin implements Listener {

	private static KontrexAPI api;
	private static JsonAPI jsonAPI;
	private static SkinAPI skinAPI;
	private static UUIDFetcher uuidFetcher;
	private static boolean bungeeCord = true;
	
	@Override
	public void onEnable() {
		api = this;
		jsonAPI = new JsonAPI(api);
		skinAPI = new SkinAPI(api);
		uuidFetcher = new UUIDFetcher(api);
		
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new KickListener(), this);
		
		this.getCommand("gamemode").setExecutor(new GameModeCommand());
	}
	
	public static UUIDFetcher getUuidFetcher() {
		return uuidFetcher;
	}
	
	public static SkinAPI getSkinAPI() {
		return skinAPI;
	}
	
	public static void setBungeeCord(boolean bungeeCord) {
		KontrexAPI.bungeeCord = bungeeCord;
	}
	
	public static KontrexAPI getApi() {
		return api;
	}
	
	public static JsonAPI getJsonAPI() {
		return jsonAPI;
	}
	
	@EventHandler
	public void on(PlayerCommandPreprocessEvent e) {
		String name = e.getMessage().split(" ")[0].replaceFirst("/", "");
		Command cmd = ((CraftServer)Bukkit.getServer()).getCommandMap().getCommand(name);
		
		if(name.contains(":")) {
			name = name.split(":")[1];
		}
		
		if(cmd == null) {
			CommandSystem.getInstance().sendCommandNotFound(e.getPlayer(), name);
			e.setCancelled(true);
			return;
		}
		
		if(!e.getPlayer().hasPermission("kontrex.plugins") && (name.equalsIgnoreCase("pl") || name.equalsIgnoreCase("plugins") || name.equalsIgnoreCase("help") || name.equalsIgnoreCase("?") || name.equalsIgnoreCase("ver") || name.equalsIgnoreCase("icanhasbukkit") || name.equalsIgnoreCase("me") || name.equalsIgnoreCase("version") || name.equalsIgnoreCase("about") || name.equalsIgnoreCase("tell") || name.toLowerCase().contains("mv"))) {
			CommandSystem.getInstance().sendNoPerm(e.getPlayer());
			e.setCancelled(true);
			return;
		}
		
		if(cmd.getPermission() == null) {
			return;
		}
		
		if(!e.getPlayer().hasPermission(cmd.getPermission())) {
			e.setCancelled(true);
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_NO, 1, 1);
			CommandSystem.getInstance().sendNoPerm(e.getPlayer());
		}
		
	}

}
