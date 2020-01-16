package me.dakto101.skill;

import java.util.ArrayList;
import java.util.List;

public class SkillAPI {
	
	private static List<Skill> skillList = new ArrayList<Skill>();
	
	public static List<Skill> getAllSkill() {
		return skillList;
	}
	
	public static List<ActiveSkill> getAllActiveSkill() {
		List<ActiveSkill> activeList = new ArrayList<ActiveSkill>();
		for (Skill s : skillList) {
			if (s instanceof ActiveSkill) activeList.add((ActiveSkill) s);
		}
		return activeList;
	}
	
	public static List<PassiveSkill> getAllPassiveSkill() {
		List<PassiveSkill> passiveList = new ArrayList<PassiveSkill>();
		for (Skill s : skillList) {
			if (s instanceof PassiveSkill) passiveList.add((PassiveSkill) s);
		}
		return passiveList;
	}
	
	public static void registerSkill(Skill... skill) {
		for (Skill s : skill) skillList.add(s);
	}
	
	public static Skill getSkill(String skillName) {
		Skill skill = null;
		for (Skill s : skillList) {
			if (s.getSkillName().equals(skillName)) {
				skill = s;
				break;
			}
		}
		return skill;
	}
	
}
