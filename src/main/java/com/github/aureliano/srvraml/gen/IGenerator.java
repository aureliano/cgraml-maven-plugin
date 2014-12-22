package com.github.aureliano.srvraml.gen;

import org.apache.maven.plugin.logging.Log;
import org.raml.model.Raml;

public interface IGenerator {

	public abstract void execute();
	
	public abstract IGenerator withRaml(Raml raml);
	
	public abstract IGenerator withLogger(Log logger);
	
	public abstract IGenerator withResourcesPackage(String resourcesPackage);
	
	public abstract IGenerator withGeneratedSourcesDirectory(String path);
	
	public abstract Raml getRaml();
	
	public abstract Log getLogger();
	
	public abstract String getResourcesPackage();
	
	public abstract String getGeneratedSourcesDirectory();
	
	public abstract String outputPath();
	
	public static final String DEFAULT_GEN_DIRECTORY = "srvraml-maven-plugin";
}