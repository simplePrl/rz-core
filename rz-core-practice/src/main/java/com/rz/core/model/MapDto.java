package com.rz.core.model;


public class MapDto extends DtoBase {
	
	private String englishName;
	private boolean result;

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String name) {
		this.englishName = name;
	}
	
	public MapDto(){
		
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
