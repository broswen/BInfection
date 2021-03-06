package me.broswen.binfection;

import java.util.ArrayList;

import me.broswen.binfection.commands.InfectionCommand;
import me.broswen.binfection.events.BlockBreak;
import me.broswen.binfection.events.BlockPlace;
import me.broswen.binfection.events.CommandPreProcess;
import me.broswen.binfection.events.DropItem;
import me.broswen.binfection.events.EntityDamageByEntity;
import me.broswen.binfection.events.HungerChange;
import me.broswen.binfection.events.InventoryClick;
import me.broswen.binfection.events.PickupItem;
import me.broswen.binfection.events.PlayerDeath;
import me.broswen.binfection.events.PlayerInteract;
import me.broswen.binfection.events.PlayerJoin;
import me.broswen.binfection.events.PlayerQuit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BInfection extends JavaPlugin{
	
	static BInfection plugin;
	
	public static String lastInfected;
	
	public static World gameWorld;
	
	public static Location playerSpawn, infectedSpawn, lobbySpawn;
	
	public static boolean gameStarted, gameEnded, infectedStarted;
	
	public static FileConfiguration config;
	
	public static int totalPlaying, totalAlive, totalInfected;
	
	public static ArrayList<String> players = new ArrayList<>();
	public static ArrayList<String> alive = new ArrayList<>();
	public static ArrayList<String> infected = new ArrayList<>();
	public static ArrayList<String> regenerationCooldown = new ArrayList<>();
	public static ArrayList<String> resistanceCooldown = new ArrayList<>();
	
	public void onEnable(){
		
		plugin = this.plugin;
		
		config = this.getConfig();
		
		loadConfig();
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new BlockPlace(), this);
		pm.registerEvents(new CommandPreProcess(), this);
		pm.registerEvents(new EntityDamageByEntity(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new HungerChange(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new PickupItem(), this);
		pm.registerEvents(new DropItem(), this);
		pm.registerEvents(new PlayerInteract(), this);
		
		this.getCommand("infection").setExecutor(new InfectionCommand(this));
		
		gameWorld = Bukkit.getWorld(getConfig().getString("playerspawn.world"));
		playerSpawn = new Location(Bukkit.getWorld(getConfig().getString("playerspawn.world")), getConfig().getDouble("playerspawn.X"), getConfig().getDouble("playerspawn.Y"), getConfig().getDouble("playerspawn.Z"));
		infectedSpawn = new Location(Bukkit.getWorld(getConfig().getString("infectedspawn.world")), getConfig().getDouble("infectedspawn.X"), getConfig().getDouble("infectedspawn.Y"), getConfig().getDouble("infectedspawn.Z"));
		lobbySpawn = new Location(Bukkit.getWorld(getConfig().getString("lobbyspawn.world")), getConfig().getDouble("lobbyspawn.X"), getConfig().getDouble("lobbyspawn.Y"), getConfig().getDouble("lobbyspawn.Z"));
		
		gameStarted = false;
		gameEnded = false;
		infectedStarted = false;
		totalAlive = 0;
		totalPlaying = 0;
		totalInfected = 0;
		lastInfected = "";
	}
	
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		return true;
	}

	public void loadConfig(){
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		getConfig().options().copyHeader(true);
		saveConfig();
	}
	
	public static BInfection getInstance() {
	    return (BInfection) Bukkit.getPluginManager().getPlugin("BInfection");
	}
}
