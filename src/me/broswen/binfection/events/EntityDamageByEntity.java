package me.broswen.binfection.events;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener{
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player){
			Player player = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();
			
			if(API.isPlayer(player) && !API.isPlayer(damager)){
				
				event.setCancelled(true);
				return;
			}
			
			if(!API.isPlayer(player) && API.isPlayer(damager)){
				event.setCancelled(true);
				return;
			}
			
			if(API.isPlayer(damager) && !BInfection.gameStarted){
				event.setCancelled(true);
				return;
			}
		}
	}

}
