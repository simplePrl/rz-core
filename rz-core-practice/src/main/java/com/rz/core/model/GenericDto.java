package com.rz.core.model;

public class GenericDto<T extends DtoBase> {
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
