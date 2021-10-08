package me.fragment.armorstandgui.events;

import me.fragment.armorstandgui.ArmorStandGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MenuHandler implements Listener {


    ArmorStandGUI plugin;

    public MenuHandler(ArmorStandGUI plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void click(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Armor Stand GUI")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;

            } else if (e.getCurrentItem().getType().equals(Material.ARMOR_STAND)) {
                p.sendMessage(ChatColor.AQUA + "You have opened the armor stand menu.");
                plugin.openCreateMenu(p);
            } else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                p.sendMessage(ChatColor.RED + "Closing menu.");
                p.closeInventory();

            }
        }
    }
}
