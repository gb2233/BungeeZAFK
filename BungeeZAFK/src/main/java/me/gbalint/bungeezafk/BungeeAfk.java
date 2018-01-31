package me.gbalint.bungeezafk;

import codecrafter47.bungeetablistplus.api.bungee.Variable;
import net.md_5.bungee.api.connection.ProxiedPlayer;


public class BungeeAfk extends Variable {

    public BungeeAfk() {
        super("bungee_afk");
    }

    @Override
    public String getReplacement(ProxiedPlayer player) {
    	if(BungeeZAFK.getPlayers().contains(player)) {
    		return BungeeZAFK.getConfiguration().getString("specialText."+player.getDisplayName(),
    				BungeeZAFK.getConfiguration().getString("afkText"));
    	}
        return "";
    }
}