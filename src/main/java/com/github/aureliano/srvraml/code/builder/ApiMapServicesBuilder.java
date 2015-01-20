package com.github.aureliano.srvraml.code.builder;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.github.aureliano.srvraml.code.meta.ClassMeta;
import com.github.aureliano.srvraml.code.meta.FieldMeta;
import com.github.aureliano.srvraml.code.meta.MethodMeta;
import com.github.aureliano.srvraml.code.meta.ServiceMeta;
import com.github.aureliano.srvraml.code.meta.Visibility;
import com.github.aureliano.srvraml.helper.CodeBuilderHelper;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;

public class ApiMapServicesBuilder implements IBuilder {

	private ClassMeta clazz;
	private String baseUri;
	
	public ApiMapServicesBuilder() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApiMapServicesBuilder parse(String pkg, String entity, Object resource) {
		ServiceMeta[] resources = (ServiceMeta[]) resource;
		
		this.clazz = new ClassMeta()
			.withPackageName(pkg + ".service")
			.withJavaDoc("Generated by srvraml-maven-plugin.")
			.withClassName(StringUtils.capitalize(entity) + "Service");

		FieldMeta baseUriField = this.addBaseUriField();
		FieldMeta instanceField = this.addInstanceField();
		
		this.clazz.addMethod(CodeBuilderHelper.createGetterMethod(baseUriField));
		this.addInstanceMethod(instanceField);
		
		for (ServiceMeta service : resources) {
			this.addServiceMethod(service);
		}
		
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApiMapServicesBuilder build() {
		this.buildJavaClass();
		return this;
	}
	
	private void buildJavaClass() {
		try {
			JCodeModel codeModel = new JCodeModel();
			JDefinedClass definedClass = codeModel._class(this.clazz.getCanonicalClassName());
			definedClass.javadoc().append(this.clazz.getJavaDoc());
			
			JMethod constructor = definedClass.constructor(Visibility.PRIVATE.getMod());
			constructor.body().directStatement(String.format("this.baseUri = \"%s\";", this.baseUri));
			
			this.appendClassAttributes(codeModel, definedClass);
			this.appendClassMethods(codeModel, definedClass);
			
			codeModel.build(new File("src/main/java"));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void appendClassAttributes(JCodeModel codeModel, JDefinedClass definedClass) {
		for (FieldMeta field : this.clazz.getFields()) {
			CodeBuilderHelper.addAttributeToClass(codeModel, definedClass, field);
		}
	}

	private void appendClassMethods(JCodeModel codeModel, JDefinedClass definedClass) {
		for (MethodMeta method : this.clazz.getMethods()) {
			CodeBuilderHelper.addMethodToClass(codeModel, definedClass, method);
		}
	}

	private void addServiceMethod(ServiceMeta service) {
		MethodMeta method = new MethodMeta();
		String name = CodeBuilderHelper.sanitizedTypeName(service.getUri());
		method.setReturnType(name + "Service");
		
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		method.setName(name);
		method.setVisibility(Visibility.PUBLIC);
		
		method.setBody(String.format("return new %s(\"\");", (StringUtils.capitalize(name) + "Service")));
		this.clazz.addMethod(method);
	}
	
	private FieldMeta addBaseUriField() {
		FieldMeta field = new FieldMeta();
		field.setName("baseUri");
		field.setType(String.class.getName());
		field.setVisibility(Visibility.PRIVATE);
		
		this.clazz.addField(field);
		return field;
	}
	
	private FieldMeta addInstanceField() {
		FieldMeta field = new FieldMeta();
		field.setName("instance");
		field.setType(this.clazz.getClassName());
		field.setVisibility(Visibility.PRIVATE);
		field.setStaticField(true);
		
		this.clazz.addField(field);
		return field;
	}

	private void addInstanceMethod(FieldMeta instanceField) {
		MethodMeta method = new MethodMeta();
		
		method.setName(instanceField.getName());
		method.setReturnType(instanceField.getType());
		method.setVisibility(Visibility.PUBLIC);
		method.setStaticMethod(true);
		method.setFinalMethod(true);
		
		method.setBody(new StringBuilder("if (" + instanceField.getName() + " == null) {")
			.append("\n" + CodeBuilderHelper.tabulation(3))
			.append(instanceField.getName() + " = new " + instanceField.getType() + "();")
			.append("\n" + CodeBuilderHelper.tabulation(2))
			.append("}")
			.append("\n\n" + CodeBuilderHelper.tabulation(2))
			.append("return " + instanceField.getName() + ";")
			.toString());
		
		this.clazz.addMethod(method);
	}
	
	public ClassMeta getClazz() {
		return clazz;
	}
	
	public ApiMapServicesBuilder withClazz(ClassMeta clazz) {
		this.clazz = clazz;
		return this;
	}

	public String getBaseUri() {
		return baseUri;
	}

	public ApiMapServicesBuilder withBaseUri(String baseUri) {
		this.baseUri = baseUri;
		return this;
	}
}