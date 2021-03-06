package fr.pastalapate.CustomHolograms;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fr.pastalapate.CustomHolograms.Files.dataManager;
import fr.pastalapate.CustomHolograms.commands.HologramsCommands;

public class CHMain extends JavaPlugin {

	public dataManager data;
	
	FileConfiguration config = this.getConfig();
	@Override
	public void onEnable() {
		saveDefaultConfig();
		this.data = new dataManager(this);
		System.out.println(config.getString("messages.console.utils.prefix") + config.getString("messages.console.utils.enabling-message"));
		
		getCommand("hologram").setExecutor(new HologramsCommands(this));
	}
}