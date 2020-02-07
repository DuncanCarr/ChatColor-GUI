package cervisial.chatcolorgui;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

import java.util.UUID;

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

    public ItemStack selectedItem(String identifier) {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13 );
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(plugin.colorize("&dSelected Color &7(" + identifier + "&7)"));
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(String permission, String identifier, int decimal, Player p, String shortHand) {
        if (shortHand.equalsIgnoreCase(plugin.getConfig().getString("" + p.getUniqueId()))) {
            return selectedItem(identifier);
        } else {
            if (p.hasPermission(permission)) {
                return unlockedItem(identifier, decimal);
            } else {
                return lockedItem(identifier);
            }
        }
    }

    public ItemStack filler() {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(plugin.colorize("&r"));
        item.setItemMeta(meta);
        return item;
    }

    public Inventory generateColorInventory(Player p) {
        Inventory inventory = Bukkit.createInventory(null, 27, "Select a chat color...");

        // Blank template: inventory.setItem(, createItem("chatcolor.", "", , p));

        inventory.setItem(0, createItem("chatcolor.dark_red", "&4Dark Red",11141120, p, "&4"));
        inventory.setItem(1, createItem("chatcolor.red", "&cRed",	16733525, p, "&c"));
        inventory.setItem(2, createItem("chatcolor.gold", "&6Gold", 16755200, p, "&6"));
        inventory.setItem(3, createItem("chatcolor.yellow", "&eYellow", 16777045, p, "&e"));
        inventory.setItem(4, createItem("chatcolor.dark_green", "&2Dark Green", 	43520, p, "&2"));
        inventory.setItem(5, createItem("chatcolor.green", "&aGreen", 5635925, p, "&a"));
        inventory.setItem(6, createItem("chatcolor.aqua", "&bAqua",5636095, p, "&b"));
        inventory.setItem(7, createItem("chatcolor.dark_aqua", "&3Dark Aqua", 43690, p, "&3"));
        inventory.setItem(8, createItem("chatcolor.dark_blue", "&1Dark Blue", 170, p, "&1"));
        inventory.setItem(9, filler());
        inventory.setItem(10, createItem("chatcolor.blue", "&9Blue", 5592575, p, "&9"));
        inventory.setItem(11, createItem("chatcolor.light_purple", "&dLight Purple", 16733695, p, "&d"));
        inventory.setItem(12, createItem("chatcolor.dark_purple", "&5Dark Purple", 11141290, p, "&5"));
        inventory.setItem(13, createItem("chatcolor.white", "&fWhite",16777215, p, "&f"));
        inventory.setItem(14, createItem("chatcolor.gray", "&7Gray",11184810, p, "&7"));
        inventory.setItem(15, createItem("chatcolor.dark_gray", "&8Dark Gray", 5592405, p, "&8"));
        inventory.setItem(16, createItem("chatcolor.black", "&0Black", 0, p, "&0"));
        inventory.setItem(17, filler());
        inventory.setItem(18, filler());
        inventory.setItem(19, filler());
        inventory.setItem(20, filler());
        inventory.setItem(21, filler());
        inventory.setItem(22, filler());
        inventory.setItem(23, filler());
        inventory.setItem(24, filler());
        inventory.setItem(25, filler());
        inventory.setItem(26, filler());

        return inventory;
    }

    public void setChatColor(String color, String identifier, Player p) {
        plugin.getConfig().set("" + p.getUniqueId(), color);
        p.sendMessage(plugin.colorize("&aYour chat color has been set to: " + identifier));
        // Blank: setChatColor("", "", p);

        if (plugin.getToBe().containsKey(p.getUniqueId()) || plugin.getToBe().containsValue(p.getUniqueId())) {
            plugin.getToBe().remove(p.getUniqueId());
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        String itemDisplayName;
        if (!(item == null)) {
            itemDisplayName = item.getItemMeta().getDisplayName();
        } else {
            itemDisplayName = null;
        }


        Player p = (Player) event.getWhoClicked();
        if (plugin.getToBe().containsKey(p.getUniqueId())) {
            UUID uuid = plugin.getToBe().get(p.getUniqueId());
            p = plugin.getServer().getPlayer(uuid);
        }

        Player clicker = (Player) event.getWhoClicked();


        if (inventory.getTitle().equals("Select a chat color...")) {
            event.setCancelled(true);
            if (itemDisplayName.contains(plugin.colorize("&cLocked Color"))) {
                clicker.closeInventory();
                clicker.sendMessage(plugin.colorize("&cYou do not have access to use this color! If you think this is an error, please report it."));
            } else if (itemDisplayName.contains(plugin.colorize("&aUnlocked Color"))){
                clicker.closeInventory();
                if (itemDisplayName.contains(plugin.colorize("&4Dark Red"))) {
                    setChatColor("&4", "&4Dark Red", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&cRed"))) {
                    setChatColor("&c", "&cRed", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&6Gold"))) {
                    setChatColor("&6", "&6Gold", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&eYellow"))) {
                    setChatColor("&e", "&eYellow", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&2Dark Green"))) {
                    setChatColor("&2", "&2Dark Green", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&aGreen"))) {
                    setChatColor("&a", "&aGreen", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&bAqua"))) {
                    setChatColor("&b", "&bAqua", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&3Dark Aqua"))) {
                    setChatColor("&3", "&3Dark Aqua", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&1Dark Blue"))) {
                    setChatColor("&1", "&1Dark Blue", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&9Blue"))) {
                    setChatColor("&9", "&9Blue", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&dLight Purple"))) {
                    setChatColor("&d", "&dLight Purple", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&5Dark Purple"))) {
                    setChatColor("&5", "&5Dark Purple", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&fWhite"))) {
                    setChatColor("&f", "&fWhite", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&7Gray"))) {
                    setChatColor("&7", "&7Gray", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&8Dark Gray"))) {
                    setChatColor("&8", "&8Dark Gray", p);
                }
                if (itemDisplayName.contains(plugin.colorize("&0Black"))) {
                    setChatColor("&0", "&0Black", p);
                }
            } else if (itemDisplayName.contains(plugin.colorize("&dSelected Color"))) {
                clicker.closeInventory();
                if (clicker != p) {
                    clicker.sendMessage(plugin.colorize("&eThey already have that color selected!"));
                } else {
                    clicker.sendMessage(plugin.colorize("&eYou already have this color selected!"));
                }
            }
        } else {
            event.setCancelled(false);
        }
    }
}
