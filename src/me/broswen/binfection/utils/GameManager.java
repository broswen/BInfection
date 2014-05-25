package me.broswen.binfection.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;

public class GameManager {

	public static void startGame(){
		BInfection.gameStarted = true;
		BInfection.gameEnded = false;
		SpawnHandler.teleportToPlayerSpawn();
		API.messageAllPlayers(ChatColor.YELLOW + "The game has started!");
	}
	
	public static void endGame(){
		
		API.messageAllPlayers(ChatColor.YELLOW + "The game has ended!");
		SpawnHandler.teleportToLobbySpawn();
		
		BInfection.alive.clear();
		BInfection.infected.clear();
		
		BInfection.totalAlive = 0;
		BInfection.totalInfected = 0;
		
		BInfection.infectedStarted = false;
		BInfection.gameEnded = true;
		BInfection.gameStarted = false;
		API.clearWorldItems(BInfection.gameWorld);
	}
	
}
