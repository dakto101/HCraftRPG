package me.dakto101.playerclass;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.dakto101.database.MySQL;
import me.dakto101.database.MySQLResultSet;

public class PlayerClassAPI {
	
	private static List<PlayerClass> classList = new ArrayList<PlayerClass>();
	public static String SQL_TABLE_NAME = "class_and_skill";
	
	public static List<PlayerClass> getAllPlayerClass() {
		return classList;
	}
	
	public static PlayerClass getPlayerClass(Player p, String className) {
		PlayerClass c = null;
		for (PlayerClass classes : classList) {
			if (classes.getClassName().equals(className)) {
				c = classes;
				c.loadPlayerClassFromSQL(p);
				break;
			}
		}
		return c;
	}
	
	public static void registerPlayerClass(PlayerClass... playerClass) {
		for (PlayerClass c : playerClass) classList.add(c);
	}
	
	public static String getPlayerClassName(String playerName) {
		//MySQL
		String className = "";
		String query = "select * from " + SQL_TABLE_NAME + " where player_name = '" + playerName + "';";
		try {
			ResultSet rs;
			MySQLResultSet sqlrs = MySQL.sendSelectQuery(query, MySQL.DBNAME_HCRAFT_RPG, false);
			rs = sqlrs.getResultSet();
			while(rs.next()) {
				className = rs.getString(4);
			}
			sqlrs.getConnection().close();
		} catch (Exception e) {
			String error = "HCraftRPG: Khong load duoc du lieu cua nguoi choi " + playerName + "(PlayerClassAPI.java)";
			Bukkit.getLogger().info(error);
		}
		return className;
	}
	
}
