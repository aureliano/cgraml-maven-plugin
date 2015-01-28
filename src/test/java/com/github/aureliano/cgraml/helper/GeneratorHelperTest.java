package com.github.aureliano.cgraml.helper;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.aureliano.cgraml.code.gen.Generator;

public class GeneratorHelperTest {

	@Before
	public void beforeTest() {
		Generator.currentRamlMap = RamlHelper.parseYaml("src/test/resources/raml.yaml");
	}
	
	@Test
	public void testGetDataFromCurrentRamlHelper() {
		Assert.assertEquals("v1", GeneratorHelper.getDataFromCurrentRamlHelper(Arrays.asList("version")));
		Assert.assertEquals(Arrays.asList("secured"), GeneratorHelper.getDataFromCurrentRamlHelper(Arrays.asList("/products", "/{productId}", "is")));
	}
}