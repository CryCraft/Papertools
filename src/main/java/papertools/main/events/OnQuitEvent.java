package papertools.main.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;
import papertools.main.Papertools;

public class OnQuitEvent implements Listener {
	private Papertools plugin;

	public OnQuitEvent(Papertools plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onQuitEvent(PlayerQuitEvent event) {
		String message = this.plugin.getConfig().getBoolean("quit-message") ? ChatColor.translateAlternateColorCodes('&', "&8[&c-&8] &f{player}") : "";
		event.setQuitMessage(message.replaceAll("{player}", event.getPlayer().getDisplayName().toString()));
	}
}