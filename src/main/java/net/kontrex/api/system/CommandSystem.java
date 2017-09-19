package net.kontrex.api.system;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;

import net.kontrex.api.Var;

public class CommandSystem {

	/**
	 * Returns a newly initialized CommandSystem.
	 * @return The CommandSystem.
	 */
	public static CommandSystem getInstance() {
		return new CommandSystem();
	}

	/**
	 * Sends an unknown command message to a specified player.
	 * @param p The player.
	 * @param name The name of the command without '/'.
	 */
	public void sendCommandNotFound(Player p, String name) {
		p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
		p.sendMessage(Var.prefix + "Der Befehl §8[§b/" + name + "§8] §7wurde im System nicht gefunden§8!");
	}

	/**
	 * Returns a formatted message with available data for a parameter. Useful for commands.
	 * @param parameter The parameter which the information will be returned for.
	 * @param data The available data for the parameter, split by a ' / '.
	 * @return The formatted message.
	 */
	public String getParameterContents(String parameter, String data) {
		return getPrefix() + "§cVerfügbare Angaben für Parameter §6" + parameter + "§8: §7" + data.replace("/", "§8/§7");
	}

	/**
	 * Returns a formatted message with the real usage for a command. Useful for commands.
	 * @param cmdWithoutSlash The command without '/'.
	 * @return The formatted message.
	 */
	public String getUsage(String cmdWithoutSlash) {
		return getPrefix() + "§cKorrekte Verwendung§8: §8/§6" + cmdWithoutSlash.replace("<", "§8<§a").replace(">", "§8>§6").replace("/", "§8/§6").replace("oder", "§7oder§6");
	}

	public void sendPlayerNotFound(CommandSender sender, String playername) {
		sender.sendMessage(getPrefix() + "Der Spieler mit dem Namen §6" + playername + " §7ist nicht online§8!");
		sender.sendMessage(getPrefix() + "Bitte überprüfe die §eRechtschreibung §7des Namens§8.");
	}
	
	public String getPrefix() {
		return "§8▏ §4Kontrex §8▏ §7";
	}

	/**
	 * Returns the permission of a specified command.
	 * @param cmdName The name of the command.
	 * @return The permission as String.
	 */
	public String getCommandPerm(String cmdName) {
		return ((CraftServer)Bukkit.getServer()).getCommandMap().getCommand(cmdName).getPermission();
	}

	public void sendNonAccessToConsole() {
		Bukkit.getConsoleSender().sendMessage(getPrefix() + "§cDiese Aktion wird nicht für die Console unterstützt!");
	}

	/**
	 * Sends an unknown command message to a specified CommandSender.
	 * @param p The CommandSender which the message will be send to.
	 */
	public void sendNoPerm(CommandSender p) {
		p.sendMessage(getPrefix() + "§cDu hast keine Rechte§8!");
	}

}
