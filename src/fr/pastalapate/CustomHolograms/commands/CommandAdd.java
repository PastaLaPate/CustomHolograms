package fr.pastalapate.CustomHolograms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pastalapate.CustomHolograms.CHMain;

public class CommandAdd implements CommandExecutor {

	private CHMain plugin;

	public CommandAdd(CHMain plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			if(args.length < 5) {
				String error = ChatColor.translateAlternateColorCodes('&' ,plugin.getConfig().getString("messages.error.missing-arguments"));
				sender.sendMessage(error);
				return false;
			}
			
			Player player = (Player) sender;
			System.out.println(args[1]);
			System.out.println(args[2]);
			System.out.println(args[3]);
			System.out.println(args[4]);
			System.out.println(args[5]);
			plugin.data.addHologram(args[1], Integer.getInteger(args[2]), Integer.getInteger(args[3]), Integer.getInteger(args[4]), args[5], player);
		} else {
			sender.sendMessage(plugin.getConfig().getString("messages.error.console-cannot-execute-action"));
			return false;
		}
		return false;
	}

}
