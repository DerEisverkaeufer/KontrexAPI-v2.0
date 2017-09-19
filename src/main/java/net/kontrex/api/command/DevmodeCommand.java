package net.kontrex.api.command;

import net.kontrex.api.database.DevAPI;
import net.kontrex.api.database.DevMode;
import net.kontrex.api.system.CommandSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by CodingEis / DerEisverkaeufer:
 * - DATE: 19.09.2017
 * - TIME: 14:06
 * - PROJECT: KontrexAPI-Spigot
 * - IDE: IntelliJ IDEA
 *
 * @author CodingEis
 */

public class DevmodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if(!(s instanceof Player)) {
            CommandSystem.getInstance().sendNonAccessToConsole();
            return true;
        }

        Player p = (Player) s;

        if(DevAPI.isInDevMode(p.getUniqueId())) {
            DevAPI.notify(p, "Der DevMode wurde §cdeaktiviert§8.");
            DevAPI.setDevMode(p.getUniqueId(), false);
            DevMode.getDevModeList().remove(p);
        } else {
            DevAPI.notify(p, "Der DevMode wurde §aaktiviert§8.");
            DevAPI.setDevMode(p.getUniqueId(), true);
            DevMode.getDevModeList().add(p);
        }

        return true;
    }
}
