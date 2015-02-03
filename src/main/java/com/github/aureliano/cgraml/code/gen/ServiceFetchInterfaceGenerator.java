package com.github.aureliano.cgraml.code.gen;

import com.github.aureliano.cgraml.code.builder.CodeBuilder;
import com.github.aureliano.cgraml.code.builder.ServiceFetchInterfaceBuilder;
import com.github.aureliano.cgraml.code.meta.ClassMeta;

public class ServiceFetchInterfaceGenerator extends AbstractCodeGenerator {

	public static final String CLASS_NAME = "IServiceFetch";

	public ServiceFetchInterfaceGenerator() {
		super();
	}

	@Override
	public void execute() {
		ServiceFetchInterfaceBuilder builder = CodeBuilder
				.create(GeneratorType.SERVICE_FETCH_INTERFACE)
				.parse(super.basePackageName + ".gen.service", CLASS_NAME, null)
				.build();
			
			ClassMeta clazz = builder.getClazz();

			super.logger.info("Generated class: " + clazz.getCanonicalClassName());
			super.logger.debug(clazz.toString());
	}
}