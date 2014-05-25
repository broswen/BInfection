package me.broswen.binfection.commands;

import me.broswen.binfection.API;
import me.broswen.binfection.BInfection;
import me.broswen.binfection.utils.GameManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class InfectionCommand implements CommandExecutor{

	public InfectionCommand(BInfection bInfection) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("infection")){
			
			if(args.length != 1){
				if(!(sender instanceof Player)){
					sender.sendMessage("Usage: /infection 'join/leave/start/stop/reload'");
					return true;
				}
				Player player = (Player) sender;
				API.messageAPlayer(player, "Usage: /infection 'join/leave/start/stop/reload'");
				return true;
			}
			
			//starts the game
			if(args[0].equalsIgnoreCase("start")){
				
				if(BInfection.totalPlaying < BInfection.config.getInt("min-players")){
					if(!(sender instanceof Player)){
						sender.sendMessage("You need at least " + BInfection.config.getInt("min-players") + " players to start a game!");
						return true;
					}
					Player player = (Player) sender;
					API.messageAPlayer(player, "You need at least " + BInfection.config.getInt("min-players") + " players to start a game!");
					return true;
				}
				
				if(BInfection.gameStarted){
					if(!(sender instanceof Player)){
						sender.sendMessage("The game has already started!");
						return true;
					}
					Player player = (Player) sender;
					API.messageAPlayer(player, "The game has already started!");
					return true;
				}
				
				if(!(sender instanceof Player)){
					sender.sendMessage("You started the game!");
					return true;
				}
				Player player = (Player) sender;
				GameManager.startGame();
				API.messageAPlayer(player, "You started the game!");
				return true;
			}
			
			//stops the game
			if(args[0].equalsIgnoreCase("stop")){
				
				if(!BInfection.gameStarted){
					if(!(sender instanceof Player)){
						sender.sendMessage("The game is not running!");
						return true;
					}
					Player player = (Player) sender;
					API.messageAPlayer(player, "The game is not running!");
					return true;
				}
				
				if(!(sender instanceof Player)){
					sender.sendMessage("You ended the game!");
					return true;
				}
				Player player = (Player) sender;
				API.messageAPlayer(player, "You ended the game!");
				GameManager.endGame();
				return true;
			}
			
			//leaves the game
			if(args[0].equalsIgnoreCase("leave")){
				
				if(!(sender instanceof Player)){
					sender.sendMessage("You must be a player to do that!");
					return true;
				}
				
				Player player = (Player) sender;
				
				if(!API.isPlayer(player)){
					API.messageAPlayer(player, "You are not in a game!");
					return true;
				}
				
				player.teleport(BInfection.lobbySpawn);
				API.resetInventory(player);
				API.resetPotions(player);
				
				API.messageAPlayer(player, "You left the game!");
				API.removeFromAlive(player);
				API.removeFromPlayers(player);
				API.removeFromInfected(player);
				
				API.messageAllPlayers(player.getName() + " has left the game! [" + BInfection.totalPlaying + "/" + BInfection.config.getInt("max-players") + "]");
				
				if(BInfection.totalPlaying < BInfection.config.getInt("min-players") && BInfection.gameStarted){
					GameManager.endGame();
					return true;
				}
				
				if(BInfection.totalAlive <= 0 && BInfection.gameStarted){
					GameManager.endGame();
					return true;
				}
				
				if(BInfection.totalInfected <= 0 && BInfection.gameStarted){
					GameManager.endGame();
					return true;
				}
				
				return true;
			}
			
			//joins the game
			if(args[0].equalsIgnoreCase("join")){
				if(!(sender instanceof Player)){
					sender.sendMessage("You must be a player to do that!");
					return true;
				}
				
				Player player = (Player) sender;
				
				if(API.isPlayer(player)){
					API.messageAPlayer(player, "You are already in a game!");
					return true;
				}
				
				player.teleport(BInfection.lobbySpawn);
				API.resetInventory(player);
				API.resetPotions(player);
				API.addToPlayers(player);
				API.messageAllPlayers(player.getName() + " has joined the game! [" + BInfection.totalPlaying + "/" + BInfection.config.getInt("max-players") + "]");
				return true;
			}
			
			//reloads the config
			if(args[0].equalsIgnoreCase("reload")){
				BInfection.getInstance().reloadConfig();
				
				if(!(sender instanceof Player)){
					sender.sendMessage("The config was reloaded!");
					return true;
				}
				Player player = (Player) sender;
				API.messageAPlayer(player, "the config was reloaded!");
				return true;
			}
			
			//infects a player
			if(args[0].equalsIgnoreCase("infect")){
				if(!(sender instanceof Player)){
					sender.sendMessage("You must be a player to do that!");
					return true;
				}
				
				Player player = (Player) sender;
				
				if(!API.isPlayer(player)){
					API.messageAPlayer(player, "You must be in a game to be infected!");
					return true;
				}
				
				if(!BInfection.gameStarted){
					API.messageAPlayer(player, "The game must be started to infect you!");
					return true;
				}
				
				if(BInfection.totalInfected > 0){
					API.messageAPlayer(player, "There is already an infected player!");
					return true;
				}
				
				API.messageAllPlayers(player.getName() + " is now infected!");
				API.addToInfected(player);
				API.removeFromAlive(player);
				API.resetInventory(player);
				API.resetPotions(player);
				API.giveInfectedItems(player);
				player.teleport(BInfection.infectedSpawn);
				return true;
			}
			
			//shows developer info
			if(args[0].equalsIgnoreCase("info")){
				if(!(sender instanceof Player)){
					sender.sendMessage("You must be a player to do that!");
					return true;
				}
				
				Player player = (Player) sender;
				player.sendMessage("gameStarted: " + BInfection.gameStarted);
				player.sendMessage("gameEnded: " + BInfection.gameEnded);
				player.sendMessage("infectedStarted: " + BInfection.infectedStarted);
				player.sendMessage("totalPlaying: " + BInfection.totalPlaying);
				player.sendMessage("totalAlive: " + BInfection.totalAlive);
				player.sendMessage("totalInfected: " + BInfection.totalInfected);
				player.sendMessage("isAlive: " + API.isAlive(player));
				player.sendMessage("isPlaying: " + API.isPlayer(player));
				player.sendMessage("isInfected: " + API.isInfected(player));
				player.sendMessage("lastInfected: " + BInfection.lastInfected);
				return true;
			}
			
			
			if(!(sender instanceof Player)){
				sender.sendMessage("Usage: /infection 'join/leave/start/stop/reload'");
				return true;
			}
			
			Player player = (Player) sender;
			API.messageAPlayer(player, "Usage: /infection 'join/leave/start/stop/reload'");
			return true;
		    }	
		
		return true;
	}

	
	
}
