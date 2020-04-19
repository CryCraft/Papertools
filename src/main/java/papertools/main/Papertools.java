package papertools.main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import papertools.api.utils.SendPlayerTo;
import papertools.main.commands.PapertoolsCommand;
import papertools.main.events.OnJoinEvent;
import papertools.main.events.OnQuitEvent;

public class Papertools extends JavaPlugin implements Listener {
	public SendPlayerTo sendPlayerTo;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();

		this.sendPlayerTo = new SendPlayerTo(this);

		this.getCommand("papertools").setExecutor(new PapertoolsCommand(this));

		this.getServer().getPluginManager().registerEvents(new OnJoinEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new OnQuitEvent(this), this);

		this.getLogger().info("Papertools enabled!");
	}

	@Override
	public void onDisable() {
		this.getLogger().info("Papertools disabled!");
	}
}
