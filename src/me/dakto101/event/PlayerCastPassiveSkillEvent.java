package me.dakto101.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import me.dakto101.skill.PassiveSkill;

public final class PlayerCastPassiveSkillEvent extends PlayerCastSkillEvent implements Cancellable{
    public PlayerCastPassiveSkillEvent(PassiveSkill skill) {
		super(skill);
	}

	private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    

	@Override
	public PassiveSkill getSkill() {
		return (PassiveSkill) this.skill;
	}

}
