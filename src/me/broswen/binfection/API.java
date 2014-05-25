package me.broswen.binfection;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class API {
	
	//adds a player to the players arraylist
	public static void addToPlayers(Player player){
		BInfection.players.add(player.getName());
		BInfection.totalPlaying++;
	}
	
	//removes a player from the players arraylist
	public static void removeFromPlayers(Player player){
		if(BInfection.players.contains(player.getName())){
			BInfection.players.remove(player.getName());
			BInfection.totalPlaying--;
		}
	}
	
	//returns if a player is in the players arraylist
	public static boolean isPlayer(Player player){
		if(BInfection.players.contains(player.getName())){
			return true;
		}
		return false;
	}
	
	//adds a player to the alive arraylist
	public static void addToAlive(Player player){
		BInfection.alive.add(player.getName());
		BInfection.totalAlive++;
	}
	
	//removes a player from the alive arraylist
	public static void removeFromAlive(Player player){
		if(BInfection.alive.contains(player.getName())){
			BInfection.alive.remove(player.getName());
			BInfection.totalAlive--;
		}
	}
	
	//returns if a player is in the alive arraylist
	public static boolean isAlive(Player player){
		if(BInfection.alive.contains(player.getName())){
			return true;
		}
		return false;
	}
	
	//adds a player to the infected arraylist
	public static void addToInfected(Player player){
		BInfection.infected.add(player.getName());
		BInfection.totalInfected++;
		MobDisguise mobDisguise = new MobDisguise(DisguiseType.ZOMBIE, true, true);
		DisguiseAPI.disguiseToAll(player, mobDisguise);
	}
	
	//removes a player from the infected arraylist
	public static void removeFromInfected(Player player){
		if(BInfection.infected.contains(player.getName())){
			BInfection.infected.remove(player.getName());
			BInfection.totalInfected--;
			PlayerDisguise playerDisguise = new PlayerDisguise(player.getName());
			DisguiseAPI.disguiseToAll(player, playerDisguise);
		}
	}
	
	//returns if a player is in the infected arraylist
	public static boolean isInfected(Player player){
		if(BInfection.infected.contains(player.getName())){
			return true;
		}
		return false;
	}
	
	//sends a message to all players
	public static void messageAllPlayers(String message){
		for(Player player : Bukkit.getOnlinePlayers()){
			if(isPlayer(player)){
				player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GRAY + "BInfection" + ChatColor.DARK_GREEN + "] " + ChatColor.RESET + message);
			}
		}
	}
	
	//sends a message to a single player
	public static void messageAPlayer(Player player, String message){
		player.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GRAY + "BInfection" + ChatColor.DARK_GREEN + "] " + ChatColor.RESET + message);
	}
	
	//resets a players inventory
	public static void resetInventory(Player player){
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
	}
	
	//removes all potion effects from a player
	public static void resetPotions(Player player){
		
		if(player.getActivePotionEffects() == null){
			return;
		}
		
		for (PotionEffect effect : player.getActivePotionEffects()){
			player.removePotionEffect(effect.getType());
		}
	}
	
	//gives the infected player items
	public static void giveInfectedItems(final Player player){
		final Inventory inv = player.getInventory();
		
		player.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
		player.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
		player.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
		player.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
		inv.addItem(new ItemStack(Material.STONE_SWORD));
		inv.addItem(new ItemStack(Material.ANVIL));
	}
	
	//gives the alive player items
	public static void giveAliveItems(Player player){
		Inventory inv = player.getInventory();
		
		player.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
		player.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		player.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		player.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
		inv.addItem(new ItemStack(Material.STONE_SWORD));
		inv.addItem(new ItemStack(Material.REDSTONE_BLOCK));
	}
	
	//clears items off the ground
	public static void clearWorldItems(World world){
		for(Entity current : world.getEntities()){
            if (current instanceof Item){
            	current.remove();
            }
		}
	}
	
	//adds a player to the regenerationCooldown arraylist
	public static void addToRegenerationCooldown(Player player){
		BInfection.regenerationCooldown.add(player.getName());
	}
	
	//removes a player from the regenerationCooldown arraylist
	public static void removeFromRegenerationCooldown(Player player){
		if(BInfection.regenerationCooldown.contains(player.getName())){
			BInfection.regenerationCooldown.remove(player.getName());
		}
	}
	
	//returns if a player is in the regenerationCooldown arraylist
	public static boolean isRegenerationCooldown(Player player){
		if(BInfection.regenerationCooldown.contains(player.getName())){
			return true;
		}
		return false;
	}
	
	//adds a player to the resistanceCooldown arraylist
		public static void addToResistanceCooldown(Player player){
			BInfection.resistanceCooldown.add(player.getName());
		}
		
		//removes a player from the regenerationCooldown arraylist
		public static void removeFromResistanceCooldown(Player player){
			if(BInfection.resistanceCooldown.contains(player.getName())){
				BInfection.resistanceCooldown.remove(player.getName());
			}
		}
		
		//returns if a player is in the regenerationCooldown arraylist
		public static boolean isResistanceCooldown(Player player){
			if(BInfection.resistanceCooldown.contains(player.getName())){
				return true;
			}
			return false;
		}
	
}
