package me.broswen.binfection.utils;

import org.bukkit.ChatColor;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;

public class GameManager {

	public static void startGame(){
		BInfection.gameStarted = true;
		BInfection.gameEnded = false;
		SpawnHandler.teleportToArena();
		API.messageAllPlayers(ChatColor.YELLOW + "The game has started!");
	}
	
	public static void endGame(){
		BInfection.gameEnded = true;
		BInfection.gameStarted = false;
		SpawnHandler.teleportToLobby();
		API.messageAllPlayers(ChatColor.YELLOW + "The game has ended!");
	}
	
}
