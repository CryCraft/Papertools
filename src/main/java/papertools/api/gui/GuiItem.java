package papertools.api.gui;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GuiItem implements Listener {
    private final ItemStack item;
    public final GuiItemEvent guiItemEvent;


    public GuiItem(String name, Material material, int amount, boolean glint, String[] lore, GuiItemEvent onclick) {
        this.guiItemEvent = onclick;
        this.item = new ItemStack(material, amount);


        //item meta
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        ArrayList<String> metalore = new ArrayList<>();
        for (String s: lore) {
            metalore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(metalore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        if (glint) {
            this.item.addEnchantment(Enchantment.MENDING, 1);
        }
        this.item.setItemMeta(meta);
    }

    public GuiItem(String name, Material material, int amount, boolean glint, String[] lore) {
        this(name, material, amount, glint, lore, (player) -> {});
    }

    public ItemStack getItem() {
        return this.item;
    }
}
