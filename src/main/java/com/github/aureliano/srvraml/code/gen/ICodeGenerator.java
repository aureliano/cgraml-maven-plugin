package com.github.aureliano.srvraml.code.gen;

import java.io.File;

import org.apache.maven.plugin.logging.Log;
import org.raml.model.Raml;

public interface ICodeGenerator {

	public abstract void execute();
	
	public abstract ICodeGenerator withRaml(Raml raml);
	
	public abstract ICodeGenerator withLogger(Log logger);
	
	public abstract ICodeGenerator withBasePackageName(String basePackageName);
	
	public abstract ICodeGenerator withGeneratedSourcesTarget(File generatedSourcesTarget);
	
	public abstract Raml getRaml();
	
	public abstract Log getLogger();
	
	public abstract String getBasePackageName();
	
	public abstract File getGeneratedSourcesTarget();
	
	public abstract String outputPath();
	
	public static final File DEFAULT_GEN_DIRECTORY = new File("srvraml-maven-plugin");
}