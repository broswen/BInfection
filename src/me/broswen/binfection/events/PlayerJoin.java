package me.broswen.binfection.events;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;
import me.broswen.binfection.utils.GameManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		
		if(!BInfection.gameStarted){
			API.addToPlayers(player);
			if(BInfection.totalPlaying >= 10){
				GameManager.startGame();
			}
		}
	}
}
