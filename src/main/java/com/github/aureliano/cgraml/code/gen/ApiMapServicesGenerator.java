package com.github.aureliano.cgraml.code.gen;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.raml.model.Resource;

import com.github.aureliano.cgraml.code.builder.ApiMapServicesBuilder;
import com.github.aureliano.cgraml.code.builder.CodeBuilder;
import com.github.aureliano.cgraml.code.meta.ClassMeta;
import com.github.aureliano.cgraml.code.meta.ServiceMeta;
import com.github.aureliano.cgraml.helper.RamlHelper;

public class ApiMapServicesGenerator extends AbstractCodeGenerator {

	public ApiMapServicesGenerator() {
		super();
	}
	
	public void execute() {
		Set<ServiceMeta> services = this.getMappedServices(super.raml.getResources().values());
		if (services.isEmpty()) {
			super.logger.warn("There's not any service/resource mapped. Skipping service generation.");
			return;
		}
		
		ApiMapServicesBuilder builder = this.createApiMapServicesBuilder(services.toArray(new ServiceMeta[0])).build();
		ClassMeta clazz = builder.getClazz();

		super.logger.info("Generated class: " + clazz.getCanonicalClassName());
		super.logger.debug(clazz.toString());
	}
	
	private Set<ServiceMeta> getMappedServices(Collection<Resource> resources) {
		Set<ServiceMeta> services = new HashSet<ServiceMeta>();
		
		for (Resource resource : resources) {
			ServiceMeta service = RamlHelper.resourceToService(resource);
			services.add(service);
		}
		
		return services;
	}
	
	private ApiMapServicesBuilder createApiMapServicesBuilder(ServiceMeta[] services) {
		ApiMapServicesBuilder builder = CodeBuilder.create(GeneratorType.API_MAP_SERVICES);
		builder.withBaseUri(super.raml.getBaseUri());
		
		return builder.parse(super.basePackageName + ".gen", "ApiMap", services);
	}
}