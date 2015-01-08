package com.github.aureliano.srvraml.helper;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.github.aureliano.srvraml.code.meta.FieldMeta;
import com.github.aureliano.srvraml.code.meta.MethodMeta;
import com.github.aureliano.srvraml.code.meta.Visibility;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;

public final class CodeBuilderHelper {

	private CodeBuilderHelper() {
		super();
	}
	
	public static String getJavaType(String jsonType) {
		if (StringUtils.isEmpty(jsonType)) {
			return null;
		} else if ("string".equalsIgnoreCase(jsonType)) {
			return String.class.getName();
		} else if ("number".equalsIgnoreCase(jsonType)) {
			return Double.class.getName();
		} else if ("integer".equalsIgnoreCase(jsonType)) {
			return Integer.class.getName();
		} else if ("boolean".equalsIgnoreCase(jsonType)) {
			return Boolean.class.getName();
		} else if ("value".equalsIgnoreCase(jsonType)) {
			return Object.class.getName();
		} else if ("array".equalsIgnoreCase(jsonType)) {
			return List.class.getName();
		} else if ("object".equalsIgnoreCase(jsonType)) {
			return Object.class.getName();
		} else {		
			return StringUtils.capitalize(jsonType);
		}
	}
	
	public static Class<?> stringToClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException ex) {
			return null;
		}
	}
	
	public static JFieldVar addAttributeToClass(JCodeModel codeModel, JDefinedClass definedClass, FieldMeta field) {
		Object type = createTypeScaffold(codeModel, definedClass, field);
		if (type instanceof Class<?>) {
			return definedClass.field(field.getVisibility().getMod(), (Class<?>) type, field.getName());
		} else {
			return definedClass.field(field.getVisibility().getMod(), (JClass) type, field.getName());
		}
	}
	
	public static JMethod addMethodToClass(JCodeModel codeModel, JDefinedClass definedClass, MethodMeta method) {
		JMethod jm = null;
		Object type = null;
		
		if (method.getReturnType() == null) {
			jm = definedClass.method(method.getVisibility().getMod(), codeModel.VOID, method.getName());
		} else {
			type = createTypeScaffold(codeModel, definedClass, method);
			if (type instanceof Class<?>) {
				jm = definedClass.method(method.getVisibility().getMod(), (Class<?>) type, method.getName());
			} else {
				jm = definedClass.method(method.getVisibility().getMod(), (JClass) type, method.getName());
			}
		}
		
		for (FieldMeta param : method.getParameters()) {
			type = createTypeScaffold(codeModel, definedClass, param);
			if (type instanceof Class<?>) {
				jm.param((Class<?>) type, param.getName());
			} else {
				jm.param((JClass) type, param.getName());
			}
		}
		
		jm.body().directStatement(method.getBody());
		return jm;
	}
	
	private static Object createTypeScaffold(JCodeModel codeModel, JDefinedClass definedClass, FieldMeta field) {
		if (field == null) {
			return null;
		}
		
		Class<?> attrType = CodeBuilderHelper.stringToClass(field.getType());
		JClass jattrType = null;
		
		if (attrType == null) {
			jattrType = codeModel.ref(field.getType());
		}
		
		if (field.getGenericType() == null) {
			return (attrType != null) ? attrType : jattrType;
		} else {
			attrType = CodeBuilderHelper.stringToClass(field.getGenericType());
			jattrType = null;
			JClass jfield = null;
			
			if (attrType == null) {
				jattrType = codeModel.ref(field.getGenericType());
			}
			
			if (attrType != null) {
				jfield = codeModel.ref(List.class).narrow(attrType);
			} else {
				jfield = codeModel.ref(List.class).narrow(jattrType);
			}
			
			return jfield;
		}
	}
	
	private static Object createTypeScaffold(JCodeModel codeModel, JDefinedClass definedClass, MethodMeta method) {
		FieldMeta field = new FieldMeta();
		field.setType(method.getReturnType());
		field.setGenericType(method.getGenericReturnType());
		
		return createTypeScaffold(codeModel, definedClass, field);
	}
	
	public static MethodMeta createSetterMethod(FieldMeta attribute) {
		if (attribute == null) {
			return null;
		}
		
		MethodMeta m = new MethodMeta();
		
		m.setName("set" + StringUtils.capitalize(attribute.getName()));
		m.setVisibility(Visibility.PUBLIC);
		m.setStaticMethod(false);
		m.setFinalMethod(false);
		m.setReturnType(null);
		m.setParameters(Arrays.asList(attribute));
		m.setBody(String.format("this.%s = %s;", attribute.getName(), attribute.getName()));
		
		return m;
	}
	
	public static MethodMeta createGetterMethod(FieldMeta attribute) {
		if (attribute == null) {
			return null;
		}
		
		MethodMeta m = new MethodMeta();
		
		m.setName("get" + StringUtils.capitalize(attribute.getName()));
		m.setVisibility(Visibility.PUBLIC);
		m.setStaticMethod(false);
		m.setFinalMethod(false);
		m.setReturnType(attribute.getType());
		m.setGenericReturnType(attribute.getGenericType());
		m.setBody(String.format("return this.%s;", attribute.getName()));
		
		return m;
	}
}