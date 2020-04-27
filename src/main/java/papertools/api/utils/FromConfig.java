package papertools.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class FromConfig {
    public static Location location(ConfigurationSection config) {
        double x = config.getDouble("x");
        double y = config.getDouble("y");
        double z = config.getDouble("z");

        float yaw = Float.parseFloat(config.getString("yaw"));
        float pitch = Float.parseFloat(config.getString("pitch"));

        return new Location(Bukkit.getServer().getWorld(config.getString("world")), x, y, z, yaw, pitch);
    }
}
