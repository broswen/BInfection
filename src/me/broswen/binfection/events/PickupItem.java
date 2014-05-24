package me.broswen.binfection.events;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupItem implements Listener{
	
	@EventHandler
	public void onPickupItem(PlayerPickupItemEvent event){
		Player player = event.getPlayer();
		
		if(API.isPlayer(player) && BInfection.gameStarted){
			
			if(API.isInfected(player)){
				event.setCancelled(true);
				return;
			}
		}	
	}
}
