package me.dakto101.playerclass;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import me.dakto101.database.MySQL;
import me.dakto101.database.MySQLResultSet;
import me.dakto101.event.PlayerClassLevelChangeEvent;
import me.dakto101.event.PlayerClassLoadEvent;
import me.dakto101.event.PlayerClassSaveEvent;
import me.dakto101.event.PlayerClassSkillPointChangeEvent;
import me.dakto101.event.PlayerClassXPChangeEvent;
import me.dakto101.skill.Skill;

public abstract class PlayerClass {
	
	protected List<Skill> classSkills = new ArrayList<Skill>();
	protected String className;
	protected long xp;
	protected long requireXP;
	protected int skillPoint;
	protected int level;
	protected OfflinePlayer player;
	
	public PlayerClass(OfflinePlayer player) {
		this.player = player;
		this.requireXP = 1;
		this.xp = 0;
		this.skillPoint = 0;
		this.level = 0;
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
	public OfflinePlayer getPlayer() {
		return this.player;
	}
	public void setClassSkills(List<Skill> classSkills) {
		this.classSkills = classSkills;
	}
	public void setPlayer(OfflinePlayer p) {
		this.player = p;
	}
	public void setXP(long xp, boolean callEvent) {
		PlayerClassXPChangeEvent event = new PlayerClassXPChangeEvent(this, xp - this.xp);
		if (callEvent == true) {
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
		this.xp = event.getXPAdd() + this.xp;
	}
	public void setSkillPoint(int skillPoint, boolean callEvent) {
		PlayerClassSkillPointChangeEvent event = new PlayerClassSkillPointChangeEvent(this, skillPoint - this.skillPoint);
		if (callEvent == true) {
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
		this.skillPoint = event.getSkillPointAdd() + this.skillPoint;
	}
	public void setRequireXP(long requireXP) {
		this.requireXP = requireXP;
	}
	public void setLevel(int level, boolean callEvent) {
		PlayerClassLevelChangeEvent event = new PlayerClassLevelChangeEvent(this, level - this.level);
		if (callEvent == true) {
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
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

	
	public void reset() {
		this.skillPoint = 1;
		this.classSkills.get(0).setLevel(0);
		this.classSkills.get(1).setLevel(0);
		this.classSkills.get(2).setLevel(0);
		this.classSkills.get(3).setLevel(0);
		this.xp = 0;
		this.level = 1;
		this.requireXP = PlayerClassAPI.getRequireXP(this.level);
	}
	
	@Override
	public String toString() {
		List<String> list = new ArrayList<String>();
		list.add("Tên người chơi: " + player.getName());
		list.add("Tên lớp: " + className);
		list.add("Điểm kinh nghiệm: " + xp + "/" + requireXP);
		list.add("Cấp độ: " + level);
		list.add("Điểm kỹ năng: " + skillPoint);
		String skillLevel = "";
		for (Skill s : this.classSkills) {
			skillLevel += s.getLevel();
			skillLevel += "/";
		}
		skillLevel = skillLevel.substring(0, skillLevel.length() - 1);
		list.add("Thông số kỹ năng: " + skillLevel);
		return list.toString();
		//return "[className = " + className + ", xp = " + xp + " / " + requireXP + ", skillPoint = " + skillPoint + ", level = " + level + ", player = " + player + "]";
	}	

	
}
