package cervisial.chatcolorgui;

import cervisial.chatcolorgui.commands.ColorCMD;
import cervisial.chatcolorgui.listeners.ChatHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatColorGUI extends JavaPlugin {

    private InventoryManager iManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("color").setExecutor(new ColorCMD());

        getServer().getPluginManager().registerEvents(new InventoryManager(), this);
        getServer().getPluginManager().registerEvents(new ChatHandler(), this);
        iManager = new InventoryManager();

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public InventoryManager getInventoryManager() {
        return iManager;
    }
}
