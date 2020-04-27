package papertools.main.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import papertools.api.npc.NPCPlayer;
import papertools.main.Papertools;

import java.util.Map;

public class NpcCommand implements CommandExecutor {


    private final Papertools plugin;

    public NpcCommand(Papertools plugin) {
        this.plugin = plugin;
    }
     
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("npc")) {
            if (!sender.hasPermission("papertools.command.npc")) {
                sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to use that command!");
                return true;
            }

            if (args.length > 0) {
                //sub command
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("list")) {
                    sender.sendMessage(ChatColor.GOLD + "====== {NPC LIST} ======");
                    for (Map.Entry<Integer, NPCPlayer> entry : this.plugin.npcManager.getAll().entrySet()) {
                        sender.sendMessage(ChatColor.RED + entry.getKey().toString() + "." + ChatColor.YELLOW + " " + entry.getValue().getName());
                    }
                    sender.sendMessage(ChatColor.GOLD + "======================");
                }
                //TODO: create and delete command

            } else {
                // no args
                sender.sendMessage(ChatColor.GOLD + "/npc [ list | create <name> | remove <id> ]");
            }
        }
        return true;
    }
}
