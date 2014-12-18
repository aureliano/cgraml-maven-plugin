package com.github.aureliano.srvraml.helper;

import java.io.File;
import java.io.FileNotFoundException;

public final class ValidationHelper {

	private ValidationHelper() {
		super();
	}
	
	public static void validateRamlFilePath(File ramlFile) throws Exception {
		if (ramlFile == null) {
			throw new FileNotFoundException("A Raml file path is required for execution.");
		}
		
		if (!ramlFile.exists()) {
			throw new FileNotFoundException("Raml file " + ramlFile.getPath() + " does not exist.");
		}
		
		if (ramlFile.isDirectory()) {
			throw new RuntimeException(ramlFile.getPath() + " is a directory.");
		}
		
		if (!ramlFile.getName().endsWith(".raml")) {
			throw new RuntimeException("Raml file must have suffix '.raml'. Found => .../" + ramlFile.getName());
		}
	}
}