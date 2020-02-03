package me.dakto101.playerclass;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.dakto101.event.PlayerClassLevelChangeEvent;
import me.dakto101.event.PlayerClassSkillPointChangeEvent;
import me.dakto101.event.PlayerClassXPChangeEvent;

public class PlayerClassListener implements Listener{	
	
	
	@EventHandler
	public void onXPChange(PlayerClassXPChangeEvent e) {
		/*
		 * + (xp > require) → level up.
		 * + Send message on XP change.
		 */
		PlayerClass pc = e.getPlayerClass();
		if (pc == null) return;
		if (pc.getPlayer() == null) return;
		
		if (pc.getPlayer().isOnline()) {
			Player p = (Player) pc.getPlayer();
			if (e.getXPAdd() > 0) p.sendMessage("§a+ §6" + e.getXPAdd() + "§a XP.");
			if (e.getXPAdd() < 0) p.sendMessage("§a- §6" + Math.abs(e.getXPAdd()) + "§a XP.");
		}
		
		pc.setXP(pc.getXP() + e.getXPAdd(), true);
		//sua thanh for
		while (pc.getXP() > pc.getRequireXP()) {
			pc.setLevel(pc.getLevel() + 1, true);
			pc.setXP(pc.getXP() - pc.getRequireXP(), false);
		}

		pc.savePlayerClassToSQL();
	}

	@EventHandler
	public void onLevelChange(PlayerClassLevelChangeEvent e) {
		/*	Level up:
		 * - Add skill point (1 point = 5 level)
		 * - RequireXP = 3 + level^1.62
		 * - Send message on level change.
		 */
		PlayerClass pc = e.getPlayerClass();
		if (pc == null) return;
		if (pc.getPlayer() == null) return;
		int skillPointAdd = ((pc.getLevel() % 5) + e.getLevelAdd())/5;
		pc.setSkillPoint(pc.getSkillPoint() + skillPointAdd, true);
		pc.setRequireXP((PlayerClassAPI.getRequireXP(pc.getLevel() + e.getLevelAdd())));
		if (pc.getPlayer().isOnline()) ((Player) pc.getPlayer()).sendMessage("§aBạn đã lên cấp " + (pc.getLevel() + e.getLevelAdd()) + "!");
	}
	
	@EventHandler
	public void onSkillPointChange(PlayerClassSkillPointChangeEvent e) {
		/*
		 * + Send message on skill point change.
		 */
		PlayerClass pc = e.getPlayerClass();
		if (pc == null) return;
		if (pc.getPlayer() == null) return;
		if (e.getSkillPointAdd() <= 0) return;
		if (pc.getPlayer().isOnline()) ((Player) pc.getPlayer()).sendMessage("§aNhận được §6" + e.getSkillPointAdd() + "§a điểm kỹ năng.");
	}
	
	
	@EventHandler
	public void test(PlayerInteractEvent e) {

	}
	
}
