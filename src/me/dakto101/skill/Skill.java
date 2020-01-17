package me.dakto101.skill;

import java.util.Arrays;
import java.util.List;

import org.bukkit.event.Event;

public abstract class Skill {
	
	protected String skillName;
	protected List<String> description;
	protected int cooldown;
	protected int level;

	protected SkillType skillType;
	
	public Skill(String skillName, int level) {
		this.skillName = skillName;
		this.level = level;
		this.description = Arrays.asList("");
		this.cooldown = 0;
		this.skillType = null;
	}
	
	public String getSkillName() {
		return this.skillName;
	}
	public int getLevel() {
		return this.level;
	}
	public List<String> getDescription() {
		return this.description;
	}
	public int getCooldown() {
		return this.cooldown;
	}
	
	public SkillType getSkillType() {
		return this.skillType;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public abstract void apply(Event e, int level);
	
}
