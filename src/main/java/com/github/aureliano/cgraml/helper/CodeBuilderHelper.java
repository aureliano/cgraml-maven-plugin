package com.github.aureliano.cgraml.helper;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.raml.model.ActionType;

import com.github.aureliano.cgraml.code.meta.ActionMeta;
import com.github.aureliano.cgraml.code.meta.FieldMeta;
import com.github.aureliano.cgraml.code.meta.MethodMeta;
import com.github.aureliano.cgraml.code.meta.ServiceMeta;
import com.github.aureliano.cgraml.code.meta.Visibility;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

public final class CodeBuilderHelper {

	private static final String TAB = "    ";
	
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
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static JFieldVar addAttributeToClass(JCodeModel codeModel, JDefinedClass definedClass, FieldMeta field) {
		Object type = createTypeScaffold(codeModel, definedClass, field);
		int fieldMod = attributeMod(field);
		
		if (type instanceof Class<?>) {
			return definedClass.field(fieldMod, (Class<?>) type, field.getName());
		} else {
			return definedClass.field(fieldMod, (JClass) type, field.getName());
		}
	}
	
	public static JMethod addMethodToClass(JCodeModel codeModel, JDefinedClass definedClass, MethodMeta method) {
		JMethod jm = null;
		Object type = null;
		int methodMod = methodMod(method);		
		
		if (method.getReturnType() == null) {
			jm = definedClass.method(methodMod, codeModel.VOID, method.getName());
		} else {
			type = createTypeScaffold(codeModel, definedClass, method);
			if (type instanceof Class<?>) {
				jm = definedClass.method(methodMod, (Class<?>) type, method.getName());
			} else {
				jm = definedClass.method(methodMod, (JClass) type, method.getName());
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
	
	private static int attributeMod(FieldMeta attribute) {
		return getMod(attribute.getVisibility(), attribute.isStaticField(), attribute.isFinalField());
	}
	
	private static int methodMod(MethodMeta method) {
		return getMod(method.getVisibility(), method.isStaticMethod(), method.isFinalMethod());
	}
	
	private static int getMod(Visibility visibility, boolean ehStatic, boolean ehFinal) {
		int mod = visibility.getMod();
		if (ehStatic) {
			mod = mod | JMod.STATIC;
		}
		
		if (ehFinal) {
			mod = mod | JMod.FINAL;
		}
		
		return mod;
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
	
	public static MethodMeta createBuilderMethod(String className, FieldMeta attribute) {
		if (attribute == null) {
			return null;
		}
		
		MethodMeta m = new MethodMeta();
		
		m.setName("with" + StringUtils.capitalize(attribute.getName()));
		m.setVisibility(Visibility.PUBLIC);
		m.setStaticMethod(false);
		m.setFinalMethod(false);
		m.setReturnType(className);
		m.setParameters(Arrays.asList(attribute));
		m.setBody(new StringBuilder()
			.append(String.format("this.%s = %s;", attribute.getName(), attribute.getName()))
			.append("\n")
			.append(tabulation(2))
			.append("return this;")
			.toString()
		);
		
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
	
	public static String sanitizedTypeName(String entity) {
		if (StringUtils.isEmpty(entity)) {
			return null;
		}
		
		entity = entity.replaceAll("[{}]*", "");
		return StringUtils.capitalize(entity.substring(entity.lastIndexOf("/") + 1));
	}

	public static ActionMeta getGetAction(ServiceMeta service) {
		for (ActionMeta action : service.getActions()) {
			if (ActionType.GET.equals(action.getMethod())) {
				return action;
			}
		}
		
		return null;
	}
	
	public static String tabulation(int times) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < times; i++) {
			b.append(TAB);
		}
		
		return b.toString();
	}
}