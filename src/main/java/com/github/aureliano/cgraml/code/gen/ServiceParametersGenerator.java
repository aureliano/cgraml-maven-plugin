package com.github.aureliano.cgraml.code.gen;

import java.util.Set;

import com.github.aureliano.cgraml.code.builder.CodeBuilder;
import com.github.aureliano.cgraml.code.builder.ServiceParametersBuilder;
import com.github.aureliano.cgraml.code.meta.ClassMeta;
import com.github.aureliano.cgraml.code.meta.ServiceMeta;
import com.github.aureliano.cgraml.helper.GeneratorHelper;

public class ServiceParametersGenerator  extends AbstractCodeGenerator {

	public ServiceParametersGenerator() {
		super();
	}
	
	@Override
	public void execute() {
		Set<ServiceMeta> services = GeneratorHelper.getMappedServices(super.raml.getResources().values());
		if (services.isEmpty()) {
			super.logger.warn("There's not any service/resource mapped. Skipping service parameters generation.");
			return;
		}

		for (ServiceMeta service : services) {			
			try { 
				ServiceParametersBuilder builder = this.createServiceBuilder(service).build();
				ClassMeta clazz = builder.getClazz();

				super.logger.info("Generated class: " + clazz.getCanonicalClassName());
				super.logger.debug(clazz.toString());
			} catch (IllegalArgumentException ex) {
				super.logger.warn(ex.getMessage());
			}
		}
	}
	
	private ServiceParametersBuilder createServiceBuilder(ServiceMeta service) {
		return CodeBuilder.create(GeneratorType.SERVICE_PARAMETERS).parse(super.basePackageName + ".gen", service.getUri(), service);
	}
}