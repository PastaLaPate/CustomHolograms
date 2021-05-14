package fr.pastalapate.CustomHolograms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pastalapate.CustomHolograms.CHMain;
import fr.pastalapate.CustomHolograms.utils.TextUtils;

public class CommandCreate implements CommandExecutor {

	private CHMain plugin;
	
	public CommandCreate(CHMain plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length < 2) {
				String error = ChatColor.translateAlternateColorCodes('&' ,plugin.getConfig().getString("messages.error.missing-arguments"));
				sender.sendMessage(error);
				return false;
			}
				if (plugin.data.saveHologram(args[1], TextUtils.implode(2, args), player.getName()) == true) {
					player.sendMessage("good");
				} else {
					player.sendMessage("error");
				}
		} else {
			sender.sendMessage(plugin.getConfig().getString("messages.error.console-cannot-execute-action"));
			return false;
		}
		return true;
	}

}
