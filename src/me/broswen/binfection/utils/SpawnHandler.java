package me.broswen.binfection.utils;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class SpawnHandler {
	
	public static void teleportToPlayerSpawn(){
		
		for(Player player : Bukkit.getOnlinePlayers()){
			
			if(API.isPlayer(player)){
				player.teleport(BInfection.playerSpawn);
				API.addToAlive(player);
				API.giveAliveItems(player);
				player.setGameMode(GameMode.SURVIVAL);
			}
		}
	}
	
	public static void teleportToLobbySpawn(){
		
		for(Player player : Bukkit.getOnlinePlayers()){
			
			if(API.isPlayer(player)){
				player.teleport(BInfection.lobbySpawn);
				player.getInventory().clear();
				API.resetInventory(player);
				API.resetPotions(player);
				player.setGameMode(GameMode.SURVIVAL);
			}
			
			if(API.isInfected(player)){
				API.removeFromInfected(player);
			}
		}
	}
	
	public static void teleportToInfectedSpawn(){
		for(Player player : Bukkit.getOnlinePlayers()){
			
			if(API.isPlayer(player)){
				player.teleport(BInfection.infectedSpawn);
				player.setGameMode(GameMode.SURVIVAL);
			}
		}
	}
}
