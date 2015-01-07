package com.github.aureliano.srvraml.code.meta;

import java.util.ArrayList;
import java.util.List;

public class MethodMeta {

	private String name;
	private Class<?> returnType;
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

	public Class<?> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<?> type) {
		this.returnType = type;
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
		String t = (this.returnType == null) ? "" : this.returnType.getName();
		return String.format(
			"[ name => \"%s\", type => %s, visibility => %s, staticMethod => %s, finalMethod => %s, parameters => %s ]",
			this.name, t, this.visibility, this.staticMethod, this.finalMethod, this.parameters
		);
	}
}