package com.github.aureliano.srvraml.gen;

import org.apache.maven.plugin.logging.SystemStreamLog;

public class CodeGenerator extends AbstractGenerator {

	public CodeGenerator() {
		super();
	}
	
	public void execute() {
		super.logger.debug("Configure code generator.");
		this.configure();
		
		super.logger.debug("Execute code generation.");
		this.getCodeGenerator(GeneratorType.WEB_XML).execute();
	}
	
	private IGenerator getCodeGenerator(GeneratorType generatorType) {
		return GeneratorFactory
				.createGenerator(generatorType)
				.withLogger(this.logger)
				.withRaml(this.raml)
				.withResourcesPackage(this.resourcesPackage);
	}
	
	private void configure() {
		if (super.logger == null) {
			super.logger = new SystemStreamLog();
		}
		
		if (super.resourcesPackage == null) {
			super.resourcesPackage = "";
		}
	}

	@Override
	public String outputPath() {
		return null;
	}
}