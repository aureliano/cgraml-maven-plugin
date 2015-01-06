package com.github.aureliano.srvraml.gen;

import org.apache.maven.plugin.logging.Log;
import org.raml.model.Raml;

public interface IGenerator {

	public abstract void execute();
	
	public abstract IGenerator withRaml(Raml raml);
	
	public abstract IGenerator withLogger(Log logger);
	
	public abstract IGenerator withBasePackageName(String basePackageName);
	
	public abstract IGenerator withSourceDirectory(String sourceDirectory);
	
	public abstract Raml getRaml();
	
	public abstract Log getLogger();
	
	public abstract String getBasePackageName();
	
	public abstract String getSourceDirectory();
	
	public abstract String outputPath();
	
	public static final String DEFAULT_GEN_DIRECTORY = "srvraml-maven-plugin";
}