package com.github.aureliano.srvraml.code.meta;

public enum Visibility {

	PUBLIC(1),
	PROTECTED(2),
	PRIVATE(4);
	
	private Integer mod;

	private Visibility(Integer mod) {
		this.mod = mod;
	}
	
	public Integer getMod() {
		return this.mod;
	}
}