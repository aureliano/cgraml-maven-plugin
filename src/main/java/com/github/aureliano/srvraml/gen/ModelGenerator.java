package com.github.aureliano.srvraml.gen;

import java.util.List;
import java.util.Map;

public class ModelGenerator extends AbstractCodeGenerator {

	public ModelGenerator() {
		super();
	}
	
	public void execute() {
		List<Map<String, String>> schemas = super.raml.getSchemas();
		if (schemas.isEmpty()) {
			super.logger.warn("There isn't any schema mapped. Skipping model generation.");
			return;
		}
		
		throw new UnsupportedOperationException("Model generator not implemented yet");
	}

	@Override
	public String outputPath() {
		return null;
	}
}