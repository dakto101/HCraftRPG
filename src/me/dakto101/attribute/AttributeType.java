package me.dakto101.attribute;

public enum AttributeType {
	
	DAMAGE_INCREASE("Tăng sát thương vật lý gây ra"),
	DAMAGE_INCREASE_PERCENT("Tăng sát thương vật lý gây ra (phần trăm)"),
	DAMAGE_REDUCTION("Giảm sát thương vật lý nhận vào"),
	DAMAGE_REDUCTION_PERCENT("Giảm sát thương vật lý nhận vào (phần trăm)"),
	MAGIC_INCREASE("Tăng sát thương phép gây ra"),
	MAGIC_INCREASE_PERCENT("Tăng sát thương phép gây ra (phần trăm)"),
	MAGIC_REDUCTION("Giảm sát thương phép nhận vào"),
	MAGIC_REDUCTION_PERCENT("Giảm sát thương phép nhận vào (phần trăm)"),
	MAX_HEALTH("Máu tối đa");
	private String name;
	
	AttributeType(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
}
