package papertools.api.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import papertools.main.Papertools;


public class SendPlayerTo {
	private Papertools plugin;

	public SendPlayerTo(Papertools plugin) {
		this.plugin = plugin;
	}

	public static void server(Player player, String server, Plugin plugin) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}	

	public void spawn(Player player) {
		FileConfiguration config = this.plugin.getConfig();
		World world = Bukkit.getServer().getWorld(config.getString("spawn.world"));

		Float yaw = Float.parseFloat(config.getString("spawn.yaw"));
		Float pitch = Float.parseFloat(config.getString("spawn.pitch"));
		player.teleport( new Location(world, config.getDouble("spawn.x"), config.getDouble("spawn.y"), config.getDouble("spawn.z"), yaw, pitch));
	}
}