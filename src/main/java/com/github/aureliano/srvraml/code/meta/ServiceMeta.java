package com.github.aureliano.srvraml.code.meta;

import java.util.ArrayList;
import java.util.List;

public class ServiceMeta {

	private String uri;
	private String type;
	private String genericType;
	private List<ActionMeta> actions;
	
	public ServiceMeta() {
		this.setActions(new ArrayList<ActionMeta>());
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGenericType() {
		return genericType;
	}

	public void setGenericType(String genericType) {
		this.genericType = genericType;
	}

	public List<ActionMeta> getActions() {
		return actions;
	}

	public void setActions(List<ActionMeta> actions) {
		this.actions = actions;
	}
	
	public ServiceMeta addAction(ActionMeta action) {
		this.actions.add(action);
		return this;
	}
}