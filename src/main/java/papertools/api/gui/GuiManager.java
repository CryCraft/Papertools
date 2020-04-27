package papertools.api.gui;

import java.util.HashMap;
import java.util.Map;

public class GuiManager {
    private final Map<String, GuiInventory> Guis;

    public GuiManager() {
        this.Guis = new HashMap<>();
    }

    public Map<String, GuiInventory> getGuis() {
        return Guis;
    }

    public void addGui(String name, GuiInventory gui) {
        this.Guis.put(name, gui);
    }

    public GuiInventory getGui(String name) {
        return this.Guis.get(name);
    }
}
