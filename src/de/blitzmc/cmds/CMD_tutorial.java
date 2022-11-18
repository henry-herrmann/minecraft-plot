package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_tutorial implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("tutorial")) {
			if(args.length == 0) {
				p.sendMessage("§a----------[§eTutorial§a]----------");
				p.sendMessage(" ");
				p.sendMessage("§a- Gelange zum Spawn mit §e/spawn");
				p.sendMessage(" ");
				p.sendMessage("§a- Besuche andere Plots mit §e/visit");
				p.sendMessage(" ");
				p.sendMessage("§a- Teleportiere dich in die Farmwelt mit §e/warp");
				p.sendMessage(" ");
				p.sendMessage("§a- Baue dein eigenes, gesichertes Plot mit §e/plot <Name> §a (Max. 2 Plots) [1. Plot Size: 37x37, 2. Plot Size: 61x61]");
				p.sendMessage(" ");
				p.sendMessage("§a- Öffne deine Home-Übersicht mit §e/home §aund klicke auf eine der drei Barrieren um ein Home zu setzen.");
				p.sendMessage(" ");
				p.sendMessage("§a- Überprüfe dein Kontostand mit §e/money");
				p.sendMessage(" ");
				p.sendMessage("§a- Überweise Geld mit §e/money pay <player>");
				p.sendMessage(" ");
				p.sendMessage("§a- Verdiene Geld mit §e/job");
				p.sendMessage(" ");
				p.sendMessage("§a- Sende Teleport-Anfragen mit §e/tpa <player>");
				p.sendMessage(" ");
				p.sendMessage("§a- Gehe zum Markplatz und verkaufe Items. §e§lMehr infos: /tutorial markt");
				p.sendMessage(" ");
				p.sendMessage("§a----------[§eTutorial§a]----------");
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("markt")) {
					p.sendMessage("§a----------[§eMarktplatz§a]----------");
					p.sendMessage(" ");
					p.sendMessage("§e1. §eKlicke auf ein Schild, wo noch kein Spielername draufsteht. §aDu kannst maximal §e1 §aShop haben. Ein Shop kostet §e1800$ §amonatlich.");
					p.sendMessage(" ");
					p.sendMessage("§e2. §aPlatziere eine §eKiste §ain deiner Shop-Area.");
					p.sendMessage(" ");
					p.sendMessage("§e3. §aPlatziere ein Schild mit dem Format: Linie 1. [Shop], Linie 2: Buy <Anzahl>, Linie 3: <Preis> ohne Dollar Zeichen, an diese Kiste.           §6§lBitte stelle dich dicht vor die Kiste!");
					p.sendMessage(" ");
					p.sendMessage("§e4. §aLege von der gewünschten Itemsorte so viele Items in diese Kiste wie du willst.");
					p.sendMessage(" ");
					p.sendMessage("§e5. §aNun können Spieler, durch klicken auf das Schild, Items bei dir erwerben.");
					p.sendMessage(" ");
					p.sendMessage("§e5. §aLösche deinen Shop mit /shop delete.");
					p.sendMessage(" ");
					p.sendMessage("§a----------[§eMarktplatz§a]----------");
				}
			}
		}
		return false;
	}

}
