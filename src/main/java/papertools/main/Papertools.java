package papertools.main;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import papertools.api.gui.GuiManager;
import papertools.api.npc.NPCManager;
import papertools.api.utils.SendPlayerTo;
import papertools.main.commands.NpcCommand;
import papertools.main.commands.PapertoolsCommand;
import papertools.main.events.OnJoinEvent;
import papertools.main.events.OnQuitEvent;
import papertools.main.utils.NPCsFromConfig;

public class Papertools extends JavaPlugin implements Listener {
	public SendPlayerTo sendPlayerTo;
	public NPCManager npcManager;
	public GuiManager guiManager;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();

		this.sendPlayerTo = new SendPlayerTo(this);
		this.npcManager = new NPCManager(this);
		this.guiManager = new GuiManager();

		this.getCommand("papertools").setExecutor(new PapertoolsCommand(this));
		this.getCommand("npc").setExecutor(new NpcCommand(this));

		this.getServer().getPluginManager().registerEvents(new OnJoinEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new OnQuitEvent(this), this);

		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		NPCsFromConfig.createAll(this, this.npcManager);

		for (Player player : this.getServer().getOnlinePlayers()) {
			packetReader.inject(player, this);
		}

		this.getLogger().info("Papertools enabled!");
	}

	@Override
	public void onDisable() {
		this.npcManager.deleteAll();

		for (Player player : this.getServer().getOnlinePlayers()) {
			packetReader.eject(player);
		}

		this.getLogger().info("Papertools disabled!");
	}
}
