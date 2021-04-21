package de.butilsfaq.butilssupport.commands;

import java.lang.management.*;

import de.butilsfaq.butilssupport.utils.JavaVersion;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Data implements CommandExecutor {


    protected static final ChatColor PRIMARY_COLOR = ChatColor.DARK_AQUA;
    protected static final ChatColor SECONDARY_COLOR = ChatColor.GRAY;



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        p.sendMessage("");
        p.sendMessage("");
        p.sendMessage("§b-------------");

        p.sendMessage("§cFolgende Data sind für den Support wichtig");
        p.sendMessage("");
        p.sendMessage("§3 Minecraft Server Version: §7" + Bukkit.getVersion());
        p.sendMessage("");

        //java version info
        displayJavaVersion(sender);

        //java paths
        sendMessage(sender, "Java lib", System.getProperty("sun.boot.library.path", "Unknown"));
        sendMessage(sender, "Java home", System.getProperty("java.home", "Unknown"));
        sendMessage(sender, "Temp path", System.getProperty("java.io.tmpdir", "Unknown"));

        p.sendMessage("§b-------------");




        return false;
    }

    private void displayJavaVersion(CommandSender sender) {
        JavaVersion currentVersion = JavaVersion.detect();
        CMD_Data.send(sender, formatJavaVersion(currentVersion));

        sendMessage(sender, "Java release date", System.getProperty("java.version.date", "n/a"));
        sendMessage(sender, "Class version", System.getProperty("java.class.version"));
    }


    private BaseComponent[] formatJavaVersion(JavaVersion version) {
        ComponentBuilder builder = new ComponentBuilder("Java version: ").color(PRIMARY_COLOR.asBungee())
                .append(version.getRaw()).color(SECONDARY_COLOR.WHITE.asBungee());
        if (version.isOutdated()) {
            builder = builder.append(" (").color(ChatColor.WHITE.asBungee())
                    .append("Outdated").color(ChatColor.DARK_RED.asBungee())
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new ComponentBuilder("You're running an outdated Java version. \n"
                                    + "Java 9 and 10 are already released. \n"
                                    + "Newer versions could improve the performance or include bug or security fixes.")
                                    .color(ChatColor.DARK_AQUA.asBungee()).create()))
                    .append(")").color(ChatColor.WHITE.asBungee());
        }

        return builder.create();
    }

    protected void sendMessage(CommandSender sender, String title, String value) {
        sender.sendMessage(PRIMARY_COLOR + title + ": " + SECONDARY_COLOR + value);
    }

    public static void send(CommandSender sender, BaseComponent... components) {
        //CommandSender#sendMessage(BaseComponent[]) was introduced after 1.8. This is a backwards compatible solution
        if (sender instanceof Player) {
            sender.spigot().sendMessage(components);
        } else {
            sender.sendMessage(TextComponent.toLegacyText(components));
        }
    }
}
