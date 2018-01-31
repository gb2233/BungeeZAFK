package me.gbalint.bungeezafk;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class afkCommand extends Command{

	public afkCommand() {
	      super("bafk");
	}

	@Override
	public void execute(CommandSender commandSender, String[] strings) {
		if (strings.length > 0 && strings[0].equalsIgnoreCase("reload") && commandSender.hasPermission("bungeezafk.reload")) {
			BungeeZAFK.getPlugin().reloadConfig();
			commandSender.sendMessage(new ComponentBuilder("[BungeeZAFK] Config sikeresen újratöltve!").color(ChatColor.GREEN).create());
		}else if (strings.length > 0 && strings[0].equalsIgnoreCase("on")) {
			BungeeZAFK.addPlayer((ProxiedPlayer)commandSender);
			commandSender.sendMessage(new ComponentBuilder("[BungeeZAFK] TAB AFK bekapcsolva").color(ChatColor.GREEN).create());
		}else if (strings.length > 0 && strings[0].equalsIgnoreCase("off")){
			BungeeZAFK.removePlayer((ProxiedPlayer)commandSender);
			commandSender.sendMessage(new ComponentBuilder("[BungeeZAFK] TAB AFK kikapcsolva").color(ChatColor.GREEN).create());
		}else {
			if (BungeeZAFK.getPlayers().contains((ProxiedPlayer)commandSender)) {
				BungeeZAFK.removePlayer((ProxiedPlayer)commandSender);
				commandSender.sendMessage(new ComponentBuilder("[BungeeZAFK] TAB AFK kikapcsolva").color(ChatColor.GREEN).create());
			} else {
				BungeeZAFK.addPlayer((ProxiedPlayer)commandSender);
				commandSender.sendMessage(new ComponentBuilder("[BungeeZAFK] TAB AFK bekapcsolva").color(ChatColor.GREEN).create());
			}
		}
	}

}
