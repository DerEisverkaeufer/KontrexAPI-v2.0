package net.kontrex.api.listener;

import net.kontrex.api.Var;
import net.kontrex.api.chatapi.ActionBarAPI;
import net.kontrex.api.chatapi.ChatAPI;
import net.kontrex.api.chatapi.TitleAPI;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

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

    @EventHandler
    public void on(PlayerChatEvent e) {

        if(e.getMessage().startsWith("/"))
            return;

        String msg = e.getMessage();
        String changes = "";
        String[] args = msg.split(" ");

        for(int i = 0; i < args.length; i++) {
            String arg = args[i].toLowerCase().trim().replace("1", "i").replace("3", "e")
                    .replace("4", "a").replace("5", "s").replace("0", "o")
                    .replace(".", "").replace("!", "").replace("?", "")
                    .replace(",", "").replace("-", "");
            if(arg.equals("low")) {
                changes += " × " + args[i];
                args[i] = "gut";
            } else if(arg.equals("hs") || arg.equals("hurensohn") || arg.equals("huso")) {
                changes += " × " + args[i];
                args[i] = "Teddybär";
            } else if(arg.equals("ez") || arg.equals("easy") || arg.equals("e²")) {
                changes += " × " + args[i];
                args[i] = "knapp";
            } else if(arg.equals("misset") || arg.equals("missgeburt")) {
                changes += " × " + args[i];
                args[i] = "Blümchen";
            } else if(arg.equals("scheiße")) {
                changes += " × " + args[i];
                args[i] = "richtig";
            } else if(arg.equals("kid") || arg.equals("kiddy") || arg.equals("kiddie")) {
                changes += " × " + args[i];
                args[i] = "Schmetterling";
            } else if(arg.equals("gay")) {
                changes += " × " + args[i];
                args[i] = "cool";
            } else if(arg.equals("lowes")) {
                changes += " × " + args[i];
                args[i] = "gutes";
            } else if(arg.equals("scheiß")) {
                changes += " × " + args[i];
                args[i] = "richtiges";
            }
        }

        String newmsg = "";
        for(int i = 0; i < args.length; i++) {
            newmsg += " " + args[i];
        }

        newmsg = newmsg.replaceFirst(" ", "");
        e.setMessage(newmsg);

        if(!changes.equals("")) {
            changes = changes.replaceFirst(" ", "");
            changes = changes.replaceFirst("×", "");
            changes = "§f§l" + changes.replace("×", "§8●§f§l");
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BURP, 2, 1);
            ActionBarAPI.send(e.getPlayer(), ChatAPI.generatePrefix(ChatColor.DARK_RED, "§lCensored") + changes);
        }
    }

}
