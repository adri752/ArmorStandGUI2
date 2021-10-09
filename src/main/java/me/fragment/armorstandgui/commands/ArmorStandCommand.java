package me.fragment.armorstandgui.commands;

import me.fragment.armorstandgui.ArmorStandGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArmorStandCommand implements CommandExecutor {

    private final ArmorStandGUI plugin;

    public ArmorStandCommand(ArmorStandGUI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            plugin.openMainMenu(p);
        }

        return true;
    }
}
