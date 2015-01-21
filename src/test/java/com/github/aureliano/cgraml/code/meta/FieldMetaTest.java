package com.github.aureliano.cgraml.code.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.github.aureliano.cgraml.code.meta.FieldMeta;
import com.github.aureliano.cgraml.code.meta.Visibility;

public class FieldMetaTest {

	@Test
	public void testParse() {
		assertNull(FieldMeta.parse(null));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "test");
		map.put("type", "array");
		
		HashMap<String, String> items = new HashMap<String, String>();
		items.put("$ref", "Malabibala");
		map.put("items", items);
				
		FieldMeta f = FieldMeta.parse(map);
		assertEquals("test", f.getName());
		assertEquals(List.class.getName(), f.getType());
		assertEquals("Malabibala", f.getGenericType());
		assertEquals(Visibility.PRIVATE, f.getVisibility());
		
		map.put("visibility", "protected");
		f = FieldMeta.parse(map);
		assertEquals(Visibility.PROTECTED, f.getVisibility());
	}
}