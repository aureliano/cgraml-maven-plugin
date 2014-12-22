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
			Assert.assertEquals("A Raml file path is required for execution.", ex.getMessage());
			throw ex;
		}
 	}
		
	@Test(expected = FileNotFoundException.class)
	public void testRamlFileDoesNotExist() throws Exception {
		try {
			ValidationHelper.validateRamlFilePath(new File("fileNotFound"));
		} catch (Exception ex) {
			Assert.assertEquals("Raml file fileNotFound does not exist.", ex.getMessage());
			throw ex;
		}
	}
	
	@Test(expected = RuntimeException.class)
	public void testRamlFileIsDirectory() throws Exception {
		try {
			ValidationHelper.validateRamlFilePath(new File("src/test/resources"));
		} catch (Exception ex) {
			Assert.assertEquals("src/test/resources is a directory.", ex.getMessage());
			throw ex;
		}
	}
	
	@Test(expected = RuntimeException.class)
	public void testRamlFileWrongSuffix() throws Exception {
		try {
			ValidationHelper.validateRamlFilePath(new File("src/test/resources/modelFile"));
		} catch (Exception ex) {
			Assert.assertEquals("Raml file must have suffix '.raml'. Found => .../modelFile", ex.getMessage());
			throw ex;
		}
	}
	
	@Test
	public void testValidateRamlFilePath() throws Exception {
		ValidationHelper.validateRamlFilePath(new File("src/test/resources/modelFile.raml"));
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
		ValidationHelper.validateResourcesPackage(null);
	}
	
	@Test(expected = RuntimeException.class)
	public void testValidateResourcesPackageWithEmptyParam() {
		ValidationHelper.validateResourcesPackage("");
	}
	
	@Test
	public void testValidateResourcesPackage() {
		ValidationHelper.validateResourcesPackage("com.github.aureliano");
	}
}