package net.kontrex.api.listener;

import net.kontrex.api.KontrexAPI;
import net.kontrex.api.Var;
import net.kontrex.api.chatapi.ActionBarAPI;
import net.kontrex.api.chatapi.ChatAPI;
import net.kontrex.api.chatapi.TitleAPI;
import net.kontrex.api.database.DevAPI;
import net.kontrex.api.database.DevMode;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.HashMap;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 07.09.2017
 * - TIME: 22:52
 * - PROJECT: KontrexAPI
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class ChatListener implements Listener {

    public static HashMap<Player, Integer> count = new HashMap<>();

    @EventHandler
    public void on(PlayerChatEvent e) {

        if(!count.containsKey(e.getPlayer())) {
            count.put(e.getPlayer(), 1);
        } else {
            count.put(e.getPlayer(), count.get(e.getPlayer()) + 1);
        }

        if(count.get(e.getPlayer()) > 2 && count.get(e.getPlayer()) < 8) {

            if(DevMode.isInDevMode(e.getPlayer())) {
                DevAPI.notify(e.getPlayer(), getClass(), "Du wirst benachrichtigt, da du §e" + count.get(e.getPlayer()) + "§7 Chatnachrichten in der Sekunde gesendet hast§8!");
                return;
            }

            TitleAPI.send(e.getPlayer(), ChatAPI.generatePrefix(ChatColor.YELLOW, "AntiSpam"), "§7Bitte spamme keine Nachrichten§8! [§a" + count.get(e.getPlayer()) + "§7NPS§8]", 5, 100, 5);
            e.setCancelled(true);
            return;
        } else if(count.get(e.getPlayer()) == 8) {

            if(DevMode.isInDevMode(e.getPlayer())) {
                DevAPI.notify(e.getPlayer(), getClass(), "Du wirst gekickt, da du §e8§7 Chatnachrichten in der Sekunde gesendet hast§8!");
                return;
            }

            e.getPlayer().kickPlayer("§cBitte spamme keine Chatnachrichten§8!");
            e.setCancelled(true);
        }

        if(e.getMessage().startsWith("/"))
            return;

        String msg = e.getMessage();
        String[] args = msg.split(" ");

        for(int i = 0; i < args.length; i++) {
            String arg = args[i].toLowerCase().trim().replace("1", "i").replace("3", "e")
                    .replace("4", "a").replace("5", "s").replace("0", "o")
                    .replace(".", "").replace("!", "").replace("?", "")
                    .replace(",", "").replace("-", "");
            if(arg.equals("low")) {
                args[i] = "gut";
            } else if(arg.equals("hs") || arg.equals("hurensohn") || arg.equals("huso")) {
                args[i] = "Teddybär";
            } else if(arg.equals("ez") || arg.equals("easy") || arg.equals("e²")) {
                args[i] = "schwer";
            } else if(arg.equals("misset") || arg.equals("missgeburt")) {
                args[i] = "Blümchen";
            } else if(arg.equals("scheiße")) {
                args[i] = "krass";
            } else if(arg.equals("kid") || arg.equals("kiddy") || arg.equals("kiddie")) {
                args[i] = "Schmetterling";
            } else if(arg.equals("gay")) {
                args[i] = "cool";
            } else if(arg.equals("lowes")) {
                args[i] = "gutes";
            } else if(arg.equals("scheiß")) {
                args[i] = "krasses";
            } else if(arg.equals("fett")) {
                args[i] = "hübsch <3";
            } else {
                continue;
            }
        }

        String newmsg = "";
        for(int i = 0; i < args.length; i++) {
            newmsg += " " + args[i];
        }

        newmsg = newmsg.replaceFirst(" ", "");
        e.setMessage(newmsg);
    }

}
