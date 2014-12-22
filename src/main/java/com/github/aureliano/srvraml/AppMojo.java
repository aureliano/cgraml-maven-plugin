package com.github.aureliano.srvraml;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.github.aureliano.srvraml.gen.CodeGenerator;
import com.github.aureliano.srvraml.gen.IGenerator;
import com.github.aureliano.srvraml.helper.RamlHelper;
import com.github.aureliano.srvraml.helper.ValidationHelper;

/**
 * Maven plugin for generating Java Web RESTful sources based on RAML protocol.
 * 
 * @author Aureliano
 * @goal gen 
 */
public class AppMojo extends AbstractMojo {

	/**
	 * @parameter expression="${ramlFile}"
	 */
	private File ramlFile;
	
	/**
	 * @parameter expression="${resourcesPackage}"
	 */
	private String resourcesPackage;
	
	/**
	 * @parameter expression="${generatedSourcesDirectory}"
	 */
	private String generatedSourcesDirectory;
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			ValidationHelper.validateRamlFilePath(this.ramlFile);
			ValidationHelper.validateRamlFile(this.ramlFile);
			ValidationHelper.validateResourcesPackage(this.resourcesPackage);
		} catch (Exception ex) {
			throw new MojoExecutionException(ex.getMessage());
		}
		
		this.printExecutionInformation();
		this.buildCodeGenerator().execute();
	}
	
	private IGenerator buildCodeGenerator() {
		return new CodeGenerator()
			.withRaml(RamlHelper.parseModel(this.ramlFile))
			.withLogger(super.getLog())
			.withResourcesPackage(this.resourcesPackage)
			.withGeneratedSourcesDirectory(this.generatedSourcesDirectory);
	}
	
	private void printExecutionInformation() {
		super.getLog().info("RAML resource location: " + this.ramlFile.getPath());
		super.getLog().info("Project resources package: " + this.resourcesPackage);
		super.getLog().info("Sources directory target: " + this.generatedSourcesDirectory + " (" + IGenerator.DEFAULT_GEN_DIRECTORY + ") if null");
	}
}