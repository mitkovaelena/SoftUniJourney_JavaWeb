package org.softuni.residentEvil.utils.enums;

public enum MagnitudeEnum {
    LOW("Low"), MEDIUM("Medium"), HIGH("High");
	
	private String name;

	private MagnitudeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
}