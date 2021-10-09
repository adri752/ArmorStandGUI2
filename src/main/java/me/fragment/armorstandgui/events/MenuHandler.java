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
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * You do not need 3 different classes to handle your GUI.
 * <p>
 * I have moved the methods from MenuHandler2 and MenuHandler4 into this
 * <p>
 * Your biggest problem indeed was the horrible nesting of if statements.
 * They make your code extremely unreadable and prone to bugs. Avoid that
 * at all times. Whenever your class has a thousand closing brackets at
 * its end, you did something very, very bad.
 */
public class MenuHandler implements Listener {

    private final ArmorStandGUI plugin;

    public MenuHandler(ArmorStandGUI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClickMainMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        /*
        Instead of nesting EVERYTHING this whole method contained into this if statement,
        just do the opposite: return when this inventory is NOW your menu, otherwise continue
         */
        if (!event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Armor Stand GUI")) {
            return;
        }

        event.setCancelled(true);
        if (event.getCurrentItem() == null) {
            return;
        }

        if (event.getCurrentItem().getType().equals(Material.ARMOR_STAND)) {
            player.sendMessage(ChatColor.AQUA + "You have opened the armor stand menu.");
            plugin.openCreateMenu(player);
        } else if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
            player.sendMessage(ChatColor.RED + "Closing menu.");
            player.closeInventory();
        }
    }

    @EventHandler
    public void onClickCreateArmorStandMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!plugin.armorstands.containsKey(player.getUniqueId())) {
            ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            plugin.armorstands.put(player.getUniqueId(), stand);
            stand.setVisible(false);
        }

        // Same as above: Do not nest everything inside this if statement, but return right here
        if (!event.getView().getTitle().equals(ChatColor.GREEN + "Create an Armor Stand")) {
            return;
        }
        event.setCancelled(true);
        if (event.getCurrentItem() == null) {
            return;
        }

        // You do not have to chain if statements with "else" everytime when only one of those can be true anyway
        // Instead, you could even use a switch statement, but that's not so important
        if (event.getCurrentItem().getType().equals(Material.GOLDEN_SWORD)) {
            player.sendMessage("Add arms");
            plugin.openConfirmMenu(player, Material.GOLDEN_SWORD);
        }

        if (event.getCurrentItem().getType().equals(Material.BEACON)) {
            player.sendMessage("Add glow");
            plugin.openConfirmMenu(player, Material.BEACON);
        }

        if (event.getCurrentItem().getType().equals(Material.DIAMOND_CHESTPLATE)) {
            player.sendMessage("Add armor");
            plugin.openArmorMenu(player);
        }

        if (event.getCurrentItem().getType().equals(Material.ARMOR_STAND)) {
            player.sendMessage("Base");
            plugin.openConfirmMenu(player, Material.ARMOR_STAND);
        }

        if (event.getCurrentItem().getType().equals(Material.CYAN_WOOL)) {
            Bukkit.getScheduler().runTaskLater(ArmorStandGUI.getInstance(), player::closeInventory, 1);
            player.sendMessage("Created armor stand");
            // Important: In all the following lines, you always checked for "player" in your hashmap, but you changed
            // it to UUID. I fixed this now. IntelliJ also shows player in a yellow color to tell you that the HashMap
            // can NEVER contain player - a Player is not a UUID
            if (plugin.armorstands.containsKey(player.getUniqueId())) {
                ArmorStand stand = plugin.armorstands.get(player.getUniqueId());
                stand.setVisible(true);
                plugin.armorstands.remove(player.getUniqueId());
            }
        }

        if (event.getCurrentItem().getType().equals(Material.RED_WOOL)) {
            Bukkit.getScheduler().runTaskLater(ArmorStandGUI.getInstance(), player::closeInventory, 1);
            player.sendMessage("Deleted armor stand");
            if (plugin.armorstands.containsKey(player.getUniqueId())) {
                ArmorStand stand = plugin.armorstands.get(player.getUniqueId());
                stand.remove();
                plugin.armorstands.remove(player.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onClickChooseArmorMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!event.getView().getTitle().equals(ChatColor.BLUE + "Choose some armor.")) {
            // You never returned from this method even when a user clicked in one of the other menus...
            return;
        }

        event.setCancelled(true);

        // YOu checked this at the wrong place, like only when the inventory contained green wool.
        // You however want to return EVERYTIME from this menu when no item was clicked
        if (event.getCurrentItem() == null) {
            return;
        }

        // Instead of repeating "event.getCurrentItem().getType()" everytime, just create a variable
        final Material clickedType = event.getCurrentItem().getType();

        // You also check inside EVERY if statement, whether the map contains the player.
        // 1. YOu have to check the UUID, not the player
        // 2. Simply return here once when it's not the case instead of checking it 8 times
        if (!plugin.armorstands.containsKey(player.getUniqueId())) {
            return;
        }

        final ArmorStand stand = plugin.armorstands.get(player.getUniqueId());

        // Instead of repeating the same code X times (you had the word "DIAMOND_HELMET" 4 times in the code
        // and also just copy/pasted the whole logic behind it 4 times, WHILE ALSO nesting it, which simply
        // COULD NOT WORK), make use of loops to achieve the same thing without having to write the same
        // stuff over ander over again.
        HashMap<Material, EquipmentSlot> equipmentMap = new HashMap<>();
        equipmentMap.put(Material.DIAMOND_HELMET, EquipmentSlot.HEAD);
        equipmentMap.put(Material.DIAMOND_CHESTPLATE, EquipmentSlot.CHEST);
        equipmentMap.put(Material.DIAMOND_LEGGINGS, EquipmentSlot.LEGS);
        equipmentMap.put(Material.DIAMOND_BOOTS, EquipmentSlot.FEET);

        if (equipmentMap.containsKey(clickedType)) {
            EquipmentSlot slot = equipmentMap.get(clickedType);

            if (stand.getEquipment().getItem(slot).getType() == clickedType) {
                player.sendMessage("Remove.");
                stand.getEquipment().setItem(slot, null);
            } else {
                player.sendMessage("Added.");
                stand.getEquipment().setItem(slot, new ItemStack(clickedType));
            }
        }

        if (clickedType == Material.GREEN_WOOL) {
            player.sendMessage("Armor Confirmed.");
            plugin.openCreateMenu(player);
        }
    }

}
