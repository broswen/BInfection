package me.broswen.binfection.events;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerChange implements Listener{

	@EventHandler
	public void onHungerChange(FoodLevelChangeEvent event){

		if(!(event.getEntity() instanceof Player)){
			return;
		}
		Player player = (Player) event.getEntity();
		
		if(API.isPlayer(player) && !BInfection.gameStarted){
			player.setFoodLevel(20);
			event.setCancelled(true);
			return;
		}
		
	}
	
}
