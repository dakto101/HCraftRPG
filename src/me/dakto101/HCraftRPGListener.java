package me.dakto101;

import org.bukkit.craftbukkit.v1_14_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class HCraftRPGListener implements Listener {
	
	@EventHandler
	public void attack(EntityDamageByEntityEvent e) {
		org.bukkit.craftbukkit.v1_14_R1.entity.CraftLivingEntity entity = (CraftLivingEntity) ((LivingEntity) e.getEntity());
		entity.setNoDamageTicks(2);
	}
	
	@EventHandler
	public void interact(PlayerInteractEvent e) {
		//?
		//??
		CraftPlayer player = (CraftPlayer) e.getPlayer();
		player.setNoDamageTicks(2);
		//((CraftPlayer)e.getPlayer()).getHandle().damageEntity(DamageSource.playerAttack(((CraftPlayer) e.getPlayer()).getHandle()), 5);

	}

}
