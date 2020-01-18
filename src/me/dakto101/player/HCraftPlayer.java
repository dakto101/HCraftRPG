package me.dakto101.player;

import org.bukkit.entity.Player;

import me.dakto101.playerclass.PlayerClass;
import me.dakto101.playerclass.PlayerClassAPI;
import me.dakto101.playerstat.PlayerStat;

public class HCraftPlayer {
	
	private PlayerClass playerClass;
	private PlayerStat playerStat;
	private Player player;
	
	public HCraftPlayer(Player p) {
		this.player = p;
		playerClass = PlayerClassAPI.getPlayerClass(player);
		playerStat = new PlayerStat(0, 0, 0, 0, 0, 0, 0);
		loadData();
	}
	
	public PlayerClass getPlayerClass() {
		return this.playerClass;
	}
	
	public PlayerStat getPlayerStat() {
		return this.playerStat;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void setPlayerClass(PlayerClass playerClass) {
		this.playerClass = playerClass;
	}
	
	public void setPlayerStat(PlayerStat playerStat) {
		this.playerStat = playerStat;
	}
	
	public void loadData() {
		this.playerClass.loadPlayerClassFromSQL(player);
		this.playerStat.loadPlayerStatsFromSQL(player);
	}
	
	public void saveData() {
		this.playerClass.savePlayerClassToSQL(player);
		this.playerStat.savePlayerStatsToSQL(player);
	}
	
	@Override
	public String toString() {
		return player.toString() + playerClass.toString() + playerStat.toString();
	}
}
