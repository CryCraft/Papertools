package papertools.api.npc;

import org.bukkit.entity.Player;

import org.bukkit.plugin.Plugin;

public class SkinUpdater {
	private final Plugin plugin;

	public SkinUpdater(Plugin plugin) {
		this.plugin = plugin;
	}

	public void update(NPCPlayer npcPlayer, Player player, int duration) {
		this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> npcPlayer.removeFromTab(player), duration);
	}

}