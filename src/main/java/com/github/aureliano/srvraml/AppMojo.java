package com.github.aureliano.srvraml;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.github.aureliano.srvraml.gen.Configuration;
import com.github.aureliano.srvraml.gen.Generator;
import com.github.aureliano.srvraml.gen.ICodeGenerator;
import com.github.aureliano.srvraml.helper.ValidationHelper;

/**
 * Maven plugin for generating Java Client RESTful sources based on RAML protocol.
 * 
 * @author Aureliano
 * @goal generate
 */
public class AppMojo extends AbstractMojo {

	/**
	 * @parameter expression="${sourceDirectory}"
	 */
	private File sourceDirectory;
	
	/**
	 * @parameter expression="${basePackageName}"
	 */
	private String basePackageName;
	
	/**
	 * @parameter expression="${removeOldOutput}"
	 */
	private boolean removeOldOutput;
	
	/**
	 * @parameter skip="${skip}"
	 */
    private boolean skip;
		
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		if (this.skip) {
            super.getLog().info("Skipping execution...");
            return;
        }		
		
		this.validateExecution();
		try {
			this.executeMojo();
		} catch (Exception ex) {
			throw new MojoExecutionException(ex.getMessage(), ex);
		}
	}
	
	private void executeMojo() throws IOException {
		this.printExecutionInformation();
		
		if (this.removeOldOutput) {
			this.cleanGeneratedCode();
		}
		
		this.createGenerator().run();
	}
	
	private void printExecutionInformation() {
		super.getLog().info("RAML resource location: " + this.sourceDirectory.getPath());
		super.getLog().info("Base package name: " + this.basePackageName);
		super.getLog().info("Sources directory target: " + this.sourcesTargetDirectory() + " (" + ICodeGenerator.DEFAULT_GEN_DIRECTORY + ") if null");
		super.getLog().info("Remove old output? " + this.removeOldOutput);
	}
	
	private void cleanGeneratedCode() throws IOException {
		String generatedSourcesDir = this.sourcesTargetDirectory() + File.separator + "gen";
		super.getLog().info("Deleting generated sources directory " + generatedSourcesDir);
		
		FileUtils.deleteDirectory(new File(generatedSourcesDir));
	}

	private void validateExecution() throws MojoExecutionException {
		try {
			ValidationHelper.validateRamlFilePath(this.sourceDirectory);
			ValidationHelper.validateBasePackageName(this.basePackageName);
		} catch (Exception ex) {
			throw new MojoExecutionException(ex.getMessage());
		}
	}
	
	private Generator createGenerator() {
		return new Generator()
			.withConfiguration(
				new Configuration()
				.withBasePackageName(this.basePackageName)
				.withSourceDirectory(this.sourceDirectory)
				.withGeneratedSourcesTarget(new File(this.sourcesTargetDirectory()))
				.withLogger(super.getLog())
			);
	}
	
	private String sourcesTargetDirectory() {
		String[] baseDir = new String[] { "src", "main", "java" };
		String[] dir = this.basePackageName.split(Pattern.quote("."));

		return StringUtils.join(ArrayUtils.addAll(baseDir, dir), File.separator);
	}
}