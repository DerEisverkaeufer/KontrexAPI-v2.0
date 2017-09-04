package net.kontrex.api;

import net.kontrex.api.command.GameModeCommand;
import net.kontrex.api.listener.KickListener;
import net.kontrex.api.system.CommandSystem;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;
import java.util.TimerTask;

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
	
	public static KontrexAPI getApi() {
		return api;
	}

	public static boolean isBungeeCord() {
		return bungeeCord;
	}

	public static void enableBungeeCord() {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				bungeeCord = true;
			}
		}, 1000);
	}

	public static void disableBungeeCord() {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				bungeeCord = false;
			}
		}, 1000);
	}
	
	public static JsonAPI getJsonAPI() {
		return jsonAPI;
	}

	private static String pluginName(Plugin plugin) {
		if(plugin.isEnabled()) {
			return "§a" + plugin.getName();
		} else {
			return "§c" + plugin.getName();
		}
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
		
		if((name.equalsIgnoreCase("pl") || name.equalsIgnoreCase("plugins") || name.equalsIgnoreCase("help") || name.equalsIgnoreCase("?") || name.equalsIgnoreCase("ver") || name.equalsIgnoreCase("icanhasbukkit") || name.equalsIgnoreCase("me") || name.equalsIgnoreCase("version") || name.equalsIgnoreCase("about") || name.equalsIgnoreCase("tell") || name.toLowerCase().contains("mv"))) {
			e.setCancelled(true);
			if(!e.getPlayer().hasPermission("kontrex.plugins")) {
				CommandSystem.getInstance().sendNoPerm(e.getPlayer());
			} else {
				Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
				int count = plugins.length;
				String pluginList = "";
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BURP, 1, 1.5f);

				for(int i = 0; i < count; i++) {
					if(pluginList.equals("")) {
						pluginList = pluginName(plugins[i]);
					} else {
						pluginList += "§8, " + pluginName(plugins[i]);
					}
				}

				if(count == 0) {
				    e.getPlayer().sendMessage(Var.prefix + "Auf diesem Server sind §bkeine Plugins §7installiert§8.");
                } else {
                    e.getPlayer().sendMessage(Var.prefix + "Auf diesem Server sind §b" + count + " §7Plugins installiert§8.");
                    e.getPlayer().sendMessage(Var.prefix + pluginList);
                }

			}
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
