package com.github.aureliano.srvraml.code.gen;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.raml.model.Raml;

import com.github.aureliano.srvraml.helper.RamlHelper;
import com.github.aureliano.srvraml.helper.ValidationHelper;

public class Generator {

	private Configuration configuration;
	public static Map<?, ?> currentRamlMap;
	
	public Generator() {
		super();
	}
	
	public void run() {
		File[] ramlFiles = this.getRamlFiles();
		if (ramlFiles.length == 0) {
			throw new RuntimeException("There is no RAML file in " + this.configuration.getSourceDirectory().getPath());
		}
		
		Log logger = this.configuration.getLogger();
		for (File ramlFile : ramlFiles) {
			logger.info("------------------------------------------------------------------------");
			logger.info("Validating and Parsing RAML file " + ramlFile.getPath());
			
			ValidationHelper.validateRamlFile(ramlFile);
			Raml raml = RamlHelper.parseModel(ramlFile);
			currentRamlMap = RamlHelper.parseYaml(ramlFile);
			
			for (GeneratorType type : GeneratorType.values()) {
				logger.info("Generate code for " + type.name() + " layer.");
				this.buildCodeGenerator(type, raml).execute();
			}
		}
	}
	
	public Generator withConfiguration(Configuration configuration) {
		this.configuration = configuration;
		return this;
	}
	
	public Configuration getConfiguration() {
		return this.configuration;
	}
	
	private File[] getRamlFiles() {
		return this.configuration.getSourceDirectory().listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (name.endsWith(".raml"));
			}
		});
	}
	
	private ICodeGenerator buildCodeGenerator(GeneratorType type, Raml raml) {
		return GeneratorFactory
			.createGenerator(type)
				.withRaml(raml)
				.withLogger(this.configuration.getLogger())
				.withBasePackageName(this.configuration.getBasePackageName())
				.withGeneratedSourcesTarget(this.configuration.getGeneratedSourcesTarget());
	}
}