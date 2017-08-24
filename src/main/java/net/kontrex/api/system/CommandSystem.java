package net.kontrex.api.system;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;

import net.kontrex.api.Var;

public class CommandSystem {

	public static CommandSystem getInstance() {
		return new CommandSystem();
	}
	
	public void sendCommandNotFound(Player p, String name) {
		p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
		p.sendMessage(Var.prefix + "Der Befehl §8[§b/" + name + "§8] §7wurde im System nicht gefunden§8!");
	}
	
	public String getParameterContents(String variable, String contents) {
		return getPrefix() + "§cVerfügbare Angaben für Parameter §6" + variable + "§8: §7" + contents.replace("/", "§8/§7");
	}
	
	public String getUsage(String cmdWithoutSlash) {
		return getPrefix() + "§cKorrekte Verwendung§8: §6/" + cmdWithoutSlash;
	}
	
	public String getPrefix() {
		return "§8▏ §4Kontrex §8▏ §7";
	}
	
	public String getCommandPerm(String cmdName) {
		return ((CraftServer)Bukkit.getServer()).getCommandMap().getCommand(cmdName).getPermission();
	}
	
	public void sendNoPerm(CommandSender p) {
		p.sendMessage(getPrefix() + "§cDu hast keine Rechte§8!");
	}

}
