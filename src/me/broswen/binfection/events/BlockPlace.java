package me.broswen.binfection.events;

import me.broswen.binfection.API;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener{

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		
		if(API.isPlayer(player)){
			event.setCancelled(true);
			return;
		}
	}
	
}
