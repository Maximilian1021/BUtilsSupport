package de.butilsfaq.butilssupport;

import de.butilsfaq.commands.CMD_Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BUtilsSupport extends JavaPlugin {

    PluginDescriptionFile pdf = this.getDescription(); //Gets plugin.yml
    PluginManager pm = Bukkit.getPluginManager();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§b-------------");
        Bukkit.getConsoleSender().sendMessage("§aPlugin-Name:§6 " + pdf.getName());
        Bukkit.getConsoleSender().sendMessage("§aVersion: §6" + pdf.getVersion());
        Bukkit.getConsoleSender().sendMessage("§aAuthor: §6" + pdf.getAuthors());
        Bukkit.getConsoleSender().sendMessage("§aWebsite: §6" + pdf.getWebsite());
        registerCommands();
        registerEvents();
        Bukkit.getConsoleSender().sendMessage("§a§lErfolgreich geladen!");

        Bukkit.getConsoleSender().sendMessage("§b-------------");

    }



    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§b-------------");
        Bukkit.getConsoleSender().sendMessage("§aPlugin-Name:§6 " + pdf.getName());

        Bukkit.getConsoleSender().sendMessage("§b-------------");

    }



    private void registerEvents() {
    }

    private void registerCommands() {
        getCommand("data").setExecutor(new CMD_Data());
    }
}
