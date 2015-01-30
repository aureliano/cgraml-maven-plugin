package com.github.aureliano.cgraml.code.builder;

import java.io.File;
import java.util.List;

import com.github.aureliano.cgraml.code.meta.ClassMeta;
import com.github.aureliano.cgraml.code.meta.MethodMeta;
import com.github.aureliano.cgraml.code.meta.Visibility;
import com.github.aureliano.cgraml.helper.CodeBuilderHelper;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;

public class ModelCollectionSchemaInterfaceBuilder implements IBuilder {

	private ClassMeta clazz;
	private static final MethodMeta[] ABSTRACT_METHODS;
	
	static {
		ABSTRACT_METHODS = new MethodMeta[] {
			createGetElementsMethod(),
			createGetSize()
		};
	}
	
	public ModelCollectionSchemaInterfaceBuilder() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ModelCollectionSchemaInterfaceBuilder parse(String pkg, String entity, Object resource) {
		String javaDoc = "Generated by srvraml-maven-plugin.\n\nDefine a type for API collection schema models.";
		
		this.clazz = new ClassMeta()
			.withPackageName(pkg + ".model")
			.withJavaDoc(javaDoc)
			.withClassName(entity);
		
		for (MethodMeta method : ABSTRACT_METHODS) {
			this.clazz.addMethod(method);
		}
		
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ModelCollectionSchemaInterfaceBuilder build() {
		this.buildJavaClass();
		return this;
	}
	
	private void buildJavaClass() {
		try {
			JCodeModel codeModel = new JCodeModel();
			JDefinedClass definedClass = codeModel._class(this.clazz.getCanonicalClassName(), ClassType.INTERFACE);
			definedClass.javadoc().append(this.clazz.getJavaDoc());
			definedClass.generify("T extends IModel");
			
			this.appendClassMethods(codeModel, definedClass);
			
			codeModel.build(new File("src/main/java"));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void appendClassMethods(JCodeModel codeModel, JDefinedClass definedClass) {
		for (MethodMeta method : this.clazz.getMethods()) {
			CodeBuilderHelper.addMethodToClass(codeModel, definedClass, method);
		}
	}

	private static MethodMeta createGetElementsMethod() {
		MethodMeta method = new MethodMeta();
		
		method.setName("getElements");
		method.setVisibility(Visibility.PUBLIC);
		method.setReturnType(List.class.getName());
		method.setGenericReturnType("T");
		
		return method;
	}

	private static MethodMeta createGetSize() {
		MethodMeta method = new MethodMeta();
		
		method.setName("getSize");
		method.setVisibility(Visibility.PUBLIC);
		method.setReturnType(Integer.class.getName());
		
		return method;
	}
	
	public static MethodMeta[] getAbstractMethods() {
		return ABSTRACT_METHODS;
	}
	
	public ClassMeta getClazz() {
		return clazz;
	}
	
	public ModelCollectionSchemaInterfaceBuilder withClazz(ClassMeta clazz) {
		this.clazz = clazz;
		return this;
	}
}