package com.github.aureliano.srvraml.helper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.raml.model.Raml;

public class RamlHelperTest {

	@Test
	public void testParseModel() {
		Raml raml = RamlHelper.parseModel("src/test/resources/raml_definition.raml");
		
		assertEquals("World Music API", raml.getTitle());
		assertEquals("http://example.api.com/v1", raml.getBaseUri());
		assertEquals("v1", raml.getVersion());
		
		String[] expectedResources = new String[] { "/songs" };
		Object[] resources = raml.getResources().keySet().toArray();
		Arrays.sort(resources);
		
		for (short i = 0; i < expectedResources.length; i++) {
			assertEquals(expectedResources[i], resources[i]);
		}
	}
}