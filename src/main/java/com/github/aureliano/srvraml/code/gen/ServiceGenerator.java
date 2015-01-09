package com.github.aureliano.srvraml.code.gen;

import java.util.Collection;

import org.raml.model.Resource;

import com.github.aureliano.srvraml.code.builder.CodeBuilder;
import com.github.aureliano.srvraml.code.builder.ServiceBuilder;
import com.github.aureliano.srvraml.code.meta.ClassMeta;

public class ServiceGenerator extends AbstractCodeGenerator {

	public ServiceGenerator() {
		super();
	}
	
	@Override
	public void execute() {
		Collection<Resource> resources = super.raml.getResources().values();;
		if (resources.isEmpty()) {
			super.logger.warn("There's not any service/resource mapped. Skipping service generation.");
			return;
		}
		
		for (Resource resource : resources) {
			ServiceBuilder builder = this.createServiceBuilder(resource).build();
			ClassMeta clazz = builder.getClazz();
			
			super.logger.info("Generated class: " + clazz.getPackageName() + "." + clazz.getClassName());
			super.logger.debug(clazz.toString());
		}
	}
	
	private ServiceBuilder createServiceBuilder(Resource resource) {
		return CodeBuilder.create(GeneratorType.SERVICE).parse(super.basePackageName + ".gen", resource.getUri(), resource);
	}
}