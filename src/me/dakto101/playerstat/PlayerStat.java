package me.dakto101.playerstat;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.dakto101.database.MySQL;
import me.dakto101.database.MySQLResultSet;
import me.dakto101.event.PlayerStatLoadEvent;
import me.dakto101.event.PlayerStatSaveEvent;

public class PlayerStat {

	private Map<PlayerStatType, Integer> playerStats;
	private int statPoint = 0;

	public PlayerStat(int strength, int dexterity, int intelligence, int defence, int luck, int endurance, int statPoint) {
		this.playerStats = new HashMap<PlayerStatType, Integer>();
		this.playerStats.put(PlayerStatType.STRENGTH, strength);
		this.playerStats.put(PlayerStatType.DEXTERITY, dexterity);
		this.playerStats.put(PlayerStatType.INTELLIGENCE, intelligence);
		this.playerStats.put(PlayerStatType.DEFENCE, defence);
		this.playerStats.put(PlayerStatType.LUCK, luck);
		this.playerStats.put(PlayerStatType.ENDURANCE, endurance);
		this.statPoint = statPoint;
	}
	
	public void loadPlayerStatsFromSQL(Player p) {
		//MySQL
		String query1 = "INSERT IGNORE INTO player_stat (player_uuid, player_name, stat_point, strength, dexterity, intelligence, defence, luck, endurance) VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', 0, 0, 0, 0, 0, 0, 0);";
		String query = "select * from player_stat where player_uuid = '" + p.getUniqueId() + "';";
		MySQL.sendQuery(query1, MySQL.DBNAME_HCRAFT_RPG, false);
		try {
			ResultSet rs;
			MySQLResultSet sqlrs = MySQL.sendSelectQuery(query, MySQL.DBNAME_HCRAFT_RPG, false);
			rs = sqlrs.getResultSet();
			while(rs.next()) {
				this.playerStats = new HashMap<PlayerStatType, Integer>();
				this.statPoint = rs.getInt(4);
				this.playerStats.putIfAbsent(PlayerStatType.STRENGTH, rs.getInt(5));
				this.playerStats.putIfAbsent(PlayerStatType.DEXTERITY, rs.getInt(6));
				this.playerStats.putIfAbsent(PlayerStatType.INTELLIGENCE, rs.getInt(7));
				this.playerStats.putIfAbsent(PlayerStatType.DEFENCE, rs.getInt(8));
				this.playerStats.putIfAbsent(PlayerStatType.LUCK, rs.getInt(9));
				this.playerStats.putIfAbsent(PlayerStatType.ENDURANCE, rs.getInt(10));
			}
			sqlrs.getConnection().close();
			//Call event
			PlayerStatLoadEvent event = new PlayerStatLoadEvent(this, p);
			Bukkit.getServer().getPluginManager().callEvent(event);
		} catch (Exception e) {
			String error = "HCraftRPG: Khong load duoc du lieu cua nguoi choi " + p.getName() + "(" + this.getClass().getName() + ")";
			Bukkit.getLogger().info(error);
		}
	}
	
	public void savePlayerStatsToSQL(Player p) {
		//MySQL
		String query1 = "INSERT IGNORE INTO player_stat (player_uuid, player_name, stat_point, strength, dexterity, intelligence, defence, luck, endurance) VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', 0, 0, 0, 0, 0, 0, 0);";
		String query2 = "UPDATE player_stat "
				+ "SET stat_point = " + this.statPoint
				+ ", strength = " + this.playerStats.get(PlayerStatType.STRENGTH)
				+ ", dexterity = " + this.playerStats.get(PlayerStatType.DEXTERITY)
				+ ", intelligence = " + this.playerStats.get(PlayerStatType.INTELLIGENCE)
				+ ", defence = " + this.playerStats.get(PlayerStatType.DEFENCE)
				+ ", luck = " + this.playerStats.get(PlayerStatType.LUCK)
				+ ", endurance = " + this.playerStats.get(PlayerStatType.ENDURANCE)
				+ " WHERE player_uuid = '" + p.getUniqueId() + "';";
		try {
			MySQL.sendQuery(query1, MySQL.DBNAME_HCRAFT_RPG, false);
			MySQL.sendQuery(query2, MySQL.DBNAME_HCRAFT_RPG, false);
			//Call Event
			PlayerStatSaveEvent event = new PlayerStatSaveEvent(this, p);
			Bukkit.getServer().getPluginManager().callEvent(event);
		} catch (Exception e) {
			String error = "HCraftRPG: Khong luu duoc du lieu cua nguoi choi " + p.getName() + "(" + this.getClass().getName() + ")";
			Bukkit.getConsoleSender().sendMessage(error);
			Bukkit.getLogger().info(error);
		}
		
		//Coding...
		//Untested...
	}
	
	public Map<PlayerStatType, Integer> getPlayerStats() {
		return playerStats;
	}
	
	public void setPlayerStats(Map<PlayerStatType, Integer> map) {
		this.playerStats = map;
		return;
	}
	
	public PlayerStat(Map<PlayerStatType, Integer> playerStats, int statPoint) {
		this.playerStats = new HashMap<PlayerStatType, Integer>();
		this.playerStats = playerStats;
		this.statPoint = statPoint;
	}
	
	public boolean addStats(int add, PlayerStatType playerStatType, boolean requireStatPoints) {
		if (requireStatPoints) {
			if (this.statPoint < add) {
				return false;
			} else this.statPoint = this.statPoint - add;
		}
		int current = this.playerStats.get(playerStatType);
		this.playerStats.replace(playerStatType, current + add);
		return true;
	}
	
	public void setStats(int set, PlayerStatType playerStatType) {
		this.playerStats.replace(playerStatType, set);
	}
	
	public Integer getStats(PlayerStatType playerStatType) {
		return this.playerStats.get(playerStatType);
	}
	
	public void resetStats() {
		for (PlayerStatType type : this.playerStats.keySet()) {
			this.playerStats.replace(type, 0);
		}
	}
	
	
	public String toString() {
		return this.playerStats.toString() + "[statPoint = " + this.statPoint + "]";
	}

	public int getStatPoint() {
		return statPoint;
	}

	public void setStatPoint(int statPoint) {
		this.statPoint = statPoint;
	}
	
}
