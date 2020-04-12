package papertools;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getLogger().info("Papertools enabled!");
	}

	@Override
	public void onDisable() {
		this.getLogger().info("Papertools disabled!");
	}
}
