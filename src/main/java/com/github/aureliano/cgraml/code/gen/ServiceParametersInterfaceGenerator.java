package com.github.aureliano.cgraml.code.gen;

import com.github.aureliano.cgraml.code.builder.CodeBuilder;
import com.github.aureliano.cgraml.code.builder.ServiceParametersInterfaceBuilder;
import com.github.aureliano.cgraml.code.meta.ClassMeta;

public class ServiceParametersInterfaceGenerator extends AbstractCodeGenerator {

	public static final String CLASS_NAME = "IServiceParameters";
	public static String PACKAGE_NAME = null;

	public ServiceParametersInterfaceGenerator() {
		super();
	}

	@Override
	public void execute() {
		PACKAGE_NAME = super.basePackageName + ".gen.parameters";
		ServiceParametersInterfaceBuilder builder = CodeBuilder
				.create(GeneratorType.SERVICE_PARAMETERS_INTERFACE)
				.parse(PACKAGE_NAME, CLASS_NAME, null)
				.build();
			
			ClassMeta clazz = builder.getClazz();

			super.logger.info("Generated class: " + clazz.getCanonicalClassName());
			super.logger.debug(clazz.toString());
	}
}