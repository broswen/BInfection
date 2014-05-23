package me.broswen.binfection.events;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;
import me.broswen.binfection.utils.GameManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener{
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		
		if(!API.isPlayer(player) ){
			return;
		}
		
		API.removeFromPlayers(player);
		API.removeFromInfected(player);
		API.removeFromAlive(player);
		API.messageAllPlayers(player.getName() + " has left the game! [" + BInfection.totalPlaying + "/" + BInfection.config.getInt("max-players") + "]");
		player.teleport(BInfection.lobbySpawn);
		API.resetInventory(player);
		API.resetPotions(player);
		
		if(BInfection.totalPlaying < BInfection.config.getInt("min-players")){
			GameManager.endGame();
			return;
		}
		
		if(BInfection.totalAlive <= 0){
			GameManager.endGame();
			return;
		}
		
		if(BInfection.totalInfected <= 0){
			GameManager.endGame();
			return;
		}
	}

}
