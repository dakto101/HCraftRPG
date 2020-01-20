package me.dakto101.event;

import org.bukkit.event.HandlerList;

import me.dakto101.playerclass.PlayerClass;

public final class PlayerClassXPChangeEvent extends PlayerClassEvent{
    public PlayerClassXPChangeEvent(PlayerClass playerClass, long xpAdd) {
    	super(playerClass);
    	this.xpAdd = xpAdd;
	}

	private static final HandlerList handlers = new HandlerList();
    private long xpAdd;

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public void setXPAdd(long xpAdd) {
    	this.xpAdd = xpAdd;
    }
    public long getXPAdd() {
    	return this.xpAdd;
    }
}
