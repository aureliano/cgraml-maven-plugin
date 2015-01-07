package com.github.aureliano.srvraml.code.gen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ModelGenerator extends AbstractCodeGenerator {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	public ModelGenerator() {
		super();
	}
	
	public void execute() {
		List<Map<String, String>> schemas = super.raml.getSchemas();
		if (schemas.isEmpty()) {
			super.logger.warn("There isn't any schema mapped. Skipping model generation.");
			return;
		}
		
		for (Map<String, String> schema : schemas) {
			String json = schema.values().iterator().next();
			Map<?,?> map = this.parseJsonString(json);
			
			System.out.println(map.get("properties"));
		}
		
		throw new UnsupportedOperationException("Model generator not implemented yet");
	}
	
	private Map<?, ?> parseJsonString(String json) {
		try {
			return OBJECT_MAPPER.readValue(json, HashMap.class);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public String outputPath() {
		return null;
	}
}