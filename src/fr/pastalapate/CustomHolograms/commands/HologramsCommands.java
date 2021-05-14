package fr.pastalapate.CustomHolograms.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.pastalapate.CustomHolograms.CHMain;

public class HologramsCommands implements CommandExecutor {

	private CHMain plugin;
	
	private Map<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();
	
	public HologramsCommands(CHMain chMain) {
		this.plugin = chMain;
		commands.put("create", new CommandCreate(plugin));
		commands.put("add", new CommandAdd(plugin));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			String helpNotAvaible = "&4Help is not available in this version! Please use the LATEST &aSTABLE !";
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', helpNotAvaible));
		} else {
			String SubCommand = args[0].toLowerCase();
			CommandExecutor subCommandExecutor = commands.get(SubCommand);
			
			if (subCommandExecutor == null) {
				String helpNotAvaible = "&4Help is not available in this version! Please use the LATEST &aSTABLE !";
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', helpNotAvaible));
            } else if (!sender.hasPermission("holograms." + SubCommand)) {
                sender.sendMessage(ChatColor.RED + cmd.getPermissionMessage());
            } else {
                return subCommandExecutor.onCommand(sender, cmd, label, args);
            }
			
		}
		return false;
	}

}
