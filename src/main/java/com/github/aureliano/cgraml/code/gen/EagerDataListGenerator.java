package com.github.aureliano.cgraml.code.gen;

import com.github.aureliano.cgraml.code.builder.CodeBuilder;
import com.github.aureliano.cgraml.code.builder.EagerDataListBuilder;
import com.github.aureliano.cgraml.code.meta.ClassMeta;

public class EagerDataListGenerator extends AbstractCodeGenerator {

	public static final String CLASS_NAME = "EagerDataList";
	
	public EagerDataListGenerator() {
		super();
	}

	@Override
	public void execute() {
		EagerDataListBuilder builder = CodeBuilder.create(GeneratorType.EAGER_DATA_LIST)
				.parse(super.basePackageName + ".gen", CLASS_NAME, null)
				.build();
			
			ClassMeta clazz = builder.getClazz();

			super.logger.info("Generated class: " + clazz.getCanonicalClassName());
			super.logger.debug(clazz.toString());
	}
}