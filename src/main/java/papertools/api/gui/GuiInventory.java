package papertools.api.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import papertools.main.Papertools;

public class GuiInventory implements Listener {
	private final Inventory inventory;
	private final String name;
	public final ArrayList<GuiItem> items;

	public GuiInventory(String name, int slots, Papertools plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.items = new ArrayList<>();
		this.name = ChatColor.translateAlternateColorCodes('&', name);
		this.inventory = Bukkit.createInventory(null, slots, this.name);
	}

	public void addItem(String name, Material material, int amount, int slot, String[] lore) {
		GuiItem item = new GuiItem(name, material, amount, lore);
		this.addItem(slot, item);
	}

	public void addItem(int slot, GuiItem item) {
		Bukkit.getServer().getConsoleSender().sendMessage(item.toString());
		this.inventory.setItem(slot, item.getItem());
		this.items.add(item);
	}

	public Inventory getInventory() {
		return inventory;
	}

	@SuppressWarnings("unused")
	@EventHandler
	public void onItemClick(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase(this.name)) {
			event.setCancelled(true);
			for (GuiItem item : this.items) {
				if (event.getCurrentItem() == null) {
					return;
				}
				if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(item.getItem().getItemMeta().getDisplayName())) {
					item.guiItemEvent.onclick((Player) event.getWhoClicked());
					return;
				}
			}
		}
	}
}