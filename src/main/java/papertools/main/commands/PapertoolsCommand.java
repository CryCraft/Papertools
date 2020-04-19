package papertools.main.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import papertools.main.Papertools;

public class PapertoolsCommand implements CommandExecutor {
	private Papertools plugin;
	
	public PapertoolsCommand(Papertools plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("papertools.command.papertools")) {
			sender.sendMessage(ChatColor.RED + "You do not have the permission to use that command!");
			return true;
		}

		if(command.getName().equalsIgnoreCase("papertools")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					this.plugin.reloadConfig();
					sender.sendMessage(ChatColor.GOLD + "papertools reloaded");
				}
			} else {
				sender.sendMessage(ChatColor.GOLD + "you are running papertools " + this.plugin.getDescription().getVersion());
			}
		}
		return true;
	}
	
}