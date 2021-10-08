package me.fragment.armorstandgui.events;

import me.fragment.armorstandgui.ArmorStandGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EventHandler3 implements Listener {

    ArmorStandGUI plugin;

    public EventHandler3(ArmorStandGUI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void click(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals(ChatColor.GOLD + "Confirm your creation")) {
            e.setCancelled(true);
        }
        if (e.getClickedInventory().contains(Material.GOLDEN_SWORD)) {
            if (e.getCurrentItem() == null) {
                return;
            } else if (e.getCurrentItem().getType().equals(Material.CYAN_WOOL)) {
                p.sendMessage("Option confirmed");
                if (plugin.armorstands.containsKey(p)) {
                    ArmorStand stand = plugin.armorstands.get(p);
                    stand.setArms(true);
                }
                    plugin.openCreateMenu(p);
                } else if (e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                    p.sendMessage("Cancelled option.");
                    if (plugin.armorstands.containsKey(p)) {
                        ArmorStand stand = plugin.armorstands.get(p);
                        stand.setArms(false);
                    }
                        plugin.openCreateMenu(p);
                    }
                }
        if (e.getClickedInventory().contains(Material.BEACON)) {
            if (e.getCurrentItem() == null) {
                return;
            } else if (e.getCurrentItem().getType().equals(Material.CYAN_WOOL)) {
                p.sendMessage("Option confirmed");
                if (plugin.armorstands.containsKey(p)) {
                    ArmorStand stand = plugin.armorstands.get(p);
                    stand.setGlowing(true);
                }
                plugin.openCreateMenu(p);
            } else if (e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                p.sendMessage("Cancelled option.");
                if (plugin.armorstands.containsKey(p)) {
                    ArmorStand stand = plugin.armorstands.get(p);
                    stand.setGlowing(false);
                }
                plugin.openCreateMenu(p);
            }
        }
        if (e.getClickedInventory().contains(Material.ARMOR_STAND)) {
            if (e.getCurrentItem() == null) {
                return;
            } else if (e.getCurrentItem().getType().equals(Material.CYAN_WOOL)) {
                p.sendMessage("Option confirmed");
                if (plugin.armorstands.containsKey(p)) {
                    ArmorStand stand = plugin.armorstands.get(p);
                    stand.setBasePlate(true);
                }
                plugin.openCreateMenu(p);
            } else if (e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                p.sendMessage("Cancelled option.");
                if (plugin.armorstands.containsKey(p)) {
                    ArmorStand stand = plugin.armorstands.get(p);
                    stand.setBasePlate(false);
                }
                plugin.openCreateMenu(p);
            }
        }
            }
        }

