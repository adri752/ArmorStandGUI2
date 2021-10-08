package me.fragment.armorstandgui.commands;

import me.fragment.armorstandgui.ArmorStandGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ArmorStandCommand implements CommandExecutor {


    ArmorStandGUI plugin;
    public ArmorStandCommand(ArmorStandGUI plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       if(sender instanceof Player) {
           Player p = (Player) sender;
           plugin.openMainMenu(p);
       }





        return true;
    }
}
