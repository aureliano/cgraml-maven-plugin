package com.github.aureliano.srvraml.helper;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;
import org.raml.model.Raml;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;

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
	
	@Test
	public void testTest() {
		JCodeModel cm = new JCodeModel();
		try {
			JDefinedClass dc = cm._class("com.monstros.sa.jgen.NomeClasse");
			dc.field(4, String.class, "malabibala");
			cm.build(new File("/home/06392384677/Desktop"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}