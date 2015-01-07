package com.github.aureliano.srvraml.helper;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.github.aureliano.srvraml.code.meta.FieldMeta;
import com.github.aureliano.srvraml.code.meta.MethodMeta;

public final class CodeBuilderHelper {

	private CodeBuilderHelper() {
		super();
	}
	
	public static Class<?> getJavaType(String jsonType) {
		if ("string".equalsIgnoreCase(jsonType)) {
			return String.class;
		} else if ("number".equalsIgnoreCase(jsonType)) {
			return Double.class;
		} else if ("integer".equalsIgnoreCase(jsonType)) {
			return Integer.class;
		} else if ("boolean".equalsIgnoreCase(jsonType)) {
			return Boolean.class;
		} else if ("value".equalsIgnoreCase(jsonType)) {
			return Object.class;
		} else if ("array".equalsIgnoreCase(jsonType)) {
			return Object[].class;
		} else if ("object".equalsIgnoreCase(jsonType)) {
			return List.class;
		} else {		
			return null;
		}
	}
	
	public static MethodMeta createSetterMethod(FieldMeta attribute) {
		if (attribute == null) {
			return null;
		}
		
		MethodMeta m = new MethodMeta();
		
		m.setName("set" + StringUtils.capitalize(attribute.getName()));
		m.setStaticMethod(false);
		m.setFinalMethod(false);
		m.setReturnType(null);
		m.setParameters(Arrays.asList(attribute));
		
		return m;
	}
	
	public static MethodMeta createGetterMethod(FieldMeta attribute) {
		if (attribute == null) {
			return null;
		}
		
		MethodMeta m = new MethodMeta();
		
		m.setName("get" + StringUtils.capitalize(attribute.getName()));
		m.setStaticMethod(false);
		m.setFinalMethod(false);
		m.setReturnType(attribute.getType());
		
		return m;
	}
}