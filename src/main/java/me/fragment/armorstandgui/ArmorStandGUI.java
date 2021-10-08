package me.fragment.armorstandgui;

import me.fragment.armorstandgui.commands.ArmorStandCommand;
import me.fragment.armorstandgui.events.EventHandler3;
import me.fragment.armorstandgui.events.MenuHandler;
import me.fragment.armorstandgui.events.MenuHandler2;
import me.fragment.armorstandgui.events.MenuHandler4;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class ArmorStandGUI extends JavaPlugin {

    public HashMap<UUID, ArmorStand> armorstands = new HashMap<>();
    private static ArmorStandGUI instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getCommand("armorstand").setExecutor(new ArmorStandCommand(this));

        getServer().getPluginManager().registerEvents(new MenuHandler(this), this);
        getServer().getPluginManager().registerEvents(new MenuHandler2(this), this);
        getServer().getPluginManager().registerEvents(new EventHandler3(this), this);
        getServer().getPluginManager().registerEvents(new MenuHandler4(this), this);
    }

    public void openMainMenu(Player p){
        Inventory main_menu = Bukkit.createInventory(p, 9, ChatColor.GOLD + "Armor Stand GUI");


        //options for main menu
        ItemStack create = new ItemStack(Material.ARMOR_STAND);
        ItemMeta create_meta = create.getItemMeta();
        create_meta.setDisplayName(ChatColor.RED + "Create");
        ArrayList<String> create_lore = new ArrayList<>();
        create_lore.add(ChatColor.DARK_BLUE + "Create a new armor stand.");
        create_meta.setLore(create_lore);
        create.setItemMeta(create_meta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(close_meta);

        main_menu.setItem(0, create);
        main_menu.setItem(8, close);
        p.openInventory(main_menu);

    }
    public void openCreateMenu(Player p){
        Inventory create_menu = Bukkit.createInventory(p, 9, ChatColor.GREEN + "Create an Armor Stand");

        ItemStack arms = new ItemStack(Material.GOLDEN_SWORD);
        ItemStack glow = new ItemStack(Material.BEACON);
        ItemStack armor = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack base = new ItemStack(Material.ARMOR_STAND);
        ItemStack complete = new ItemStack(Material.CYAN_WOOL);
        ItemStack cancel = new ItemStack(Material.RED_WOOL);

        ItemMeta arms_meta = arms.getItemMeta();
        arms_meta.setDisplayName(ChatColor.GRAY + "Arms");
        ItemMeta glow_meta = arms.getItemMeta();
        glow_meta.setDisplayName(ChatColor.AQUA + "Glowing");
        ItemMeta armor_meta = arms.getItemMeta();
        armor_meta.setDisplayName(ChatColor.GREEN + "Armor");
        ItemMeta base_meta = arms.getItemMeta();
        base_meta.setDisplayName(ChatColor.GOLD + "Base");
        ItemMeta complete_meta = arms.getItemMeta();
        complete_meta.setDisplayName(ChatColor.BLUE + "Complete & Create");
        ItemMeta cancel_meta = arms.getItemMeta();
        cancel_meta.setDisplayName(ChatColor.RED + "Cancel");
        arms.setItemMeta(arms_meta);
        glow.setItemMeta(glow_meta);
        armor.setItemMeta(armor_meta);
        base.setItemMeta(base_meta);
        complete.setItemMeta(complete_meta);
        cancel.setItemMeta(cancel_meta);

        create_menu.setItem(0, arms);
        create_menu.setItem(2, armor);
        create_menu.setItem(1, glow);
        create_menu.setItem(3, base);
        create_menu.setItem(7, complete);
        create_menu.setItem(8, cancel);

        p.openInventory(create_menu);
    }

    public void openConfirmMenu(Player p, Material option){
        Inventory confirm_menu = Bukkit.createInventory(p, 36, ChatColor.GOLD + "Confirm your creation");
        ItemStack option_item = new ItemStack(option);
        ItemMeta option_meta = option_item.getItemMeta();
        if(option == Material.GOLDEN_SWORD){
            option_meta.setDisplayName(ChatColor.BLUE + "Give arms");
            option_item.setItemMeta(option_meta);
        } else if (option == Material.BEACON) {
            option_meta.setDisplayName(ChatColor.BLUE + "Add glow");
            option_item.setItemMeta(option_meta);
        } else if (option == Material.DIAMOND_CHESTPLATE) {
            option_meta.setDisplayName(ChatColor.BLUE + "Add armor");
            option_item.setItemMeta(option_meta);
        } else if (option == Material.ARMOR_STAND) {
            option_meta.setDisplayName(ChatColor.BLUE + "Base");
            option_item.setItemMeta(option_meta);
        }
        ItemStack yes = new ItemStack(Material.CYAN_WOOL);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "YES");
        yes.setItemMeta(yes_meta);
        ItemStack no = new ItemStack(Material.RED_WOOL);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.RED + "NO");
        no.setItemMeta(no_meta);


        confirm_menu.setItem(13, option_item);
        confirm_menu.setItem(21, yes);
        confirm_menu.setItem(23, no);
        p.openInventory(confirm_menu);

    }
    public void openArmorMenu(Player p){

        Inventory armorMenu = Bukkit.createInventory(p, 45, ChatColor.BLUE + "Choose some armor.");

        ItemStack diamondHead = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack diamondChest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack diamondLeg = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack diamondBoots = new ItemStack(Material.DIAMOND_BOOTS);


        ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirm_meta = confirm.getItemMeta();
        confirm_meta.setDisplayName(ChatColor.GREEN + "Done");
        confirm.setItemMeta(confirm_meta);

        armorMenu.setItem(11, diamondHead);
        armorMenu.setItem(14, diamondBoots);
        armorMenu.setItem(12, diamondChest);
        armorMenu.setItem(13, diamondLeg);
        armorMenu.setItem(44, confirm);
        p.openInventory(armorMenu);
    }

    public static ArmorStandGUI getInstance() {
        return instance;
    }
}
