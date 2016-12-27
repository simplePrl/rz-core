package com.rz.core.practice.model;

import lombok.Data;

@Data
public class MapDto extends DtoBase {
    private static final long serialVersionUID = 1L;
    
    private String englishName;
	private boolean result;
}
