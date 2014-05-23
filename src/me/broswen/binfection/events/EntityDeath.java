package me.broswen.binfection.events;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;
import me.broswen.binfection.utils.GameManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener{

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event){
		
		if(event.getEntity() instanceof Player){
			Player player = (Player) event.getEntity();
			
			if(!API.isPlayer(player)){
				return;
			}
			
			if(!BInfection.gameStarted){
				return;
			}
			
			if(player.getKiller() instanceof Player){
				Player killer = player.getKiller();
				
				API.messageAllPlayers(player.getName() + " is now infected!");
				API.addToInfected(player);
				API.removeFromAlive(player);
				player.teleport(BInfection.infectedSpawn);
				player.setHealth(20D);
			}
			
			if(BInfection.totalPlaying < BInfection.config.getInt("min-players")){
				GameManager.endGame();
				return;
			}
			
			if(BInfection.totalAlive <= 0){
				GameManager.endGame();
			}
			
			if(BInfection.totalInfected <= 0){
				GameManager.endGame();
				return;
			}
		}
	}
}
