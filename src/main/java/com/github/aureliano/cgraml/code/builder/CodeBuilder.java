package com.github.aureliano.cgraml.code.builder;

import com.github.aureliano.cgraml.code.gen.GeneratorType;

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
			case MODEL_SCHEMA_INTERFACE : return (T) new ModelSchemaInterfaceBuilder();
			case MODEL_COLLECTION_SCHEMA_INTERFACE : return (T) new ModelCollectionSchemaInterfaceBuilder();
			case SERVICE_FETCH_INTERFACE : return (T) new ServiceFetchInterfaceBuilder();
			case SERVICE_PARAMETERS_INTERFACE : return (T) new ServiceParametersInterfaceBuilder();
			case EAGER_DATA_LIST : return (T) new EagerDataListBuilder();
			default : return null;
		}
	}
}