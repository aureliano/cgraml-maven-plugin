package com.github.aureliano.cgraml.code.meta;

import java.util.ArrayList;
import java.util.List;

import org.raml.model.ActionType;

public class ActionMeta {

	private ActionType method;
	private List<FieldMeta> parameters;
	private boolean typedCollection;
	
	public ActionMeta() {
		this.parameters = new ArrayList<FieldMeta>();
	}

	public ActionType getMethod() {
		return method;
	}

	public void setMethod(ActionType method) {
		this.method = method;
	}

	public List<FieldMeta> getParameters() {
		return parameters;
	}

	public void setParameters(List<FieldMeta> parameters) {
		this.parameters = parameters;
	}
	
	public ActionMeta addParameter(FieldMeta param) {
		this.parameters.add(param);
		return this;
	}
	
	public void setTypedCollection(boolean typedCollection) {
		this.typedCollection = typedCollection;
	}
	
	public boolean isTypedCollection() {
		return typedCollection;
	}
}