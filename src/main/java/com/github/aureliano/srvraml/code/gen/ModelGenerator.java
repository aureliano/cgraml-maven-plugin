package com.github.aureliano.srvraml.code.gen;

import java.util.List;
import java.util.Map;

import com.github.aureliano.srvraml.code.builder.CodeBuilder;
import com.github.aureliano.srvraml.code.builder.ModelBuilder;
import com.github.aureliano.srvraml.code.meta.ClassMeta;

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
		
		for (Map<String, String> schema : schemas) {
			String entity = schema.keySet().iterator().next();
			String json = schema.values().iterator().next();
			
			ModelBuilder builder = this.createModelBuilder(entity, json).build();
			ClassMeta clazz = builder.getClazz();
			
			super.logger.info("Generated class: " + clazz.getPackageName() + "." + clazz.getClassName());
		}
	}
	
	private ModelBuilder createModelBuilder(String entity, String json) {
		return CodeBuilder.create(GeneratorType.MODEL).parse(super.basePackageName, entity, json);
	}

	@Override
	public String outputPath() {
		return null;
	}
}