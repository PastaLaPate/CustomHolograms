package fr.pastalapate.CustomHolograms.Files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.pastalapate.CustomHolograms.CHMain;

public class dataManager {

	private CHMain plugin;
	private FileConfiguration dataConfig = null;
	private File configFile = null;
	
	public dataManager(CHMain plugin) {
		this.plugin = plugin;
		saveDefaultConfig();
	}
	
	public void reloadConfig() {
		if (this.configFile == null) {
			this.configFile = new File(this.plugin.getDataFolder(), "data.yml");
		}
		
		this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
		
		InputStream defaultStream = this.plugin.getResource("data.yml");
		if (defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.dataConfig.setDefaults(defaultConfig);
		}
	}
	
	public FileConfiguration getConfig() {
		if(this.dataConfig == null)
			reloadConfig();
		return this.dataConfig;
	}
	
	public void saveConfig() {
	  if (this.dataConfig == null || this.configFile == null) {
		  return;
	  }
	  try {
		this.getConfig().save(this.configFile);
	} catch (IOException e) {
		plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
	}
	}
	
	public void saveDefaultConfig() {
		if (this.configFile == null) {
			this.configFile = new File(this.plugin.getDataFolder(), "data.yml");
		
		if (!this.configFile.exists()) {
			this.plugin.saveResource("data.yml", false);
		}
		}
	}
	
	public boolean saveHologram(String name, String[] text) {
		if (getConfig().get("holograms.created") == null) {
			getConfig().set("holograms.created", true);
			saveConfig();
		}
		if(getConfig().getBoolean("holograms." + name + ".exist") == true) {
			return false;
		} else {
			getConfig().set("holograms." + name + ".name", name);
			getConfig().set("holograms." + name + ".text", text);
			getConfig().set("holograms." + name + ".exist", true);
			saveConfig();
		}
		return true;
	}
	
	public boolean saveHologram(String name, String text,String creator) {
		if (getConfig().get("holograms.created") == null) {
			getConfig().set("holograms.created", true);
			saveConfig();
		}
		if(getConfig().getBoolean("holograms." + name + ".exist") == true) {
			return false;
		} else {
			getConfig().set("holograms." + name + ".name", name);
			getConfig().set("holograms." + name + ".text", text);
			getConfig().set("holograms." + name + ".creator", creator);
			getConfig().set("holograms." + name + ".exist", true);
			saveConfig();
		}
		return true;
	}

	public boolean addHologram(String name, Integer x, Integer y, Integer z, String world, Player sender) {
		if(getConfig().get("holograms.created") == null) {
			getConfig().set("holograms.created", true);
			saveConfig();
		}
		if(getConfig().get("holograms." + name + ".exist") == null) {
			sender.sendMessage("error");
			return false;
		}
		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
		//String text = getConfig().getString("holograms." + name + ".text");
		
		ArmorStand armorStand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		armorStand.setVisible(false);
		//armorStand.setCustomName(text);
		
		
		return true;
	}
	
	
	
}