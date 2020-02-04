package cervisial.chatcolorgui.commands;

import cervisial.chatcolorgui.ChatColorGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ColorCMD implements CommandExecutor {

    private ChatColorGUI plugin = JavaPlugin.getPlugin(ChatColorGUI.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.colorize("&cYou must be a player to use this command!"));
            return true;
        }

        Player p = (Player) sender;
        p.openInventory(plugin.getInventoryManager().generateColorInventory(p));
        return true;
    }
}
