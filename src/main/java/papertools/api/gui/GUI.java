package papertools.api.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class GUI {
	private Inventory inventory;

	public GUI(String name, int slots, Player owner) {
		this.inventory = Bukkit.createInventory(owner, slots, ChatColor.translateAlternateColorCodes('&', name));
	}

	public static boolean compareItem(ItemStack one, ItemStack two) {
		return one.getItemMeta().getDisplayName().equals(two.getItemMeta().getDisplayName());
	}

	public void addItem(String displayname, Material material, int amount, int slot, String... loreList) {

		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();

		List<String> lore = new ArrayList<String>();

		for (String s : loreList) {
			lore.add(ChatColor.translateAlternateColorCodes('&', s));
		}

		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayname));
		meta.setLore(lore);

		item.setItemMeta(meta);
		this.inventory.setItem(slot, item);
	}

	public ItemStack getItem(int slot) {
		return this.inventory.getItem(slot);
	}

	public Inventory getInventory() {
		return this.inventory;
	}
}