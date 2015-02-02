package com.github.aureliano.cgraml.code.gen;

import com.github.aureliano.cgraml.code.builder.CodeBuilder;
import com.github.aureliano.cgraml.code.builder.ServiceFetchInterfaceBuilder;
import com.github.aureliano.cgraml.code.meta.ClassMeta;

public class ServiceFetchInterfaceGenerator extends AbstractCodeGenerator {

	public static final String CLASS_NAME = "IServiceFetch";
	public static String PACKAGE_NAME = null;

	public ServiceFetchInterfaceGenerator() {
		super();
	}

	@Override
	public void execute() {
		PACKAGE_NAME = super.basePackageName + ".gen.service";
		ServiceFetchInterfaceBuilder builder = CodeBuilder
				.create(GeneratorType.SERVICE_FETCH_INTERFACE)
				.parse(PACKAGE_NAME, CLASS_NAME, null)
				.build();
			
			ClassMeta clazz = builder.getClazz();

			super.logger.info("Generated class: " + clazz.getCanonicalClassName());
			super.logger.debug(clazz.toString());
	}
}