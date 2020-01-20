package me.dakto101.skill;

public enum SkillType {
	
	ACTIVE("Kích hoạt"), PASSIVE("Bị động");

	private String name;

	SkillType(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
