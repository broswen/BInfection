package me.broswen.binfection.events;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteract implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		final Player player = event.getPlayer();
		
		if(!API.isPlayer(player) || !BInfection.gameStarted){
			return;
		}
		
		if(event.getItem() == null || event.getAction() == null){
			return;
		}
		
		if(event.getItem().equals(new ItemStack(Material.REDSTONE_BLOCK)) && API.isAlive(player)){
			if(!API.isRegenerationCooldown(player)){
				API.messageAPlayer(player, "You recieved regeneration for 5 seconds!");
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 1));
				API.addToRegenerationCooldown(player);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(BInfection.getInstance(), new Runnable(){

					@Override
					public void run() {
						API.removeFromRegenerationCooldown(player);
						if(API.isAlive(player)){
							API.messageAPlayer(player, "You can use regeneration again!");
						}
					}
				}, 400L);
				return;
			}
			
			API.messageAPlayer(player, "You can only use regeneration every 20 seconds!");
		}
		
		if(event.getItem().equals(new ItemStack(Material.ANVIL)) && API.isInfected(player)){
			if(!API.isResistanceCooldown(player)){
				API.messageAPlayer(player, "You recieved resistance for 5 seconds!");
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 1));
				API.addToResistanceCooldown(player);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(BInfection.getInstance(), new Runnable(){

					@Override
					public void run() {
						API.removeFromResistanceCooldown(player);
						if(API.isInfected(player)){
							API.messageAPlayer(player, "You can use resistance again!");
						}
					}
				}, 400L);
				return;
			}
			
			API.messageAPlayer(player, "You can only use regeneration every 20 seconds!");
		}
	}
}
