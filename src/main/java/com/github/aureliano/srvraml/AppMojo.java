package com.github.aureliano.srvraml;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

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
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			ValidationHelper.validateRamlFilePath(this.ramlFile);
		} catch (Exception ex) {
			throw new MojoExecutionException("Invalid Raml file: " + ex.getMessage());
		}
	}
}