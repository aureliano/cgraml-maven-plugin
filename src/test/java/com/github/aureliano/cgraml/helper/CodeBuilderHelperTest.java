package com.github.aureliano.cgraml.helper;

import java.util.List;

import org.junit.Test;

import com.github.aureliano.cgraml.code.meta.FieldMeta;
import com.github.aureliano.cgraml.code.meta.MethodMeta;
import com.github.aureliano.cgraml.code.meta.Visibility;
import com.github.aureliano.cgraml.helper.CodeBuilderHelper;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CodeBuilderHelperTest {

	@Test
	public void testGetJavaType() {
		assertNull(CodeBuilderHelper.getJavaType(null));
		assertNull(CodeBuilderHelper.getJavaType(""));
		
		assertEquals("Test", CodeBuilderHelper.getJavaType("test"));
		
		assertEquals(String.class.getName(), CodeBuilderHelper.getJavaType("string"));
		assertEquals(String.class.getName(), CodeBuilderHelper.getJavaType("String"));
		
		assertEquals(Double.class.getName(), CodeBuilderHelper.getJavaType("number"));
		assertEquals(Double.class.getName(), CodeBuilderHelper.getJavaType("Number"));
		
		assertEquals(Integer.class.getName(), CodeBuilderHelper.getJavaType("integer"));
		assertEquals(Integer.class.getName(), CodeBuilderHelper.getJavaType("Integer"));
		
		assertEquals(Boolean.class.getName(), CodeBuilderHelper.getJavaType("boolean"));
		assertEquals(Boolean.class.getName(), CodeBuilderHelper.getJavaType("Boolean"));
		
		assertEquals(Object.class.getName(), CodeBuilderHelper.getJavaType("value"));
		assertEquals(Object.class.getName(), CodeBuilderHelper.getJavaType("Value"));
		
		assertEquals(List.class.getName(), CodeBuilderHelper.getJavaType("array"));
		assertEquals(List.class.getName(), CodeBuilderHelper.getJavaType("Array"));
		
		assertEquals(Object.class.getName(), CodeBuilderHelper.getJavaType("object"));
		assertEquals(Object.class.getName(), CodeBuilderHelper.getJavaType("Object"));
	}
	
	@Test
	public void testCreateSetterMethod() {
		assertNull(CodeBuilderHelper.createSetterMethod(null));
		
		FieldMeta f = new FieldMeta();
		f.setName("special");
		f.setType(Boolean.class.getName());
		f.setVisibility(Visibility.PRIVATE);
		
		MethodMeta m = CodeBuilderHelper.createSetterMethod(f);
		assertEquals("setSpecial", m.getName());
		assertNull(m.getReturnType());
		assertEquals(Visibility.PUBLIC, m.getVisibility());
		assertFalse(m.getParameters().isEmpty());
		assertEquals(f.getName(), m.getParameters().get(0).getName());
		assertEquals("this.special = special;", m.getBody());
	}
	
	@Test
	public void testCreateGetterMethod() {
		assertNull(CodeBuilderHelper.createGetterMethod(null));
		
		FieldMeta f = new FieldMeta();
		f.setName("special");
		f.setType(List.class.getName());
		f.setGenericType(Boolean.class.getName());
		f.setVisibility(Visibility.PRIVATE);
		
		MethodMeta m = CodeBuilderHelper.createGetterMethod(f);
		assertEquals("getSpecial", m.getName());
		assertEquals(List.class.getName(), m.getReturnType());
		assertEquals(Boolean.class.getName(), m.getGenericReturnType());
		assertEquals(Visibility.PUBLIC, m.getVisibility());
		assertTrue(m.getParameters().isEmpty());
		assertEquals("return this.special;", m.getBody());
	}
	
	@Test
	public void testSanitizedTypeName() {
		assertNull(CodeBuilderHelper.sanitizedTypeName(null));
		assertNull(CodeBuilderHelper.sanitizedTypeName(""));
		
		assertEquals("ResourceName", CodeBuilderHelper.sanitizedTypeName("resourceName"));
		assertEquals("ResourceName", CodeBuilderHelper.sanitizedTypeName("/rootPath/resourceName"));
		assertEquals("ResourceId", CodeBuilderHelper.sanitizedTypeName("/rootPath/resourceName/{resourceId}"));
	}
}