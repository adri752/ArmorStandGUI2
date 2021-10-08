package me.fragment.armorstandgui.events;

import me.fragment.armorstandgui.ArmorStandGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class MenuHandler2 implements Listener {


    ArmorStandGUI plugin;

    public MenuHandler2(ArmorStandGUI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void click(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (!plugin.armorstands.containsKey(player.getUniqueId())){
            ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            plugin.armorstands.put(player.getUniqueId(), stand);
            stand.setVisible(false);
        }

        if (e.getView().getTitle().equals(ChatColor.GREEN + "Create an Armor Stand")){
            e.setCancelled(true);
        if (e.getCurrentItem() == null) {
            return;
        } else if (e.getCurrentItem().getType().equals(Material.GOLDEN_SWORD)) {
            player.sendMessage("Add arms");
            plugin.openConfirmMenu(player, Material.GOLDEN_SWORD);
        } else if (e.getCurrentItem().getType().equals(Material.BEACON)){
            player.sendMessage("Add glow");
            plugin.openConfirmMenu(player, Material.BEACON);
        } else if(e.getCurrentItem().getType().equals(Material.DIAMOND_CHESTPLATE)){
            player.sendMessage("Add armor");
            plugin.openArmorMenu(player);
        } else if(e.getCurrentItem().getType().equals(Material.ARMOR_STAND)){
            player.sendMessage("Base");
            plugin.openConfirmMenu(player, Material.ARMOR_STAND);
        } else if(e.getCurrentItem().getType().equals(Material.CYAN_WOOL)){
            Bukkit.getScheduler().runTaskLater(ArmorStandGUI.getInstance(), player::closeInventory, 1);
            player.sendMessage("Created armor stand");
            if(plugin.armorstands.containsKey(player)){
                ArmorStand stand = plugin.armorstands.get(player);
                stand.setVisible(true);
                plugin.armorstands.remove(player);
            }
        } else if(e.getCurrentItem().getType().equals(Material.RED_WOOL)){
            Bukkit.getScheduler().runTaskLater(ArmorStandGUI.getInstance(), player::closeInventory, 1);
            player.sendMessage("Deleted armor stand");
            if(plugin.armorstands.containsKey(player)) {
                ArmorStand stand = plugin.armorstands.get(player);
                stand.remove();
                plugin.armorstands.remove(player);
            }
    }
    }
}
    }