package com.rz.core.practice.model;

import lombok.Data;

@Data
public class MonitorDto extends DtoBase {
    private static final long serialVersionUID = 1L;
    
    @TagAnnotation(value = "TagAnnotation")
	private String name;
	private int age;
	private MapDto map;
}