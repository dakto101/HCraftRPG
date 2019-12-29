package me.dakto101.skill.dausi;

import java.util.Hashtable;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.dakto101.event.PlayerCastActiveSkillEvent;
import me.dakto101.skill.ActiveSkill;
import me.dakto101.skill.Cooldown;
import net.minecraft.server.v1_14_R1.DamageSource;

public class GiaoChien extends ActiveSkill implements Cooldown  {
	
	public static Hashtable<String, Long> timer = new Hashtable<String, Long>();
	private static short b = 0;

	public GiaoChien(int level) {
		super("Giao Chiến", level);
		this.cooldown = 15;
		this.description = "§7Nhận hiệu ứng §bSức mạnh II§7 trong 4 giây, gây hiệu ứng §bSuy yếu II §7và §bChậm II\n"		
		+ "§7cho kẻ địch trong 4 giây và gây §6(8 + 2 X cấp) §7sát thương vật lý. (" + cooldown + "s hồi)"
				+ "\n§7Yêu cầu: §bRìu";
		
	}
	
	@Override
	public void apply(Event event, int level) {
		if (level <= 0) return;
		//Check
		PlayerInteractEntityEvent e;
		if (event instanceof PlayerInteractEntityEvent) e = (PlayerInteractEntityEvent) event;
		else return;
		if (!(e.getRightClicked() instanceof LivingEntity)) return;
		Player entity = e.getPlayer();
		//Check rìu
		if (!entity.getInventory().getItemInMainHand().getType().toString().contains("_AXE")) return;
		LivingEntity target = (LivingEntity) e.getRightClicked();
		//Cooldown
		if (timer.get(entity.getName()) == null) timer.put(entity.getName(), 0L);
		if (onCooldown(entity.getName())) {
			b++;
			if (b == 4) {
				entity.sendMessage("§6" + this.skillName + "§7 - Hồi chiêu còn§6 " + getRemainingCooldown(entity.getName())/1000 + "§7 giây.");
				b = 0;
			}
			return;
		}
		//Event
		PlayerCastActiveSkillEvent eventt = new PlayerCastActiveSkillEvent(this);
		Bukkit.getServer().getPluginManager().callEvent(eventt);
		if (eventt.isCancelled()) return;
		//Kiểm tra mục tiêu là vật tấn công được.
		EntityDamageByEntityEvent testEvent = new EntityDamageByEntityEvent(entity, target, DamageCause.ENTITY_ATTACK, 10);
		Bukkit.getServer().getPluginManager().callEvent(testEvent);
		if (testEvent.isCancelled()) {
			e.getPlayer().sendMessage("Không thể tấn công mục tiêu này.");
			return;
		}
		//Code
		float damage = 8 + level * 2;
		entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 80, 1, true, true, true));
		target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 80, 1, true, true, true));
		target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 1, true, true, true));
		CraftLivingEntity targett = (CraftLivingEntity) target;
		targett.getHandle().damageEntity(DamageSource.mobAttack(targett.getHandle()), damage);
		target.setVelocity(entity.getEyeLocation().getDirection().multiply(-1));
		targett.getWorld().playSound(targett.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
		targett.getWorld().spawnParticle(Particle.SMOKE_LARGE, targett.getLocation(), 20);
		
		entity.sendMessage("§aKỹ năng §b" + this.skillName + "§a gây §6" + damage + "§a sát thương vật lý lên mục tiêu.");
		target.sendMessage("§aKẻ địch vừa dùng kỹ năng §b" + this.skillName + "§a lên bạn, gây §6" + damage + "§a sát thương vật lý.");
		//
		startCooldown(entity.getName());
	}

	@Override
	public void startCooldown(Object playerName) {
		if (GiaoChien.timer.size() > 200) GiaoChien.timer.clear();
		GiaoChien.timer.put(playerName.toString(), System.currentTimeMillis());
	}

	@Override
	public boolean onCooldown(Object playerName) {
		return (System.currentTimeMillis() - timer.get(playerName) > cooldown * 1000) ? false : true; 
	}

	@Override
	public long getRemainingCooldown(Object playerName) {
		return GiaoChien.timer.get(playerName) + cooldown * 1000 - System.currentTimeMillis();
	}


}
