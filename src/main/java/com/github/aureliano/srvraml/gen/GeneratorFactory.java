package com.github.aureliano.srvraml.gen;

public final class GeneratorFactory {

	public static ICodeGenerator createGenerator(GeneratorType type) {
		if (type == null) {
			return null;
		}
		
		switch (type) {
			default : return null;
		}
	}
}