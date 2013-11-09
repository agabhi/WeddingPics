package com.weddingpics.rest.utills;

public enum ImageTypeEnum {
	
	WEDDING(1), COVER(2);

	private Integer value;

	private ImageTypeEnum(Integer value) {
		this.value = value;
	}

	/**
	 * Returns Image Type 
	 * 
	 * @return Integer
	 */
	public Integer getValue() {
		return value;
	}

}
