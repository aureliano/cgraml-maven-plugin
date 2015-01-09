package com.github.aureliano.srvraml.code.builder;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface IBuilder {
	
	public abstract <T extends IBuilder> T parse(String pkg, String entity, Object resource);

	public abstract <T extends IBuilder> T build();
	
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}