package me.dakto101.database;

import java.sql.*;
import org.bukkit.Bukkit;

public class MySQL {
	
	public static final String DBNAME_HCRAFT_RPG = "hcraft_rpg";
	
	public static void setup() {
		Bukkit.getConsoleSender().sendMessage("<HCraftRPG - MySQL>: Creating database if not exists...");
		String createDatabase = "CREATE DATABASE IF NOT EXISTS hcraft_rpg;";
		String createPlayerStatTable = "CREATE TABLE IF NOT EXISTS player_stat("
				+ "id INT(11) NOT NULL AUTO_INCREMENT, "
				+ "player_uuid VARCHAR(255) NOT NULL UNIQUE, "
				+ "player_name VARCHAR(255) NOT NULL, "
				+ "stat_point INT(11) NOT NULL, "
				+ "strength INT(4), "
				+ "dexterity INT(4), "
				+ "intelligence INT(4), "
				+ "defence INT(4), "
				+ "luck INT(4), "
				+ "endurance INT(4), "
				+ ""
				+ "CONSTRAINT pk_id PRIMARY KEY(id)"
				+ ") ENGINE = INNODB;";
		String createClassAndSkillTable = "CREATE TABLE IF NOT EXISTS class_and_skill("
				+ "id INT(11) NOT NULL AUTO_INCREMENT, "
				+ "player_uuid VARCHAR(255) NOT NULL UNIQUE, "
				+ "player_name VARCHAR(255) NOT NULL, "
				+ "class_name VARCHAR(255), "
				+ "skill_point INT(4), "
				+ "skill1 INT(4), "
				+ "skill2 INT(4), "
				+ "skill3 INT(4), "
				+ "skill4 INT(4), "
				+ "xp INT, "
				+ "require_xp INT, "
				+ "level INT, "
				+ ""
				+ "CONSTRAINT pk_id PRIMARY KEY(id)"
				+ ") ENGINE = INNODB;";
		sendQuery(createDatabase, "" , false);
		sendQuery(createPlayerStatTable, DBNAME_HCRAFT_RPG , false);
		sendQuery(createClassAndSkillTable, DBNAME_HCRAFT_RPG, false);
	}
	
	public static synchronized MySQLResultSet sendSelectQuery(String query, String databaseName, boolean notifyConsole) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName + "?useUnicode=yes&characterEncoding=UTF-8&useSSL=false","dakto101",""); 
            Statement stmt = con.createStatement();
            
            ResultSet rs;
            rs = stmt.executeQuery(query);
            
            if (notifyConsole == true) {
            	Bukkit.getServer().getConsoleSender().sendMessage("<HCraftRPG - MySQL>: " + query);
            }
            
            MySQLResultSet sqlrs = new MySQLResultSet(con, rs);
            
            return sqlrs;
            
        } catch (SQLException e) {
            System.err.println("(me.dakto101.database.MySQL): SQLException! " + e.getMessage());
        } catch (Exception e) {
        	System.err.println("(me.dakto101.database.MySQL): Exception! " + e.getMessage());
        }
		return null;
	}
	public static synchronized int sendQuery(String query, String databaseName, boolean notifyConsole) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false","dakto101",""); 
            Statement stmt = con.createStatement();
            if (notifyConsole == true) Bukkit.getServer().getLogger().info("<HCraftRPG - MySQL>: " + query);
            
            int rs;
            rs = stmt.executeUpdate(query);
            stmt.execute(query);
            con.close();
            return rs;
        } catch (SQLException e) {
            System.err.println("(me.dakto101.database.MySQL): SQLException! " + e.getMessage());
        } catch (Exception e) {
        	System.err.println("(me.dakto101.database.MySQL): Exception! " + e.getMessage());
        }
		return 0;
	}
	
	public static synchronized int sendUpdate(String query, String databaseName, boolean notifyConsole) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false","dakto101",""); 
            Statement stmt = con.createStatement();
            
            int rs;
            rs = stmt.executeUpdate(query);
            stmt.executeUpdate(query);
            
            if (notifyConsole == true) Bukkit.getServer().getConsoleSender().sendMessage("<HCraftRPG - MySQL>: " + query);

            con.close();
            return rs;
        } catch (SQLException e) {
            System.err.println("(me.dakto101.database.MySQL): SQLException! " + e.getMessage());
        } catch (Exception e) {
        	System.err.println("(me.dakto101.database.MySQL): Exception! " + e.getMessage());
        }
		return 0;
	}
	
}
