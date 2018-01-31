package me.gbalint.bungeezafk;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashSet;

import codecrafter47.bungeetablistplus.api.bungee.BungeeTabListPlusAPI;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeZAFK extends Plugin {
	private static BungeeZAFK plugin;
	
	public static BungeeZAFK getPlugin() {
		return plugin;
	}
	private static HashSet<ProxiedPlayer> players;
	private static Configuration configuration;
	
    public static Configuration getConfiguration() {
		return configuration;
	}

	public static HashSet<ProxiedPlayer> getPlayers() {
		return players;
	}

	public static void addPlayer(ProxiedPlayer player) {
		BungeeZAFK.players.add(player);
	}

	public static void removePlayer(ProxiedPlayer player) {
		BungeeZAFK.players.remove(player);
	}

	@Override
    public void onEnable() {
		if(this.getProxy().getPluginManager().getPlugin("BungeeTabListPlus") != null) {
			getLogger().info("BungeeTabLisPlus found, loading");
	    	players = new HashSet<>();
			plugin = this;
			reloadConfig();
			this.getProxy().getPluginManager().registerCommand( this, new afkCommand());
	    	BungeeTabListPlusAPI.registerVariable(plugin, new BungeeAfk());
		}
		else
			getLogger().severe("BungeeTabLisPlus cannot be found, terminating");
    }
	void reloadConfig() {
		if (!getDataFolder().exists())
            getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		try {
			configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
