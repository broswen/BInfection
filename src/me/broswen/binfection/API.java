package me.broswen.binfection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class API {
	
	//adds a player to the players arraylist
	public static void addToPlayers(Player player){
		BInfection.players.add(player.getName());
		BInfection.totalPlaying++;
	}
	
	//removes a player from the players arraylist
	public static void removeFromPlayers(Player player){
		if(BInfection.players.contains(player.getName())){
			BInfection.players.remove(player.getName());
			BInfection.totalPlaying--;
		}
	}
	
	//returns if a player is in the players arraylist
	public static boolean isPlayer(Player player){
		if(BInfection.players.contains(player.getName())){
			return true;
		}
		return false;
	}
	
	//adds a player to the alive arraylist
	public static void addToAlive(Player player){
		BInfection.alive.add(player.getName());
		BInfection.totalAlive++;
	}
	
	//removes a player from the alive arraylist
	public static void removeFromAlive(Player player){
		if(BInfection.alive.contains(player.getName())){
			BInfection.alive.remove(player.getName());
			BInfection.totalAlive--;
		}
	}
	
	//returns if a player is in the alive arraylist
	public static boolean isAlive(Player player){
		if(BInfection.alive.contains(player.getName())){
			return true;
		}
		return false;
	}
	
	//adds a player to the infected arraylist
	public static void addToInfected(Player player){
		BInfection.infected.add(player.getName());
		BInfection.totalInfected++;
	}
	
	//removes a player from the infected arraylist
	public static void removeFromInfected(Player player){
		if(BInfection.infected.contains(player.getName())){
			BInfection.infected.remove(player.getName());
			BInfection.totalInfected--;
		}
	}
	
	//returns if a player is in the infected arraylist
	public static boolean isInfected(Player player){
		if(BInfection.infected.contains(player.getName())){
			return true;
		}
		return false;
	}
	
	//sends a message to all players
	public static void messageAllPlayers(String message){
		for(Player player : Bukkit.getOnlinePlayers()){
			if(isPlayer(player)){
				player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GRAY + "BInfection" + ChatColor.DARK_GREEN + "] " + ChatColor.RESET + message);
			}
		}
	}
	
	//sends a message to a single player
	public static void messageAPlayer(Player player, String message){
		player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GRAY + "BInfection" + ChatColor.DARK_GREEN + "] " + ChatColor.RESET + message);
	}
	
	//resets a players inventory
	public static void resetInventory(Player player){
		player.getInventory().clear();
		player.getInventory().getArmorContents().equals(null);
	}
	
	//removes all potion effects from a player
	public static void resetPotions(Player player){
		
		if(player.getActivePotionEffects() == null){
			return;
		}
		
		for (PotionEffect effect : player.getActivePotionEffects()){
			player.removePotionEffect(effect.getType());
		}
	}
	
}
