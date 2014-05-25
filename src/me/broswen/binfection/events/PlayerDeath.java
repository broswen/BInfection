package me.broswen.binfection.events;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;
import me.broswen.binfection.utils.GameManager;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerDeath implements Listener{
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		
		if(event.getEntity() instanceof Player){
			final Player player = (Player) event.getEntity();
			
			if(!API.isPlayer(player)){
				return;
			}
			
			if(!BInfection.gameStarted){
				return;
			}
			
			if(API.isAlive(player)){
				event.setDeathMessage(null);
				
				for(ItemStack item : event.getDrops()){
					player.getWorld().dropItem(player.getLocation(), item);
				}
				
				event.getDrops().clear();
				API.messageAllPlayers(player.getName() + " is now infected!");
				player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
				API.addToInfected(player);
				API.removeFromAlive(player);
				player.setVelocity(new Vector(0, 0, 0));
				player.teleport(BInfection.infectedSpawn);
				player.setHealth(20.0);
				API.giveInfectedItems(player);
			}
			
			if(API.isInfected(player)){
				event.setDeathMessage(null);
				event.getDrops().clear();
				API.messageAPlayer(player.getKiller(), "You killed " + player.getName() + "!");
				player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
				player.setVelocity(new Vector(0, 0, 0));
				player.setHealth(20.0);
				player.teleport(BInfection.infectedSpawn);
				API.giveInfectedItems(player);
			}
			
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
}
