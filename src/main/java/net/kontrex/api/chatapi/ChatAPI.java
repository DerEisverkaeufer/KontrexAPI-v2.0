package net.kontrex.api.chatapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

/**
 * Created by DerEisverkaeufer on 05.09.2017.
 */
public class ChatAPI {

    /**
     * Returns a prefix like in other Kontrex.net plugins.
     * @param chatColor The color of the prefix
     * @param name The name which will be between two vertical lines and will have the color of the parameter 'chatColor'.
     * @return The prefix as String (Example: "§8▏ §9Kontrex §8▏ §7')
     */
    public static String generatePrefix(@Nonnull ChatColor chatColor, @Nonnull String name) {
        return "§8▏ " + chatColor.toString() + name + " §8▏ §7";
    }

    /**
     * Clears the chat of all players.
     */
    public static void clearChat() {
        for(int i = 0; i < 256; i++) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage("§a");
            }
        }
    }

    /**
     * Clears the chat of some specified players.
     * @param players The players whose chat will be cleared.
     */
    public static void clearChat(Player... players) {
        for(int i = 0; i < 256; i++) {
            for (Player all : players) {
                all.sendMessage("§a");
            }
        }
    }

}
