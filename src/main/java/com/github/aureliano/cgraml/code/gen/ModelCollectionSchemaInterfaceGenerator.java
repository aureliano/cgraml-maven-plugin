package com.github.aureliano.cgraml.code.gen;

import com.github.aureliano.cgraml.code.builder.CodeBuilder;
import com.github.aureliano.cgraml.code.builder.ModelCollectionSchemaInterfaceBuilder;
import com.github.aureliano.cgraml.code.meta.ClassMeta;

public class ModelCollectionSchemaInterfaceGenerator extends AbstractCodeGenerator {

	public ModelCollectionSchemaInterfaceGenerator() {
		super();
	}

	@Override
	public void execute() {
		ModelCollectionSchemaInterfaceBuilder builder = CodeBuilder
			.create(GeneratorType.MODEL_COLLECTION_SCHEMA_INTERFACE)
			.parse(super.basePackageName + ".gen", "ICollectionModel", null)
			.build();
		
		ClassMeta clazz = builder.getClazz();

		super.logger.info("Generated class: " + clazz.getCanonicalClassName());
		super.logger.debug(clazz.toString());
	}
}