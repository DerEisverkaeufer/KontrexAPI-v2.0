package net.kontrex.api.command;

import net.kontrex.api.Var;
import net.kontrex.api.system.CommandSystem;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 06.09.2017
 * - TIME: 16:08
 * - PROJECT: KontrexAPI
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class TeleportCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(!(commandSender instanceof Player)) {
            return true;
        }

        Player p = (Player) commandSender;

        if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                CommandSystem.getInstance().sendPlayerNotFound(p, args[0]);
                return true;
            }
            p.teleport(target);
            p.sendMessage(Var.prefix + "Du wurdest zu §6" + target.getName() + " §7teleportiert§8.");
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
        } else if(args.length == 2) {
            Player target1 = Bukkit.getPlayer(args[0]);
            Player target2 = Bukkit.getPlayer(args[1]);

            if(target1 == null) {
                CommandSystem.getInstance().sendPlayerNotFound(p, args[0]);
                return true;
            }

            if(target2 == null) {
                CommandSystem.getInstance().sendPlayerNotFound(p, args[1]);
                return true;
            }

            target1.teleport(target2);
            p.sendMessage(Var.prefix + "§6" + target1.getName() + " §7wurde zu §6" + target2.getName() + " §7teleportiert§8.");
            target1.sendMessage(Var.prefix + "Du wurdest zu §6" + target2 + " §7teleportiert§8.");
            target1.playSound(target1.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
        } else {
            p.sendMessage(CommandSystem.getInstance().getUsage("tp <Spieler> oder /tp <Spieler1> <Spieler2>"));
        }

        return false;
    }
}
