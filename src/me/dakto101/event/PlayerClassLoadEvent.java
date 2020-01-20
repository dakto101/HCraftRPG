package me.dakto101.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import me.dakto101.playerclass.PlayerClass;

public final class PlayerClassLoadEvent extends PlayerClassEvent {
    private static final HandlerList handlers = new HandlerList();
	private Player player;
    public PlayerClassLoadEvent(PlayerClass playerClass, Player p) {
    	super(playerClass);
    	this.player = p;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public Player getPlayer() {
    	return this.player;
    }


}
