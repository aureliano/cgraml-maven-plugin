package com.github.aureliano.cgraml.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.raml.model.Resource;

import com.github.aureliano.cgraml.code.gen.Generator;
import com.github.aureliano.cgraml.code.meta.ServiceMeta;


public final class GeneratorHelper {

	private GeneratorHelper() {
		super();
	}
	
	public static Object getDataFromCurrentRamlHelper(List<String> chainedKeys) {
		Object value = null;
		
		if ((chainedKeys == null) || (chainedKeys.isEmpty())) {
			return value;
		}
		
		for (String key : chainedKeys) {
			if (value == null) {
				value = Generator.currentRamlMap.get(key);
				if (value == null) {
					return null;
				}
			} else {
				value = ((Map<?, ?>) value).get(key);
				if (value == null) {
					return null;
				}
			}
		}
		
		return value;
	}
	
	public static Set<ServiceMeta> getMappedServices(Collection<Resource> resources) {
		Set<ServiceMeta> services = new HashSet<ServiceMeta>();
		
		for (Resource resource : resources) {
			ServiceMeta service = RamlHelper.resourceToService(resource);
			services.add(service);
			
			if (!resource.getResources().isEmpty()) {
				service.setNextServices(RamlHelper.resourcesToServices(resource.getResources().values()));
				services.addAll(getMappedServices(resource.getResources().values()));
			}
		}
		
		return services;
	}
}