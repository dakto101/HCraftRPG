package me.dakto101.playerclass;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.dakto101.database.MySQL;
import me.dakto101.database.MySQLResultSet;
import me.dakto101.event.PlayerClassLevelChangeEvent;
import me.dakto101.event.PlayerClassLoadEvent;
import me.dakto101.event.PlayerClassSaveEvent;
import me.dakto101.event.PlayerClassXPChangeEvent;
import me.dakto101.skill.Skill;

public abstract class PlayerClass {
	
	protected List<Skill> classSkills = new ArrayList<Skill>();
	protected String className;
	protected long xp;
	protected long requireXP;
	protected int skillPoint;
	protected int level;
	protected Player player;
	
	public PlayerClass(Player player) {
		this.player = player;
	}
	
	public PlayerClass(String className) {
		this.className = className;
	}
	
	public List<Skill> getClassSkills() {
		return this.classSkills;
	}
	public String getClassName() {
		return this.className;
	}
	public long getXP() {
		return this.xp;
	}
	public long getRequireXP() {
		return this.requireXP;
	}
	public int getSkillPoint() {
		return this.skillPoint;
	}
	public int getLevel() {
		return this.level;
	}
	public Player getPlayer() {
		return this.player;
	}
	public void setXP(long xp) {
		PlayerClassXPChangeEvent event = new PlayerClassXPChangeEvent(this, xp - this.xp);
		Bukkit.getServer().getPluginManager().callEvent(event);
		this.xp = event.getXPAdd() + this.xp;
	}
	public void setSkillPoint(int skillPoint) {
		this.skillPoint = skillPoint;
	}
	public void setRequireXP(int requireXP) {
		this.requireXP = requireXP;
	}
	public void setLevel(int level) {
		PlayerClassLevelChangeEvent event = new PlayerClassLevelChangeEvent(this, level - this.level);
		Bukkit.getServer().getPluginManager().callEvent(event);
		this.level = event.getLevelAdd() + this.level;
	}
	
	public void loadPlayerClassFromSQL() {
		if (player == null) return;
		//MySQL
		String query = "select * from " + PlayerClassAPI.SQL_TABLE_NAME
				+ " where player_uuid = '" + this.player.getUniqueId() + "';";
		try {
			ResultSet rs;
			MySQLResultSet sqlrs = MySQL.sendSelectQuery(query, MySQL.DBNAME_HCRAFT_RPG, false);
			rs = sqlrs.getResultSet();
			while(rs.next()) {
				className = rs.getString(4);
				skillPoint = rs.getShort(5);
				classSkills.get(0).setLevel(rs.getInt(6));
				classSkills.get(1).setLevel(rs.getInt(7));
				classSkills.get(2).setLevel(rs.getInt(8));
				classSkills.get(3).setLevel(rs.getInt(9));
				xp = rs.getLong(10);
				requireXP = rs.getLong(11);
				level = rs.getInt(12);
			}
			sqlrs.getConnection().close();
			//Call event...
			PlayerClassLoadEvent e = new PlayerClassLoadEvent(this, this.player);
			Bukkit.getServer().getPluginManager().callEvent(e);
		} catch (Exception exception) {
			String error = "HCraftRPG: Khong load duoc du lieu cua nguoi choi " + this.player.getName() + "(PlayerClass.java)";
			Bukkit.getLogger().info(error);
		}
	}
	
	public void savePlayerClassToSQL() {
		if (player == null) return;
		//
		String query1 = "INSERT IGNORE INTO " + PlayerClassAPI.SQL_TABLE_NAME + " (player_uuid, player_name, class_name, skill_point, skill1, skill2, skill3, skill4, xp, require_xp, level) VALUES ('" + this.player.getUniqueId() + "', '" + this.player.getName() + "', NULL, 0, 0, 0, 0, 0, 0, 0, 0);";
		String query2 = "UPDATE " + PlayerClassAPI.SQL_TABLE_NAME + " "
				+ "SET class_name = '" + this.className + "'"
				+ ", skill_point = " + this.skillPoint
				+ ", skill1 = " + this.classSkills.get(0).getLevel()
				+ ", skill2 = " + this.classSkills.get(1).getLevel()
				+ ", skill3 = " + this.classSkills.get(2).getLevel()
				+ ", skill4 = " + this.classSkills.get(3).getLevel()
				+ ", xp = " + this.xp
				+ ", require_xp = " + this.requireXP
				+ ", level = " + this.level
				+ " WHERE player_uuid = '" + this.player.getUniqueId() + "';";
		try {
			MySQL.sendQuery(query1, MySQL.DBNAME_HCRAFT_RPG, false);
			MySQL.sendQuery(query2, MySQL.DBNAME_HCRAFT_RPG, false);
			//Call event
			PlayerClassSaveEvent e = new PlayerClassSaveEvent(this, this.player);
			Bukkit.getServer().getPluginManager().callEvent(e);
		} catch (Exception exception) {
			String error = "HCraftRPG: Khong luu duoc du lieu cua nguoi choi " + this.player.getName() + "(" + this.getClass().getName() + ")";
			Bukkit.getConsoleSender().sendMessage(error);
			Bukkit.getLogger().info(error);
		}
	}
	
	@Override
	public String toString() {
		return "[className = " + className + ", xp = " + xp + ", requireXP = " + requireXP + ", skillPoint = " + skillPoint + ", level = " + level + "]";
	}	
	
	
}
