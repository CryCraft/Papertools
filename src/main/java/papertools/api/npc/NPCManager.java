package papertools.api.npc;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.Plugin;

public class NPCManager {
	private final HashMap<Integer, NPCPlayer> npcs;
	private final Plugin plugin;

	//INIT
	public NPCManager(Plugin plugin) {
		this.plugin = plugin;
		this.npcs = new HashMap<>();
	}


	//NPC FUNCTIONS
	public void createNPC(int id, Location loc, String name, UUID uuid, String texture, String signature, byte layers, NPCBehavior behavior) {

		String username = ChatColor.translateAlternateColorCodes('&', name);

		NPCPlayer npcPlayer = new NPCPlayer(username, loc, uuid, behavior, texture, signature, layers);
		npcPlayer.create();

		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			npcPlayer.show(player);
			new SkinUpdater(this.plugin).update(npcPlayer, player, 10);
		}

		this.npcs.put(id, npcPlayer);
	}

	public void showAll(Player player) {
		for (NPCPlayer npcPlayer : this.npcs.values()) {
			npcPlayer.show(player);
			new SkinUpdater(this.plugin).update(npcPlayer, player, 40);
		}
	}

	public void deleteNPC(int id) {
		NPCPlayer npcPlayer = this.npcs.get(id);
		for (Player player : this.plugin.getServer().getOnlinePlayers()) {
			npcPlayer.remove(player);
		}
		this.npcs.remove(id);
	}

	// config operators
	public void deleteAll() {
		for (NPCPlayer npcPlayer : this.npcs.values()) {
			for (Player player : this.plugin.getServer().getOnlinePlayers()) {
				npcPlayer.remove(player);
			}
		}
		this.npcs.clear();
	}

	// getters
	public NPCPlayer getNPC(int index) {
		return this.npcs.get(index);	
	}

	public HashMap<Integer, NPCPlayer> getAll() {
		return this.npcs;
	}
}