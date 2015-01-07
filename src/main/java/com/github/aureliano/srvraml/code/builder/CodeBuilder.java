package com.github.aureliano.srvraml.code.builder;

import com.github.aureliano.srvraml.code.gen.GeneratorType;

public final class CodeBuilder {

	private CodeBuilder() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IBuilder> T create(GeneratorType type) {
		if (type == null) {
			return null;
		}
		
		switch (type) {
			case MODEL : return (T) new ModelBuilder();
			default : return null;
		}
	}
}