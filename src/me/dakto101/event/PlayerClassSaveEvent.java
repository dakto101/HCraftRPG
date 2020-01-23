package me.dakto101.event;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import me.dakto101.playerclass.PlayerClass;

public final class PlayerClassSaveEvent extends PlayerClassEvent {
    private static final HandlerList handlers = new HandlerList();
	private OfflinePlayer player;
    public PlayerClassSaveEvent(PlayerClass playerClass, OfflinePlayer player2) {
    	super(playerClass);
    	this.player = player2;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public OfflinePlayer getPlayer() {
    	return this.player;
    }

}
