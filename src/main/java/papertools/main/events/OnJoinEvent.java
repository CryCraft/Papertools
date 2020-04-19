package papertools.main.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;
import papertools.main.Papertools;

public class OnJoinEvent implements Listener {
	private Papertools plugin;

	public OnJoinEvent(Papertools plugin) {
		this.plugin = plugin;
	};

	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
		String message = this.plugin.getConfig().getBoolean("join-message") ? ChatColor.translateAlternateColorCodes('&', "&8[&a+&8] &f{player}") : "";
		event.setJoinMessage(message.replace("{player}", event.getPlayer().getDisplayName().toString()));
		if (this.plugin.getConfig().getBoolean("spawn-join")) {
			this.plugin.sendPlayerTo.spawn(event.getPlayer());
		}
	}
}