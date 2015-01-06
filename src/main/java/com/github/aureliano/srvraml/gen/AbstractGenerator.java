package com.github.aureliano.srvraml.gen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.logging.Log;
import org.raml.model.Raml;

public abstract class AbstractGenerator implements IGenerator {

	protected Raml raml;
	protected Log logger;
	protected String sourceDirectory;
	protected String basePackageName;
	
	public AbstractGenerator() {
		super();
	}
	
	protected void saveFile(String content) {
		try {
			File genDir = new File(this.getSourceDirectory());
			if (!genDir.exists()) {
				FileUtils.forceMkdir(genDir);
			}
			
			IOUtils.write(content, new FileOutputStream(this.outputFile()));
			this.logger.info("Created file: " + this.outputFile().getPath());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public File outputFile() {
		return new File(this.getSourceDirectory() + File.separator + this.outputPath());
	}
	
	@Override
	public IGenerator withRaml(Raml raml) {
		this.raml = raml;
		return this;
	}

	@Override
	public IGenerator withLogger(Log logger) {
		this.logger = logger;
		return this;
	}
	
	@Override
	public IGenerator withBasePackageName(String basePackageName) {
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
	public IGenerator withSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
		return this;
	}

	@Override
	public String getSourceDirectory() {
		if (StringUtils.isEmpty(this.sourceDirectory)) {
			this.sourceDirectory = IGenerator.DEFAULT_GEN_DIRECTORY;
		}
		
		return this.sourceDirectory;
	}
}