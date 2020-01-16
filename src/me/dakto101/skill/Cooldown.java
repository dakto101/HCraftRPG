package me.dakto101.skill;

public interface Cooldown {

	void startCooldown(Object o);
	long getRemainingCooldown(Object o);
	boolean onCooldown(Object o);
	
}
