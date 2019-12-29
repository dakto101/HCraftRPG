package me.dakto101.skill.dausi;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.dakto101.event.PlayerCastPassiveSkillEvent;
import me.dakto101.skill.Cooldown;
import me.dakto101.skill.PassiveSkill;
import net.minecraft.server.v1_14_R1.DamageSource;

public class PhanCong extends PassiveSkill implements Cooldown  {
	
	public PhanCong(int level) {
		super("Phản Công", level);
		this.cooldown = 0;
		this.description = "§7Có §f(3 + 2 X Cấp)% xác suất §7phản lại §650%§7 sát thương \n"
				+ "từ đòn đánh thường. "
				+ "\n§7Yêu cầu: §bRìu";
	}
	
	@Override
	public void apply(Event event, int level) {
		if (level <= 0) return;
		EntityDamageByEntityEvent e;
		if (event instanceof EntityDamageByEntityEvent) e = (EntityDamageByEntityEvent) event;
		else return;
		if (!(e.getEntity() instanceof Player)) return;
		if (!(e.getDamager() instanceof LivingEntity)) return;
		if (!e.getCause().equals(DamageCause.ENTITY_ATTACK)) return;
		Player player = (Player) e.getEntity();
		if (!player.getInventory().getItemInMainHand().getType().toString().contains("_AXE")) return;
		//Event
		PlayerCastPassiveSkillEvent eventt = new PlayerCastPassiveSkillEvent(this);
		Bukkit.getServer().getPluginManager().callEvent(eventt);
		if (eventt.isCancelled()) return;
		//Code
		double chance = 0.03 + 0.02 * level;
		if (chance <= Math.random()) return;
		double damage = e.getDamage() * 0.5;
		CraftLivingEntity targett = (CraftLivingEntity) e.getDamager();
		targett.getHandle().damageEntity(DamageSource.mobAttack(targett.getHandle()), (float) damage);
		targett.getWorld().playSound(targett.getLocation(), Sound.ENCHANT_THORNS_HIT, 1, 1);
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
