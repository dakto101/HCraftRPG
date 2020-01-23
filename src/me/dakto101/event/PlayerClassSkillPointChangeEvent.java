package me.dakto101.event;

import org.bukkit.event.HandlerList;

import me.dakto101.playerclass.PlayerClass;

public final class PlayerClassSkillPointChangeEvent extends PlayerClassEvent {
    public PlayerClassSkillPointChangeEvent(PlayerClass playerClass, int skillPointAdd) {
    	super(playerClass);
    	this.skillPointAdd = skillPointAdd;
	}

	private static final HandlerList handlers = new HandlerList();
    private int skillPointAdd;

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public int getSkillPointAdd() {
    	return this.skillPointAdd;
    }
    public void setSkillPointAdd(int skillPointAdd) {
    	this.skillPointAdd = skillPointAdd;
    }
}
