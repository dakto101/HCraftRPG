package me.dakto101.playerclass;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import me.dakto101.database.MySQL;
import me.dakto101.database.MySQLResultSet;

public class PlayerClassAPI {
	
	private static List<PlayerClass> classList = new ArrayList<PlayerClass>();
	public static String SQL_TABLE_NAME = "class_and_skill";
	
	public static List<PlayerClass> getAllPlayerClass() {
		return classList;
	}
	public static PlayerClass getPlayerClass(OfflinePlayer p) {
		PlayerClass c = null;
		//Lấy trong database ra.
		try {
			for (PlayerClass classes : classList) {
				if (classes.getClassName().equals(getPlayerClassName(p))) {
					c = classes;
					c.setPlayer(p);
					c.loadPlayerClassFromSQL();
					break;
				}
			}
		} catch (Exception e) {
			String error = "HCraftRPG: Khong load duoc du lieu cua nguoi choi " + p.getName() + "(PlayerClassAPI.java, getPlayerClass(Player))";
			Bukkit.getLogger().info(error);
		}

		return c;
	}
	
	public static void registerPlayerClass(PlayerClass... playerClass) {
		for (PlayerClass c : playerClass) classList.add(c);
	}
	private static String getPlayerClassName(OfflinePlayer p) {
		//MySQL
		String className = "";
		String query = "select * from " + SQL_TABLE_NAME 
				+ " where player_uuid = '" + p.getUniqueId() + "';";
		try {
			ResultSet rs;
			MySQLResultSet sqlrs = MySQL.sendSelectQuery(query, MySQL.DBNAME_HCRAFT_RPG, false);
			rs = sqlrs.getResultSet();
			while(rs.next()) {
				className = rs.getString(4);
			}
			sqlrs.getConnection().close();
		} catch (Exception e) {
			String error = "HCraftRPG: Khong load duoc du lieu cua nguoi choi " + p.getName() + "(PlayerClassAPI.java)";
			Bukkit.getLogger().info(error);
		}
		return className;
	}
	
	public static long getRequireXP(int level) {
		return (long) (3 + Math.pow(level, 1.62));
	}
	
	public static long getTotalRequireXP(int level) {
		long result = 0;
		for (int i = 1; i < level + 1; i++) {
			result += getRequireXP(i);
		}
		return result;
	}
	
	public static void createNewPlayerClassToSQL(OfflinePlayer player) {
		String query1 = "INSERT IGNORE INTO " + PlayerClassAPI.SQL_TABLE_NAME + " (player_uuid, player_name, class_name, skill_point, skill1, skill2, skill3, skill4, xp, require_xp, level) VALUES ('" + player.getUniqueId() + "', '" + player.getName() + "', 'Đấu Sĩ', 0, 0, 0, 0, 0, 0, 0, 0);";
		try {
			MySQL.sendQuery(query1, MySQL.DBNAME_HCRAFT_RPG, false);
		} catch (Exception exception) {
			String error = "HCraftRPG: Khong the tao du lieu mac dinh cho nguoi choi " + player.getName() + " (" + PlayerClass.class.getName() + ")";
			Bukkit.getConsoleSender().sendMessage(error);
			Bukkit.getLogger().info(error);
		} 
	}
	
	public static boolean changePlayerClassNameSQL(OfflinePlayer player, String className) {
		boolean checkClassName = false;
		for (PlayerClass pc : PlayerClassAPI.getAllPlayerClass()) {
			if (pc.getClassName().equals(className)) checkClassName = true;
		}
		if (checkClassName == false) return checkClassName;
		String query1 = "UPDATE " + PlayerClassAPI.SQL_TABLE_NAME + " "
				+ "SET class_name = '" + className + "'"
				+ " WHERE player_uuid = '" + player.getUniqueId() + "';";
		try {
			MySQL.sendQuery(query1, MySQL.DBNAME_HCRAFT_RPG, false);
		} catch (Exception exception) {
			String error = "HCraftRPG: Khong the thay doi ten class cho nguoi choi " + player.getName() + " (" + PlayerClass.class.getName() + ")";
			Bukkit.getConsoleSender().sendMessage(error);
			Bukkit.getLogger().info(error);
		} 
		return checkClassName;
	}
	
	
	
}
