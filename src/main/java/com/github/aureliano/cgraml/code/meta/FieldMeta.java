package com.github.aureliano.cgraml.code.meta;

import java.util.Map;

import com.github.aureliano.cgraml.helper.CodeBuilderHelper;

public class FieldMeta {

	private String name;
	private String type;
	private String genericType;
	private Visibility visibility;
	private boolean staticField;
	private boolean finalField;
	private String initVaule;
	private String defaultValue;
	
	public FieldMeta() {
		super();
	}
	
	public static FieldMeta parse(Map<String, ?> data) {
		if (data == null) {
			return null;
		}
		
		FieldMeta f = new FieldMeta();
		
		f.setName(data.get("name").toString());
		
		if (data.get("type") != null) {
			f.setType(CodeBuilderHelper.getJavaType(data.get("type").toString()));
		} else {
			if (data.get("$ref") != null) {
				f.setType(CodeBuilderHelper.getJavaType(data.get("$ref").toString()));
			} else {
				f.setType(Map.class.getName());
			}
		}
		
		if (data.get("items") != null) {
			Map<String, String> items = (Map<String, String>) data.get("items");
			f.setGenericType(CodeBuilderHelper.getJavaType(items.get("$ref")));
		}
		
		if (data.get("visibility") == null) {
			f.setVisibility(Visibility.PRIVATE);
		} else {
			f.setVisibility(Visibility.valueOf(data.get("visibility").toString().toUpperCase()));
		}
		
		return f;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	
	public boolean isStaticField() {
		return staticField;
	}

	public void setStaticField(boolean staticField) {
		this.staticField = staticField;
	}

	public boolean isFinalField() {
		return finalField;
	}

	public void setFinalField(boolean finalField) {
		this.finalField = finalField;
	}
	
	public String getInitValue() {
		return initVaule;
	}
	
	public void setInitValue(String initValue) {
		this.initVaule = initValue;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	@Override
	public FieldMeta clone() {
		FieldMeta f = new FieldMeta();
		
		f.setFinalField(this.finalField);
		f.setGenericType(this.genericType);
		f.setName(this.name);
		f.setStaticField(this.staticField);
		f.setType(this.type);
		f.setVisibility(this.visibility);
		f.setInitValue(this.initVaule);
		f.setDefaultValue(this.defaultValue);
		
		return f;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		FieldMeta other = (FieldMeta) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
			"[ name => \"%s\", type => %s, genericType => %s, visibility => %s ]",
			this.name, this.type, this.genericType, this.visibility
		);
	}
}