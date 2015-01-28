package com.github.aureliano.cgraml.code.builder;

import com.github.aureliano.cgraml.code.gen.GeneratorType;
import com.github.aureliano.cgraml.code.gen.ServiceParametersBuilder;

public final class CodeBuilder {

	private CodeBuilder() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IBuilder> T create(GeneratorType type) {
		if (type == null) {
			return null;
		}
		
		switch (type) {
			case MODEL : return (T) new ModelBuilder();
			case SERVICE : return (T) new ServiceBuilder();
			case SERVICE_PARAMETERS : return (T) new ServiceParametersBuilder();
			case API_MAP_SERVICES : return (T) new ApiMapServicesBuilder();
			default : return null;
		}
	}
}