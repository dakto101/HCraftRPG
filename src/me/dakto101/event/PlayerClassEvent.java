package me.dakto101.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.dakto101.playerclass.PlayerClass;

public abstract class PlayerClassEvent extends Event{
    protected static final HandlerList handlers = new HandlerList();
	protected PlayerClass playerClass;
    public PlayerClassEvent(PlayerClass playerClass) {
    	this.playerClass = playerClass;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public PlayerClass getPlayerClass() {
    	return this.playerClass;
    }


}
