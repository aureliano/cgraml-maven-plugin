package com.github.aureliano.srvraml.code.meta;

import java.util.Map;

import com.github.aureliano.srvraml.helper.CodeBuilderHelper;

public class FieldMeta {

	private String name;
	private Class<?> type;
	private Visibility visibility;
	
	public FieldMeta() {
		super();
	}
	
	public static FieldMeta parse(Map<String, String> data) {
		if (data == null) {
			return null;
		}
		
		FieldMeta f = new FieldMeta();
		
		f.setName(data.get("name"));
		f.setType(CodeBuilderHelper.getJavaType(data.get("type")));
		
		if (data.get("visibility") == null) {
			f.setVisibility(Visibility.PRIVATE);
		} else {
			f.setVisibility(Visibility.valueOf(data.get("visibility").toUpperCase()));
		}
				
		return f;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	
	@Override
	public String toString() {
		return String.format("[ name => \"%s\", type => %s, visibility => %s ]", this.name, this.type, this.visibility);
	}
}