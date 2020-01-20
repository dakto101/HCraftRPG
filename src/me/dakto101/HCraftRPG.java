package me.dakto101;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.dakto101.database.MySQL;
import me.dakto101.gui.GUIListener;
import me.dakto101.playerclass.DauSi;
import me.dakto101.playerclass.PlayerClassAPI;
import me.dakto101.playerclass.PlayerClassListener;
import me.dakto101.skill.SkillAPI;
import me.dakto101.skill.dausi.BenBi;
import me.dakto101.skill.dausi.GiaoChien;
import me.dakto101.skill.dausi.KetLieu;
import me.dakto101.skill.dausi.PhanCong;

public class HCraftRPG extends JavaPlugin {

	public static HCraftRPG plugin;
	
	public void onEnable() {
		plugin = this;
		
		this.getCommand("hcraftrpg").setExecutor(new HCraftRPGCommand());
		
		
		getLogger().info("Plugin cua server HCraft da chay thanh cong!" + this.getName());
		registerEvents(new HCraftRPGListener(), new GUIListener(),  
				new PlayerClassListener());
		registerEvents(new DauSi(null));
		SkillAPI.registerSkill(new GiaoChien(0), new KetLieu(0), new BenBi(0), new PhanCong(0));
		PlayerClassAPI.registerPlayerClass(new DauSi(null));
		
		MySQL.setup();
		
	}
	
	public void onDisable() { 
		getLogger().info("Plugin cua server HCraft dang tat!" + this.getName());
	}
	
	private void registerEvents(Listener... l) {
		for (Listener listener : l) getServer().getPluginManager().registerEvents(listener, this);
	}
	
	
}



	  
	


