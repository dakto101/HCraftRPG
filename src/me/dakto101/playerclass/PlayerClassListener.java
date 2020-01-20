package me.dakto101.playerclass;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.dakto101.event.PlayerClassLevelChangeEvent;
import me.dakto101.event.PlayerClassXPChangeEvent;

public class PlayerClassListener implements Listener{	
	
	//If xp > require then level up.
	@EventHandler
	public void onXPChange(PlayerClassXPChangeEvent e) {
		PlayerClass pc = e.getPlayerClass();
		if (e.getXPAdd() > 0) pc.getPlayer().sendMessage("§a+ §6" + e.getXPAdd() + "§aXP.");
		if (e.getXPAdd() < 0) pc.getPlayer().sendMessage("§a- §6" + Math.abs(e.getXPAdd()) + "§aXP.");
		long requireXP = pc.getRequireXP();
		long currentXP = pc.getXP();
		long xpAdd = e.getXPAdd();
		if (currentXP + xpAdd >= requireXP) {
			pc.setLevel(pc.getLevel() + 1);
			pc.setXP(currentXP + xpAdd - requireXP);
			pc.savePlayerClassToSQL();
		}
	}
	/*	Level up:
	 * - Add skill point (1 point = 1 level)
	 * - RequireXP = 3 + level^1.62
	 */
	@EventHandler
	public void onLevelChange(PlayerClassLevelChangeEvent e) {
		PlayerClass pc = e.getPlayerClass();
		pc.setSkillPoint(pc.getSkillPoint() + e.getLevelAdd());
		pc.setRequireXP((int) (3 + Math.pow(pc.getLevel() + e.getLevelAdd(), 1.62)));
		pc.getPlayer().sendMessage("§aBạn đã lên cấp " + (pc.getLevel() + e.getLevelAdd()) + "!");
	}
	
	/*
	@EventHandler
	public void test(PlayerInteractEvent e) {
		PlayerClass pc = new PlayerClass(e.getPlayer());
		pc.loadPlayerClassFromSQL();
		pc.setXP(pc.getXP() + 1);
	}
	*/
}
