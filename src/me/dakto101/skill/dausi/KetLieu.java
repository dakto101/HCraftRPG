package me.dakto101.skill.dausi;

import java.util.Arrays;
import java.util.Hashtable;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import me.dakto101.event.PlayerCastPassiveSkillEvent;
import me.dakto101.skill.Cooldown;
import me.dakto101.skill.PassiveSkill;

public class KetLieu extends PassiveSkill implements Cooldown  {
	
	public static Hashtable<String, Long> timer = new Hashtable<String, Long>();

	public KetLieu(int level) {
		super("Kết Liễu", level);
		this.cooldown = 40;
		this.description = Arrays.asList("§7Khi kẻ địch còn dưới §c30% máu§7, đòn đánh kế tiếp gây thêm ", 
				"§610 + 2 X cấp§7 sát thương vật lý. (" + cooldown + "s hồi)", 
				"§7Yêu cầu: §bRìu");
	}
	
	@Override
	public void apply(Event event, int level) {
		if (level <= 0) return;
		EntityDamageByEntityEvent e;
		if (event instanceof EntityDamageByEntityEvent) e = (EntityDamageByEntityEvent) event;
		else return;
		if (!(e.getEntity() instanceof LivingEntity)) return;
		if (!(e.getDamager() instanceof LivingEntity)) return;
		Player player = (Player) e.getDamager();
		//Check rìu
		if (!player.getInventory().getItemInMainHand().getType().toString().contains("_AXE")) return;
		LivingEntity target = (LivingEntity) e.getEntity();
		//Check < 30% mau toi da
		if ((target.getHealth() / target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) > 0.3) return;
		//Cooldown
		if (timer.get(player.getName()) == null) timer.put(player.getName(), 0L);
		if (onCooldown(player.getName())) return;
		//Event
		PlayerCastPassiveSkillEvent eventt = new PlayerCastPassiveSkillEvent(this);
		Bukkit.getServer().getPluginManager().callEvent(eventt);
		if (eventt.isCancelled()) return;
		//Code
		float damage = 10 + level * 2;
		e.setDamage(e.getDamage() + damage);

		target.getWorld().playSound(target.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1, 1);
		target.getWorld().spawnParticle(Particle.SMOKE_LARGE, target.getLocation(), 20);
		
		player.sendMessage("§aKỹ năng §b" + this.skillName + "§a gây §6" + damage + "§a sát thương vật lý lên kẻ địch.");
		target.sendMessage("§aKẻ địch vừa dùng kỹ năng §b" + this.skillName + "§a lên bạn, gây §6" + damage + "§a sát thương vật lý.");
		//
		startCooldown(player.getName());
	}

	@Override
	public void startCooldown(Object playerName) {
		if (KetLieu.timer.size() > 200) KetLieu.timer.clear();
		KetLieu.timer.put(playerName.toString(), System.currentTimeMillis());
	}

	@Override
	public boolean onCooldown(Object playerName) {
		return (System.currentTimeMillis() - timer.get(playerName) > cooldown * 1000) ? false : true; 
	}

	@Override
	public long getRemainingCooldown(Object playerName) {
		return KetLieu.timer.get(playerName) + cooldown * 1000 - System.currentTimeMillis();
	}


}
