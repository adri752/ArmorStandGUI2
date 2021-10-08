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
import org.bukkit.inventory.ItemStack;

public class MenuHandler4 implements Listener {

    ArmorStandGUI plugin;

    public MenuHandler4(ArmorStandGUI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void click(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals(ChatColor.BLUE + "Choose some armor.")) {
            e.setCancelled(true);
        }
        if (e.getClickedInventory().contains(Material.GREEN_WOOL)) {
            if (e.getCurrentItem() == null) {
                return;
            }
        } else if (e.getCurrentItem().getType().equals(Material.DIAMOND_HELMET)) {
            if (plugin.armorstands.containsKey(player)) {
                ArmorStand stand = plugin.armorstands.get(player);
                stand.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                if (stand.getEquipment().getHelmet().getType() == Material.DIAMOND_HELMET) {
                    stand.getEquipment().setHelmet(null);
                    player.sendMessage("Remove.");
                } else {
                    stand.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                    player.sendMessage("Added.");
                }
                if (e.getCurrentItem().getType().equals(Material.DIAMOND_CHESTPLATE)) {
                    if (plugin.armorstands.containsKey(player)) {
                        stand.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                        player.sendMessage();
                        if (stand.getEquipment().getChestplate().getType() == Material.DIAMOND_CHESTPLATE) {
                            stand.getEquipment().setChestplate(null);
                            player.sendMessage("Remove.");
                            System.out.println("2");
                        } else {
                            stand.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                            player.sendMessage("Added.");
                        }
                        if (e.getCurrentItem().getType().equals(Material.DIAMOND_LEGGINGS)) {
                            if (plugin.armorstands.containsKey(player)) {
                                stand.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                                player.sendMessage();
                                if (stand.getEquipment().getLeggings().getType() == Material.DIAMOND_LEGGINGS) {
                                    stand.getEquipment().setLeggings(null);
                                    player.sendMessage("Remove.");
                                    System.out.println("3");
                                } else {
                                    stand.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                                    player.sendMessage("Added.");
                                }
                                if (e.getCurrentItem().getType().equals(Material.DIAMOND_BOOTS)) {
                                    if (plugin.armorstands.containsKey(player)) {
                                        stand.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                                        player.sendMessage("Added");
                                        System.out.println("4");
                                        if (stand.getEquipment().getBoots().getType() == Material.DIAMOND_BOOTS) {
                                            stand.getEquipment().setBoots(null);
                                            player.sendMessage("Remove.");
                                        } else {
                                            stand.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_BOOTS));
                                            player.sendMessage("Added.");
                                        }if (e.getCurrentItem().getType().equals(Material.GREEN_WOOL)){
                                            player.sendMessage("Armor Confirmed.");
                                            plugin.openCreateMenu(player);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
