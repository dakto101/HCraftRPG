package me.dakto101.skill;

import org.bukkit.event.Event;

public abstract class PassiveSkill extends Skill {

	public PassiveSkill(String skillName, int level) {
		super(skillName, level);
		this.skillType = SkillType.PASSIVE;
	}
	
	public void apply(Event e, int level) {};
}
