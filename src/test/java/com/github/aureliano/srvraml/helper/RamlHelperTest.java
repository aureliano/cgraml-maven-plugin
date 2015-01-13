package com.github.aureliano.srvraml.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.raml.model.Raml;

import com.github.aureliano.srvraml.code.gen.Generator;
import com.github.aureliano.srvraml.code.meta.FieldMeta;
import com.github.aureliano.srvraml.code.meta.ServiceMeta;

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
	public void testParseYaml() {
		Map<?, ?> raml = RamlHelper.parseYaml("src/test/resources/raml.yaml");
		
		assertEquals("Muse: Mule Sales Enablement API", raml.get("title"));
		assertEquals("http://example.api.com/v1", raml.get("baseUri"));
		assertEquals("v1", raml.get("version"));
	}
	
	@Test
	public void testResourceToService() {
		Generator.currentRamlMap = RamlHelper.parseYaml("src/test/resources/raml.yaml");
		Raml raml = RamlHelper.parseModel("src/test/resources/raml.yaml");
		ServiceMeta service = RamlHelper.resourceToService(raml.getResource("/products"));
		
		assertNotNull(service);
		
		assertEquals("/products", service.getUri());
		assertEquals("Products", service.getType());
		assertEquals("Product", service.getGenericType());
		
		assertEquals(2, service.getActions().size());
		
		List<FieldMeta> params = service.getActions().get(0).getParameters();
		assertEquals(3, params.size());
		assertEquals("region", params.get(0).getName());
		assertEquals("java.lang.String", params.get(0).getType());
	}
	
	@Test
	public void  testGetResourceTypeMapping() {
		Generator.currentRamlMap = RamlHelper.parseYaml("src/test/resources/raml.yaml");
		Raml raml = RamlHelper.parseModel("src/test/resources/raml.yaml");
		
		Map<String, Map<String, ?>> map = (Map<String, Map<String, ?>>) RamlHelper.getResourceMapping(raml.getResource("/products").getResource("/{productId}"));
		assertEquals("typedMember", map.get("type").keySet().iterator().next());
	}
}