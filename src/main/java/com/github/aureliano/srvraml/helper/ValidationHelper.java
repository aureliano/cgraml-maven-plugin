package com.github.aureliano.srvraml.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.raml.parser.rule.ValidationResult;
import org.raml.parser.visitor.RamlValidationService;

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
	
	public static void validateRamlFile(File ramlFile) {
		String location = ramlFile.getPath().replaceFirst(new File("").getAbsolutePath(), "").replaceFirst("^/", "");
		List<ValidationResult> result = RamlValidationService.createDefault().validate(location);

		if (result == null || result.isEmpty()) {
			return;
		}
		
		StringBuilder message = new StringBuilder("Raml validation has found " + result.size() + " errors. See details:");
		for (ValidationResult v : result) {
			message
				.append("\n")
				.append(v.getLevel().name() + ": ")
				.append(v.getMessage())
				.append(" (" + ramlFile.getName() + ":" + v.getLine() + ")");
		}
		
		throw new RuntimeException(message.toString());
	}
}