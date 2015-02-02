package com.github.aureliano.cgraml.code.gen;

public final class GeneratorFactory {

	public static ICodeGenerator createGenerator(GeneratorType type) {
		if (type == null) {
			return null;
		}
		
		switch (type) {
			case MODEL : return new ModelGenerator();
			case SERVICE : return new ServiceGenerator();
			case SERVICE_PARAMETERS : return new ServiceParametersGenerator();
			case API_MAP_SERVICES : return new ApiMapServicesGenerator();
			case MODEL_SCHEMA_INTERFACE : return new ModelSchemaInterfaceGenerator();
			case MODEL_COLLECTION_SCHEMA_INTERFACE : return new ModelCollectionSchemaInterfaceGenerator();
			case SERVICE_FETCH_INTERFACE : return new ServiceFetchInterfaceGenerator();
			case SERVICE_PARAMETERS_INTERFACE : return new ServiceParametersInterfaceGenerator();
			default : return null;
		}
	}
}