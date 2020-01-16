package me.dakto101.attribute;

import java.util.HashMap;
import java.util.Map;

public class Attribute {
	private Map<AttributeType, Double> attribute;
	
	public Attribute() {
		
		this.attribute = new HashMap<AttributeType, Double>();
		this.attribute.put(AttributeType.DAMAGE_INCREASE, 0d);
		this.attribute.put(AttributeType.DAMAGE_INCREASE_PERCENT, 0d);
		this.attribute.put(AttributeType.DAMAGE_REDUCTION, 0d);
		this.attribute.put(AttributeType.DAMAGE_REDUCTION_PERCENT, 0d);
		this.attribute.put(AttributeType.MAGIC_INCREASE, 0d);
		this.attribute.put(AttributeType.MAGIC_INCREASE_PERCENT, 0d);
		this.attribute.put(AttributeType.MAGIC_REDUCTION, 0d);
		this.attribute.put(AttributeType.MAGIC_REDUCTION_PERCENT, 0d);
		this.attribute.put(AttributeType.MAX_HEALTH, 0d);
		
	}
	
	public Map<AttributeType, Double> get() {
		return this.attribute;
	}
	
	public void set(HashMap<AttributeType, Double> a) {
		this.attribute = a;
	}
	
	public void set(AttributeType key, Double value) {
		this.attribute.replace(key, value);
	}
	
}
