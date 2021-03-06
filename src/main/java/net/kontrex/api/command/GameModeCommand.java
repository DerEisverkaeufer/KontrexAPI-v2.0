package net.kontrex.api.command;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.kontrex.api.Var;
import net.kontrex.api.system.CommandSystem;

public class GameModeCommand implements CommandExecutor {

	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		
		if(!(s instanceof Player))
			return true;
		
		Player p = (Player) s;
		
		if(args.length == 1) {
			
			try {
				int i = Integer.parseInt(args[0]);
				
				if(i < 0 || i > 3) {
					p.sendMessage(CommandSystem.getInstance().getParameterContents("Modus", "1 / 2 / 3 / 4"));
					p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 1);
					p.playSound(p.getLocation(), Sound.BAT_LOOP, 1, 1);
					return true;
				}
				
				p.setGameMode(GameMode.getByValue(i));
				
				String gm = p.getGameMode().toString();
				gm = gm.toLowerCase();
				char c = gm.charAt(0);
				c = Character.toUpperCase(c);
				gm = gm.substring(1);
				gm = c + gm;
				
				p.sendMessage(Var.prefix + "Dein Spielmodus wurde zu §b" + gm + " §7geändert§8.");
				p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
				
			} catch (Exception e) {
				p.sendMessage(CommandSystem.getInstance().getParameterContents("Modus", "1 / 2 / 3 / 4"));
				p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 1);
				p.playSound(p.getLocation(), Sound.BAT_LOOP, 1, 1);
				return true;
			}
			
		} else if(args.length == 2) {

			try {
				int i = Integer.parseInt(args[0]);
				
				if(i < 0 || i > 3) {
					p.sendMessage(CommandSystem.getInstance().getParameterContents("Modus", "1 / 2 / 3 / 4"));
					p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 1);
					p.playSound(p.getLocation(), Sound.BAT_LOOP, 1, 1);
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				
				if(target == null) {
					p.sendMessage(Var.prefix + "Der Spieler §b" + args[1] + " §7wurde nicht gefunden§8!");
					p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 1);
					p.playSound(p.getLocation(), Sound.BAT_LOOP, 1, 1);
					return true;
				}
				
				target.setGameMode(GameMode.getByValue(i));
				String gm = target.getGameMode().toString();
				gm = gm.toLowerCase();
				char c = gm.charAt(0);
				c = Character.toUpperCase(c);
				gm = gm.substring(1);
				gm = c + gm;
				
				p.sendMessage(Var.prefix + "Der Spielmodus von §e" + target.getName() + " §7wurde zu §b" + gm + " §7geändert§8.");
				p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
				
			} catch (Exception e) {
				p.sendMessage(CommandSystem.getInstance().getParameterContents("Modus", "1 / 2 / 3 / 4"));
				p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 1);
				p.playSound(p.getLocation(), Sound.BAT_LOOP, 1, 1);
				return true;
			}
			
		} else {
			p.sendMessage(CommandSystem.getInstance().getUsage("gamemode <Modus>"));
			p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 1);
			p.playSound(p.getLocation(), Sound.BAT_LOOP, 1, 1);
		}
		
		return true;
	}
	
}
