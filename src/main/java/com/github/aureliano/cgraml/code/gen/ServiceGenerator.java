package com.github.aureliano.cgraml.code.gen;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.raml.model.Resource;

import com.github.aureliano.cgraml.code.builder.CodeBuilder;
import com.github.aureliano.cgraml.code.builder.ServiceBuilder;
import com.github.aureliano.cgraml.code.meta.ClassMeta;
import com.github.aureliano.cgraml.code.meta.ServiceMeta;
import com.github.aureliano.cgraml.helper.RamlHelper;

public class ServiceGenerator extends AbstractCodeGenerator {

	public ServiceGenerator() {
		super();
	}
	
	@Override
	public void execute() {
		Set<ServiceMeta> services = this.getMappedServices(super.raml.getResources().values());
		if (services.isEmpty()) {
			super.logger.warn("There's not any service/resource mapped. Skipping service generation.");
			return;
		}

		for (ServiceMeta service : services) {			
			try { 
				ServiceBuilder builder = this.createServiceBuilder(service).build();
				ClassMeta clazz = builder.getClazz();

				super.logger.info("Generated class: " + clazz.getCanonicalClassName());
				super.logger.debug(clazz.toString());
			} catch (IllegalArgumentException ex) {
				super.logger.warn(ex.getMessage());
			}
		}
	}
	
	private Set<ServiceMeta> getMappedServices(Collection<Resource> resources) {
		Set<ServiceMeta> services = new HashSet<ServiceMeta>();
		
		for (Resource resource : resources) {
			ServiceMeta service = RamlHelper.resourceToService(resource);
			services.add(service);
			
			if (!resource.getResources().isEmpty()) {
				service.setNextServices(RamlHelper.resourcesToServices(resource.getResources().values()));
				services.addAll(this.getMappedServices(resource.getResources().values()));
			}
		}
		
		return services;
	}
	
	private ServiceBuilder createServiceBuilder(ServiceMeta service) {
		return CodeBuilder.create(GeneratorType.SERVICE).parse(super.basePackageName + ".gen", service.getUri(), service);
	}
}