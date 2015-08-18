package com.github.aureliano.cgraml.code.meta;

import java.util.ArrayList;
import java.util.List;

public class ServiceMeta {

	private String uri;
	private String type;
	private String resourceType;
	private String genericType;
	private List<ActionMeta> actions;
	private List<ServiceMeta> nextServices;
	
	public ServiceMeta() {
		this.setActions(new ArrayList<ActionMeta>());
		this.setNextServices(new ArrayList<ServiceMeta>());
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

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceType() {
		return resourceType;
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

	public List<ServiceMeta> getNextServices() {
		return nextServices;
	}

	public void setNextServices(List<ServiceMeta> nextServices) {
		this.nextServices = nextServices;
	}

	public ServiceMeta addNextService(ServiceMeta nextService) {
		this.nextServices.add(nextService);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceMeta other = (ServiceMeta) obj;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
}