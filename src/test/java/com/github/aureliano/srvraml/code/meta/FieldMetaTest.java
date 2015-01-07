package com.github.aureliano.srvraml.code.meta;

import java.util.HashMap;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FieldMetaTest {

	@Test
	public void testParse() {
		assertNull(FieldMeta.parse(null));
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "test");
		map.put("type", "string");
				
		FieldMeta f = FieldMeta.parse(map);
		assertEquals("test", f.getName());
		assertEquals(String.class, f.getType());
		assertEquals(Visibility.PRIVATE, f.getVisibility());
		
		map.put("visibility", "protected");
		f = FieldMeta.parse(map);
		assertEquals(Visibility.PROTECTED, f.getVisibility());
	}
}