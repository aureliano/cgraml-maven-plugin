package com.github.aureliano.srvraml.gen;

import org.apache.maven.plugin.logging.Log;
import org.raml.model.Raml;

public interface ICodeGenerator {

	public abstract void execute();
	
	public abstract ICodeGenerator withRaml(Raml raml);
	
	public abstract ICodeGenerator withLogger(Log logger);
	
	public abstract ICodeGenerator withBasePackageName(String basePackageName);
	
	public abstract ICodeGenerator withSourceDirectory(String sourceDirectory);
	
	public abstract Raml getRaml();
	
	public abstract Log getLogger();
	
	public abstract String getBasePackageName();
	
	public abstract String getSourceDirectory();
	
	public abstract String outputPath();
	
	public static final String DEFAULT_GEN_DIRECTORY = "srvraml-maven-plugin";
}