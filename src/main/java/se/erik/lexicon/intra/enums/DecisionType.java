package se.erik.lexicon.intra.enums;

import com.fasterxml.jackson.annotation.JsonGetter;

public enum DecisionType {
	
	APL("apl"),FUB("fub"),EDUCATION("education"),NONE("none");
	
	private String name;

	private DecisionType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@JsonGetter
	public String getName() {
		return this.name;
	}
	

}
