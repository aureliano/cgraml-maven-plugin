package com.github.aureliano.srvraml.gen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.junit.Test;
import org.raml.model.Raml;

public class WebXmlGeneratorTest {

	@Test
	public void testWithRaml() {
		Raml raml = this.createRaml();
		
		WebXmlGenerator g = (WebXmlGenerator) new WebXmlGenerator().withRaml(raml);
		
		assertNotNull(g.getRaml());
		assertEquals("http://www.test.com", g.getRaml().getBaseUri());
		assertEquals("My unit test", g.getRaml().getTitle());
	}
	
	@Test
	public void testWithLogger() {
		WebXmlGenerator g = (WebXmlGenerator) new WebXmlGenerator().withLogger(new SystemStreamLog());
		
		assertNotNull(g.getLogger());
	}
	
	@Test
	public void testWithResourcesPackage() {
		WebXmlGenerator g = (WebXmlGenerator) new WebXmlGenerator().withResourcesPackage("com.github.aureliano");
		
		assertNotNull(g.getResourcesPackage());
		assertEquals("com.github.aureliano", g.getResourcesPackage());
	}
	
	@Test
	public void testExecute() throws IOException {
		WebXmlGenerator g = (WebXmlGenerator) new WebXmlGenerator()
			.withLogger(new SystemStreamLog())
			.withRaml(this.createRaml())
			.withResourcesPackage("com.github.aureliano")
			.withGeneratedSourcesDirectory("target/gen");
		
		g.execute();
		
		String xml = FileUtils.readFileToString(g.outputFile());
		assertTrue(xml.contains("<servlet>\n        <servlet-name>" + g.getRaml().getTitle() + "</servlet-name>"));
		assertTrue(xml.contains("<servlet-mapping>\n        <servlet-name>" + g.getRaml().getTitle() + "</servlet-name>"));
		
		assertTrue(xml.contains(
				"<param-name>jersey.config.server.provider.packages</param-name>\n            <param-value>" +
						g.getResourcesPackage() + "</param-value>"));
		
		assertTrue(xml.contains("<url-pattern>" + g.getRaml().getBaseUri() + "</url-pattern>"));
	}
	
	private Raml createRaml() {
		Raml raml = new Raml();
		raml.setBaseUri("http://www.test.com");
		raml.setTitle("My unit test");
		
		return raml;
	}
}