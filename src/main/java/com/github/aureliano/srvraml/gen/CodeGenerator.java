package com.github.aureliano.srvraml.gen;

import org.apache.maven.plugin.logging.SystemStreamLog;

public class CodeGenerator extends AbstractCodeGenerator {

	public CodeGenerator() {
		super();
	}
	
	public void execute() {
		super.logger.debug("Configure code generator.");
		this.configure();
		
		super.logger.debug("Execute code generation.");
		this.getCodeGenerator(GeneratorType.WEB_XML).execute();
	}
	
	private ICodeGenerator getCodeGenerator(GeneratorType generatorType) {
		return GeneratorFactory
				.createGenerator(generatorType)
				.withLogger(this.logger)
				.withRaml(this.raml)
				.withBasePackageName(this.basePackageName);
	}
	
	private void configure() {
		if (super.logger == null) {
			super.logger = new SystemStreamLog();
		}
		
		if (super.basePackageName == null) {
			super.basePackageName = "";
		}
	}

	@Override
	public String outputPath() {
		return null;
	}
}