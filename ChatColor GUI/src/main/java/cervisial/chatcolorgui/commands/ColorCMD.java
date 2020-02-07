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
        if (args.length == 0) {
            if (p.hasPermission("chatcolor.gui")) {
                p.openInventory(plugin.getInventoryManager().generateColorInventory(p));
                return true;
            } else {
                p.sendMessage(plugin.colorize("&cYou do not have permission to use this command!"));
                return true;
            }
        } if (args.length == 1) {
            if (p.hasPermission("chatcolor.gui.other")) {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage(plugin.colorize("&cPlayer not found."));
                } else {
                    p.openInventory(plugin.getInventoryManager().generateColorInventory(target));
                    plugin.getToBe().put(p.getUniqueId(), target.getUniqueId());
                }
            } else {
                p.sendMessage(plugin.colorize("&cYou do not have permission to use this command!"));
                return true;
            }
        }
        return true;
    }
}
