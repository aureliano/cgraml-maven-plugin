package com.github.aureliano.srvraml.code.meta;

import java.util.ArrayList;
import java.util.List;

public class MethodMeta {

	private String name;
	private String returnType;
	private String genericReturnType;
	private Visibility visibility;
	private boolean staticMethod;
	private boolean finalMethod;
	private List<FieldMeta> parameters;
	private String body;
	
	public MethodMeta() {
		this.parameters = new ArrayList<FieldMeta>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String type) {
		this.returnType = type;
	}

	public String getGenericReturnType() {
		return genericReturnType;
	}

	public void setGenericReturnType(String genericreturnType) {
		this.genericReturnType = genericreturnType;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
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
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return String.format(
			"[ name => \"%s\", returnType => %s, genericReturnType => %s, visibility => %s, staticMethod => %s, finalMethod => %s, parameters => %s ]",
			this.name, this.returnType, this.genericReturnType, this.visibility, this.staticMethod, this.finalMethod, this.parameters
		);
	}
}