package net.kontrex.api.command;

import net.kontrex.api.Var;
import net.kontrex.api.system.CommandSystem;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 13.09.2017
 * - TIME: 17:20
 * - PROJECT: KontrexAPI-Spigot
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class TimeCommand implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if(!(s instanceof Player)) {
            CommandSystem.getInstance().sendNonAccessToConsole();
        }

        Player p = (Player) s;

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("day")) {
                p.getWorld().setTime(0);
                p.sendMessage(Var.prefix + "Die Zeit in der Welt §b" + p.getWorld().getName() + " §7wurde zu §eTag §8/ §e0 Ticks §7gesetzt§8!");
                p.playSound(p.getLocation(), Sound.BAT_LOOP, 1, 1);
                p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 2);
            } else if(args[0].equalsIgnoreCase("night")) {
                p.getWorld().setTime(18000);
                p.sendMessage(Var.prefix + "Die Zeit in der Welt §b" + p.getWorld().getName() + " §7wurde zu §eNacht §8/ §e18000 Ticks §7gesetzt§8!");
                p.playSound(p.getLocation(), Sound.BAT_LOOP, 1, 1);
                p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 2);
            } else {
                p.sendMessage(CommandSystem.getInstance().getParameterContents("time", "day / night"));
            }
        } else {
            p.sendMessage(CommandSystem.getInstance().getUsage("time <time>"));
        }

        return true;
    }

}
