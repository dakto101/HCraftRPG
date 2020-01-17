package me.dakto101.player;

import org.bukkit.entity.Player;

import me.dakto101.playerclass.PlayerClass;
import me.dakto101.playerstat.PlayerStat;

public class HCraftPlayer {
	
	private PlayerClass playerClass;
	private PlayerStat playerStat;
	private Player player;
	
	public HCraftPlayer(Player p) {
		this.player = p;
		playerClass = new PlayerClass();
		playerStat = new PlayerStat(0, 0, 0, 0, 0, 0, 0);
		playerStat.loadPlayerStatsFromSQL(player);
		playerClass.loadPlayerClassFromSQL(player);
	}
	
}
