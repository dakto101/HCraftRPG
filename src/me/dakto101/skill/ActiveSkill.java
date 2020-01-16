package me.dakto101.skill;

import org.bukkit.event.Event;

public abstract class ActiveSkill extends Skill{
	
	public ActiveSkill(String skillName, int level) {
		super(skillName, level);
		this.skillType = SkillType.ACTIVE;
	}
	
	public void apply(Event e, int level) {}
	
}
