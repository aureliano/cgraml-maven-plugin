package com.github.aureliano.cgraml.code.gen;

import com.github.aureliano.cgraml.code.builder.CodeBuilder;
import com.github.aureliano.cgraml.code.builder.ModelSchemaInterfaceBuilder;
import com.github.aureliano.cgraml.code.meta.ClassMeta;

public class ModelSchemaInterfaceGenerator extends AbstractCodeGenerator {

	public ModelSchemaInterfaceGenerator() {
		super();
	}

	@Override
	public void execute() {
		ModelSchemaInterfaceBuilder builder = CodeBuilder.create(GeneratorType.MODEL_SCHEMA_INTERFACE)
			.parse(super.basePackageName + ".gen", "IModel", null)
			.build();
		
		ClassMeta clazz = builder.getClazz();

		super.logger.info("Generated class: " + clazz.getCanonicalClassName());
		super.logger.debug(clazz.toString());
	}
}