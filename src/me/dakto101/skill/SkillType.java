package me.dakto101.skill;

public enum SkillType {
	
	ACTIVE("Mobifone"), PASSIVE("Viettel");

	private String name;

	SkillType(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
