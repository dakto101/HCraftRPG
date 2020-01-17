package me.dakto101.skill.dausi;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.dakto101.event.PlayerCastPassiveSkillEvent;
import me.dakto101.skill.Cooldown;
import me.dakto101.skill.PassiveSkill;

public class BenBi extends PassiveSkill implements Cooldown  {
	
	public BenBi(int level) {
		super("Bền Bỉ", level);
		this.cooldown = 0;
		this.description = Arrays.asList("§7Giảm §6(0.5 + Cấp X 0.4)§7 sát thương từ đòn đánh thường.");
	}
	
	@Override
	public void apply(Event event, int level) {
		if (level <= 0) return;
		EntityDamageByEntityEvent e;
		if (event instanceof EntityDamageByEntityEvent) e = (EntityDamageByEntityEvent) event;
		else return;
		if (!(e.getEntity() instanceof LivingEntity)) return;
		if (!(e.getDamager() instanceof LivingEntity)) return;
		if (!e.getCause().equals(DamageCause.ENTITY_ATTACK)) return;
		//Event
		PlayerCastPassiveSkillEvent eventt = new PlayerCastPassiveSkillEvent(this);
		Bukkit.getServer().getPluginManager().callEvent(eventt);
		//Code
		float reduction = (float) (0.5 + level * 0.4);
		e.setDamage(e.getDamage() - reduction);
	}

	@Override
	public void startCooldown(Object o) {	
	}

	@Override
	public long getRemainingCooldown(Object o) {
		return 0;
	}

	@Override
	public boolean onCooldown(Object o) {
		return false;
	}


}
