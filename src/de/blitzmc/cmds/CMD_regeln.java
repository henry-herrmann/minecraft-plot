package de.blitzmc.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_regeln implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player) sender;
		
		if(args.length == 0) {
			p.sendMessage("§7----------[§cRegeln§7]----------");
			p.sendMessage(" ");
			p.sendMessage("§c1.§7 Beleidigung anderer ist untersagt und wird mit einem §4§lBann §r§7bestraft.");
			p.sendMessage(" ");
			p.sendMessage("§c2.§7 Clientmodifikationen, die Vorteile gegenüber anderen schaffen sind nicht gestattet.");
			p.sendMessage(" ");
			p.sendMessage("§c3.§7 Das Ausnutzen von Bugs ist §cverboten §7und müssen unverzüglich einem Administrator gemeldet werden.");
			p.sendMessage(" ");
			p.sendMessage("§c4.§7 Jegliche Art von Server Crashern, sind verboten und werden mit einem §4§lBann §r§7bestraft.");
			p.sendMessage(" ");
			p.sendMessage("§c4.§7 Wir wollen alle nur Spaß haben und keine persönlichen Angelegenheiten im Chat klären.");
			p.sendMessage(" ");
			p.sendMessage("§c4.§7 Keine Diskriminierung, wie sexistische und rassistische Aussdrücke und Bauwerke.");
			p.sendMessage(" ");
			p.sendMessage("§7----------[§cRegeln§7]----------");
		}
		return false;
	}

}
