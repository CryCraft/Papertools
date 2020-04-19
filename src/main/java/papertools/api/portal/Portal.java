package papertools.api.portal;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;

public class Portal {
	public BoundingBox area;
	public String destination;

	public Portal(Location loc1, Location loc2, String destination) {
		this.area = new BoundingBox(loc1.getX(), loc1.getY(), loc1.getZ(), loc2.getX(), loc2.getY(), loc2.getZ());
		this.destination = destination;
	}
}