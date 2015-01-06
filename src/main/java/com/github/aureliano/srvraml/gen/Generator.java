package com.github.aureliano.srvraml.gen;

import java.io.File;
import java.io.FilenameFilter;

import com.github.aureliano.srvraml.helper.RamlHelper;

public class Generator {

	private Configuration configuration;
	
	public Generator() {
		super();
	}
	
	public void run() {
		File[] ramlFiles = this.getRamlFiles();
		if (ramlFiles.length == 0) {
			throw new RuntimeException("There is no RAML file in " + this.configuration.getSourceDirectory().getPath());
		}
		
		for (File ramlFile : ramlFiles) {
			this.configuration.getLogger().info("Generate client code over " + ramlFile.getPath());
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
	
	/*private ICodeGenerator buildCodeGenerator() {
		return new CodeGenerator()
		.withRaml(RamlHelper.parseModel(this.sourceDirectory))
		.withLogger(super.getLog())
		.withBasePackageName(this.basePackageName);
}*/
}