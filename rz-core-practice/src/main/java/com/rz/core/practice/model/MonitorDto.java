package com.rz.core.practice.model;

import lombok.Data;

@Data
public class MonitorDto extends DtoBase {
	@TagAnnotation(value = "TagAnnotation")
	public String name;
	private int age;

	public MonitorDto() {
	}
}