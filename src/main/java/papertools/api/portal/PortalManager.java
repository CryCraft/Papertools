package papertools.api.portal;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import papertools.api.utils.SendPlayerTo;

public class PortalManager implements Listener {
	private Plugin plugin;
	private ArrayList<Portal> Portals = new ArrayList<Portal>();

	public PortalManager(Plugin plugin) {
		this.plugin = plugin;
	}

	public void createAll(FileConfiguration configFile) {
		for (String portal : configFile.getConfigurationSection("portals").getKeys(false)) {
			ConfigurationSection config = configFile.getConfigurationSection("portals." + portal);
			Location loc1 = new Location(Bukkit.getServer().getWorld(config.getString("world")), config.getDouble("loc1.x"), config.getDouble("loc1.y"), config.getDouble("loc1.z"));
			Location loc2 = new Location(Bukkit.getServer().getWorld(config.getString("world")), config.getDouble("loc2.x"), config.getDouble("loc2.y"), config.getDouble("loc2.z"));
			String destination = config.getString("destination");
			this.createPortal(loc1, loc2, destination); 
		}
	}

	public void createPortal(Location loc1, Location loc2, String destination) {
		Portal portal = new Portal(loc1, loc2, destination);
		this.Portals.add(portal);
	}

	public void removeAll() {
		for (int i = 0; i < this.Portals.size(); i++) {
			this.removePortal(i);
		}
	}

	public void removePortal(int index) {
		this.Portals.remove(index);	
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		for (Portal portal : this.Portals) {
			Location loc = event.getPlayer().getLocation();
			if (portal.area.contains(loc.getX(), loc.getY(), loc.getZ())) {
				SendPlayerTo.server(event.getPlayer(), portal.destination, this.plugin);
			}
		}
	}
}