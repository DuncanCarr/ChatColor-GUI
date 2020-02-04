package cervisial.chatcolorgui;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryManager implements Listener {

    private ChatColorGUI plugin = JavaPlugin.getPlugin(ChatColorGUI.class);

    public ItemStack lockedItem(String identifier) {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(plugin.colorize("&cLocked Color &7(" + identifier + "&7)"));
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack unlockedItem(String identifier, int decimal) {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(decimal));
        meta.setDisplayName(plugin.colorize("&aUnlocked Color &7(" + identifier + "&7)"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack createItem(String permission, String identifier, int decimal, Player p) {
        if (p.hasPermission(permission)) {
            return unlockedItem(identifier, decimal);
        } else {
            return lockedItem(identifier);
        }
    }

    public Inventory generateColorInventory(Player p) {
        Inventory inventory = Bukkit.createInventory(null, 27, "Select a chat color...");

        // Blank template: inventory.setItem(, createItem("chatcolor.", "", , p));

        inventory.setItem(0, createItem("chatcolor.dark_red", "&4Dark Red",11141120, p));
        inventory.setItem(1, createItem("chatcolor.red", "&cRed",	16733525, p));
        inventory.setItem(2, createItem("chatcolor.gold", "&6Gold", 16755200, p));
        inventory.setItem(3, createItem("chatcolor.yellow", "&eYellow", 16777045, p));
        inventory.setItem(4, createItem("chatcolor.dark_green", "&2Dark Green", 	43520, p));
        inventory.setItem(5, createItem("chatcolor.green", "&aGreen", 5635925, p));
        inventory.setItem(6, createItem("chatcolor.aqua", "&bAqua",5636095, p));
        inventory.setItem(7, createItem("chatcolor.dark_aqua", "&3Dark Aqua", 43690, p));
        inventory.setItem(8, createItem("chatcolor.dark_blue", "&1Dark Blue", 170, p));
        inventory.setItem(9, createItem("chatcolor.blue", "&9Blue", 5592575, p));
        inventory.setItem(10, createItem("chatcolor.light_purple", "&dLight Purple", 16733695, p));
        inventory.setItem(11, createItem("chatcolor.dark_purple", "&5Dark Purple", 11141290, p));
        inventory.setItem(12, createItem("chatcolor.white", "&fWhite",16777215, p));
        inventory.setItem(13, createItem("chatcolor.gray", "&7Gray",11184810, p));
        inventory.setItem(14, createItem("chatcolor.dark_gray", "&8Dark Gray", 5592405, p));
        inventory.setItem(15, createItem("chatcolor.black", "&0Black", 0, p));


        return inventory;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if (inventory.getTitle().equals("Select a chat color...")) {
            event.setCancelled(true);
            if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&cLocked Color"))) {
                p.closeInventory();
                p.sendMessage(plugin.colorize("&cYou do not have access to use this color! If you think this is an error, please report it."));
            } else {
                p.closeInventory();
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&4Dark Red"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&4");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &4Dark Red"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&cRed"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&c");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &cRed"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&6Gold"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&6");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &6Gold"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&eYellow"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&e");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &eYellow"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&2Dark Green"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&2");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &2Dark Green"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&aGreen"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&a");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &aGreen"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&bAqua"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&b");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &bAqua"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&3Dark Aqua"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&3");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &3Dark Aqua"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&1Dark Blue"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&1");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &1Dark Blue"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&9Blue"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&9");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &9Blue"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&dLight Purple"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&d");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &dLight Purple"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&5Dark Purple"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&5");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &5Dark Purple"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&fWhite"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&f");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &fWhite"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&7Gray"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&7");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &7Gray"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&8Dark Gray"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&8");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &8Dark Gray"));
                }
                if (item.getItemMeta().getDisplayName().contains(plugin.colorize("&0Black"))) {
                    plugin.getConfig().set("" + p.getUniqueId(), "&0");
                    p.sendMessage(plugin.colorize("&aYou have set your chat color to: &0Black"));
                }
            }
        } else {
            event.setCancelled(false);
        }
    }
}
