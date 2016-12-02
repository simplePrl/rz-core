package com.rz.core.practice.model;

import lombok.Data;

@Data
public class MonitorDto extends DtoBase {
	@TagAnnotation(value = "TagAnnotation")
	private String name;
	private int age;
	private MapDto map;
}