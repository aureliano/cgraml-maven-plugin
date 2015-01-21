package com.github.aureliano.cgraml.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.raml.parser.rule.ValidationResult;
import org.raml.parser.visitor.RamlValidationService;

public final class ValidationHelper {

	private ValidationHelper() {
		super();
	}
	
	public static void validateRamlFilePath(File sourcePath) throws Exception {
		if (sourcePath == null) {
			throw new FileNotFoundException("A source path is required for execution.");
		}
		
		if (!sourcePath.exists()) {
			throw new FileNotFoundException("Source directory " + sourcePath.getPath() + " does not exist.");
		}
		
		if (!sourcePath.isDirectory()) {
			throw new RuntimeException(sourcePath.getPath() + " is not a directory.");
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
	
	public static void validateBasePackageName(String pkg) {
		if (StringUtils.isEmpty(pkg)) {
			throw new RuntimeException("Base package name must be provided.");
		}
	}
}