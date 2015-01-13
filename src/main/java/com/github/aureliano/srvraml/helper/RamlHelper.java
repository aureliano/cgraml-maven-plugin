package com.github.aureliano.srvraml.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.parameter.QueryParameter;
import org.raml.parser.visitor.RamlDocumentBuilder;
import org.yaml.snakeyaml.Yaml;

import com.github.aureliano.srvraml.code.gen.Generator;
import com.github.aureliano.srvraml.code.meta.ActionMeta;
import com.github.aureliano.srvraml.code.meta.FieldMeta;
import com.github.aureliano.srvraml.code.meta.ServiceMeta;

public final class RamlHelper {

	private RamlHelper() {
		super();
	}
	
	public static Raml parseModel(String path) {
		return RamlHelper.parseModel(new File(path));
	}
	
	public static Raml parseModel(File file) {
		String location = file.getPath().replaceFirst(new File("").getAbsolutePath(), "").replaceFirst("^/", "");
		return new RamlDocumentBuilder().build(location);
	}
	
	public static Map<?, ?> parseYaml(String path) {
		return RamlHelper.parseYaml(new File(path));
	}
	
	public static Map<?, ?> parseYaml(File file) {
		String location = file.getPath().replaceFirst(new File("").getAbsolutePath(), "").replaceFirst("^/", "");
		
		try {
			return new Yaml().loadAs(new FileInputStream(location), Map.class);
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static List<ServiceMeta> resourcesToServices(Collection<Resource> resources) {
		List<ServiceMeta> services = new ArrayList<ServiceMeta>();
		for (Resource resource : resources) {
			services.add(resourceToService(resource));
		}
		
		return services;
	}

	public static ServiceMeta resourceToService(Resource resource) {
		Map<String, Map<String, ?>> map = getResourceMapping(resource);
		ServiceMeta service = new ServiceMeta();
		
		service.setUri(resource.getUri());
		
		if (map.get("type") != null) {
			Map<String, Map<String, ?>> type = (Map<String, Map<String, ?>>) map.get("type");
			String key = type.keySet().iterator().next();
			Map<String, String> schemaTypes = (Map<String, String>) type.get(key);
			
			if (StringUtils.isEmpty(schemaTypes.get("collectionSchema"))) {
				service.setType(schemaTypes.get("schema"));
			} else {
				service.setGenericType(schemaTypes.get("schema"));
				service.setType(schemaTypes.get("collectionSchema"));
			}
		}
		
		for (ActionType key : resource.getActions().keySet()) {
			Action action = resource.getAction(key);
			ActionMeta am = new ActionMeta();
			
			am.setMethod(key);
			Map<String, QueryParameter> parameters = action.getQueryParameters();
			for (String paramName : parameters.keySet()) {
				FieldMeta param = new FieldMeta();
				param.setName(paramName);
				param.setType(parameters.get(paramName).getType().name());
				
				am.addParameter(param);
			}
			
			service.addAction(am);
		}
		
		return service;
	}
	
	@SuppressWarnings("unchecked")
	protected static Map<String, Map<String, ?>> getResourceMapping(Resource resource) {
		Map<?, ?> map = Generator.currentRamlMap;
		String[] keys = resource.getUri().split("/");

		for (int i = 0; i < keys.length; i++) {
			if (StringUtils.isEmpty(keys[i])) {
				continue;
			}
			
			map = (Map<?, ?>) map.get("/" + keys[i]);
		}
		
		return (Map<String, Map<String, ?>>) map;
	}
}