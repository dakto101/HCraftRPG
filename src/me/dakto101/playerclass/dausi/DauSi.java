package me.dakto101.playerclass.dausi;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import me.dakto101.playerclass.PlayerClass;
import me.dakto101.skill.dausi.*;

public class DauSi extends PlayerClass implements Listener {

	public DauSi() {
		super("Đấu Sĩ");
		this.classSkills.add(new GiaoChien(0));
		this.classSkills.add(new KetLieu(0));
		this.classSkills.add(new BenBi(0));
		this.classSkills.add(new PhanCong(0));
		this.requireXP = 1;
		this.xp = 0;
		this.skillPoint = 0;
		this.level = 0;
	}
	
	@EventHandler
	public static void giaoChien(PlayerInteractEntityEvent e) {
		
	}
	
	@EventHandler
	public static void ketLieu(EntityDamageByEntityEvent e) {
		
	}
	
	@EventHandler
	public static void phanCong(EntityDamageByEntityEvent e) {
		
	}
	
	@EventHandler
	public static void benBi(EntityDamageByEntityEvent e) {
		
	}

}
