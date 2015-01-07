package com.github.aureliano.srvraml.helper;

import java.util.List;

import org.junit.Test;

import com.github.aureliano.srvraml.code.meta.FieldMeta;
import com.github.aureliano.srvraml.code.meta.MethodMeta;
import com.github.aureliano.srvraml.code.meta.Visibility;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CodeBuilderHelperTest {

	@Test
	public void testGetJavaType() {
		assertNull(CodeBuilderHelper.getJavaType(null));
		assertNull(CodeBuilderHelper.getJavaType(""));
		assertNull(CodeBuilderHelper.getJavaType("test"));
		
		assertEquals(String.class, CodeBuilderHelper.getJavaType("string"));
		assertEquals(String.class, CodeBuilderHelper.getJavaType("String"));
		
		assertEquals(Double.class, CodeBuilderHelper.getJavaType("number"));
		assertEquals(Double.class, CodeBuilderHelper.getJavaType("Number"));
		
		assertEquals(Integer.class, CodeBuilderHelper.getJavaType("integer"));
		assertEquals(Integer.class, CodeBuilderHelper.getJavaType("Integer"));
		
		assertEquals(Boolean.class, CodeBuilderHelper.getJavaType("boolean"));
		assertEquals(Boolean.class, CodeBuilderHelper.getJavaType("Boolean"));
		
		assertEquals(Object.class, CodeBuilderHelper.getJavaType("value"));
		assertEquals(Object.class, CodeBuilderHelper.getJavaType("Value"));
		
		assertEquals(Object[].class, CodeBuilderHelper.getJavaType("array"));
		assertEquals(Object[].class, CodeBuilderHelper.getJavaType("Array"));
		
		assertEquals(List.class, CodeBuilderHelper.getJavaType("object"));
		assertEquals(List.class, CodeBuilderHelper.getJavaType("Object"));
	}
	
	@Test
	public void testCreateWritterMethod() {
		assertNull(CodeBuilderHelper.createSetterMethod(null));
		
		FieldMeta f = new FieldMeta();
		f.setName("special");
		f.setType(Boolean.class);
		f.setVisibility(Visibility.PRIVATE);
		
		MethodMeta m = CodeBuilderHelper.createSetterMethod(f);
		assertEquals("setSpecial", m.getName());
		assertNull(m.getReturnType());
		assertFalse(m.getParameters().isEmpty());
		assertEquals(f.getName(), m.getParameters().get(0).getName());
	}
	
	@Test
	public void testCreateGetterMethod() {
		assertNull(CodeBuilderHelper.createGetterMethod(null));
		
		FieldMeta f = new FieldMeta();
		f.setName("special");
		f.setType(Boolean.class);
		f.setVisibility(Visibility.PRIVATE);
		
		MethodMeta m = CodeBuilderHelper.createGetterMethod(f);
		assertEquals("getSpecial", m.getName());
		assertEquals(Boolean.class, m.getReturnType());
		assertTrue(m.getParameters().isEmpty());
	}
}