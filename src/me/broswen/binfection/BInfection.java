package me.broswen.binfection;

import java.util.ArrayList;

import me.broswen.binfection.events.BlockBreak;
import me.broswen.binfection.events.BlockPlace;
import me.broswen.binfection.events.CommandPreProcess;
import me.broswen.binfection.events.EntityDamageByEntity;
import me.broswen.binfection.events.EntityDeath;
import me.broswen.binfection.events.HungerChange;
import me.broswen.binfection.events.PlayerJoin;
import me.broswen.binfection.events.PlayerQuit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
oswen.binfection.events.HungerChange;

import me.broswen.binfection.events.PlayerJoin;
import me.broswen.binfection.events.PlayerQuit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BInfection extends JavaPlugin{
	public static BInfection plugin;
	
	public static String lastInfected;
	
	public static Location playerSpawn, infectedSpawn, lobbySpawn;
	
	public boolean gameStarted, gameEnded, infectedStarted;
	
	public static FileConfiguration config;
	
	public static int totalPlaying, totalAlive, totalInfected, TotalAlive;
	
	public static ArrayList<String> players = new ArrayList<>();
	public static ArrayList<String> alive = new ArrayList<>();
	public static ArrayList<String> infected = new ArrayList<>();
	public static ArrayList<String> kills = new ArrayList<>();
	
	public void onEnable(){
		
		loadConfig();
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new BlockPlace(), this);
		pm.registerEvents(new CommandPreProcess(), this);
		pm.registerEvents(new EntityDamageByEntity(), this);
		pm.registerEvents(new EntityDeath(), this);
		pm.registerEvents(new HungerChange(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		
		playerSpawn = new Location(Bukkit.getWorld(getConfig().getString("playerspawn.world")), getConfig().getDouble("playerspawn.X"), getConfig().getDouble("playerspawn.Y"), getConfig().getDouble("playerspawn.Z"));
		infectedSpawn = new Location(Bukkit.getWorld(getConfig().getString("taggerspawn.world")), getConfig().getDouble("taggerspawn.X"), getConfig().getDouble("taggerspawn.Y"), getConfig().getDouble("taggerspawn.Z"));
		lobbySpawn = new Location(Bukkit.getWorld(getConfig().getString("lobbyspawn.world")), getConfig().getDouble("lobbyspawn.X"), getConfig().getDouble("lobbyspawn.Y"), getConfig().getDouble("lobbyspawn.Z"));
		
		config = this.getConfig();
		
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
}
