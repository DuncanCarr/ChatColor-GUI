package cervisial.chatcolorgui.listeners;

import cervisial.chatcolorgui.ChatColorGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatHandler implements Listener {

    private ChatColorGUI plugin = JavaPlugin.getPlugin(ChatColorGUI.class);

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();

        if (plugin.getConfig().contains("" + p.getUniqueId())) {
            event.setMessage(plugin.colorize(plugin.getConfig().getString("" + p.getUniqueId()) + event.getMessage()));
        } else {
            plugin.getConfig().set("" + p.getUniqueId(), plugin.getConfig().getString("default-chat-color"));
            plugin.saveConfig();
            event.setMessage(plugin.colorize(plugin.getConfig().getString("" + p.getUniqueId()) + event.getMessage()));
        }
    }
}
