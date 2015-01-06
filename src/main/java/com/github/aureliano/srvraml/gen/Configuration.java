package com.github.aureliano.srvraml.gen;

import java.io.File;

import org.apache.maven.plugin.logging.Log;

public class Configuration {

	private String basePackageName;
	private File sourceDirectory;
	private File generatedSourcesTarget;
	private Log logger;
	
	public Configuration() {
		super();
	}

	public String getBasePackageName() {
		return basePackageName;
	}

	public Configuration withBasePackageName(String basePackageName) {
		this.basePackageName = basePackageName;
		return this;
	}

	public File getSourceDirectory() {
		return sourceDirectory;
	}

	public Configuration withSourceDirectory(File sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
		return this;
	}
	
	public File getGeneratedSourcesTarget() {
		return this.generatedSourcesTarget;
	}
	
	public Configuration withGeneratedSourcesTarget(File generatedSourcesTarget) {
		this.generatedSourcesTarget = generatedSourcesTarget;
		return this;
	}
	
	public Log getLogger() {
		return this.logger;
	}
	
	public Configuration withLogger(Log logger) {
		this.logger = logger;
		return this;
	}
}