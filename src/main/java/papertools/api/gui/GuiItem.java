package papertools.api.gui;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GuiItem implements Listener {
    private final ItemStack item;
    public final GuiItemEvent guiItemEvent;


    public GuiItem(String name, Material material, int amount, String[] lore, GuiItemEvent onclick) {
        this.guiItemEvent = onclick;
        this.item = new ItemStack(material, amount);

        //item meta
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        meta.setLore(Arrays.asList(lore));
        this.item.setItemMeta(meta);
    }

    public GuiItem(String name, Material material, int amount, String[] lore) {
        this(name, material, amount, lore, (player) -> {});
    }

    public ItemStack getItem() {
        return this.item;
    }
}
