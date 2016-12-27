package com.rz.core.practice.model;

import java.io.Serializable;

public class GenericDto<T extends DtoBase> implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private T _monitor;

	public T getMonitor() {
		return _monitor;
	}

	public void setMonitor(T _monitor) {
		this._monitor = _monitor;
	}
	
	public GenericDto() {

	}
	
	public GenericDto(T monitor) {
		this._monitor = monitor;
	}
}
