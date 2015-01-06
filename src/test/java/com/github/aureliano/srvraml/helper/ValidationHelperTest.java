package com.github.aureliano.srvraml.helper;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class ValidationHelperTest {

	@Test(expected = FileNotFoundException.class)
	public void testRamlFileIsRequired() throws Exception {
		try {
			ValidationHelper.validateRamlFilePath(null);
		} catch (Exception ex) {
			Assert.assertEquals("A source path is required for execution.", ex.getMessage());
			throw ex;
		}
 	}
		
	@Test(expected = FileNotFoundException.class)
	public void testRamlFileDoesNotExist() throws Exception {
		try {
			ValidationHelper.validateRamlFilePath(new File("fileNotFound"));
		} catch (Exception ex) {
			Assert.assertEquals("Source directory fileNotFound does not exist.", ex.getMessage());
			throw ex;
		}
	}
	
	@Test(expected = RuntimeException.class)
	public void testRamlFileIsNotDirectory() throws Exception {
		try {
			ValidationHelper.validateRamlFilePath(new File("src/test/resources/raml_definition.raml"));
		} catch (Exception ex) {
			Assert.assertEquals("src/test/resources/raml_definition.raml is not a directory.", ex.getMessage());
			throw ex;
		}
	}
	
	@Test
	public void testValidateRamlFilePath() throws Exception {
		ValidationHelper.validateRamlFilePath(new File("src/test/resources"));
	}
	
	@Test(expected = RuntimeException.class)
	public void testValidateRamlFileWithError() {
		ValidationHelper.validateRamlFile(new File("src/test/resources/modelFile.raml"));
	}
	
	@Test
	public void testValidateRamlFile() {
		ValidationHelper.validateRamlFile(new File("src/test/resources/raml_definition.raml"));
	}
	
	@Test(expected = RuntimeException.class)
	public void testValidateResourcesPackageWithNullParam() {
		ValidationHelper.validateBasePackageName(null);
	}
	
	@Test(expected = RuntimeException.class)
	public void testValidateBasePackageNameWithEmptyParam() {
		ValidationHelper.validateBasePackageName("");
	}
	
	@Test
	public void testValidateBasePackageName() {
		ValidationHelper.validateBasePackageName("com.github.aureliano");
	}
}