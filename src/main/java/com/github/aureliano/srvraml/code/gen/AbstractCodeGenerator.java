package com.github.aureliano.srvraml.code.gen;

import java.io.File;

import org.apache.maven.plugin.logging.Log;
import org.raml.model.Raml;

public abstract class AbstractCodeGenerator implements ICodeGenerator {

	protected Raml raml;
	protected Log logger;
	protected File generatedSourcesTarget;
	protected String basePackageName;
	
	public AbstractCodeGenerator() {
		super();
	}
	
	@Override
	public ICodeGenerator withRaml(Raml raml) {
		this.raml = raml;
		return this;
	}

	@Override
	public ICodeGenerator withLogger(Log logger) {
		this.logger = logger;
		return this;
	}
	
	@Override
	public ICodeGenerator withBasePackageName(String basePackageName) {
		this.basePackageName = basePackageName;
		return this;
	}

	@Override
	public Raml getRaml() {
		return this.raml;
	}

	@Override
	public Log getLogger() {
		return this.logger;
	}
	
	@Override
	public String getBasePackageName() {
		return this.basePackageName;
	}

	@Override
	public ICodeGenerator withGeneratedSourcesTarget(File generatedSourcesTarget) {
		this.generatedSourcesTarget = generatedSourcesTarget;
		return this;
	}

	@Override
	public File getGeneratedSourcesTarget() {
		if (this.generatedSourcesTarget == null) {
			this.generatedSourcesTarget = ICodeGenerator.DEFAULT_GEN_DIRECTORY;
		}
		
		return this.generatedSourcesTarget;
	}
}