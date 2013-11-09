package com.weddingpics.rest.utills;

public enum UserTypeEnum {
	
	BRIDE(1), GROOM(2), PARTNER(3);

	private Integer value;

	private UserTypeEnum(Integer value) {
		this.value = value;
	}

	/**
	 * Returns User Type value
	 * 
	 * @return Integer
	 */
	public Integer getValue() {
		return value;
	}

}
