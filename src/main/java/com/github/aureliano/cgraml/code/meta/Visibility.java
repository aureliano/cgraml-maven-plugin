package com.github.aureliano.cgraml.code.meta;

import com.sun.codemodel.JMod;

public enum Visibility {

	PUBLIC(JMod.PUBLIC),
	PROTECTED(JMod.PROTECTED),
	PRIVATE(JMod.PRIVATE);
	
	private Integer mod;

	private Visibility(Integer mod) {
		this.mod = mod;
	}
	
	public Integer getMod() {
		return this.mod;
	}
}