package me.dakto101.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.dakto101.playerstat.PlayerStat;

public class PlayerStatSaveEvent extends Event{
    private static final HandlerList handlers = new HandlerList();
	private PlayerStat playerStat;
	private Player player;
    public PlayerStatSaveEvent(PlayerStat playerStat, Player p) {
    	this.playerStat = playerStat;
    	this.player = p;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public PlayerStat getPlayerStat() {
    	return this.playerStat;
    }
    public Player getPlayer() {
    	return this.player;
    }


}
