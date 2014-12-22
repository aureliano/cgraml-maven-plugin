package com.github.aureliano.srvraml.gen;

public final class GeneratorFactory {

	public static IGenerator createGenerator(GeneratorType type) {
		if (type == null) {
			return null;
		}
		
		switch (type) {
			case WEB_XML : return new WebXmlGenerator();
			default : return null;
		}
	}
}