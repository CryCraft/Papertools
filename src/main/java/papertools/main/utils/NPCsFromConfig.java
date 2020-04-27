package papertools.main.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import papertools.api.npc.NPCBehavior;
import papertools.api.npc.NPCBehaviorType;
import papertools.api.npc.NPCManager;
import papertools.api.utils.FromConfig;

import java.util.UUID;

public class NPCsFromConfig {
    public static void createAll(Plugin plugin, NPCManager npcManager) {
        for (String configNPC : plugin.getConfig().getConfigurationSection("npcs").getKeys(false)) {

            ConfigurationSection config = plugin.getConfig().getConfigurationSection("npcs." + configNPC);
            Bukkit.getServer().getLogger().info("npc: " + config.toString());
            Location loc = FromConfig.location(config.getConfigurationSection("location"));

            String name = ChatColor.translateAlternateColorCodes('&', config.getString("name"));
            UUID uuid = UUID.fromString(config.getString("uuid"));
            String texture = config.getString("texture");
            String signature = config.getString("signature");
            int id = config.getInt("id");
            byte layers = (byte) 126;
            NPCBehavior interact = new NPCBehavior(NPCBehaviorType.valueOf(config.getString("interact.type")), config.getString("interact.info"));

            npcManager.createNPC(id, loc, name, uuid, texture, signature, layers, interact);
        }
    }
}
