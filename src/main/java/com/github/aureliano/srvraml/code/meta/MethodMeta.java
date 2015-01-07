package com.github.aureliano.srvraml.code.meta;

import java.util.ArrayList;
import java.util.List;

public class MethodMeta {

	private String name;
	private Class<?> returnType;
	private boolean staticMethod;
	private boolean finalMethod;
	private List<FieldMeta> parameters;
	
	public MethodMeta() {
		this.parameters = new ArrayList<FieldMeta>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<?> type) {
		this.returnType = type;
	}

	public boolean isStaticMethod() {
		return staticMethod;
	}

	public void setStaticMethod(boolean staticMethod) {
		this.staticMethod = staticMethod;
	}

	public boolean isFinalMethod() {
		return finalMethod;
	}

	public void setFinalMethod(boolean finalMethod) {
		this.finalMethod = finalMethod;
	}

	public List<FieldMeta> getParameters() {
		return parameters;
	}

	public void setParameters(List<FieldMeta> parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public String toString() {
		return String.format(
			"[ name => \"%s\", type => %s, staticMethod => %s, finalMethod => %s, parameters => %s ]",
			this.name, this.returnType, this.staticMethod, this.finalMethod, this.parameters
		);
	}
}