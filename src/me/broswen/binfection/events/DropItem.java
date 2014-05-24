package me.broswen.binfection.events;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItem implements Listener{

	@EventHandler
	public void onDrpItem(PlayerDropItemEvent event){
		Player player = event.getPlayer();
		
		if(API.isPlayer(player) && BInfection.gameStarted){
			
			if(API.isInfected(player)){
				event.setCancelled(true);
				return;
			}
		}
	}
}
