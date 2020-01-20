package me.dakto101.event;

import org.bukkit.event.HandlerList;

import me.dakto101.playerclass.PlayerClass;

public final class PlayerClassLevelChangeEvent extends PlayerClassEvent {
    public PlayerClassLevelChangeEvent(PlayerClass playerClass, int levelAdd) {
    	super(playerClass);
    	this.levelAdd = levelAdd;
	}

	private static final HandlerList handlers = new HandlerList();
    private int levelAdd;

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public int getLevelAdd() {
    	return this.levelAdd;
    }
    public void setLevelAdd(int levelAdd) {
    	this.levelAdd = levelAdd;
    }
}
